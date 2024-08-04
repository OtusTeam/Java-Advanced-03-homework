# Изучение основных инструментов JDK

### Выпустить самоподписанный сертификат, подписать им jar-файл(из одного класса с методом main()), верифицировать подпись

собираем .jar
```
mvn package
```
создаем сертификат
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ keytool -genkeypair -alias testcert -storepass ps123456 -validity 365 -keyalg RSA -keystore ./keytool-certs/test_keystore
What is your first and last name?
[Unknown]:  nicshal
What is the name of your organizational unit?
[Unknown]:  ru
What is the name of your organization?
[Unknown]:  nic
What is the name of your City or Locality?
[Unknown]:  Moscow
What is the name of your State or Province?
[Unknown]:  msk
What is the two-letter country code for this unit?
[Unknown]:  ru
Is CN=nicshal, OU=ru, O=nic, L=Moscow, ST=msk, C=ru correct?
[no]:  y
```
проверяем
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ keytool -list -storepass ps123456 -keystore ./keytool-certs/test_keystore
Keystore type: PKCS12
Keystore provider: SUN

Your keystore contains 1 entry

testcert, 4 авг. 2024 г., PrivateKeyEntry, 
Certificate fingerprint (SHA-256): 81:4C:E8:E6:2B:3C:10:08:57:55:AC:99:9E:E0:0A:EF:4A:0F:D8:FB:FC:1D:F2:DA:CA:2D:A0:58:BE:C9:90:39

nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ keytool -list -v -alias testcert -storepass ps123456 -keystore ./keytool-certs/test_keystore
Alias name: testcert
Creation date: 4 авг. 2024 г.
Entry type: PrivateKeyEntry
Certificate chain length: 1
Certificate[1]:
Owner: CN=nicshal, OU=ru, O=nic, L=Moscow, ST=msk, C=ru
Issuer: CN=nicshal, OU=ru, O=nic, L=Moscow, ST=msk, C=ru
Serial number: 45aa40e0
Valid from: Sun Aug 04 21:33:54 MSK 2024 until: Mon Aug 04 21:33:54 MSK 2025
Certificate fingerprints:
         SHA1: F6:66:53:A4:AF:24:73:9D:5C:79:75:C5:F8:DA:94:DB:BE:CD:76:BA
         SHA256: 81:4C:E8:E6:2B:3C:10:08:57:55:AC:99:9E:E0:0A:EF:4A:0F:D8:FB:FC:1D:F2:DA:CA:2D:A0:58:BE:C9:90:39
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions: 

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 1D 73 2C 45 91 32 DD 3B   E0 6F E8 C5 37 0A 97 A8  .s,E.2.;.o..7...
0010: 87 83 FD 9D                                        ....
]
]

```
пробуем верифицировать .jar
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ jarsigner -verify ./target/keytool-jlink-1.0-SNAPSHOT.jar

jar is unsigned.
```
подписываем .jar
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ jarsigner ./target/keytool-jlink-1.0-SNAPSHOT.jar -keystore ./keytool-certs/test_keystore testcert
Enter Passphrase for keystore: 
jar signed.

Warning: 
The signer's certificate is self-signed.
POSIX file permission and/or symlink attributes detected. These attributes are ignored when signing and are not protected by the signature.
```
еще раз пробуем верифицировать .jar
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ jarsigner -verify ./target/keytool-jlink-1.0-SNAPSHOT.jar

jar verified.

Warning: 
This jar contains entries whose certificate chain is invalid. Reason: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
This jar contains entries whose signer certificate is self-signed.
This jar contains signatures that do not include a timestamp. Without a timestamp, users may not be able to validate this jar after any of the signer certificates expire (as early as 2025-08-04).
POSIX file permission and/or symlink attributes detected. These attributes are ignored when signing and are not protected by the signature.

Re-run with the -verbose and -certs options for more details.
```

## Написать класс с одним методом main(), и из него собрать custom lighthweight jre, и запустить свою программу с помощью этой jre

создаем модуль, смотрим зависимости
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ /home/nicshal/.jdks/corretto-21.0.2/bin/javac -d jlink-target ./src/main/java/ru/nicshal/advanced/jlink/Main.java
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ /home/nicshal/.jdks/corretto-21.0.2/bin/javac -d jlink-target ./src/main/module-info.java
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ /home/nicshal/.jdks/corretto-21.0.2/bin/jdeps --module-path jlink-target -s --module ru.nicshal.advanced.jlink
ru.nicshal.advanced.jlink -> java.base
ru.nicshal.advanced.jlink -> java.logging
```
запускаем
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ /home/nicshal/.jdks/corretto-21.0.2/bin/java --module-path jlink-target --module ru.nicshal.advanced.jlink/ru.nicshal.advanced.jlink.Main
авг. 05, 2024 1:20:05 AM ru.nicshal.advanced.jlink.Main main
INFO: Hello world!
```
создаем собственнный jre и запускаемся, использую созданный jre
```
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ /home/nicshal/.jdks/corretto-21.0.2/bin/jlink --module-path /home/nicshal/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink/jlink-target:/home/nicshal/.jdks/corretto-21.0.2/jmods --add-modules ru.nicshal.advanced.jlink --output jlink-customjre
nicshal@nicshal-ubuntu:~/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink$ /home/nicshal/IdeaProjects/Java-Advanced-homework-next/shalashov/keytool-jlink/jlink-customjre/bin/java --module-path jlink-target --module ru.nicshal.advanced.jlink/ru.nicshal.advanced.jlink.Main
Aug 05, 2024 1:23:16 AM ru.nicshal.advanced.jlink.Main main
INFO: Hello world!
```


