# Профилирование с помощью visualvm

## Описание

В работе имитируется проблема - лишний запрос в БД findAll().

Запускаем JFR через VisualVM 
<img alt="1.png" height="970" src="1.png" width="1404"/>

Запускаем нагрузку через Jmeter

Во вкладке Profiler видим, что метод findAll() выполняется большую часть времени 
<img alt="2.png" height="2234" src="2.png" width="3456"/>

Смотрим запрос, который выполняется дольше всего 
<img alt="3.png" height="629" src="3.png" width="632"/>


Запускаем AsyncProfiler.  
Также запускаем нагрузку через Jmeter.  

<img alt="flamegraph.png" height="1050" src="flamegraph.png" width="2504"/>

Фильтруем граф по контроллеру, куда приходит запрос и видим, что большую часть занимает ненужный метод findAll()

<img alt="flamegraph2.png" height="868" src="flamegraph2.png" width="2438"/>
