# Реактивное программирование: Reactor + Вспоминаем Docker

## Описание

Реализация сервиса с использованием реактивного подхода и Запуск SpringBoot приложения в Docker

Для создания jar файла  
```mvn package```

Для сборки образа:  
```docker build -t task14-15_image .```

Для запуска:   
```docker-compose up```

План для нагрузки:  
 [Test Plan.jmx](Test%20Plan.jmx)

Метрики в Grafana  
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)  
Подключенный SwaggerAPI 
![img_4.png](img_4.png)