time java \
    -XX:ArchiveClassesAtExit=spring_cds.jsa \
    -Xlog:class+load:file=spring_cds_dump.log \
    -Dspring.output.ansi.enabled=always \
    -Dcom.sun.management.jmxremote \
    -Dspring.jmx.enabled=true \
    -Dspring.liveBeansView.mbeanDomain \
    -Dspring.application.admin.enabled=true \
    -Dfile.encoding=UTF-8 \
    -jar ../../../target/Registration.jar
