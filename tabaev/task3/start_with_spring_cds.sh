time /lib/jvm/jdk-17-oracle-x64/bin/java \
-XX:SharedArchiveFile=app_spring_cds.jsa \
-XX:TieredStopAtLevel=1 -Dspring.output.ansi.enabled=always \
-Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true \
-Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true \
-Dfile.encoding=UTF-8 \
-jar ./target/task3-0.0.1-SNAPSHOT.jar
