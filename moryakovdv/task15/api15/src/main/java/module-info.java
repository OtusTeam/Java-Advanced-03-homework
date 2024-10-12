open module otus.moryakovdv.task15.api15 {
	exports otus.moryakovdv.task15.service;
	exports otus.moryakovdv.task15.model;
	exports otus.moryakovdv.task15.repository;
	exports otus.moryakovdv.task15.web;
	exports  otus.moryakovdv.task15;

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
	
	requires org.springdoc.openapi.webmvc.core;
	requires io.swagger.v3.core;
	requires io.swagger.v3.oas.annotations;

}