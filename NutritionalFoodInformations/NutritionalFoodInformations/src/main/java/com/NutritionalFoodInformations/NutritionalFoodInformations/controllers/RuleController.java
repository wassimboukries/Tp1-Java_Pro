package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;

import com.NutritionalFoodInformations.NutritionalFoodInformations.entity.Rule;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.RuleService;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.RuleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RuleController {

    @Autowired
    RuleServiceImpl ruleServiceImpl;

    @GetMapping("/rules/{element}/{value}")
    public Rule GetRule(@PathVariable("element") String element, @PathVariable("value") double value)
    {
        return ruleServiceImpl.fetchRuleByValue(element, value);
    }

}
