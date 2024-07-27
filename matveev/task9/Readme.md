# Homework async Profiler.

App create users, hash passwords and put it into h2 database.
1) Added BackgroundExceptionsProcessor -> throw new NoSuchElementException() every 5 seconds.
2) Controller getException method calls encrypt with not exist encryptType and thrown exception there 

### Run our app with async profiler(async-profiler also comes bundled with IntelliJ IDEA Ultimate 2018.3 and later):

![RunWithAsync.png](RunWithAsync.png)

Check that record started.

### Run Load via Jmeter

### Stop recording. Check results.
![FlameGraph.png](FlameGraph.png)

### Analyze it:
1) Exception in createUser controller:
![createUserException.png](createUserException.png)
2) Exception in getException controller:
![getExceptionController.png](getExceptionController.png)

3) BackgroundExceptionsProcessor:
![BackgroundExceptionsProcessor.png](BackgroundExceptionsProcessor.png)


### Also we found some unexpected issues:
![UnexpectedClassNotFound.png](UnexpectedClassNotFound.png)

### Get call tree of controller methods: 
![creteUserMethodDetails.png](creteUserMethodDetails.png)
![getExceptionMethodDetails.png](getExceptionMethodDetails.png)

