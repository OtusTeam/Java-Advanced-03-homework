# Поиск утечки памяти в приложении

## Описание
Реализован сервис регистрации пользователей. 
Параметры запуска  
Через jmeter отправляются запросы.
#### -Xmx64m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseParallelGC
<img alt="jmeter.png" height="300" src="jmeter.png" width="600"/>


<img alt="eclipse 1.png" height="300" src="eclipse 1.png" width="600"/>
<img alt="eclipse 2.png" height="300" src="eclipse 2.png" width="600"/>

Анализируя dominator_tree можно сказать, что кэш содержит большое количество записей и занимает 17% памяти.

Чтобы избавиться от этой проблемы, заменяю реализацию кэша с HashMap на кэш от Caffeine.

<img alt="eclipse 3.png" height="300" src="eclipse 3.png" width="600"/>
Проблема с ООМ из-за кэша решена



