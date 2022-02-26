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
}
