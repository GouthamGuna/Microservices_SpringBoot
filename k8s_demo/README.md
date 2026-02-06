# K8s Demo Application Deployment Guide

## 1️⃣ Build Spring Boot Application

```
echo "Building Spring Boot application..."
mvn clean package -DskipTests
```

## 2️⃣ Build Docker Image
```
echo "Building Docker image..."
docker build -t k8s-demo-app:latest .
```

## 3️⃣ Deploy to Kubernetes
```
echo "Deploying to Kubernetes..."
kubectl apply -f k8s-deployment.yaml
kubectl apply -f k8s-service.yaml
```

4️⃣ Check Deployment Status
```
echo "Checking deployment status..."
kubectl get pods
kubectl get services
```

5️⃣ Get NodePort Details
```
kubectl get svc k8s-app-service
```
Find NodePort
```
kubectl get svc k8s-app-service -o wide
```
OR
```
kubectl describe svc k8s-app-service
```

6️⃣ Delete Existing Resources
```
kubectl delete deployment k8s-demo
kubectl delete service k8s-app-service
```