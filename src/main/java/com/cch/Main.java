package com.cch;


import com.cch.config.AppConfig;
import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.services.CompetitionService;
import com.cch.services.CyclistService;
import com.cch.services.TeamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan(basePackages = "com.cch")
public class Main {
    public static void main(String[] args) {

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        TeamService teamService = context.getBean(TeamService.class);
//        CyclistService cyclistService = context.getBean(CyclistService.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        CompetitionService competitionService = context.getBean(CompetitionService.class);

//        Team team = new Team("Team F");
//        teamService.save(team);
//
//        Cyclist cyclist = new Cyclist("Marry", "Perles", "USA", LocalDate.of(2000, 5, 19), team);
//        Cyclist savedCyclist = cyclistService.save(cyclist);
//
//        if (savedCyclist != null && savedCyclist.getId() != null) {
//            System.out.println("Cyclist saved successfully with ID: " + savedCyclist.getId());
//        } else {
//            System.out.println("Failed to save cyclist.");
//        }


//        List<Cyclist> cyclists = cyclistService.findAll();
//        if (cyclists.isEmpty()) {
//            System.out.println("\nNo Cyclists found");
//        }else {
//            System.out.println("\nList of Cyclists: ");
//            for (Cyclist cyclist : cyclists) {
//                System.out.println("Cyclist Id: " + cyclist.getFName());
//                System.out.println("First Name: " + cyclist.getFName());
//                System.out.println("Last Name: " + cyclist.getLName());
//                System.out.println("Nationality: " + cyclist.getNationality());
//                System.out.println("Birth Date: " + cyclist.getBirthDate());
//                System.out.println("Team: " + cyclist.getTeam().getTeam());
//                System.out.println("-----------------------------");
//            }
//        }

//        Long cyclistId = 2L;
//        Optional<Cyclist> foundCyclist = cyclistService.findById(cyclistId);
//        if(foundCyclist.isPresent()) {
//            Cyclist c = foundCyclist.get();
//            System.out.println("Cyclist found :");
//            System.out.println("First Name: " + c.getFName());
//            System.out.println("Last Name: " + c.getLName());
//            System.out.println("Nationality: " + c.getNationality());
//            System.out.println("Birth Date: " + c.getBirthDate());
//            System.out.println("Team: " + c.getTeam().getTeam());
//        }else {
//            System.out.println("Cyclist not found");
//        }

//        Long cyclistIdToDelete = 1L;
//
//        Optional<Cyclist> cyclistToDelete = cyclistService.findById(cyclistIdToDelete);
//
//        if (cyclistToDelete.isPresent()) {
//            cyclistService.deleteById(cyclistIdToDelete);
//            System.out.println("Cyclist with ID " + cyclistIdToDelete + " deleted successfully.");
//        } else {
//            System.out.println("Cyclist with ID " + cyclistIdToDelete + " not found.");
//        }

        Competition competition1 = new Competition("Tour de France", "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));
        Competition savedCompetition = competitionService.saveCompetition(competition1);
        if(savedCompetition != null) {
            System.out.println("Competition saved successfully with ID: " + savedCompetition.getId());
        }else {
            System.out.println("Failed to save competition.");
        }
    }
}