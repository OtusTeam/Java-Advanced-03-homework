# nio: ByteBuffer&MappedByteBuffer homework

### Run Main class and follow instruction in terminal-dialog menu.

Menu:
1) Get file
2) Load file
3) Get cached files
4) Clean cache
5) Quit


### Example:
```
Select type of cache: 1- softRef, 2 - weakRef
2
=====================
Choose action:
    1) Get file
    2) Load file
    3) Get cached files
    4) Clean cache
    5) Quit
=====================

2
Write file name:
Text1.txt
Added Text1.txt to cache via MappedByteBuffer with content: java.nio.DirectByteBufferR[pos=0 lim=18 cap=18]
=====================
Choose action:
    1) Get file
    2) Load file
    3) Get cached files
    4) Clean cache
    5) Quit
=====================

1
Write file name:
Text1.txt
Get fileText1.txt content from cache successfully, content: Hello
Olla
Bonjour
=====================
Choose action:
    1) Get file
    2) Load file
    3) Get cached files
    4) Clean cache
    5) Quit
=====================
```


