# Поиск утечки памяти в приложении

## Запуск приложения

 - Для запуска приложения требуется Java 17 или выше.
 - Приложение запускается с опциями: -Xmx56m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/nicshal/tmp/heapdump2.hprof

## Описание воспроизведения утечки памяти
 - запустить приложение с опциями -Xmx56m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath="путь_куда_сохранить_дапм"
 - зайти по адресу http://localhost:8081
 - попробовать зарегистрироваться
 - при вводе несуществующего логина-пароля (или при неправильном пароле) получаем ошибку

### Ошибка в приложении
![Ошибка в приложенин](img1.png)

Блок кода с ошибкой
```
List<Object[]> arrays = new LinkedList<>();
for (; ; ) {
    arrays.add(new Object[10]);
}
```

### Диагностика

При возникновении OutOfMemoryError был сохранен дамп heapdump2.hprof
Открываем дамп при помощи Eclipce Memory Analyzer
Выбираем «Leak Suspects Report», после этого откроется краткий обзор возможных утечек памяти:

![Проблема](img2.png)

Видим место, где проблема возникла и причину:
```
ru.nicshal.advanced.outofmemory.security.UserRepositoryUserDetailsService.loadUserByUsername(Ljava/lang/String;)Lorg/springframework/security/core/userdetails/UserDetails; (UserRepositoryUserDetailsService.java:35)
java.util.LinkedList @ 0xfccf6c68 retains 34 134 432 (62,18 %) bytes
```

Если вернуться на вкладку «Overview» и выбрать «Dominator Tree», то можно увидеть более подробную картину:

![Подробности 1](img3.png)
![Подробности 2](img4.png)

Видим, что израсходована вся выделеннач память (100%)
Израсходовано 62% на локальный LinkedList
