# MinIO Docker to Kubernetes Migration Guide

## Overview
This guide helps you migrate your existing MinIO instance from Docker to Kubernetes **without data loss**.

## Pre-Migration Checklist

1. **Verify existing data path**: `/path/ggs/minio/data`
2. **Backup your data** (recommended):
   ```bash
   cp -r /path/ggs/minio/data /path/ggs/minio/data-backup
   ```
3. **Stop the Docker container**:
   ```bash
   docker stop minio_server
   docker rm minio_server
   ```

## Changes Made

### 1. Created PersistentVolume (pv.yml)
- Points to your existing host path: `/path/ggs/minio/data`
- Storage capacity: 50Gi (increased from 10Gi)
- Reclaim policy: `Retain` (prevents data deletion)

### 2. Updated PersistentVolumeClaim (pvc.yml)
- Changed storage class from `local-path` to `manual`
- Increased storage request to 50Gi
- Binds to the new PersistentVolume

### 3. Existing Configuration Review
- ✅ Mount path `/data` matches Docker setup
- ✅ Ports 9000 (API) and 9001 (Console) configured
- ⚠️  **Note**: Console port changed from 9090 (Docker) to 9001 (K8s)

## Deployment Steps

### Step 1: Create the namespace (if not exists)
```bash
kubectl create namespace minio-dev
```

### Step 2: Create MinIO credentials secret
```bash
kubectl create secret generic minio-secret-dev \
  --from-literal=MINIO_ROOT_USER=minioadmin \
  --from-literal=MINIO_ROOT_PASSWORD=minioadmin \
  -n minio-dev
```

### Step 3: Deploy in order
```bash
# 1. Create PersistentVolume
kubectl apply -f pv.yml

# 2. Create PersistentVolumeClaim
kubectl apply -f pvc.yml

# 3. Verify PVC is bound
kubectl get pvc -n minio-dev

# 4. Deploy MinIO application
kubectl apply -f deployment.yml

# 5. Create service
kubectl apply -f service.yml

# 6. Create ingress (optional)
kubectl apply -f ingress.yml
```

### Step 4: Verify deployment
```bash
# Check pod status
kubectl get pods -n minio-dev

# Check PV/PVC binding
kubectl get pv,pvc -n minio-dev

# View logs
kubectl logs -f -n minio-dev -l app=minio-app
```

## Access MinIO

### Via Port Forward
```bash
# Console access
kubectl port-forward -n minio-dev svc/minio-app-service 9001:9001

# API access
kubectl port-forward -n minio-dev svc/minio-app-service 9000:9000
```

Then access:
- **Console**: http://localhost:9001
- **API**: http://localhost:9000

### Via Ingress (if configured)
- **Console**: http://minio.127.0.0.1.sslip.io

## Data Verification

After deployment:
1. Access the MinIO console
2. Login with credentials (minioadmin/minioadmin)
3. Verify your bucket and data are present
4. Test file upload/download operations

## Important Notes

⚠️ **Host Path Requirement**: Ensure the K8s node where the pod runs has access to `/path/ggs/minio/data`

⚠️ **Node Affinity**: Since we're using `hostPath`, the pod must always run on the same node. Consider adding node affinity if you have multiple nodes:

```yaml
spec:
  affinity:
    nodeAffinity:
      requiredDuringSchedulingIgnoredDuringExecution:
        nodeSelectorTerms:
        - matchExpressions:
          - key: kubernetes.io/hostname
            operator: In
            values:
            - <your-node-name>
```

## Troubleshooting

### PVC stuck in Pending
```bash
kubectl describe pvc minio-pvc -n minio-dev
```
- Check if PV is available
- Verify storage class matches

### Pod not starting
```bash
kubectl describe pod -n minio-dev -l app=minio-app
kubectl logs -n minio-dev -l app=minio-app
```
- Check if secret exists
- Verify volume mount permissions

### Data not visible
- Verify the host path contains your data
- Check pod is running on the correct node
- Verify mount path in the container: `kubectl exec -it -n minio-dev <pod-name> -- ls -la /data`

## Rollback Plan

If migration fails:
```bash
# Delete K8s resources
kubectl delete -f deployment.yml
kubectl delete -f service.yml
kubectl delete -f pvc.yml
kubectl delete -f pv.yml

# Restart Docker container
docker run -p 9000:9000 -p 9090:9090 --name minio_server \
  -v /path/ggs/minio/data:/data \
  -e MINIO_ROOT_USER=minioadmin \
  -e MINIO_ROOT_PASSWORD=minioadmin \
  minio/minio server /data --console-address ":9090"
```

## Summary of Changes

- ✅ **Zero data loss**: Direct mount of existing data directory
- ✅ **Minimal code changes**: Only storage configuration updated
- ✅ **Same credentials**: Using your existing minioadmin credentials
- ✅ **Same data path**: `/data` mount point preserved
- ⚠️ **Port change**: Console port 9090 → 9001 (update any client configurations)
