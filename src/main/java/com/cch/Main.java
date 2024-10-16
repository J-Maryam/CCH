package com.cch;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(com.cch.config.AppConfig.class);
        SessionFactory sessionFactory = (SessionFactory) context.getBean("SessionFactory.class");
        System.out.print("Hello and welcome!");

    }
}