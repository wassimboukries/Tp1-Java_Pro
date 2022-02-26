package com.NutritionalFoodInformations.NutritionalFoodInformations.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    private String email;

    private List<Product> products;
}
