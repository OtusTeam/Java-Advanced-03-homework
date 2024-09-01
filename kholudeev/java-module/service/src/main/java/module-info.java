module ru.otus.kholudeev.service {
    requires ru.otus.kholudeev.core;

    requires lombok;
    requires org.slf4j;

    requires spring.context;
    requires jakarta.persistence;

    exports ru.otus.kholudeev.dao.service;
    exports ru.otus.kholudeev.dao.exception;
    exports ru.otus.kholudeev.exception;
    opens ru.otus.kholudeev.dao.service;
}