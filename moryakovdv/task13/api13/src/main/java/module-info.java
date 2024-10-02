open module otus.moryakovdv.task13.api13 {
	exports otus.moryakovdv.task13.service;
	exports otus.moryakovdv.task13.model;
	exports otus.moryakovdv.task13.repository;
	exports otus.moryakovdv.task13.web;
	exports  otus.moryakovdv.task13;

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