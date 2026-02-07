# POC focused on learning Kafka end-to-end

* Minimal but realistic microservices
* Strong Kafka learning coverage
* Zero real IoT hardware (manual + simulator based)
* Clean extensible structure
* Spring Boot 4 + Java 21 friendly
* Practical failure handling & state modelling

No code — only **LLD + interaction + topic design + storage + simulation strategy**.

---

# 🧠 Smart Office Meeting Intelligence — LLD (POC Version)

---

# 1️⃣ Core Functional Scope (POC)

System tracks:

✅ Room booking
✅ Room occupancy (simulated IoT)
✅ Meeting violations detection
✅ Room utilization analytics

---

# 2️⃣ High Level Architecture

```
[Manual UI / Bruno / Simulator]
            │
            ▼
 ┌────────────────────┐
 │ Booking Service     │
 └────────────────────┘
            │
            ▼
        Kafka Topics
            │
            ▼
 ┌────────────────────┐
 │ Occupancy Service   │  (IoT Simulator)
 └────────────────────┘
            │
            ▼
        Kafka Topics
            │
            ▼
 ┌────────────────────┐
 │ Policy Engine       │
 └────────────────────┘
            │
            ▼
        Kafka Topics
            │
            ▼
 ┌────────────────────┐
 │ Analytics Service   │
 └────────────────────┘
```

---

# 3️⃣ Service Breakdown (LLD)

---

## 🏢 3.1 Booking Service

### 🎯 Responsibility

Handles meeting scheduling.

---

### 📥 Input

Manual booking via:

* REST API
* Bruno testing

---

### 📤 Produces Kafka Events

Topic → `room-booking-events`

Event Types:

```
ROOM_BOOKED
ROOM_CANCELLED
ROOM_UPDATED
```

---

### 🗃 Database Tables

#### meeting_booking

| field        | type      |
| ------------ | --------- |
| booking_id   | UUID      |
| room_id      | String    |
| organizer_id | String    |
| start_time   | Timestamp |
| end_time     | Timestamp |
| status       | ENUM      |
| created_at   | Timestamp |

---

### 🧠 Kafka Learning Here

* Producer reliability
* Transactional producer
* Outbox pattern (recommended)

---

---

## 📡 3.2 Occupancy Sensor Simulator Service

👉 This replaces real IoT hardware.

---

### ❓ Your Question Answer

> "How to collect IoT input without sensor?"

You simulate using:

### Option A — Manual REST Trigger

```
POST /simulate/room-entry
POST /simulate/room-exit
```

---

### Option B — Scheduled Random Event Generator

Spring Scheduler randomly generates occupancy signals.

---

### Option C — CLI / Postman / Bruno

Manually send sensor signals.

---

### Option D — JSON File Playback

Replay recorded sensor events.

---

👉 In real life, IoT devices push MQTT/HTTP → Gateway → Kafka
👉 In POC, your simulator behaves like that gateway.

---

### 📤 Produces Kafka Events

Topic → `room-occupancy-events`

Event Types:

```
ROOM_OCCUPIED
ROOM_VACATED
PERSON_COUNT_CHANGED
```

---

### Event Payload Example Concept

```
room_id
timestamp
person_count
sensor_id
confidence_score
```

---

### 🧠 Kafka Learning Here

* High frequency event streaming
* Partition strategy testing
* Handling noisy IoT events
* Event deduplication

---

---

## ⚖️ 3.3 Policy Enforcement Service

🔥 This is your **core Kafka brain**

---

### 🎯 Responsibility

Consumes:

* booking events
* occupancy events

Correlates them and detects violations.

---

### 📥 Consumes Topics

```
room-booking-events
room-occupancy-events
```

---

### 🧠 State Management Needed

Policy service must maintain:

```
Room Current Occupancy State
Active Meeting State
```

---

👉 You should use:

```
Kafka Streams / State Store
```

OR

```
Local cache + PostgreSQL
```

---

### 📤 Produces Events

Topic → `room-policy-events`

Event Types:

```
GHOST_MEETING_DETECTED
ROOM_AUTO_RELEASED
OVERSTAY_DETECTED
UNAUTHORIZED_USAGE
```

---

### Example Policy Logic

---

#### Ghost Meeting Rule

```
Booking exists
AND
Room empty for X minutes after start
→ Trigger violation
```

---

#### Overstay Rule

```
Meeting ended
AND
Occupancy still detected
```

---

### 🧠 Kafka Learning Here

⭐ Multi-topic event correlation
⭐ Stateful processing
⭐ Window-based evaluation
⭐ Event ordering problems
⭐ Exactly once processing

---

---

## 📊 3.4 Analytics Service

---

### 🎯 Responsibility

Aggregates long-term insights.

---

### 📥 Consumes

```
room-booking-events
room-policy-events
room-occupancy-events
```

---

### 🗃 Storage

PostgreSQL (Analytics tables)

---

### Example Tables

#### room_utilization

| room_id | total_booked_hours | actual_used_hours |

---

#### violation_metrics

| room_id | ghost_count | overstay_count |

---

### 🧠 Kafka Learning Here

* Event replay
* Materialized views
* Consumer scaling
* Consumer lag monitoring

---

---

# 4️⃣ Kafka Topic Design

---

## room-booking-events

Partition Key:

```
room_id
```

Reason:

* All room booking events stay ordered per room

---

## room-occupancy-events

Partition Key:

```
room_id
```

Important for state correlation.

---

## room-policy-events

Partition Key:

```
room_id
```

---

---

# 5️⃣ Event Schema Strategy

Use:

```
Avro or Protobuf
```

Add version field.

---

Example Envelope:

```
event_id
event_type
event_version
event_timestamp
payload
source_service
```

---

---

# 6️⃣ Consumer Group Strategy

| Service        | Consumer Group      |
| -------------- | ------------------- |
| Policy Service | policy-engine-group |
| Analytics      | analytics-group     |

---

---

# 7️⃣ Failure Handling Design

---

## Retry Strategy

```
main-topic
   ↓
retry-topic
   ↓
dlq-topic
```

---

---

## Duplicate Event Protection

Use:

```
event_id + idempotency table
```

---

---

# 8️⃣ Database Usage Philosophy

| Service   | DB Purpose                 |
| --------- | -------------------------- |
| Booking   | Source of truth            |
| Policy    | Optional state persistence |
| Analytics | Reporting store            |

---

---

# 9️⃣ POC IoT Simulation Design (Important)

You can create:

---

## 🧪 Sensor Simulator Module

### Modes

```
Manual Mode
Random Mode
Replay Mode
```

---

### Example Manual Workflow

```
1. Create booking
2. Send occupancy entry event
3. Send occupancy exit event
4. Observe policy output
```

---

👉 This is **100% industry valid POC strategy**

Many real teams simulate IoT exactly like this.

---

---

# 🔟 Maven Multi Module Suggestion

```
smart-office-parent
│
├── booking-service
├── occupancy-simulator
├── policy-engine-service
├── analytics-service
├── common-event-library
```

---

---

# 1️⃣1️⃣ Observability (Highly Recommended)

Include:

* Kafka lag monitoring
* Micrometer metrics
* Structured logging with event_id tracing

---

---

# 1️⃣2️⃣ Learning Milestone Path (0 → 100 Kafka)

---

### Phase 1

Basic producer + consumer

---

### Phase 2

Partitioning & consumer groups

---

### Phase 3

Retry + DLQ

---

### Phase 4

Transactional producer + Outbox

---

### Phase 5

Kafka Streams stateful processing

---

### Phase 6

Schema registry + versioning

---

### Phase 7

Event replay & backfill

---

---

# ❤️ Honest POC Advice

Don't build UI initially.
Use:

```
Bruno
curl
Postman
JSON playback
```

---

---

# ⭐ Small But Powerful Extension Ideas Later

* Auto release unused room
* Slack/Email notification producer
* Real time dashboard via WebSocket
* Historical replay engine

---

---

# If You Want Next Level Help

I can design next:

✅ Detailed Event Contracts
✅ Kafka Topic Configuration (replication, retention, compaction)
✅ Policy Engine State Model
✅ Sequence Flow Diagram
✅ Folder structure per service
✅ Testing strategy for Kafka

Just say **"Next step LLD deep dive"** 😄
