# ДЗ-5
## moryakovdv

# jvm-tools
Простое приложение с выводом строки *Task5 Application started* в консольный логгер

# Запуск
*$ ./build.sh*  
  
- компилирует классы  *javac* и запускает через *java*
- собирает jar и проверяет запуск через *java jar*
- создает самоподписанный сертификат, подписывает им jar, проверяет jar с помощью *jarsigner verify*
- запускает jar с кастомной jvm, созданной с помощью jlink

# 

Пример вывода на скриншотах:
1. компиляция, сборка jar, запуск в различных вариантах, сертификат и подпись архива  
![Selection_1261](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/3c69ba78-9c4c-4086-b59f-c534e0d64da3)  

2. верификация архива, сборка custom-jdk, запуск с использованием --module-path target/customjre/bin:target  
![Selection_1262](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/62ddfdf5-d836-4f64-bc1f-f5c51887d73d)  

3. результат в каталоге /target
   
![Selection_1260](https://github.com/OtusTeam/Java-Advanced-homework/assets/14349345/6b0b3167-4684-412d-b40f-465ac29bcd74)




