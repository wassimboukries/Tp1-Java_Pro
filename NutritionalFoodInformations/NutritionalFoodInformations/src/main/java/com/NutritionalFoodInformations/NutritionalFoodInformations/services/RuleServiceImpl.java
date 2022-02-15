package com.NutritionalFoodInformations.NutritionalFoodInformations.services;

import com.NutritionalFoodInformations.NutritionalFoodInformations.entity.Rule;
import com.NutritionalFoodInformations.NutritionalFoodInformations.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    RuleRepository ruleRepository;

    @Override
    public List<Rule> fetchRuleByValue(String elementNutritional, double value)
    {
        return ruleRepository.findByNameAndMin_BoundGreaterThan(elementNutritional, value);
    }

}
