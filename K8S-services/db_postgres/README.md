# PostgreSQL on Kubernetes (Rancher Desktop / K3s)

Namespace: **db-dev**

---

## 📁 File Structure

| File              | Resource   | Description                        |
|-------------------|------------|------------------------------------|
| `namespace.yaml`  | Namespace  | `db-dev` namespace                 |
| `secret.yaml`     | Secret     | Postgres credentials (base64)      |
| `pvc.yaml`        | PVC        | 5Gi persistent storage             |
| `service.yaml`    | Service    | ClusterIP on port 5432             |
| `deployment.yaml` | Deployment | Postgres container (1 replica)     |

---

## 🚀 Deploy (in order)

```bash
# 1. Create namespace
kubectl apply -f namespace.yaml

# 2. Create secret
kubectl apply -f secret.yaml

# 3. Create persistent volume claim
kubectl apply -f pvc.yaml

# 4. Create service
kubectl apply -f service.yaml

# 5. Create deployment
kubectl apply -f deployment.yaml
```

Or apply everything at once:

```bash
kubectl apply -f .
```

---

## 🔌 Port Forward

Access Postgres locally on `localhost:5432`:

```bash
kubectl port-forward -n db-dev svc/postgres-svc 5432:5432
```

Or forward to a different local port (e.g. `5433`) if `5432` is already in use:

```bash
kubectl port-forward -n db-dev svc/postgres-svc 5433:5432
```

Then connect using:

```bash
psql -h localhost -p 5432 -U <your-user> -d <your-db>
# password: <your-password>
```

---

## 🛠 Utility Commands

```bash
# Check all resources in db-dev namespace
kubectl get all -n db-dev

# Check pod status
kubectl get pods -n db-dev

# Check PVC status
kubectl get pvc -n db-dev

# View pod logs
kubectl logs -n db-dev -l app=postgres -f

# Describe deployment
kubectl describe deployment postgres -n db-dev

# Describe pod (troubleshoot)
kubectl describe pod -n db-dev -l app=postgres

# Exec into postgres pod
kubectl exec -it -n db-dev deploy/postgres -- bash

# Run psql inside the pod directly
kubectl exec -it -n db-dev deploy/postgres -- psql -U <your-user> -d <your-db>

# View secret (decoded)
kubectl get secret postgres-secret -n db-dev -o jsonpath='{.data.POSTGRES_PASSWORD}' | base64 -d

# Restart deployment
kubectl rollout restart deployment postgres -n db-dev

# Check rollout status
kubectl rollout status deployment postgres -n db-dev

# Scale replicas
kubectl scale deployment postgres -n db-dev --replicas=0
kubectl scale deployment postgres -n db-dev --replicas=1
```

---

## 🗑 Cleanup

```bash
# Delete all resources
kubectl delete -f .

# Or delete namespace (removes everything inside)
kubectl delete namespace db-dev
```
