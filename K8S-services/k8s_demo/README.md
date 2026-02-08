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
kubectl delete service k8s-app-service
kubectl delete deployment k8s-demo
```
7️⃣ List Services
```
# Basic list
kubectl get services
kubectl get svc  # Short form

# With more details
kubectl get services -o wide
kubectl get svc -o wide

# With specific namespace
kubectl get services -n <namespace-name>
kubectl get svc --all-namespaces  # All namespaces
```
8️⃣ List Deployments
```
# Basic list
kubectl get deployments
kubectl get deploy  # Short form

# With more details
kubectl get deployments -o wide
kubectl get deploy -o wide

# With specific namespace
kubectl get deployments -n <namespace-name>
kubectl get deploy --all-namespaces  # All namespaces
```

9️⃣ Combined/Useful Commands
```
# See both deployments and services together
kubectl get deploy,svc

# See all resources (pods, services, deployments, etc.)
kubectl get all

# With labels/selectors
kubectl get svc --show-labels
kubectl get deploy -l app=myapp

# Detailed YAML output
kubectl get svc <service-name> -o yaml
kubectl get deploy <deployment-name> -o yaml

# JSON output
kubectl get svc -o json
```
🔟 Watch Mode (real-time updates)
```
# Watch services changes
kubectl get svc -w

# Watch deployments
kubectl get deploy -w
```

✍️ Examples
```
# See all services in default namespace
kubectl get svc

# See all deployments across all namespaces
kubectl get deploy -A

# See services in "production" namespace with details
kubectl get svc -n production -o wide

# See both deployments and services in current namespace
kubectl get deploy,svc
```
**NOTE: Add -A or --all-namespaces flag to see resources across all namespaces, or specify a namespace with -n <namespace>.**