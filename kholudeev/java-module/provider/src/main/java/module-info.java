module ru.otus.kholudeev.provider {
    requires ru.otus.kholudeev.service;

    requires lombok;
    requires org.slf4j;
    requires jakarta.validation;
    requires com.fasterxml.jackson.annotation;
    requires org.mapstruct;

    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;

    exports ru.otus.kholudeev.facade;
    exports ru.otus.kholudeev.constant;
    exports ru.otus.kholudeev.dto.request;
    exports ru.otus.kholudeev.dto.response;
    opens ru.otus.kholudeev.mapper;
    opens ru.otus.kholudeev.dto.request;
    opens ru.otus.kholudeev.dto.response;
}