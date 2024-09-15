open module otus.moryakovdv.task9.api9 {
	exports otus.moryakovdv.task9.service;
	exports otus.moryakovdv.task9.model;
	exports otus.moryakovdv.task9.repository;
	exports otus.moryakovdv.task9.web;
	exports  otus.moryakovdv.task9;

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