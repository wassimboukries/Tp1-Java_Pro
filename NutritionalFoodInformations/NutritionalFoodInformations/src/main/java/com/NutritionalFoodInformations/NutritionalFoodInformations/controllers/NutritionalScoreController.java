package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NutritionalScoreController {

    @GetMapping("/{barCode}")
    public int getNutritionalScore(@PathVariable int barCode)
    {
        //ghat3yt l api dyal open food facts

        // hadi dyal l7sab

        return 6;
    }
    @GetMapping("/api")
    public ResponseEntity getNutritionalScore()
    {
        //ghat3yt l api dyal open food facts

        // hadi dyal l7sab

        return ResponseEntity.ok("10");
    }

}
