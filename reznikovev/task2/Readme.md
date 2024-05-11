Поиск утечки памяти в приложении.
Моя цель показать утечку через внутренний кеш приложения

Тесты проходили с использованием трех шагов

* изменение двух ключей в файле параметров

```
user_cache.maximumSize
user_cache.expireAfterAccessInMinutes
```

* запуск приложения

```shell
 mvn spring-boot:run 
```

* генерация искусственных данных

```shell
curl 'http://localhost:8081/api/generator/user'
```

1. Запуск приложения с отсутствующим кешом

   ![img1.jpg](img1.jpg)

   ![img2.jpg](img2.jpg)

2. Запуск приложения с включенным кешом

   ![img3.jpg](img3.jpg)