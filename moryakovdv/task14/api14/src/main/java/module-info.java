open module otus.moryakovdv.task14.api14 {
	exports otus.moryakovdv.task14.service;
	exports otus.moryakovdv.task14.model;
	exports otus.moryakovdv.task14.repository;
	exports otus.moryakovdv.task14.web;
	exports  otus.moryakovdv.task14;

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