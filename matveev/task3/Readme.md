
### Homework by the lesson "Class data sharing".

## Build project, generate basic CDS-archive: 
```java -Xshare:dump```

## Run without sharing classes:

```time java -jar -Xshare:off -Xmx1g target/task3-0.0.1-SNAPSHOT.jar```

```
com.example.otus.Homework3Application    : Started Homework3Application in 2.734 seconds (process running for 3.039)
Seconds = 2.8734035
StopWatch 'App': 2.8734035 seconds
----------------------------------------
Seconds       %       Task name
----------------------------------------
2.8734035     100%    App Startup

```


## Run with sharing classes:

```time java -jar -Xshare:on -Xmx1g target/task3-0.0.1-SNAPSHOT.jar```

```
com.example.otus.Homework3Application    : Started Homework3Application in 2.632 seconds (process running for 2.881)
Seconds = 2.742528917
StopWatch 'App': 2.742528917 seconds
----------------------------------------
Seconds       %       Task name
----------------------------------------
2.742528917   100%    App Startup

```


# Conclution:

CDS - is super narrowly focused tool.
Difficulties which will you face(like updating archive) more harder than startup time profit.

My point of view:
1) In modern world, where everybody trying to split APP to micro-services this tool absolutely useless.
2) If you still have a monolit app - this tool won't help you too. The profit from this tool - the drop to sea.

Result: Better not to use it at all.