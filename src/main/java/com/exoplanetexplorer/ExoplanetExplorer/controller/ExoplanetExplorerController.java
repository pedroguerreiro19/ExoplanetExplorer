package com.exoplanetexplorer.ExoplanetExplorer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exoplanetexplorer.ExoplanetExplorer.dto.PlanetDTO;
import com.exoplanetexplorer.ExoplanetExplorer.service.APIService;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/planets")
public class ExoplanetExplorerController {
    private APIService apiService;

    public ExoplanetExplorerController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlanetDTO>> getPlanets() {
        return ResponseEntity.ok(apiService.getAllPlanets());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PlanetDTO>> filterPlanets(@RequestParam Map<String, String> filters) {
        return ResponseEntity.ok(apiService.getFilteredPlanets(filters));
    }
    
}
