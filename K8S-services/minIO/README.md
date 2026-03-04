## 🧾 MinIO Docker Run Commands – Notes

### 🔹 Version: Without Volume Mount (Temporary Storage)

**Name Suggestion:**
`minio-container-temp-storage`

```
docker run -d --name minio -p 9000:9000 -p 9001:9001 -e "MINIO_ROOT_USER=minioadmin" -e "MINIO_ROOT_PASSWORD=minioadmin"  minio/minio:RELEASE.2025-09-07T16-13-09Z-cpuv1 server /data --console-address ":9001"
```

### 📝 Notes

* Runs MinIO container in detached mode.
* Uses default internal container storage (`/data`).
* Data will be **lost if container is removed**.
* Ports:

    * `9000` → MinIO API / S3 Access
    * `9001` → MinIO Web Console
* Credentials:

    * Username: `minioadmin`
    * Password: `minioadmin`

---

---

### 🔹 Version: With Volume Mount (Persistent Storage)

**Name Suggestion:**
`minio-container-persistent-storage`

```
docker run -d --name minio -p 9000:9000 -p 9001:9001 -e MINIO_ROOT_USER=minioadmin -e MINIO_ROOT_PASSWORD=minioadmin -v /opt/minio/data:/data minio/minio:RELEASE.2025-09-07T16-13-09Z-cpuv1 server /data --console-address ":9001"
```

### 📝 Notes

* Mounts host directory `/opt/minio/data` → container `/data`.
* Data is **persistent** (survives container restart/removal).
* Recommended for production or long-term storage.
* Same ports and credentials as above.

---

## 📌 Naming Convention Recommendation

| Purpose                  | Suggested Container Name |
| ------------------------ | ------------------------ |
| Testing / Temporary      | `minio-temp`             |
| Development              | `minio-dev`              |
| Persistent Local Storage | `minio-storage`          |
| Production               | `minio-prod`             |

---

📛 Create Namespace
```
kubectl create namespace minio-dev
```

🔐 CMD to Create Secret From TXT File
```
kubectl create secret generic minio-secret-dev --from-env-file=minio-secret-dev.txt -n minio-dev
```

🏁 Deployment Order
```
kubectl apply -f namespace.yml        (if using)
kubectl apply -f secret.yml           (if using)
kubectl apply -f pv.yml               ⚠️ NEW: PersistentVolume for existing data
kubectl apply -f pvc.yml
kubectl apply -f deployment.yml
kubectl apply -f service.yml
kubectl apply -f ingress.yml
```

⚠️ **IMPORTANT: Docker to K8s Migration**
If migrating from Docker with existing data, see [MIGRATION_GUIDE.md](MIGRATION_GUIDE.md) for detailed instructions.

🧠 Internal Networking Flow (Now)
```
Browser
   ↓
Ingress (Traefik)
   ↓
Service (ClusterIP)
   ↓
MinIO Pod
   ↓
PVC Storage
   ↓
PersistentVolume (HostPath: /path/ggs/minio/data)
```
✅ Quick Checklist (Run These Now)
```
kubectl get pods -n minio-dev
kubectl get svc -n minio-dev
kubectl get ingress -n minio-dev
kubectl get pods -A | findstr traefik
kubectl get nodes -o wide
kubectl port-forward svc/minio-app-service 9001:9001 -n minio-dev
ping minio.local
http://minio.127.0.0.1.sslip.io
```

# ⭐ When You Change a Kubernetes Secret — What Needs Restart? (Recommended Command)

```
kubectl delete secret minio-secret-dev -n minio-dev

kubectl rollout restart deployment minio-app -n minio-dev
```

This will:

 ✔ Restart pods safely
 ✔ Pull new secret values
 ✔ Zero downtime (if replicas > 1)

---

# 🧠 What Happens Internally

```
Secret Updated
   ↓
Deployment Restart Triggered
   ↓
New Pods Created
   ↓
Pods Load New Secret Values
   ↓
Old Pods Terminated
```

After migrating 🚀 Quick Start:
```
# 1. Stop Docker container
docker stop minio_server && docker rm minio_server

# 2. Create secret
kubectl create secret generic minio-secret-dev \
  --from-literal=MINIO_ROOT_USER=minioadmin \
  --from-literal=MINIO_ROOT_PASSWORD=minioadmin \
  -n minio-dev

# 3. Deploy (in order)
kubectl apply -f pv.yml
kubectl apply -f pvc.yml
kubectl apply -f deployment.yml
kubectl apply -f service.yml
kubectl apply -f ingress.yml

# 4. Verify
kubectl get pods,pvc -n minio-dev
```

# Check Resources in Namespace
```
# Check all resources in the namespace
kubectl get all -n minio-dev

# Check specific resource types
kubectl get pods -n minio-dev
kubectl get deployments -n minio-dev
kubectl get services -n minio-dev
kubectl get statefulsets -n minio-dev
kubectl get daemonsets -n minio-dev
kubectl get jobs -n minio-dev
kubectl get cronjobs -n minio-dev
kubectl get configmaps -n minio-dev
kubectl get secrets -n minio-dev
kubectl get pvc -n minio-dev  # PersistentVolumeClaims
kubectl get ingress -n minio-dev
```