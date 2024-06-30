# JDK tools


## Описание
```mvn package```
для создания jar файла

```bash
javac -d jlink-target ./src/main/java/module-info.java
```
<img alt="1.png" height="104" src="1.png" width="564"/>

```bash
javac -d jlink-target --module-path jlink-target ./src/main/java/ru/otus/java/advanced/SignedJLink.java
```

<img alt="2.png" height="171" src="2.png" width="281"/>

```bash
java --module-path jlink-target --module ru.otus.java.advanced/ru.otus.java.advanced.SignedJLink
```
<img alt="3.png" height="60" src="3.png" width="514"/>


```bash
jdeps --module-path jlink-target -s --module ru.otus.java.advanced
```
<img alt="4.png" height="60" src="4.png" width="514"/>

```bash
 jlink --module-path "/Users/alpatov/IdeaProjects/Java-Advanced-homework/alpatov/task5/jlink-target" --add-modules ru.otus.java.advanced  --output jlink-customjre
 ```
<img alt="5.png" height="250" src="5.png" width="280"/>


```bash
jlink --launcher customjrelauncher=ru.otus.java.advanced/ru.otus.java.advanced.SignedJLink --module-path "/Users/alpatov/IdeaProjects/Java-Advanced-homework/alpatov/task5/jlink-target" --add-modules ru.otus.java.advanced --output jlink-customjre
 ```
<img alt="6.png" height="250" src="6.png" width="280"/>

```bash
"/Users/alpatov/IdeaProjects/Java-Advanced-homework/alpatov/task5/jlink-customjre/bin/java" -classpath "/Users/alpatov/IdeaProjects/Java-Advanced-homework/alpatov/task5/jlink-customjre" --module ru.otus.java.advanced/ru.otus.java.advanced.SignedJLink
 ```
<img alt="7.png" height="75" src="7.png" width="580"/>

```bash
keytool -genkeypair -alias otus_cert -keypass 123456 -validity 365 -storepass 123456 -keyalg RSA
 ```
<img alt="8.png" height="460" src="8.png" width="1100"/>

```bash
jarsigner -verify ./target/task5-1.0-SNAPSHOT-jar-with-dependencies.jar
 ```
<img alt="9.png" height="74" src="9.png" width="903"/>


```bash
jarsigner ./target/task5-1.0-SNAPSHOT-jar-with-dependencies.jar otus_cert
 ```
<img alt="10.png" height="191" src="10.png" width="1167"/>

```bash
jarsigner -verify ./target/task5-1.0-SNAPSHOT-jar-with-dependencies.jar
 ```
<img alt="11.png" height="325" src="11.png" width="1635"/>
