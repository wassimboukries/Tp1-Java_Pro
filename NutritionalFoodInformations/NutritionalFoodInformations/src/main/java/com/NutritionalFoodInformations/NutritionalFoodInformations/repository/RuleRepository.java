package com.NutritionalFoodInformations.NutritionalFoodInformations.repository;

import com.NutritionalFoodInformations.NutritionalFoodInformations.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {

    List<Rule> findByNameAndMin_BoundGreaterThan(String elementNutritional, double valeur);
}
