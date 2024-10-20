package com.cch;


import com.cch.config.AppConfig;
import com.cch.entities.*;
import com.cch.entities.enums.StageType;
import com.cch.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "com.cch")
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TeamService teamService = context.getBean(TeamService.class);
        CyclistService cyclistService = context.getBean(CyclistService.class);


        AnnotationConfigApplicationContext cntx = new AnnotationConfigApplicationContext(Main.class);
        GeneralResultService generalResultService = cntx.getBean(GeneralResultService.class);
        CompetitionService competitionService = cntx.getBean(CompetitionService.class);
//        StageService stageService = context.getBean(StageService.class);

        Team team = teamService.findByTeamName("Team A");
        Competition competition = new Competition();
        competition.setId(5L);
        Cyclist cyclist = new Cyclist("John", "Doe", "USA", LocalDate.of(2000, 5, 19), team);
        cyclistService.save(cyclist);
        generalResultService.inscrireCycliste(competition, cyclist);

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
//        } else {
//            System.out.println("\nList of Cyclists: ");
//            for (Cyclist cyclist : cyclists) {
//                System.out.println("Cyclist Id: " + cyclist.getId());
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

//        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 23));
////        Competition competition1 = new Competition("Giro d'Italia", "Italy", LocalDate.of(2025, 5, 6), LocalDate.of(2025, 6, 28));
//        Competition savedCompetition = competitionService.saveCompetition(competition);
//        if(savedCompetition != null) {
//            System.out.println("Competition saved successfully with ID: " + savedCompetition.getId());
//        }else {
//            System.out.println("Failed to save competition.");
//        }
//
//        Stage stage = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, competition);
//        stageService.saveStage(stage);

//        System.out.println("\n===========================================================");
//        String lName = "Russo";
//        String nationality = "UK";
//        Team team = teamService.findByTeamName("Team A");
////        System.out.println("\nTri Par nom : " + lName);
////        System.out.println("Tri Par nationalité : " + nationality);
//        System.out.println("Tri Par équipe");
//
////        List<Cyclist> cyclistsSortedBy = cyclistService.findAllSortedByLastName(lName);
////        List<Cyclist> cyclistsSortedBy = cyclistService.findAllSortedByNationality(nationality);
//        List<Cyclist> cyclistsSortedBy = cyclistService.findAllSortedByTeam(team);
//
//
//        if (cyclistsSortedBy.isEmpty()) {
//            System.out.println("\nAucun cycliste trouvé");
//        } else {
//            System.out.println("\nListe des cyclistes : ");
//            for (Cyclist cyclist : cyclistsSortedBy) {
//                System.out.println("ID: " + cyclist.getId());
//                System.out.println("Prénom: " + cyclist.getFName());
//                System.out.println("Nom: " + cyclist.getLName());
//                System.out.println("Nationalité: " + cyclist.getNationality());
//                System.out.println("Date de naissance: " + cyclist.getBirthDate());
//                System.out.println("Équipe: " + cyclist.getTeam().getTeam());
//                System.out.println("-----------------------------");
//            }
//        }
    }
}