package com.populationusa.PopulationAPI.model;

public class PopulationEntity {

    private int year;
    private String idNation;

    public PopulationEntity(int year, String idNation) {
        this.year = year;
        this.idNation = idNation;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIdNation() {
        return idNation;
    }

    public void setIdNation(String idNation) {
        this.idNation = idNation;
    }
}
