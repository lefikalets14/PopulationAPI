package com.populationusa.PopulationAPI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.populationusa.PopulationAPI.model.PopulationData;
import com.populationusa.PopulationAPI.model.PopulationEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PopulationService {

    private static final Logger LOGGER = Logger.getLogger(PopulationService.class.getName());

    private List<PopulationData> fetchPopulationData() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = "https://datausa.io/api/data?drilldowns=Nation&measures=Population";
            String rawJson = restTemplate.getForObject(url, String.class);
            LOGGER.log(Level.INFO, "Raw JSON Response: {0}", rawJson);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(rawJson);
            JsonNode dataNode = rootNode.path("data");

            PopulationData[] populationDataArray = mapper.treeToValue(dataNode, PopulationData[].class);
            return Arrays.asList(populationDataArray);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching population data", e);
        }
        return List.of();
    }

    public List<PopulationData> getAllPopulationData() {
        return fetchPopulationData();
    }

    public Optional<PopulationData> getPopulationByYearAndNation(PopulationEntity entity) {
        return fetchPopulationData().stream()
                .filter(data -> data.getIdYear() == entity.getYear() && data.getIdNation().equals(entity.getIdNation()))
                .findFirst();
    }

    public long getTotalPopulation() {
        return fetchPopulationData().stream()
                .mapToLong(PopulationData::getPopulation)
                .sum();
    }
}
