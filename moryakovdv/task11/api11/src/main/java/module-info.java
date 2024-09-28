open module otus.moryakovdv.task11.api11 {
	exports otus.moryakovdv.task11.service;
	exports otus.moryakovdv.task11.model;
	exports otus.moryakovdv.task11.repository;
	exports otus.moryakovdv.task11.web;
	exports  otus.moryakovdv.task11;

	requires lombok;
	requires org.slf4j;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.web;
	requires spring.webflux;
	
	requires java.sql;
	
	
	requires reactor.core;
	requires org.reactivestreams;
	requires spring.r2dbc;
	requires r2dbc.spi;
	requires spring.data.r2dbc;
	requires r2dbc.h2;
}