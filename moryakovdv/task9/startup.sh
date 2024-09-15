#!/bin/sh
java -Xms256m -Xmx512m \
-XX:+UnlockDiagnosticVMOptions \
-XX:+DebugNonSafepoints \
-XX:+UnlockExperimentalVMOptions \
-XX:+PrintFlagsFinal \
-jar api9/target/api9.jar

