package com.NutritionalFoodInformations.NutritionalFoodInformations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalInformations {

    @Id
    private int id;

    private String barCode;

    private String name;

    private Double nutritionScore;

    private String classe;

    private String color;

    public void setClassAndColor()
    {
        Double nutritionScore = getNutritionScore();
        if (-10 <= nutritionScore && nutritionScore <= -1) {
            setClasse("Trop Bon");
            setColor("green");
        } else if (0 <= nutritionScore && nutritionScore <= 2) {
            setClasse("Bon");
            setColor("light green");
        }else if (3 <= nutritionScore && nutritionScore <= 10) {
            setClasse("Mangeable");
            setColor("yellow");
        }else if (11 <= nutritionScore && nutritionScore <= 18) {
            setClasse("Mouai");
            setColor("orange");
        }else if (19 <= nutritionScore && nutritionScore <= 40) {
            setClasse("Degueu");
            setColor("red");
        }
    }
}
