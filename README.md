# Gestion de Bibliothèque

## Description du Projet
Cette application est une solution de gestion complète pour les bibliothèques, permettant de gérer les livres, les membres, les emprunts et les retours.

## Fonctionnalités Principales

### 1. Gestion des Livres
- Ajout, modification et suppression des livres
- Catégorisation des livres
- Recherche avancée (par titre, auteur, catégorie)
- Gestion des exemplaires
- Suivi du statut (disponible, emprunté, en maintenance)

### 2. Gestion des Membres
- Inscription et désinscription des membres
- Gestion des informations personnelles
- Historique des emprunts
- Système de points/récompenses
- Gestion des pénalités

### 3. Gestion des Emprunts
- Système de réservation
- Gestion des dates d'emprunt et de retour
- Alertes automatiques pour les retards
- Gestion des pénalités
- Historique des transactions

### 4. Gestion Administrative
- Tableau de bord administrateur
- Génération de rapports
- Statistiques d'utilisation
- Gestion des utilisateurs et des rôles
- Configuration système

## Architecture Technique

### Stack Technique
- Frontend: Angular 16+
- Backend: Spring Boot
- Base de données: MySQL
- API: RESTful
- Sécurité: Spring Security
- Documentation: Swagger

### Structure du Projet
```
src/main/java/sn/uasz/gestionBibliotheque/
├── controller/      # Contrôleurs REST
├── modele/         # Classes d'entités
├── repository/     # Interfaces de repository
└── service/        # Services métier
```

## Installation et Configuration

### Prérequis
- Java 17 ou supérieur
- Maven 3.6.3 ou supérieur
- MySQL 8.0 ou supérieur
- Node.js 18+ et npm
- Angular CLI

### Configuration de la Base de Données
Créer un fichier `application.properties` dans `src/main/resources` avec les configurations suivantes:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_bibliotheque
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Installation du Frontend
1. Créer un nouveau projet Angular:
```bash
ng new frontend
```

2. Installer les dépendances nécessaires:
```bash
cd frontend
ng add @angular/material
ng add @angular/animations
ng add @angular/pwa
```

### Lancement de l'Application
1. Backend:
```bash
# Build le projet
mvn clean install

# Lancer l'application
mvn spring-boot:run
```

2. Frontend:
```bash
# Dans le dossier frontend
ng serve
```

## API Documentation
L'application inclut une documentation Swagger accessible à l'adresse:
```
http://localhost:8080/swagger-ui.html
```

## Sécurité
- Authentification par username/password
- Rôles: ADMIN, BIBLIO, USER
- Protection des endpoints sensibles
- JWT pour l'authentification
- CORS configuration pour Angular

## Contribution
Pour contribuer au projet:
1. Fork le repository
2. Créez votre branche de fonctionnalité (`git checkout -b feature/amazing-feature`)
3. Commit vos changements (`git commit -m 'Add some amazing feature'`)
4. Push vers la branche (`git push origin feature/amazing-feature`)
5. Ouvrez une Pull Request

## License
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.