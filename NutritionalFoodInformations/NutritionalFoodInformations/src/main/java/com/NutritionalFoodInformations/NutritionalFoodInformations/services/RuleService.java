package com.NutritionalFoodInformations.NutritionalFoodInformations.services;

import com.NutritionalFoodInformations.NutritionalFoodInformations.entity.Rule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RuleService {

    // Read operation
    Rule fetchRuleByValue(String elementNutritional, double value);
}
