Домашнее задание
Создать метрики для отслеживания нагрузки на Rest сервис

Цель:
Покрыть Rest сервис метриками, построить дашборд к Grafana и продемонстрировать результаты дашборда, подавая нагрузку через JMeter

запуск контейнеров с grafana, prometheus, metrics-app
docker-compose -p otus up -d

![img_1.png](img_1.png)

http://localhost:8080/actuator/prometheus


![img.png](img.png)

Peometheus
![img_2.png](img_2.png)

Grafana - дашборд добавленный в конфигурации infra/grafana/dashboards/otus.json
![img_3.png](img_3.png)
![img_4.png](img_4.png)
![img_5.png](img_5.png)



## Update
Latency
![img.png](img.png)

Errors
![img_1.png](img_1.png)



## Update
Latency
![img.png](img.png)

Errors
![img_1.png](img_1.png)

RPS,RPM
![img_6.png](img_6.png)