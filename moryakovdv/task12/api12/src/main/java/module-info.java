open module otus.moryakovdv.task12.api12 {
	exports otus.moryakovdv.task12.service;
	exports otus.moryakovdv.task12.model;
	exports otus.moryakovdv.task12.repository;
	exports otus.moryakovdv.task12.web;
	exports  otus.moryakovdv.task12;

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