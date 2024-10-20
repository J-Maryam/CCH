
# CCH - Gestion des Compétitions Cyclistes

## Description

CCH (Cyclo Club Horizon) est une application conçue pour gérer les courses contre la montre de cyclisme organisées par le club Cyclo Club Horizon. Chaque coureur concourt individuellement pour réaliser le meilleur temps sur un parcours donné. L'application se concentre sur la gestion des coureurs, des compétitions, des inscriptions, des résultats et des classements.

## Fonctionnalités

### Gestion des Coureurs
- **Ajouter un coureur** : Enregistrer un coureur avec des informations telles que le nom, prénom, date de naissance, nationalité, et équipe.
- **Modifier un coureur** : Mettre à jour les informations d'un coureur existant.
- **Supprimer un coureur** : Retirer un coureur du système.
- **Consulter la liste des coureurs** : Afficher la liste des coureurs avec des options de tri (par nom, nationalité, ou équipe).

### Gestion des Compétitions
- **Créer une compétition** : Organiser une nouvelle compétition avec le nom, la date, le lieu, et la distance.
- **Modifier ou supprimer une compétition** : Gérer les compétitions existantes.
- **Consulter les compétitions** : Afficher la liste des compétitions avec des filtres par date ou lieu.

### Gestion des Inscriptions
- **Inscrire des coureurs à une compétition**.
- **Retirer un coureur d'une compétition**.
- **Consulter la liste des inscrits**.

### Gestion des Résultats et Classements
- **Enregistrer les temps des coureurs** pour une compétition.
- **Calculer automatiquement les classements** une fois les résultats enregistrés.
- **Afficher les classements** d'une compétition et les classements cumulés sur plusieurs compétitions.

### Rapports et Historique
- **Générer des rapports complets** sur une compétition (résultats, participants, classements).
- **Consulter l'historique** des compétitions passées et des performances des coureurs.

### Configuration
L'application utilise trois types de configuration :
- **Configuration XML** pour la persistance (ex. Hibernate).
- **Annotations Spring** pour l'injection des dépendances (`@Component`, `@Service`, `@Repository`, `@Autowired`).
- **Classes Java** avec des `@Configuration` pour déclarer des beans.

## Tests Unitaires
- Des tests unitaires sont implémentés pour valider les services et DAO.
- L'approche TDD est utilisée pour certaines fonctionnalités.

## Évolution Future

Cette version se concentre sur la gestion des données en backend, avec des tests manuels et des tests automatisés. Une version future intégrera **Spring MVC** pour exposer des API REST, permettant l'interaction avec une interface utilisateur via des endpoints REST.

## Technologies Utilisées

- Java
- Spring Data
- JPA
- JUnit et Mockito

## Installation et Lancement

1. Cloner le projet depuis GitHub :
    ```bash
    https://github.com/J-Maryam/CCH.git
    ```
2. Configurer la base de données dans le fichier `xml` (ou selon la configuration).
3. Compiler et lancer l'application :
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Auteur

Ce projet a été développé par [Maryam Jammar].
