open module otus.moryakovdv.task7.api {
	exports otus.moryakovdv.task7.service;
	exports otus.moryakovdv.task7.model;
	exports otus.moryakovdv.task7.repository;
	exports otus.moryakovdv.task7.web;
	exports  otus.moryakovdv.task7;

	requires jakarta.persistence;
	requires lombok;
	requires org.slf4j;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.data.jpa;
	requires spring.web;
}