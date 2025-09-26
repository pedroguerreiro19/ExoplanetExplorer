package com.exoplanetexplorer.ExoplanetExplorer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/planets")
public class ExoplanetExplorerController {
    private APIService apiService;

    public ExoplanetExplorerController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/all")
    public String getPlanets() {
        return apiService.getAllPlanets();
    }
    
    
    
}
