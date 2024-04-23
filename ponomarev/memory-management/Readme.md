# Использование SoftReference & WeakReference в кэшах

# Information

Консольное приложение для сохранения в кеш текстовых файлов и получения из кеша их содержимого.

Пользователю предлагается вводом через клавиатуру:

- указать кэшируемую директорию
  Директория с текстовыми файлами приложения:
  memory-management/src/main/resources

- загрузить содержимое файла в кэш

- получить содержимое файла из кэша

- выйти из приложения

# Configuration Information

## Project conf:

JDK 17, Maven

## Сборка:

mvn clean package

## Запуск:

java -jar memory-management-1.0-SNAPSHOT.java soft
Использование SoftReference для кеша

$ java -jar memory-management-1.0-SNAPSHOT.java weak
Использование WeakReference для кеша

$ java -jar memory-management-1.0-SNAPSHOT.java
Использование SoftReference для кеша будет по умолчанию

# Start Information

для ввода и вывода информации приложения используется консоль

Приложение будет в консоли предлагать загрузить/прочитать на выбор файлы из указанной вами директории

