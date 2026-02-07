# Kafka-learning focused

You specifically asked:

✅ Deep LLD
✅ Service responsibilities
✅ Internal class design per service
✅ Event contracts
✅ State model
✅ Interaction flow
✅ Still minimal & clean

No code — but **realistic class naming + package design** (very close to how senior teams design).

---

# 🧠 Smart Office Meeting Intelligence

## Deep LLD Design

---

# 1️⃣ Overall Service Communication Flow

```
Booking Service
     │
     ▼
room-booking-events Topic
     │
     ▼
Policy Engine  ◄──── Occupancy Simulator
     │                     │
     │                     ▼
     │              room-occupancy-events
     │
     ▼
room-policy-events
     │
     ▼
Analytics Service
```

---

# 2️⃣ Common Shared Library Design

👉 VERY IMPORTANT in event-driven systems

---

## 📦 Module: `common-event-library`

---

### Package Structure

```
com.smartoffice.events
    ├── envelope
    ├── booking
    ├── occupancy
    ├── policy
    └── serialization
```

---

### Core Classes

---

## ⭐ Event Envelope

```
EventEnvelope
```

Fields:

```
String eventId
String eventType
String eventVersion
Instant eventTimestamp
String sourceService
Object payload
```

---

## ⭐ Base Event Marker

```
interface DomainEvent
```

---

---

# 3️⃣ Booking Service — Deep LLD

---

## 🎯 Responsibility

Meeting lifecycle management + Kafka publishing.

---

## 📦 Package Structure

```
com.smartoffice.booking
    ├── controller
    ├── service
    ├── producer
    ├── repository
    ├── entity
    ├── mapper
    ├── outbox
    └── config
```

---

---

## 📂 Controller Layer

### Classes

```
MeetingBookingController
```

Responsibilities:

* Create booking
* Cancel booking
* Update booking

---

---

## 📂 Service Layer

### Classes

```
MeetingBookingService
MeetingBookingValidator
MeetingBookingDomainService
```

---

### Responsibilities

#### MeetingBookingService

* Orchestrates use case
* Calls domain logic
* Saves entity
* Triggers outbox event

---

#### MeetingBookingValidator

* Time conflict validation
* Room availability validation

---

#### MeetingBookingDomainService

* Booking lifecycle logic
* Status transitions

---

---

## 📂 Entity Layer

```
MeetingBookingEntity
```

---

---

## 📂 Repository

```
MeetingBookingRepository
```

Spring Data JPA interface.

---

---

## 📂 Mapper

```
MeetingBookingEventMapper
MeetingBookingDtoMapper
```

---

---

## 📂 Kafka Producer Layer

```
BookingEventProducer
BookingEventPublisher
```

---

Responsibilities:

* Wrap payload into EventEnvelope
* Publish to Kafka

---

---

## 📂 Outbox Pattern (VERY IMPORTANT Kafka Learning)

```
OutboxEventEntity
OutboxRepository
OutboxScheduler
OutboxEventPublisher
```

---

👉 Flow:

```
Booking saved
→ Outbox record saved
→ Scheduler publishes event to Kafka
```

---

---

# 4️⃣ Occupancy Simulator Service — Deep LLD

---

## 🎯 Responsibility

Simulates IoT gateway events.

---

## 📦 Package Structure

```
com.smartoffice.occupancy
    ├── controller
    ├── simulator
    ├── producer
    ├── model
    ├── scheduler
    └── config
```

---

---

## 📂 Controller

```
OccupancySimulationController
```

Endpoints:

```
simulateEntry()
simulateExit()
updatePersonCount()
```

---

---

## 📂 Simulator Layer

```
OccupancySimulationService
SensorSignalGenerator
SensorNoiseInjector
```

---

### Responsibilities

#### OccupancySimulationService

* Converts input into sensor event

---

#### SensorSignalGenerator

* Creates realistic occupancy patterns

---

#### SensorNoiseInjector

(optional but cool learning)

* Duplicate signals
* Delayed events
* Wrong counts

---

---

## 📂 Producer

```
OccupancyEventProducer
OccupancyEventPublisher
```

---

---

## 📂 Scheduler

```
RandomOccupancyScheduler
```

Used for auto simulation.

---

---

# 5️⃣ Policy Engine Service — Deep LLD (Most Important)

🔥 This is your **Kafka mastery module**

---

## 🎯 Responsibility

Event correlation + violation detection.

---

## 📦 Package Structure

```
com.smartoffice.policy
    ├── consumer
    ├── processor
    ├── state
    ├── rules
    ├── producer
    ├── repository
    ├── model
    └── config
```

---

---

## 📂 Kafka Consumer Layer

```
BookingEventConsumer
OccupancyEventConsumer
```

---

Responsibilities:

* Deserialize event
* Forward to processor

---

---

## 📂 Event Processor Layer

```
PolicyEventProcessor
RoomStateAggregator
MeetingStateAggregator
```

---

### Responsibilities

#### PolicyEventProcessor

Central orchestrator.

---

#### RoomStateAggregator

Maintains:

```
Current Occupancy
Last Occupancy Timestamp
Person Count
```

---

#### MeetingStateAggregator

Maintains:

```
Active Meeting
Meeting Time Window
```

---

---

## 📂 State Storage

👉 You have two POC choices:

---

### Option A — In Memory + Cache

```
RoomStateStore
MeetingStateStore
```

---

### Option B — PostgreSQL

```
RoomStateRepository
MeetingStateRepository
```

---

---

## 📂 Rule Engine Layer

```
PolicyRuleEngine
GhostMeetingRule
OverstayRule
UnauthorizedUsageRule
```

---

### Pattern Used

👉 Strategy Pattern

---

PolicyRuleEngine internally holds:

```
List<PolicyRule>
```

Each rule implements:

```
interface PolicyRule
    evaluate(RoomState, MeetingState)
```

---

---

## 📂 Violation Producer

```
PolicyEventProducer
PolicyEventPublisher
```

---

---

# 6️⃣ Analytics Service — Deep LLD

---

## 🎯 Responsibility

Aggregation + Reporting Store.

---

## 📦 Package Structure

```
com.smartoffice.analytics
    ├── consumer
    ├── aggregator
    ├── repository
    ├── entity
    ├── projection
    └── scheduler
```

---

---

## 📂 Consumers

```
BookingAnalyticsConsumer
OccupancyAnalyticsConsumer
PolicyAnalyticsConsumer
```

---

---

## 📂 Aggregators

```
RoomUtilizationAggregator
ViolationMetricsAggregator
UsageHeatmapAggregator
```

---

---

## 📂 Entities

```
RoomUtilizationEntity
ViolationMetricsEntity
```

---

---

# 7️⃣ Event Contract Classes

---

## Booking Events

```
RoomBookedEvent
RoomCancelledEvent
RoomUpdatedEvent
```

---

## Occupancy Events

```
RoomOccupiedEvent
RoomVacatedEvent
PersonCountChangedEvent
```

---

## Policy Events

```
GhostMeetingDetectedEvent
OverstayDetectedEvent
UnauthorizedUsageEvent
RoomAutoReleasedEvent
```

---

---

# 8️⃣ Kafka Topic Config LLD

---

## room-booking-events

```
Partitions → 3
Retention → 7 days
Key → roomId
```

---

## room-occupancy-events

```
Partitions → 6
Retention → 2 days
High throughput topic
```

---

## room-policy-events

```
Partitions → 3
Retention → 30 days
```

---

---

# 9️⃣ End-to-End Sequence Example

---

## Scenario → Ghost Meeting

---

### Step 1

Booking Service:

```
MeetingBookingController
 → MeetingBookingService
 → Outbox
 → Kafka publish RoomBookedEvent
```

---

### Step 2

Occupancy Simulator:

```
No occupancy events produced
```

---

### Step 3

Policy Engine:

```
BookingEventConsumer
 → PolicyEventProcessor
 → MeetingStateAggregator
 → GhostMeetingRule triggered
 → PolicyEventProducer
```

---

---

# 🔟 Error Handling Components

Add inside each service:

```
RetryTopicListener
DeadLetterEventHandler
IdempotencyService
```

---

---

# 1️⃣1️⃣ Recommended Design Patterns Used

| Pattern            | Where              |
| ------------------ | ------------------ |
| Outbox Pattern     | Booking Service    |
| Strategy Pattern   | Rule Engine        |
| Aggregator Pattern | Policy Service     |
| Envelope Pattern   | Event Contract     |
| Scheduler Pattern  | Simulator + Outbox |

---

---

# 1️⃣2️⃣ Observability Classes

```
KafkaLagMonitor
EventTracingInterceptor
MetricsPublisher
```

---

---

# ⭐ Optional But Senior Level Addition

Add:

```
CorrelationIdGenerator
EventAuditLogger
```

---

---

# 🧩 Suggested Maven Module Tree

```
smart-office
 ├── common-event-library
 ├── booking-service
 ├── occupancy-simulator-service
 ├── policy-engine-service
 ├── analytics-service
```

---

---

# ❤️ Honest Advice

This design is already **mid-level production architecture**.
If you implement this fully → You will understand Kafka deeper than most developers.

---

---

# 👉 If You Want Next

I can design:

✅ Class level interaction diagram
✅ Database schema full ER diagram
✅ Kafka Streams version of policy engine
✅ Folder structure with exact package naming
✅ Event versioning & schema evolution strategy
✅ Testing strategy (Embedded Kafka + TestContainers)

Just say:

"Next deep dive <topic name>" 😄
