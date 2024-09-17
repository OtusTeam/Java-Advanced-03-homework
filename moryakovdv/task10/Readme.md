# ДЗ-1
## moryakovdv

# byte-buffer

Консольное приложение, реализующее кеширование содержимого файла ФС в раздел off-heap.  
Применяются две релизации: ByteBuffer и MappedByteBuffer.  

## Стек:
JDK 17
Maven

# Сборка
*$ mvn clean package*

# Запуск
*$ java -jar byte-buffer.java*  
Использование файлов из ресурсов и ByteBuffer, по умолчанию буффер 256 bytes, смещение 0 bytes

*$ java -jar byte-buffer.java /home/moryakov/testfiles mapped N M*
Использование файлов из указанного каталога и MappedByteBuffer, заданный буффер N bytes, смещение M bytes

## Оcновные классы:
- Application - запускающий класс.
- OffHeapContent - общий абстрактный класс 
- ByteBufferContent - реализация чтения чанками по *bufferSize* с помощью  java.nio.ByteBuffer
- MappedByteBufferContent - реализация чтения чанками по *bufferSize* с помощью  java.nio.MappedByteBuffer



## Результаты

На одном и том же файле Readme.md с буфером 512 байт чтение с помощью MappedByteBuffer показывает результаты примерно на 15% лучше чем через ByteBuffer (см. CommonTests.java).
![image](https://github.com/user-attachments/assets/ba0e5359-4dc9-4d33-b39f-f8584b7ae1df)
