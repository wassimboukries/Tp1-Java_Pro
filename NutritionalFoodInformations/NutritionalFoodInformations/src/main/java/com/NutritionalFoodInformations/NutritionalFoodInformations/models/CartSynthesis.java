package com.NutritionalFoodInformations.NutritionalFoodInformations.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartSynthesis {

    private Double nutritionScore;

    private String classe;

    public void setClasse()
    {
        Double nutritionScore = getNutritionScore();
        if (-10 <= nutritionScore && nutritionScore <= -1) {
            setClasse("Trop Bon");
        } else if (0 <= nutritionScore && nutritionScore <= 2) {
            setClasse("Bon");
        }else if (3 <= nutritionScore && nutritionScore <= 10) {
            setClasse("Mangeable");
        }else if (11 <= nutritionScore && nutritionScore <= 18) {
            setClasse("Mouai");
        }else if (19 <= nutritionScore && nutritionScore <= 40) {
            setClasse("Degueu");
        }
    }
}
