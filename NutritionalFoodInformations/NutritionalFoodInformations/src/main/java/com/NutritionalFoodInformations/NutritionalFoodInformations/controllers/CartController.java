package com.NutritionalFoodInformations.NutritionalFoodInformations.controllers;


import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.CartService;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.NutritionalScoreService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/CartSynthesis")
    public ResponseEntity<CartSynthesis> getCartSynthesis(@RequestBody Cart cart) throws UnsupportedEncodingException, ParseException {
        return ResponseEntity.ok(cartService.getCartSynthesis(cart));
    }
}
