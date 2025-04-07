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
```

Vous pouvez copier ce contenu directement dans votre fichier README.# epiviz-spring-back
