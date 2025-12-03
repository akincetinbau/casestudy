helm repo add argocd https://argoproj.github.io/argo-helm

helm repo update

helm upgrade --install argocd -f values.yaml argo/argo-cd --version 9.1.5 --namespace argocd --create-namespace

# first password
#kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
#3OCfJ65Z51sWr1D6