kubectl create namespace monitoring --dry-run=client -o yaml | kubectl apply -f -
helm upgrade prometheus \
    --values ./infra-monitor/prometheus-values.yaml \
    --namespace monitoring \
    oci://registry-1.docker.io/bitnamicharts/prometheus
helm upgrade grafana-mimir \
    --values ./infra-monitor/values.yaml \
    --namespace monitoring \
    oci://registry-1.docker.io/bitnamicharts/grafana