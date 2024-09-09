#!/bin/sh
java -Xms256m -Xmx512m -XX:StartFlightRecording:filename=./task8-journal.jfr -jar api8/target/api8.jar