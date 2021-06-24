sudo docker build -t eventify-api .
sudo docker tag eventify-api:latest aswinavofficial/eventify-api:latest
sudo docker push aswinavofficial/eventify-api:latest
kubectl apply -f kubernetes/deployments/eventify-api-deployment.yaml
kubectl apply -f kubernetes/services/eventify-api-service.yaml
kubectl get deployments
kubectl get pods
kubectl get services