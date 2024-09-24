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

Пример работы с имлементацией через ByteBuffer

$ java -jar byte-buffer.java  

```Started
Using default filePath and ByteBuffer
Using path ./testfiles
Using buffer ByteBufferFileContent
USAGE: <filename> - check in cache for <filename> 
	   count - see counter of cache hits
	   quit - time to say goodbye
Address.txt
Scanning cache for ./testfiles/Address.txt
./testfiles/Address.txt NOT found in cache.
./testfiles/Address.txt NOW IN CACHE
Address.txt
Scanning cache for ./testfiles/Address.txt
./testfiles/Address.txt FOUND in cache. See content below
===BYTEBUFFER BEGIN OF FILE ===
г. Москва
г. Пермь===BYTEBUFFER END OF FILE ===
  
```
$ java -jar byte-buffer.java /home/moryakov mapped


```
Started
Two parameter passed. Treat them as filePath and Buffer type
Using path /home/moryakov
Using buffer MappedByteBufferFileContent
USAGE: <filename> - check in cache for <filename> 
	   count - see counter of cache hits
	   quit - time to say goodbye
testdisk.log
Scanning cache for /home/moryakov/testdisk.log
/home/moryakov/testdisk.log NOT found in cache.
/home/moryakov/testdisk.log NOW IN CACHE
testdisk.log
Scanning cache for /home/moryakov/testdisk.log
/home/moryakov/testdisk.log FOUND in cache. See content below
===MAPPEDBYTEBUFFER BEGIN OF FILE ===


Thu Apr 18 22:00:58 2024
Command line: TestDisk

TestDisk 7.1, Data Recovery Utility, July 2019
Christophe GRENIER <grenier@cgsecurity.org>
https://www.cgsecurity.org
OS: Linux, kernel 5.4.0-176-generic (#196-Ubuntu SMP Fri Mar 22 16:46:39 UTC 2024) x86_64
Compiler: GCC 9.2
ext2fs lib: 1.45.5, ntfs lib: libntfs-3g, reiserfs lib: none, ewf lib: none, curses lib: ncurses 6.1
User is not root!
Hard disk list


TestDisk exited normally.
Using locale 'LC_CTYPE=en_US.UTF-8;LC_NUMERIC=ru_RU.UTF-8;LC_TIME=ru_RU.UTF-8;LC_COLLATE=en_US.UTF-8;LC_MONETARY=ru_RU.UTF-8;LC_MESSAGES=en_US.UTF-8;LC_PAPER=ru_RU.UTF-8;LC_NAME=ru_RU.UTF-8;LC_ADDRESS=ru_RU.UTF-8;LC_TELEPHONE=ru_RU.UTF-8;LC_MEASUREMENT=ru_RU.UTF-8;LC_IDENTIFICATION=ru_RU.UTF-8'.

Current partition structure:
 1 P EFI System                  2048    1050623    1048576 [EFI System Partition]
 2 P Linux filesys. data      1050624  500117503  499066880
SIGINT detected! TestDisk has been killed.
===MAPPEDBYTEBUFFER END OF FILE ===

```

  
