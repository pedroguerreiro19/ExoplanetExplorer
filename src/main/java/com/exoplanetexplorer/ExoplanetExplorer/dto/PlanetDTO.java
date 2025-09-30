package com.exoplanetexplorer.ExoplanetExplorer.dto;


public class PlanetDTO {
    private String name;
    private Double radius; // in Earth radii
    private Double mass;   // in Earth masses
    private Double distance; // in parsecs
    private String starType; 
    private Integer discoveryYear; 
    private String discoveryMethod;
    private Double temperature; // in Kelvin
    private Integer systemPlanets;
    private Integer systemStars;
    private String category; 
    
 public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getDiscoveryYear() {
        return discoveryYear;
    }

    public void setDiscoveryYear(Integer discoveryYear) {
        this.discoveryYear = discoveryYear;
    }

    public String getDiscoveryMethod() {
        return discoveryMethod;
    }

    public void setDiscoveryMethod(String discoveryMethod) {
        this.discoveryMethod = discoveryMethod;
    }

    public Integer getSystemPlanets() {
        return systemPlanets;
    }

    public void setSystemPlanets(Integer systemPlanets) {
        this.systemPlanets = systemPlanets;
    }

    public Integer getSystemStars() {
        return systemStars;
    }

    public void setSystemStars(Integer systemStars) {
        this.systemStars = systemStars;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}