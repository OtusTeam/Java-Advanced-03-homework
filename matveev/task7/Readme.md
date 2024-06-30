# Homework Jmeter.

## Part 1. Run Jmeter via UI.

###  Load plan:
![ThreadGroup.png](ThreadGroup.png)

###  Request data:
![RequestData.png](RequestData.png)

### Result tree:
![Result_tree.png](Result_tree.png)

### Result graph:
![GraphResult.png](GraphResult.png)


## Part 2. Run jmeter via maven plugin.

### 1. Run application Homework7Application

### Run performance tests via load profile HTTPRequestDefaults.jmx
```
mvn clean install -P performance-test
```

### Results:
![MavenPerformanceTest1.png](MavenPerformanceTest1.png)
![MavenPerformanceTest2.png](MavenPerformanceTest2.png)