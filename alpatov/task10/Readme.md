# Использование ByteBuffer

## Описание
Реализовано 2 типа буфера:
1. ByteBuffer 
2. MappedByteBuffer

Реализовано 2 способа кэширование текстовых файлов:
1. WeakReference
2. SoftReference

## Алгоритм работы

После запуска приложения необходимо выбрать тип буфера  
0 - ByteBuffer  
1 - MappedByteBuffer

Выбрать тип кэширования файлов  
0 - WeakReference  
1 - SoftReference  

Затем ввести 1 и указать кэшируемую директорию  
Пример: /Users/user/IdeaProjects/Java-Advanced-homework/alpatov/task1/src/main/resources

Затем ввести 2 и указать кэшируемый файл  
Пример: c++.txt

Затем ввести 3 и указать файл в кэше
Пример: c++.txt

Ввести 4 для выхода