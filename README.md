# casestudy
# DevOps Engineer Case Study

**Project Status**
- temel izleme (Prometheus + Grafana) kurulumu çalışır durumda. Kafka ve ArgoCD entegrasyonları ok; 

Eksik/iyileştirme gereken noktalar liveness readiness

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
Start-Job -ScriptBlock {     kubectl port-forward svc/monitoring-stack-kube-prom-prometheus 9090:443 -n monitoring }
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

**Repo kısa özet**
- **`chart/`**: Projeye ait Helm chart'ların bulunduğu ana dizin
- **`k8s/`**: Kubernetes ile ilgili manifest ve Helm paketlerinin bulunduğu dizin
  - **`k8s/helm/argocd`**: Argo CD kurulumu için Helm chart ve run.sh
  - **`k8s/helm/prometheus-stack`**: Prometheus + Grafana stack için Helm values.yaml ve run.sh
- **`docker-compose.yaml`**: dockor compose for mongo&kafka

**hatirlatici komutlar**
- Localde Docker Compose ile çalıştırma:
  - `docker-compose up -d` — servisleri arka planda başlatır.
  - `docker-compose down` — servisleri durdurup ağları/volumeleri temizler.
  - `docker-compose stop` — servisleri durdurur ama kaynakları bırakır.
- Prometheus stack (Helm) kurulum örneği:
  - `kubectl create namespace monitoring`
  - `helm repo add prometheus-community https://prometheus-community.github.io/helm-charts`
  - `helm repo update`
  - `helm upgrade --install monitoring-stack prometheus-community/kube-prometheus-stack -n monitoring -f k8s/helm/prometheus-stack/kube-prometheus-stack-values.yaml`
- Argo CD (Helm) kurulum örneği:
  - `helm repo add argocd https://argoproj.github.io/argo-helm`
  - `helm repo update`
  - `helm upgrade --install argocd -f k8s/helm/argocd/values.yaml argo/argo-cd --version 9.1.5 --namespace argocd --create-namespace`

**Port-forward örnekleri**
- Grafana: `kubectl port-forward svc/monitoring-stack-grafana 3000:80 -n monitoring` (web UI için `http://localhost:3000`)
- Prometheus: `kubectl port-forward svc/monitoring-stack-kube-prom-prometheus 9090:443 -n monitoring`

**Annotated `run.sh` (kısa açıklamalar)**

- `k8s/helm/argocd/run.sh` içerik ve açıklama:

```bash
helm repo add argocd https://argoproj.github.io/argo-helm

helm repo update

helm upgrade --install argocd -f values.yaml argo/argo-cd --version 9.1.5 --namespace argocd --create-namespace

# (opsiyonel) İlk admin parolası almak için:
# kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
```

Açıklama:
- `helm repo add` ve `helm repo update` ile Argo Helm deposunu kaydeder ve günceller.
- `helm upgrade --install` komutu chart'ı belirtilen namespace'e yükler veya günceller; `--create-namespace` namespace yoksa oluşturur.
- Son satır (yorum) Argo CD'nin başlangıç admin parolasını almak içindir; bu çıktıyı güvenli şekilde not edin veya bir Kubernetes Secret yöneticisi ile yönetin.

- `k8s/helm/prometheus-stack/run.sh` içerik ve açıklama:

```bash
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

helm repo update

kubectl create namespace monitoring

helm upgrade --install monitoring-stack prometheus-community/kube-prometheus-stack -n monitoring -f kube-prometheus-stack-values.yaml

kubectl port-forward svc/monitoring-stack-grafana 3000:80 -n monitoring
```

Açıklama:
- `helm repo add` + `helm repo update` ile Prometheus topluluğu chart deposu eklenir/güncellenir.
- `kubectl create namespace monitoring` ile `monitoring` namespace'i oluşturulur (varsa hata vermez).
- `helm upgrade --install` ile `kube-prometheus-stack` chart'ı `monitoring` namespace'ine uygulanır; `-f` parametresi proje içindeki values dosyasını kullanır.
- Son satır örnek olarak Grafana servisinin port-forward'ını başlatır; yerel `http://localhost:3000` üzerinden arayüze erişim sağlar.
