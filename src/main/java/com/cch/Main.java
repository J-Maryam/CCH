package com.cch;


import com.cch.config.AppConfig;
import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.services.CyclistService;
import com.cch.services.TeamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Team team = new Team("Team A");
        TeamService teamService = context.getBean(TeamService.class);
        teamService.save(team);

        CyclistService cyclistService = context.getBean(CyclistService.class);
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);
        cyclistService.save(cyclist);

        System.out.println("Cyclist saved successfully!");


    }
}