#!/bin/sh
##simply compile sources
echo 'compile app.'
javac -d target $(find jvmtools -name "*.java")
java -cp target otus.moryakovdv.task5.jvmtools.Application FROM_COMPILED
echo '=========================='
echo 'started with modules path.'
java --module-path target --module otus.moryakovdv.jvmtools/otus.moryakovdv.task5.jvmtools.Application WITH_MODULES
echo '=========================='

#create jar
cd target
echo 'creating jar'
jar -cfe jvmtools.jar otus.moryakovdv.task5.jvmtools.Application otus/moryakovdv/task5/jvmtools/Application.class 
java -jar jvmtools.jar FROM_JAR
echo '=========================='

#keytool and jarsigner
echo 'Generate certificate in non-interactive mode in target'
keytool -genkey -alias task5 -validity 365 -storepass task5password -keyalg RSA -keysize 2048 -dname "cn=moryakovdv, ou=otus, o=otus, c=otus" -keystore task5.keystore
echo 'Testing keystore in target'
keytool -list -v -alias task5 -keystore task5.keystore -storepass task5password
echo '=========================='


echo 'Sign jar with jarsigner'
jarsigner -keystore task5.keystore -storepass task5password jvmtools.jar task5
echo '=========================='
echo 'Verify jar with jarsigner'
jarsigner -verify jvmtools.jar -keystore task5.keystore task5

echo '=========================='

#jlink and lightweight jvm
echo 'Show modules dependencies'
cd ..
jdeps --module-path target -s --module otus.moryakovdv.jvmtools

echo 'create custom jre'
jlink --module-path "/usr/lib/jvm/java-17-openjdk-amd64/jmods:target" --add-modules otus.moryakovdv.jvmtools --output target/customjre
java --module-path target/customjre/bin:target --module otus.moryakovdv.jvmtools/otus.moryakovdv.task5.jvmtools.Application WITH_CUSTOM_JRE
echo '========THE END.============'
