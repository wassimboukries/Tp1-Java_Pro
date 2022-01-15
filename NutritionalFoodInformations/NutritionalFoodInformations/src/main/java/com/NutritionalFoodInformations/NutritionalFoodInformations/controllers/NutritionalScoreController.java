package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.NutritionalScoreService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class NutritionalScoreController {

    NutritionalScoreService nutritionalScoreService;

    @Autowired
    private NutritionalScoreController(NutritionalScoreService nutritionalScoreService) {
        this.nutritionalScoreService = nutritionalScoreService;
    }

    @GetMapping("/product/{barCode}")
    public ResponseEntity<NutritionalInformations> getNutritionalScore(@PathVariable String barCode) throws UnsupportedEncodingException, ParseException {
        return ResponseEntity.ok(nutritionalScoreService.getNutritionalInformation(barCode));
    }
    @GetMapping("/api")
    public ResponseEntity getNutritionalScore()
    {
        //ghat3yt l api dyal open food facts

        // hadi dyal l7sab

        return ResponseEntity.ok("10");
    }

}
