time java -Xshare:dump -XX:SharedClassListFile=app_classes.lst -XX:SharedArchiveFile=app_cds.jsa \
     -Dspring.context.exit=onRefresh \
     -jar ./target/task3-0.0.1-SNAPSHOT.jar