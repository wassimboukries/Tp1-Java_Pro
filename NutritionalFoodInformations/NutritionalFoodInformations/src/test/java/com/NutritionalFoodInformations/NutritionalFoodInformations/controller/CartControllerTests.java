package com.NutritionalFoodInformations.NutritionalFoodInformations.controller;

import com.NutritionalFoodInformations.NutritionalFoodInformations.controllers.CartController;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CartControllerTests {

    @Autowired
    CartController cartController;

    Cart cart1 = Cart
            .builder()
            .email("wassim.youssef@pascal.com")
            .products(new ArrayList<>(
                    asList(new Product("7622210449283", 3)
                            , new Product("3017760000062", 4))))
            .build();

    Cart cart2 = Cart
            .builder()
            .email("ayoub.ismail@pascal.com")
            .products(new ArrayList<>(
                    asList(new Product("7622201133337", 8)
                            , new Product("4125796267741", 2))))
            .build();

    @Test
    void shouldGetCorrectCartSynthesis() throws UnsupportedEncodingException, ParseException {

        ResponseEntity<CartSynthesis> cartSynthesis = cartController.getCartSynthesis(cart1);

        assertThat(cartSynthesis.getBody().getNutritionScore()).isEqualTo(10.57);
        assertThat(cartSynthesis.getBody().getClasse()).isEqualTo("Mouai");
    }

    @Test
    void shouldComputeScoreForOnlyExistingProducts() throws UnsupportedEncodingException, ParseException {

        ResponseEntity<CartSynthesis> cartSynthesis = cartController.getCartSynthesis(cart2);

        assertThat(cartSynthesis.getBody().getNutritionScore()).isEqualTo(9);
        assertThat(cartSynthesis.getBody().getClasse()).isEqualTo("Mangeable");

    }

}
