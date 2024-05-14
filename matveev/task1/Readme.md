# Пошаговая инструкция

### Run Main class and follow instruction in terminal-dialog menu.

Menu:
1) Set directory path
2) Get directory path
3) Get file
4) Load file
5) Get cached files
6) Clean cache
7) Quit

### Description
1) Select work directory:
```set absolute path like: /Users/hellomoto/IdeaProjects/Java-Advanced-homework/matveev/task1/src/main/resources/files```
3) Get file:
```Return the file from cache OR load to the cache and return it. ```

6) Clean cache:```will call gc() and show RefQueue links, clear cache.```


### Example:
```
Select type of cache: 1- softRef, 2 - weakRef
2
=====================
Choose action:
    1) Set directory path
    2) Get directory path
    3) Get file
    4) Load file
    5) Get cached files
    6) Clean cache
    7) Quit
=====================

1
Set path: 
/Users/userEntity/Java-Advanced-homework/hello-my-pet/src/main/resources/files
=====================
Choose action:
    1) Set directory path
    2) Get directory path
    3) Get file
    4) Load file
    5) Get cached files
    6) Clean cache
    7) Quit
=====================

2
/Users/userEntity/Java-Advanced-homework/hello-my-pet/src/main/resources/files
=====================
Choose action:
    1) Set directory path
    2) Get directory path
    3) Get file
    4) Load file
    5) Get cached files
    6) Clean cache
    7) Quit
=====================

4
Write file name:
Text1.txt
Added Text1.txt to cache with content: Hello
Olla
Bonjour
=====================
Choose action:
    1) Set directory path
    2) Get directory path
    3) Get file
    4) Load file
    5) Get cached files
    6) Clean cache
    7) Quit
=====================

4
Write file name:
Text2.txt
Added Text2.txt to cache with content: Goodbye
Bye
...
=====================
Choose action:
    1) Set directory path
    2) Get directory path
    3) Get file
    4) Load file
    5) Get cached files
    6) Clean cache
    7) Quit
=====================

5
[
Key: Text1.txt, value: Hello
Olla
Bonjour, 
Key: Text2.txt, value: Goodbye
Bye
...]
=====================
Choose action:
    1) Set directory path
    2) Get directory path
    3) Get file
    4) Load file
    5) Get cached files
    6) Clean cache
    7) Quit
=====================

6
Cache size before: 2
-----------------------
RefQueue links:
0) null
1) null
-----------------------
Run gc()
-----------------------
RefQueue links:
0) java.lang.ref.WeakReference@506e1b77
1) java.lang.ref.WeakReference@4fca772d
-----------------------
Check cache after gc():
[
Key: Text1.txt, value: null, 
Key: Text2.txt, value: null]
-----------------------
Running clear cache
-----------------------
Cache size after: 0
-----------------------
Run gc() again
-----------------------
RefQueue links:
null

```


