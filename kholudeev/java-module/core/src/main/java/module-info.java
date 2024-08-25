module ru.otus.kholudeev.core {
    requires lombok;
    requires jakarta.persistence;

    requires spring.context;
    requires org.hibernate.orm.core;
    requires spring.data.jpa;
    requires spring.data.commons;

    exports ru.otus.kholudeev.dao.repository;
    exports ru.otus.kholudeev.dao.model;
    opens ru.otus.kholudeev.dao.model to org.hibernate.orm.core, spring.core;
}