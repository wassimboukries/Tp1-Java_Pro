package com.NutritionalFoodInformations.NutritionalFoodInformations.models;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class Cart {

    private String email;

    private List<Product> products;
}
