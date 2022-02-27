package com.NutritionalFoodInformations.NutritionalFoodInformations.services;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.repository.RuleRepository;
import org.json.JSONException;
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

    public NutritionalInformations getNutritionalInformation(String barCode) throws ParseException, JSONException, UnsupportedEncodingException {

        String host = "https://fr.openfoodfacts.org/api/v0/produit/";
        // Format query for preventing encoding problems
        String query = String.format("s=%s", URLEncoder.encode(barCode, String.valueOf(StandardCharsets.UTF_8)));

        RestTemplate restTemplate = new RestTemplate();
        String Response = restTemplate.getForObject(host + query + ".json", String.class);

        // Parse the response into a JSON format for better handling
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonResponse = new JSONObject(jsonParser.parse(Response).toString());

        try
        {
            JSONObject product = jsonResponse.getJSONObject("product");
            String name = product.getJSONObject("ecoscore_data").getJSONObject("agribalyse").getString("name_fr");

            nutritionalInformations.setBarCode(barCode);
            nutritionalInformations.setName(name);
            // Compute the nutritional score (as the substraction of the negative score from the positive one)
            nutritionalInformations.setNutritionScore(computeNutritionalScore(jsonResponse));
            nutritionalInformations.setClassAndColor();
        }
        catch (JSONException e)
        {
            System.out.println("Product " + barCode + " not found !");
            throw new JSONException("Product " + barCode + " not found !");
        }

        return nutritionalInformations;
    }

    public Double computeNutritionalScore(JSONObject jsonResponse)
    {
        JSONObject nutriments = jsonResponse.getJSONObject("product").getJSONObject("nutriments");

        // getting the infos needed for the negative score
        Double energy = nutriments.getDouble("energy_100g");
        Double fat = nutriments.getDouble("saturated-fat_100g");
        Double sugar = nutriments.getDouble("sugars_100g");
        Double salt = nutriments.getDouble("salt_100g");

        // getting the infos needed for the positive score
        Double fiber = nutriments.getDouble("fiber_100g");
        Double proteins = nutriments.getDouble("proteins_100g");

        return computeNegativeNutritionalScore(energy, fat, sugar, salt) - computePositiveNutritionalScore(fiber, proteins);
    }

    public Double computeNegativeNutritionalScore(Double energy, Double fat, Double sugar, Double salt) {
        Double negativeScore = 0.;

        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("energy_100g", energy).getPoints();
        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("saturated-fat_100g", fat).getPoints();
        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("sugars_100g", sugar).getPoints();
        negativeScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("saturated-fat_100g", salt).getPoints();

        return negativeScore;
    }

    public Double computePositiveNutritionalScore(Double fiber, Double proteins) {
        Double positiveScore = 0.;

        positiveScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("fiber_100g", fiber).getPoints();
        positiveScore += ruleRepository.findTopByNameAndMinboundLessThanOrderByMinboundDesc("proteins_100g", proteins).getPoints();

        return positiveScore;
    }
}
