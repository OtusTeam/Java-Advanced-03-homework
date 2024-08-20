Требования: Java 17


## Задание:
1. Выпустить самоподписанный сертификат, подписать им jar-файл(из одного класса с методом main()), верифицировать подпись
2. Написать класс с одним методом main(), и из него собрать custom lightweight jre, и запустить свою программу с помощью этой jre

## Как воспроизвести работу приложения:

1. Скомпилировать jar
2. Создать самоподписанный сертификат в новом хранилище сертификатов
```shell
keytool -genkeypair -alias customcert -keypass qwerty123 -validity 365 -storepass qwerty123 -keyalg RSA -keystore my_keystore
keytool -list -keystore my_keystore -storepass qwerty123
Keystore type: PKCS12
Keystore provider: SUN

Your keystore contains 1 entry

customcert, 17 авг. 2024 г., PrivateKeyEntry, 
Certificate fingerprint (SHA-256): 58:65:48:58:57:21:04:09:E6:42:22:DE:BA:D2:C2:58:A2:A9:20:A2:F1:14:6F:01:81:5E:CC:0D:E2:F5:27:D0
```
3. Подписать jar созданным сертификатом
```shell
jarsigner target/jdk-tools.jar -keystore my_keystore -storepass qwerty123 customcert
jar signed.

Warning:
The signer's certificate is self-signed.
```
4. Верифицировать подпись в jar
```shell
$ jarsigner -verify target/jdk-tools.jar -keystore my_keystore -storepass qwerty123 customcert

jar verified.
```
5. Компиляция приложения с помощью javac
```shell
javac -d jlink-target src/main/java/module-info.java
javac -d jlink-target --module-path jlink-target src/main/java/ru/otus/kholudeev/Main.java
jlink --launcher customjrelauncher=otus.kholudeev.jdk.tools/ru.otus.kholudeev.Main --module-path "C:\Users\dekholud\Documents\repos\java_pet\otus-java-advanced\kholudeev\jdk-tools\jlink-target" --add-modules otus.kholudeev.jdk.tools --output jlink-customjre
```
6. Создание легковесной JRE
```shell
jlink --launcher customjrelauncher=otus.kholudeev.jdk.tools/ru.otus.kholudeev.Main --module-path "C:\Users\dekholud\Documents\repos\java_pet\otus-java-advanced\kholudeev\jdk-tools\jlink-target" --add-modules otus.kholudeev.jdk.tools --output jlink-customjre
```
7. Запуск приложения
```shell
./jlink-customjre/bin/customjrelauncher.bat
```