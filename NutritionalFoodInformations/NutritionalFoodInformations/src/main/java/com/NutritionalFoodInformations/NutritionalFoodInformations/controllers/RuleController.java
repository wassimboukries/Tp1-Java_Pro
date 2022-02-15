package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;

import com.NutritionalFoodInformations.NutritionalFoodInformations.entity.Rule;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.RuleService;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.RuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleController {

    @Autowired
    RuleServiceImpl ruleServiceImpl;

    @PostMapping("/rules/{element}/{value}")
    public List<Rule> GetRules(@PathVariable("element") String element, @PathVariable("value") double value)
    {
        return ruleServiceImpl.fetchRuleByValue(element, value);
    }

}
