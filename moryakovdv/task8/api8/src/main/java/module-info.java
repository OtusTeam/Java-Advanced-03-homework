open module otus.moryakovdv.task8.api8 {
	exports otus.moryakovdv.task8.service;
	exports otus.moryakovdv.task8.model;
	exports otus.moryakovdv.task8.repository;
	exports otus.moryakovdv.task8.web;
	exports  otus.moryakovdv.task8;

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