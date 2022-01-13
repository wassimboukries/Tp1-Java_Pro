package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class NutritionalScoreController {

    @GetMapping("/product/{barCode}")
    public String getNutritionalScore(@PathVariable Long barCode) throws UnsupportedEncodingException, ParseException {
        //ghat3yt l api dyal open food facts
        String host = "https://fr.openfoodfacts.org/api/v0/produit/";
        // Format query for preventing encoding problems
        String s = barCode.toString();
        String query = String.format("s=%s",
                URLEncoder.encode(s, "UTF-8"));

        RestTemplate restTemplate = new RestTemplate();
        String Response = restTemplate.getForObject(host + query + ".json", String.class);

        JSONParser parser = new JSONParser();
        Object resObj = parser.parse(Response);
        JSONObject resJson = (JSONObject) resObj;
        JSONObject product = (JSONObject) resJson.get("product");
        JSONObject ecoscore_dataObj = (JSONObject) product.get("ecoscore_data");
        JSONObject agribalyseObj = (JSONObject) ecoscore_dataObj.get("agribalyse");
        String name = (String) agribalyseObj.get("name_en");
        // hadi dyal l7sab

        return "The name of the product is : " + name;
    }
    @GetMapping("/api")
    public ResponseEntity getNutritionalScore()
    {
        //ghat3yt l api dyal open food facts

        // hadi dyal l7sab

        return ResponseEntity.ok("10");
    }

}
