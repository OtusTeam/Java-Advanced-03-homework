# ДЗ-1
## moryakovdv

# memory-management

Консольное приложение, реализующее кеширование содержимого файла ФС в память.  
Для более гибкого управления памятью использует классы SoftReference или WeakReference.  
Интерфейс с пользоваателем осуществляется через объект Emulator

## Стек:
JDK 17
Maven

# Сборка
*$ mvn clean package*

# Запуск
*$ java -jar memory-management.java*  
Использование файлов из ресурсов и WeakReferenceValueCache  

*$ java -jar memory-management.java /home/moryakov/testfiles*  
Использование файлов из указанного каталога и WeakReferenceValueCache  

*$ java -jar memory-management.java /home/moryakov/testfiles soft*  
 Использование файлов из указанного каталога и SoftReferenceValueCache 

## Оcновные классы:
- Application - запускающий класс.  
- Cache, CacheValue - интерфейсы верхнего уровня для реализации кеша и хранимого в нем значения соответственно.  
- FileContent - содержимое файла, хранимое в кеше.  
- Emulator - класс, обеспечивающий UI, консольный ввод-вывод.  
- ExtendedSoftReference, ExtendedWeakReference - наследники от SoftReference и WeakReference соответственно.  Дополнительно хранят ключ, по которому ранее в кеш было добавлено значение.   
По этому ключу специальный поток *reaper* в кеше удаляет ссылки с "обнуленным" referent из очереди ReferenceQueue.  
- SoftReferenceValueCache, WeakReferenceValueCache - непосредственно реализации кеша. В качестве хранилища используют ConcurrentHashMap, делегируя ей действия с Entries.  

## команды 

- имя_файла - Запрос кеша. Проверяет наличие содержимого файла по этому ключу. Выводит на экран или заносит в кеш, -  если его нет.  

- count - выаодит счетчик хитов   
- gc - вызывает GC  
- quit - звершает работу  

## Пример работы с WeakReferenceCache 

*$ java -jar memory-management.java /home/moryakov/testfiles*

Started  
reaper started  
Using path /home/moryakov/testfiles  
Using cache WeakReferenceValueCache  
USAGE: filename - check in cache for filename  
	   count - see counter of cache hits    
	   gc - perform GC and see what happens   
	   quit - time to say goodbye  
	   
*Names.txt*  
Scanning cache for Names.txt  
Names.txt NOT found in cache.  
Names.txt NOW IN CACHE  

*count*  
Hits: 0  

*Names.txt*  
Scanning cache for Names.txt  
Names.txt FOUND in cache. See content below  
[Вася, Петя]  

*count*  
Hits: 1  

*Names.txt*  
Scanning cache for Names.txt  
Names.txt FOUND in cache. See content below  
[Вася, Петя]  

*count*  
Hits: 2  

*gc*  
GC Called!!  
CACHE CONTENT BEFORE GC   
key=Names.txt;ref=otus.moryakovdv.task1.ExtendedWeakReference@2aafb23c;value=[Вася, Петя]  
CACHE CONTENT AFTER GC   
key=Names.txt;ref=otus.moryakovdv.task1.ExtendedWeakReference@2aafb23c;value=null  

*Names.txt*  
Scanning cache for Names.txt  
Names.txt NOT found in cache.  
Names.txt NOW IN CACHE  

*count*  
Hits: 2  

*Names.txt*  
Scanning cache for Names.txt  
Names.txt FOUND in cache. See content below  
[Вася, Петя]  

*count*  
Hits: 3  

*quit*  
BYE!!  
Stopped  