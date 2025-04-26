package com.example.epivizappapi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.epivizappapi.dto.DataSummaryDTO;
import com.example.epivizappapi.model.Data;
import com.example.epivizappapi.repository.CalendrierRepository;
import com.example.epivizappapi.repository.DataRepository;

@Service
public class DataService {

        private static final Logger logger = LoggerFactory.getLogger(DataService.class);

        @Autowired
        private DataRepository dataRepository;

        @Autowired
        private CalendrierRepository calendrierRepository;

        public Data saveData(Data data) {
                if (data.getCalendrier() == null || data.getCalendrier().getId() <= 0) {
                        logger.error("Calendrier must have a valid ID.");
                        throw new IllegalArgumentException("Calendrier must have a valid ID.");
                }
                Data savedData = dataRepository.save(data);
                logger.info("Data saved: {}", savedData);
                return savedData;
        }

        public DataSummaryDTO calculateTotals() {
                return calculateTotals(false);
        }

        public DataSummaryDTO calculateTotals(boolean filterZeroValues) {
                List<Data> allData = filterZeroValues ? getFilteredData() : dataRepository.findAll();

                long totalCases = allData.stream()
                                .mapToLong(Data::getTotalCases)
                                .sum();

                long totalDeaths = allData.stream()
                                .mapToLong(Data::getTotalDeaths)
                                .sum();

                long newCases = allData.stream()
                                .mapToLong(Data::getNewCases)
                                .sum();

                long newDeaths = allData.stream()
                                .mapToLong(Data::getNewDeaths)
                                .sum();

                logger.info("Calculated totals: totalCases={}, totalDeaths={}, newCases={}, newDeaths={}",
                                totalCases, totalDeaths, newCases, newDeaths);

                return new DataSummaryDTO(totalCases, totalDeaths, newCases, newDeaths);
        }

        public List<Data> getFilteredData() {
                List<Data> filteredData = dataRepository.findAll().stream()
                                .filter(data -> data.getLocalisation() != null && data.getLocalisation().getId() != null
                                                && data.getLocalisation().getId() != 0)
                                .filter(data -> data.getTotalCases() > 0 ||
                                                data.getTotalDeaths() > 0 ||
                                                data.getNewCases() > 0 ||
                                                data.getNewDeaths() > 0)
                                .collect(Collectors.toList());
                logger.info("Filtered data: {}", filteredData);
                return filteredData;
        }

        public List<Data> getDataByLocalisation(Long localisationId) {
                return getDataByLocalisation(localisationId, false);
        }

        public List<Data> getDataByLocalisation(Long localisationId, boolean filterZeroValues) {
                List<Data> dataList = dataRepository.findAll().stream()
                                .filter(data -> data.getLocalisation() != null &&
                                                data.getLocalisation().getId().equals(localisationId))
                                .collect(Collectors.toList());
                if (filterZeroValues) {
                        dataList = dataList.stream()
                                        .filter(data -> data.getLocalisation() != null
                                                        && data.getLocalisation().getId() != null
                                                        && data.getLocalisation().getId() != 0)
                                        .filter(data -> data.getTotalCases() > 0 ||
                                                        data.getTotalDeaths() > 0 ||
                                                        data.getNewCases() > 0 ||
                                                        data.getNewDeaths() > 0)
                                        .collect(Collectors.toList());
                }
                logger.info("Data by localisation {}: {}", localisationId, dataList);
                return dataList;
        }

        public List<Data> getDataByPandemie(Long pandemieId) {
                return getDataByPandemie(pandemieId, false);
        }

        public List<Data> getDataByPandemie(Long pandemieId, boolean filterZeroValues) {
                List<Data> dataList = dataRepository.findAll().stream()
                                .filter(data -> data.getPandemie() != null &&
                                                data.getPandemie().getId().equals(pandemieId))
                                .collect(Collectors.toList());
                if (filterZeroValues) {
                        dataList = dataList.stream()
                                        .filter(data -> data.getLocalisation() != null
                                                        && data.getLocalisation().getId() != null
                                                        && data.getLocalisation().getId() != 0)
                                        .filter(data -> data.getTotalCases() > 0 ||
                                                        data.getTotalDeaths() > 0 ||
                                                        data.getNewCases() > 0 ||
                                                        data.getNewDeaths() > 0)
                                        .collect(Collectors.toList());
                }
                logger.info("Data by pandemie {}: {}", pandemieId, dataList);
                return dataList;
        }

        public Map<Long, DataSummaryDTO> calculateTotalsByLocalisation() {
                return calculateTotalsByLocalisation(false);
        }

        public Map<Long, DataSummaryDTO> calculateTotalsByLocalisation(boolean filterZeroValues) {
                Map<Long, DataSummaryDTO> totalsByLocalisation = new HashMap<>();
                List<Long> localisationIds = getAllLocalisationIds();
                for (Long locId : localisationIds) {
                        List<Data> localisationData = getDataByLocalisation(locId, filterZeroValues);
                        if (filterZeroValues && localisationData.isEmpty()) {
                                continue;
                        }
                        long totalCases = localisationData.stream()
                                        .mapToLong(Data::getTotalCases)
                                        .sum();
                        long totalDeaths = localisationData.stream()
                                        .mapToLong(Data::getTotalDeaths)
                                        .sum();
                        long newCases = localisationData.stream()
                                        .mapToLong(Data::getNewCases)
                                        .sum();
                        long newDeaths = localisationData.stream()
                                        .mapToLong(Data::getNewDeaths)
                                        .sum();
                        totalsByLocalisation.put(locId,
                                        new DataSummaryDTO(totalCases, totalDeaths, newCases, newDeaths));
                }
                logger.info("Calculated totals by localisation: {}", totalsByLocalisation);
                return totalsByLocalisation;
        }

        public Map<Long, DataSummaryDTO> calculateTotalsByPandemie() {
                return calculateTotalsByPandemie(false);
        }

        public Map<Long, DataSummaryDTO> calculateTotalsByPandemie(boolean filterZeroValues) {
                Map<Long, DataSummaryDTO> totalsByPandemie = new HashMap<>();
                List<Long> pandemieIds = getAllPandemieIds();
                for (Long panId : pandemieIds) {
                        List<Data> pandemieData = getDataByPandemie(panId, filterZeroValues);
                        if (filterZeroValues && pandemieData.isEmpty()) {
                                continue;
                        }
                        long totalCases = pandemieData.stream()
                                        .mapToLong(Data::getTotalCases)
                                        .sum();
                        long totalDeaths = pandemieData.stream()
                                        .mapToLong(Data::getTotalDeaths)
                                        .sum();
                        long newCases = pandemieData.stream()
                                        .mapToLong(Data::getNewCases)
                                        .sum();
                        long newDeaths = pandemieData.stream()
                                        .mapToLong(Data::getNewDeaths)
                                        .sum();
                        totalsByPandemie.put(panId, new DataSummaryDTO(totalCases, totalDeaths, newCases, newDeaths));
                }
                logger.info("Calculated totals by pandemie: {}", totalsByPandemie);
                return totalsByPandemie;
        }

        public List<Data> getFilteredDataByLocalisationAndPandemie(Long localisationId, Long pandemieId,
                        boolean filterZeroValues) {
                List<Data> dataList = dataRepository.findAll().stream()
                                .filter(data -> data.getLocalisation() != null &&
                                                data.getLocalisation().getId().equals(localisationId) &&
                                                data.getPandemie() != null &&
                                                data.getPandemie().getId().equals(pandemieId))
                                .collect(Collectors.toList());
                if (filterZeroValues) {
                        dataList = dataList.stream()
                                        .filter(data -> data.getLocalisation() != null
                                                        && data.getLocalisation().getId() != null
                                                        && data.getLocalisation().getId() != 0)
                                        .filter(data -> data.getTotalCases() > 0 ||
                                                        data.getTotalDeaths() > 0 ||
                                                        data.getNewCases() > 0 ||
                                                        data.getNewDeaths() > 0)
                                        .collect(Collectors.toList());
                }
                logger.info("Filtered data by localisation {} and pandemie {}: {}", localisationId, pandemieId,
                                dataList);
                return dataList;
        }

        private List<Long> getAllLocalisationIds() {
                List<Long> localisationIds = dataRepository.findAll().stream()
                                .filter(data -> data.getLocalisation() != null && data.getLocalisation().getId() != null
                                                && data.getLocalisation().getId() != 0)
                                .map(data -> data.getLocalisation().getId())
                                .distinct()
                                .collect(Collectors.toList());
                logger.info("All localisation IDs: {}", localisationIds);
                return localisationIds;
        }

        private List<Long> getAllPandemieIds() {
                List<Long> pandemieIds = dataRepository.findAll().stream()
                                .filter(data -> data.getPandemie() != null)
                                .map(data -> data.getPandemie().getId())
                                .distinct()
                                .collect(Collectors.toList());
                logger.info("All pandemie IDs: {}", pandemieIds);
                return pandemieIds;
        }
}
