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
}
