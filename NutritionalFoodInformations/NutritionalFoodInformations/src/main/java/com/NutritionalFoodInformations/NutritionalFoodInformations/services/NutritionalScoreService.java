package com.NutritionalFoodInformations.NutritionalFoodInformations.services;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import org.json.simple.JSONObject;
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

    public NutritionalInformations getNutritionalInformation(String barCode) throws ParseException {

        //ghat3yt l api dyal open food facts

        String host = "https://fr.openfoodfacts.org/api/v0/produit/";
        // Format query for preventing encoding problems
        String query = String.format("s=%s",
                URLEncoder.encode(barCode, StandardCharsets.UTF_8));

        RestTemplate restTemplate = new RestTemplate();
        String Response = restTemplate.getForObject(host + query + ".json", String.class);

        JSONParser parser = new JSONParser();
        Object resObj = parser.parse(Response);
        JSONObject resJson = (JSONObject) resObj;
        JSONObject product = (JSONObject) resJson.get("product");
        JSONObject ecoscore_dataObj = (JSONObject) product.get("ecoscore_data");
        JSONObject agribalyseObj = (JSONObject) ecoscore_dataObj.get("agribalyse");
        String name = (String) agribalyseObj.get("name_fr");

        JSONObject nutriments = (JSONObject) product.get("nutriments");
        Long energy = (Long) nutriments.get("energy_100g");
        Double fat = (Double) nutriments.get("saturated-fat_100g");
        Double sugar = (Double) nutriments.get("sugars_100g");
        Double salt = (Double) nutriments.get("salt_100g");

        nutritionalInformations.setBarCode(barCode);
        nutritionalInformations.setName(name);

        // hadi dyal l7sab
        nutritionalInformations.setNutritionScore(computeNutritionalScore(energy, fat, sugar, salt));

        return nutritionalInformations;
    }

    private Double computeNutritionalScore(Long energy, Double fat, Double sugar, Double salt) {
        return 0.0;
    }

}