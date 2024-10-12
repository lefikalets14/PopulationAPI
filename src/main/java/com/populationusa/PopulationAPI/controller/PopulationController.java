package com.populationusa.PopulationAPI.controller;

import com.populationusa.PopulationAPI.model.PopulationData;
import com.populationusa.PopulationAPI.model.PopulationEntity;
import com.populationusa.PopulationAPI.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/population")
public class PopulationController {

    @Autowired
    private PopulationService populationService;

    @GetMapping("/population/identity")
    public PopulationData getPopulationByYearAndId(@RequestBody PopulationEntity populationEntity) {
        Optional<PopulationData> populationData = populationService.getPopulationByYearAndNation(populationEntity);
        return populationData.orElse(null);
    }

    @GetMapping("/total")
    public long getTotalPopulation() {
        return populationService.getTotalPopulation();
    }

    @GetMapping
    public List<PopulationData> getAllPopulationData() {
        return populationService.getAllPopulationData();
    }
}
