open module otus.moryakovdv.task17.api17 {
	exports otus.moryakovdv.task17.service;
	exports otus.moryakovdv.task17.model;
	exports otus.moryakovdv.task17.repository;
	exports otus.moryakovdv.task17.web;
	exports  otus.moryakovdv.task17;

	requires transitive jakarta.servlet;
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
	
	requires transitive  io.github.resilience4j.circuitbreaker;
	requires transitive io.github.resilience4j.annotations;
	
	requires transitive  io.github.resilience4j.ratelimiter;
	requires transitive  io.github.resilience4j.core;
	
}