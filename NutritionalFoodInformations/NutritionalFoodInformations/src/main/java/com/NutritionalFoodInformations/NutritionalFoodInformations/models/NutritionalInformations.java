package com.NutritionalFoodInformations.NutritionalFoodInformations.models;

import org.springframework.stereotype.Component;

@Component
public class NutritionalInformations {

    private static int nextId = 0;
    private final int id;

    private String barCode;

    private String name;

    private Double nutritionScore;

    public NutritionalInformations() {
        setBarCode("7622210449283");
        id = nextId++;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNutritionScore(Double nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public Integer getId() {
        return id;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public Double getNutritionScore() {
        return nutritionScore;
    }

    @Override
    public String toString() {
        return "NutritionalInformations{" +
                "id=" + id +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", nutritionScore='" + nutritionScore + '\'' +
                '}';
    }
}
