# ДЗ-3
## moryakovdv

## Class data sharing



## Стек:
JDK 17  
Maven  
Spring-boot  
Jetty  

# Сборка
*$ mvn clean package*

# Запуск
*$ java -jar cds.jar*  

##Запуск приложения без cds .jsa файла
*$ java -Xms64m -Xmx128m -Xlog:cds -jar cds.jar > no-jsa.log*  

##Запуск приложения с генерацией cds .jsa файла при завершении. 
*Спринг завершается сразу после старта. Лог CDS выводится в файл*  
  
*$ java -Xms64m -Xmx128m -XX:ArchiveClassesAtExit=application.jsa -Dspring.context.exit=onRefresh -Xlog:cds -jar cds.jar > jsa-creation.log*

##Запуск приложения с использованием cds .jsa файла
*$ java -Xms64m -Xmx128m -Xlog:cds -XX:SharedArchiveFile=application.jsa -jar cds.jar > with-jsa.log *  





