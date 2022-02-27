package com.NutritionalFoodInformations.NutritionalFoodInformations.repository;

import com.NutritionalFoodInformations.NutritionalFoodInformations.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {

    /**
     *  finding the score of a particular value of a particular nutritional element
     * @param elementNutritional the name of the nutritional element
     * @param value the numerical value of the nutritional element in the product
     * @return
     */
    Rule findTopByNameAndMinboundLessThanOrderByMinboundDesc(String elementNutritional, double value);
}
