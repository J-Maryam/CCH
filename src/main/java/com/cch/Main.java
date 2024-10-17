package com.cch;


import com.cch.config.AppConfig;
import com.cch.entities.Cyclist;
import com.cch.services.CyclistService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

//        System.out.print("Hello and welcome!");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        CyclistService cyclistService = context.getBean(CyclistService.class);
//        Cyclist cyclist = new Cyclist("John", "Doe", "USA", 29);
//        cyclistService.save(cyclist);
//
//        System.out.println("Cyclist saved successfully!");


    }
}