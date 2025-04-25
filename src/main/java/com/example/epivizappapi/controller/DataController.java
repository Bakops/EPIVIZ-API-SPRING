package com.example.epivizappapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.epivizappapi.dto.DataSummaryDTO;
import com.example.epivizappapi.model.Calendrier;
import com.example.epivizappapi.model.Data;
import com.example.epivizappapi.repository.CalendrierRepository;
import com.example.epivizappapi.repository.DataRepository;
import com.example.epivizappapi.services.DataService;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private CalendrierRepository calendrierRepository;

    @Autowired
    private DataService dataService;

    @GetMapping
    public List<DataSummaryDTO> getAllData() {
        List<Data> dataList = dataRepository.findAllWithValidCalendrier();
        logger.info("Données récupérées : {}", dataList);
        return dataList.stream()
                .map(data -> new DataSummaryDTO(
                        data.getTotalCases(),
                        data.getTotalDeaths(),
                        data.getNewCases(),
                        data.getNewDeaths()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DataSummaryDTO getDataById(@PathVariable Long id) {
        Data data = dataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée introuvable"));
        logger.info("Donnée récupérée par ID {} : {}", id, data);
        return new DataSummaryDTO(
                data.getTotalCases(),
                data.getTotalDeaths(),
                data.getNewCases(),
                data.getNewDeaths());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        try {
            dataRepository.deleteById(id);
            logger.info("Donnée supprimée avec l'ID : {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de la donnée avec l'ID {} : {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        try {
            if (data.getCalendrier() == null || data.getCalendrier().getId() == null) {
                logger.warn("Calendrier non spécifié dans les données reçues");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            Long calendrierId = data.getCalendrier().getId();
            Calendrier calendrierExistant = calendrierRepository.findById(calendrierId)
                    .orElseThrow(
                            () -> new RuntimeException("Le calendrier avec l'id " + calendrierId + " n'existe pas"));

            data.setCalendrier(calendrierExistant);

            Data savedData = dataService.saveData(data);
            logger.info("Donnée enregistrée : {}", savedData);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
        } catch (IllegalArgumentException e) {
            logger.error("Erreur de validation des données : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (RuntimeException e) {
            logger.error("Erreur interne du serveur : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Nouvel endpoint pour récupérer les données par pandémie et localisation
    @GetMapping("/timeline/{pandemieId}/{locationId}")
    public ResponseEntity<?> getTimelineByPandemieAndLocation(@PathVariable Long pandemieId,
            @PathVariable Long locationId) {
        try {
            logger.info("Récupération des données pour la pandémie ID: {} et localisation ID: {}", pandemieId,
                    locationId);

            // Utiliser la méthode repository existante
            List<Data> filteredData = dataRepository.findByPandemieIdAndLocalisationId(pandemieId, locationId);

            logger.info("Données filtrées: {} éléments trouvés", filteredData.size());

            List<DataSummaryDTO> summaryData = filteredData.stream()
                    .map(data -> new DataSummaryDTO(
                            data.getTotalCases(),
                            data.getTotalDeaths(),
                            data.getNewCases(),
                            data.getNewDeaths()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(summaryData);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des données timeline: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des données: " + e.getMessage());
        }
    }

    // Nouvel endpoint pour récupérer les données globales d'une pandémie
    @GetMapping("/global/{pandemieId}")
    public ResponseEntity<?> getGlobalDataByPandemie(@PathVariable Long pandemieId) {
        try {
            logger.info("Récupération des données globales pour la pandémie ID: {}", pandemieId);

            // Utiliser la méthode repository existante
            List<Data> filteredData = dataRepository.findByPandemieId(pandemieId);

            logger.info("Données globales: {} éléments trouvés", filteredData.size());

            DataSummaryDTO summaryData = dataService.calculateTotalsByPandemie(false).get(pandemieId);

            return ResponseEntity.ok(summaryData);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des données globales: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des données: " + e.getMessage());
        }
    }
}
