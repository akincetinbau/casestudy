# casestudy
# DevOps Engineer Case Study

# build app
docker build -t my-spring-app:1.0 .

# pod stop
kubectl scale deployment grafana --replicas=0
kubectl scale deployment prometheus --replicas=0


# up services with docker-compose.yaml
docker-compose up -d
docker-compose down
docker-compose stop

# Create secret
kubectl create secret generic db-credentials --from-literal=MONGO_PASSWORD=rootpassword

# restart 
kubectl rollout restart deployments/spring-boot-deployment

## grafana + kafka cozulecek + argocd

# dashboard id 11378
# prometheus server url http://prometheus:9090

## prometheus grafana ok, kafka, argocd, github actions cozulecek