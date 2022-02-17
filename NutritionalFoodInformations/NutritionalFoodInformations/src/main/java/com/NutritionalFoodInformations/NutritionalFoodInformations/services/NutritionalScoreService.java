package com.NutritionalFoodInformations.NutritionalFoodInformations.services;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.repository.RuleRepository;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class NutritionalScoreService {


    @Autowired
    NutritionalInformations nutritionalInformations;

    @Autowired
    RuleRepository ruleRepository;

    public NutritionalInformations getNutritionalInformations(String barCode) throws ParseException, UnsupportedEncodingException {

        String host = "https://fr.openfoodfacts.org/api/v0/produit/";
        // Format query for preventing encoding problems
        String query = String.format("s=%s", URLEncoder.encode(barCode, String.valueOf(StandardCharsets.UTF_8)));

        RestTemplate restTemplate = new RestTemplate();
        String Response = restTemplate.getForObject(host + query + ".json", String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonResponse = new JSONObject(jsonParser.parse(Response).toString());

        String name = jsonResponse.getJSONObject("product").getJSONObject("ecoscore_data").getJSONObject("agribalyse").getString("name_fr");

        nutritionalInformations.setBarCode(barCode);
        nutritionalInformations.setName(name);
        nutritionalInformations.setNutritionScore(computeNutritionalScore(jsonResponse));
        setElementClassAndColor(nutritionalInformations);

        return nutritionalInformations;
    }

    private void setElementClassAndColor(NutritionalInformations nutritionalInformations)
    {
        Double NutritionScore = nutritionalInformations.getNutritionScore();
        if (-10 <= NutritionScore && NutritionScore <= -1) {
            nutritionalInformations.setClasse("Trop Bon");
            nutritionalInformations.setColor("green");
        } else if (0 <= NutritionScore && NutritionScore <= 2) {
            nutritionalInformations.setClasse("Bon");
            nutritionalInformations.setColor("light green");
        }else if (3 <= NutritionScore && NutritionScore <= 10) {
            nutritionalInformations.setClasse("Mangeable");
            nutritionalInformations.setColor("yellow");
        }else if (11 <= NutritionScore && NutritionScore <= 18) {
            nutritionalInformations.setClasse("Mouai");
            nutritionalInformations.setColor("orange");
        }else if (19 <= NutritionScore && NutritionScore <= 40) {
            nutritionalInformations.setClasse("Degueu");
            nutritionalInformations.setColor("red");
        }
    }
    private Double computeNutritionalScore(JSONObject jsonResponse)
    {
        JSONObject nutriments = jsonResponse.getJSONObject("product").getJSONObject("nutriments");

        Double energy = nutriments.getDouble("energy_100g");
        Double fat = nutriments.getDouble("saturated-fat_100g");
        Double sugar = nutriments.getDouble("sugars_100g");
        Double salt = nutriments.getDouble("salt_100g");

        Double fiber = nutriments.getDouble("fiber_100g");
        Double proteins = nutriments.getDouble("proteins_100g");

        return computeNegativeNutritionalScore(energy, fat, sugar, salt) - computePositiveNutritionalScore(fiber, proteins);
    }

    private Double computeNegativeNutritionalScore(Double energy, Double fat, Double sugar, Double salt) {
        Double negativeScore = 0.;

        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("energy_100g", energy).getPoints();
        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("saturated-fat_100g", fat).getPoints();
        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("sugars_100g", sugar).getPoints();
        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("saturated-fat_100g", salt).getPoints();

        return negativeScore;
    }

    private Double computePositiveNutritionalScore(Double fiber, Double proteins) {
        Double positiveScore = 0.;

        positiveScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("fiber_100g", fiber).getPoints();
        positiveScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("proteins_100g", proteins).getPoints();

        return positiveScore;
    }


}
