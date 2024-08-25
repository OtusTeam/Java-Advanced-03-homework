module ru.otus.kholudeev.api {
    requires ru.otus.kholudeev.service;
    requires ru.otus.kholudeev.provider;

    requires com.h2database;
    requires java.sql;
    requires lombok;
    requires org.slf4j;
    requires org.apache.tomcat.embed.core;
    requires jakarta.validation;

    requires spring.context;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;
    requires spring.core;
    requires spring.tx;

    exports ru.otus.kholudeev;
    exports ru.otus.kholudeev.configuration;
    exports ru.otus.kholudeev.controller;
    opens ru.otus.kholudeev;
    opens ru.otus.kholudeev.configuration;
    opens ru.otus.kholudeev.controller;
}