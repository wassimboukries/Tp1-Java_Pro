package com.NutritionalFoodInformations.NutritionalFoodInformations.beans;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import org.springframework.context.annotation.Bean;

public class NutritionalInformationsDto {

    @Bean
    public NutritionalInformations nutritionalInformations() {
        return new NutritionalInformations();
    }
}
