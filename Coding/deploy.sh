#!/bin/bash

echo "🚀 Deploying Spring-redbook application to local Kubernetes..."

# Check if Kubernetes is available
if ! kubectl cluster-info > /dev/null 2>&1; then
    echo "❌ Error: Kubernetes cluster is not available."
    echo "Please enable Kubernetes in Docker Desktop:"
    echo "1. Open Docker Desktop"
    echo "2. Go to Settings → Kubernetes" 
    echo "3. Check 'Enable Kubernetes'"
    echo "4. Click 'Apply & Restart'"
    exit 1
fi

# Deploy the application
echo "📦 Deploying application and database..."
kubectl apply -f dev-deployment.yaml

# Wait for deployments to be ready
echo "⏳ Waiting for deployments to be ready..."
kubectl wait --for=condition=available --timeout=300s deployment/mysql
kubectl wait --for=condition=available --timeout=300s deployment/redbook

# Get service information
echo "🌐 Service information:"
kubectl get services

# Get pod status
echo "📋 Pod status:"
kubectl get pods

echo "✅ Deployment completed!"
echo ""
echo "🔗 Access the application at: http://localhost:30080/api/v1/posts"
echo "📊 Health checks:"
echo "   - Liveness:  http://localhost:30080/api/v1/posts/liveness"
echo "   - Readiness: http://localhost:30080/api/v1/posts/readiness"
echo ""
echo "🔍 Useful commands:"
echo "   kubectl get pods"
echo "   kubectl logs -f deployment/redbook"
echo "   kubectl logs -f deployment/mysql"
echo "   kubectl delete -f dev-deployment.yaml  # To clean up"