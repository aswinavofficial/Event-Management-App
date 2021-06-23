# Eventify

[Frontend](https://angry-keller-52ae0c.netlify.app)

[![Netlify Status](https://api.netlify.com/api/v1/badges/5a8696fa-2546-4438-b2db-cd71a61d6d78/deploy-status)](https://app.netlify.com/sites/angry-keller-52ae0c/deploys)

[Backend](https://eventify-springsecurity-2aknj4a4kq-el.a.run.app)

[![publish-spring-security](https://github.com/aswinavofficial/Event-Management-App/actions/workflows/cloud-run-action-spring-security.yaml/badge.svg)](https://github.com/aswinavofficial/Event-Management-App/actions/workflows/cloud-run-action-spring-security.yaml)

--------

  
Deployment

1. [Install & start Minikube environment](https://minikube.sigs.k8s.io/docs/start/)
2. [Clone this repository](https://github.com/aswinavofficial/Event-Management-App)
3. Create a docker image                -  sudo docker build -t eventify-api .
4. Tag docker image                     -  sudo docker tag eventify-api:latest aswinavofficial/eventify-api:latest
5. Push docker the image to Docker hub  -  sudo docker push aswinavofficial/eventify-api:latest


