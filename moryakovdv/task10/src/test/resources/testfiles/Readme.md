# ДЗ-10
## moryakovdv

# byte-buffer

Консольное приложение, реализующее кеширование содержимого файла ФС в HashMap c off-heap значением.  
Применяются две релизации: ByteBuffer и MappedByteBuffer.  

## Стек:
JDK 17
Maven

# Сборка
*$ mvn clean package*

# Запуск
*$ java -jar byte-buffer.java*  
Использование файлов из ресурсов и ByteBuffer  

*$ java -jar byte-buffer.java /home/moryakov/testfiles mapped  
Использование файлов из указанного каталога и MappedByteBuffer  

## Оcновные классы:
- Application - запускающий класс.
- Emulator - класс консольного UI
- ByteBufferFileContent - кеш на  java.nio.ByteBuffer
- MappedByteBufferFileContent -кеш на java.nio.MappedByteBuffer



## Результаты

