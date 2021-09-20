# Eventify

[Frontend](https://angry-keller-52ae0c.netlify.app)

[![Netlify Status](https://api.netlify.com/api/v1/badges/5a8696fa-2546-4438-b2db-cd71a61d6d78/deploy-status)](https://app.netlify.com/sites/angry-keller-52ae0c/deploys)

[Backend](https://eventify-springsecurity-2aknj4a4kq-el.a.run.app)

[![publish-spring-security](https://github.com/aswinavofficial/Event-Management-App/actions/workflows/cloud-run-action-spring-security.yaml/badge.svg)](https://github.com/aswinavofficial/Event-Management-App/actions/workflows/cloud-run-action-spring-security.yaml)

--------

  
Deployment

1. [Install & start Minikube environment](https://minikube.sigs.k8s.io/docs/start/)
2. Install ingress addons               -  minikube addons enable ingress
3. [Clone this repository](https://github.com/aswinavofficial/Event-Management-App)
4. Create a docker image                -  sudo docker build -t eventify-api .
5. Tag docker image                     -  sudo docker tag eventify-api:latest aswinavofficial/eventify-api:latest
6. Push docker the image to Docker hub  -  sudo docker push aswinavofficial/eventify-api:latest

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=aswinavofficial_Event-Management-App)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=aswinavofficial_Event-Management-App&metric=alert_status)](https://sonarcloud.io/dashboard?id=aswinavofficial_Event-Management-App)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=aswinavofficial_Event-Management-App&metric=ncloc)](https://sonarcloud.io/dashboard?id=aswinavofficial_Event-Management-App)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=aswinavofficial_Event-Management-App&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=aswinavofficial_Event-Management-App)


