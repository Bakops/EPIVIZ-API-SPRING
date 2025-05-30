# EPIVIZ-API-SPRING

## Tests Postman

### 1. DATA

- **URL de la requête** : `http://localhost:8080/api/data`
- **Corps de la requête en JSON** :
  ```json
  {
    "total_cases": 100,
    "total_deaths": 10,
    "new_cases": 5,
    "new_deaths": 2,
    "localisation": {
      "id": 1
    },
    "pandemie": {
      "id": 1
    },
    "calendrier": {
      "id": 1
    }
  }
  ```

### 2. CALENDRIERS

- **URL de la requête** : `http://localhost:8080/api/calendriers`
- **Corps de la requête en JSON** :
  ```json
  {
    "date": 20231001
  }
  ```

### 3. LOCALISATIONS

- **URL de la requête** : `http://localhost:8080/api/localisations`
- **Corps de la requête en JSON** :
  ```json
  {
    "country": "France",
    "continent": "Europe"
  }
  ```

### 4. PANDÉMIE

- **URL de la requête** : `http://localhost:8080/api/pandemies`
- **Corps de la requête en JSON** :
  ```json
  {
    "type": "Virus X"
  }
  ```

````


---

# **Documentation: Pipeline CI/CD**

---

## **1. Objectif**

Cette pipeline CI/CD automatise les étapes critiques du développement pour garantir la qualité, la sécurité et la portabilité de l'application Spring Boot. Elle inclut :

- **Analyse de la qualité du code avec Checkstyle** : Vérifie que le code respecte les normes de style.
- **Analyse de sécurité avec Trivy** : Détecte les vulnérabilités dans le système de fichiers.
- **Analyse de qualité et couverture de code avec SonarCloud** : Évalue la qualité globale du code et la couverture des tests.
- **Packaging de l'application en fichier JAR** : Compile et package l'application.
- **Containerisation avec Docker et push vers Docker Hub** : Crée une image Docker et la pousse vers Docker Hub.

---

## **2. Commandes Utilisées**

### **Checkstyle**
Vérifie la qualité du code :
```bash
./mvnw checkstyle:check
````

### **Build Maven**

Compile et package l'application en fichier JAR :

```bash
./mvnw clean package
```

### **Trivy**

Scanne les vulnérabilités dans le système de fichiers :

```bash
trivy fs .
```

### **SonarCloud**

Analyse la qualité et la couverture du code :

```bash
./mvnw sonar:sonar -Dsonar.login=$SONAR_TOKEN
```

### **Docker**

Construire l'image Docker :

```bash
docker build -t bakops224/epivizappapi:latest .
```

Pousser l'image vers Docker Hub :

```bash
docker push bakops224/epivizappapi:latest
```

---
