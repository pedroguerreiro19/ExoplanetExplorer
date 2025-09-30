package com.exoplanetexplorer.ExoplanetExplorer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exoplanetexplorer.ExoplanetExplorer.dto.PlanetDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class APIService {
    private static final String NASA_API_URL = "https://exoplanetarchive.ipac.caltech.edu/TAP/sync";
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public APIService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    private PlanetDTO mapToPlanetDTO(JsonNode node) {
        PlanetDTO dto = new PlanetDTO();
        dto.setName(node.path("pl_name").asText());
        dto.setRadius(node.path("pl_rade").isMissingNode() ? null : node.path("pl_rade").asDouble());
        dto.setMass(node.path("pl_bmasse").isMissingNode() ? null : node.path("pl_bmasse").asDouble());
        dto.setDistance(node.path("sy_dist").isMissingNode() ? null : node.path("sy_dist").asDouble());
        dto.setStarType(node.path("st_spectype").asText(null));
        dto.setTemperature(node.path("pl_eqt").isMissingNode() ? null : node.path("pl_eqt").asDouble());
        dto.setDiscoveryYear(node.path("disc_year").isMissingNode() ? null : node.path("disc_year").asInt());
        dto.setDiscoveryMethod(node.path("discoverymethod").asText(null));
        dto.setSystemPlanets(node.path("sy_pnum").isMissingNode() ? null : node.path("sy_pnum").asInt());
        dto.setSystemStars(node.path("sy_snum").isMissingNode() ? null : node.path("sy_snum").asInt());

        Double radius = dto.getRadius();
        if (radius != null) {
            if (radius < 1.5) dto.setCategory("Rocky");
            else if (radius <= 6.0) dto.setCategory("Neptune-like");
            else dto.setCategory("Gas Giant");
        }
        return dto;
    }

    private List<PlanetDTO> parseResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            List<PlanetDTO> planets = new ArrayList<>();
            for (JsonNode node : root ) {
                planets.add(mapToPlanetDTO(node));
            }
            return planets;
        } catch (Exception e) {
            throw new RuntimeException("Eror parsing response", e);
        }
    }


    public List<PlanetDTO> getAllPlanets() {
        String query = "select+pl_name,pl_rade,pl_bmasse,sy_dist,st_spectype,disc_year+from+pscomppars";
        String url = NASA_API_URL + "?query=" + query + "&format=json";

        String response = restTemplate.getForObject(url, String.class);
        return parseResponse(response);
    }

    public List<PlanetDTO> getFilteredPlanets(Map<String, String> filters) {
        String base = "select pl_name, pl_rade, pl_bmasse, sy_dist, st_spectype, disc_year, discoverymethod, pl_eqt from pscompars where 1=1";
        List<String> conditions = new ArrayList<>();

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.endsWith("_min")) {
                String field = key.replace("_min", "");
                conditions.add(field + ">=" + value);
            } else if (key.endsWith("_max")) {
                String field = key.replace("_max", "");
                conditions.add(field + "<=" + value);
            }

            else if (key.equals("habitable") && value.equalsIgnoreCase("true")) {
                conditions.add("pl_rade between 0.8 and 1.5");
                conditions.add("pl_bmasse between 0.5 and 5");
                conditions.add("pl_eqt between 250 and 350");
            }
            else {
                conditions.add(key + "='" + value + "'");
            }
        }
        
        String finalQuery = base;
        if (!conditions.isEmpty()) {
            finalQuery += " and " + String.join(" and ", conditions);
        }

        String url = NASA_API_URL + "?query=" + finalQuery.replace(" ", "+") + "&format=json";
        String response = restTemplate.getForObject(url, String.class);
        return parseResponse(response);
        }
}
