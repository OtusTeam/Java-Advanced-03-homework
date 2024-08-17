# Homework Prometeus/Gragana.

## Run app, prometeus and grafana services via docker-compose.
```
docker-compose -f docker-compose.yml up -d 
```
![dockercomposeservicesUP.png](dockercomposeservicesUP.png)



## Check that actuator work, open:
```
http://localhost:8081/actuator/prometheus
```
![actuatorPrometeusCheck.png](actuatorPrometeusCheck.png)

### Login to Grafana(admin/qwerty)
```
http://localhost:3000/ 
```

### Run jMeter load profile: HTTP Request Defaults.jmx


### Check that prometheus collect logs, go to:
```
http://localhost:9090/
```
![PrometeusRequestsCount.png](PrometeusRequestsCount.png)


### Check requests statistic in grafana:
![Grafana1.png](Grafana1.png)
![GrafanaHTTPStatistic.png](GrafanaHTTPStatistic.png)

### Filter success requests:
![Status200.png](Status200.png)


### Filter bad requests:
![Status400.png](Status400.png)