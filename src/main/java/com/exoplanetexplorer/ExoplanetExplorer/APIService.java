package com.exoplanetexplorer.ExoplanetExplorer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {
    private static final String NASA_API_URL = "https://exoplanetarchive.ipac.caltech.edu/TAP/sync";
    
    private final RestTemplate restTemplate;

    public APIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getAllPlanets() {
        String query = "select+pl_name,pl_rade,pl_bmasse,sy_dist,st_spectype,disc_year+from+pscomppars";
        String url = NASA_API_URL + "?query=" + query + "&format=json";

        return restTemplate.getForObject(url, String.class);
    }
}
