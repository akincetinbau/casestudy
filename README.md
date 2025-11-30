# casestudy
# DevOps Engineer Case Study

# build app
docker build -t my-spring-app:1.0 .

# up services with docker-compose.yaml
docker-compose up -d
docker-compose down
docker-compose stop

# Create secret
kubectl create secret generic db-credentials --from-literal=MONGO_PASSWORD=rootpassword

# restart 
kubectl rollout restart deployments/spring-boot-deployment

## grafana + kafka cozulecek + argocd
## prometheus grafana ok, kafka, argocd, github actions cozulecek