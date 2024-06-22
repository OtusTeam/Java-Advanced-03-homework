module api {
    requires service;
    requires provider;
    requires core;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    exports otus.homework.api;
}