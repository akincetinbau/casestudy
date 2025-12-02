# casestudy
# DevOps Engineer Case Study

kubectl get pods -o wide

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

# port forward
Start-Job -ScriptBlock {     kubectl port-forward svc/monitoring-stack-grafana 3000:80 -n monitoring }
Get-Job
Stop-Job -Id 1

# grafana
# http://localhost:3000/dashboards
kubectl get secret monitoring-stack-grafana -n monitoring -o yaml
# admin 
# password: ouGdMBAHD4TDVdIWE8mPcUI26e5onUKDVearRnWF

## grafana + kafka cozulecek + argocd

# dashboard id 11378
# prometheus server url http://prometheus:9090

## prometheus grafana ok, kafka, argocd, github actions cozulecek
