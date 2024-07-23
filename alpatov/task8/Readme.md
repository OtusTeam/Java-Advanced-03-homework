# Диагностика приложения с помощью JFR

## Описание
Запускаем JFR через VisualVM 
<img alt="1.png" height="970" src="1.png" width="1404"/>

Запускаем нагрузку через Jmeter

Во вкладке Profiler видим, что метод findAll() выполняется большую часть времени 
<img alt="2.png" height="2234" src="2.png" width="3456"/>

Смотрим запрос, который выполняется дольше всего 
<img alt="3.png" height="629" src="3.png" width="632"/>

