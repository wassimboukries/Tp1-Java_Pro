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
        if (nutritionScore == null) {
            setClasse(null);
            return;
        }
        Double nutritionScore = getNutritionScore();
        if (-10 <= nutritionScore && nutritionScore <= -1) {
            setClasse("Trop Bon");
        } else if (-1 < nutritionScore && nutritionScore <= 2) {
            setClasse("Bon");
        }else if (2 < nutritionScore && nutritionScore <= 10) {
            setClasse("Mangeable");
        }else if (10 < nutritionScore && nutritionScore <= 18) {
            setClasse("Mouai");
        }else if (18 < nutritionScore && nutritionScore <= 40) {
            setClasse("Degueu");
        }
    }
}
