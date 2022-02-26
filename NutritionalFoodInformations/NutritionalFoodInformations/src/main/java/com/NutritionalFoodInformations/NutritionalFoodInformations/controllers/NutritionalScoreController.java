package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.NutritionalScoreService;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class NutritionalScoreController {

    @Autowired
    NutritionalScoreService nutritionalScoreService;

    @GetMapping("/product/{barCode}")
    public ResponseEntity<NutritionalInformations> getNutritionalInformations(@PathVariable String barCode) throws JSONException, UnsupportedEncodingException, ParseException {
        return ResponseEntity.ok(nutritionalScoreService.getNutritionalInformations(barCode)); // hna nzid message
    }

}
