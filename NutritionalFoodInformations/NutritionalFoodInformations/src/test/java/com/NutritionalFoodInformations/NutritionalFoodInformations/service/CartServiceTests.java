package com.NutritionalFoodInformations.NutritionalFoodInformations.service;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import com.NutritionalFoodInformations.NutritionalFoodInformations.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
public class CartServiceTests{

    @Autowired
    CartService cartService;

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
                    asList(new Product("7622210449283", 8)
                            , new Product("4125796267741", 2))))
            .build();

    @Test
    void shouldGetCorrectCartSynthesis() throws UnsupportedEncodingException, ParseException {

        CartSynthesis cartSynthesis = cartService.getCartSynthesis(cart1);

        assertThat(cartSynthesis.getNutritionScore()).isEqualTo(5.29);
        assertThat(cartSynthesis.getClasse()).isEqualTo("Mangeable");
    }

    @Test
    void shouldSendExceptionIfAProductDoesntExist() {

        //CartSynthesis cartSynthesis = cartService.getCartSynthesis(cart1);

        assertThatExceptionOfType(new JSONException("At least one product is not found !").getClass())
                .isThrownBy(() -> {
                    CartSynthesis cartSynthesis = cartService.getCartSynthesis(cart2);
                    log.error(String.valueOf(cartSynthesis.getNutritionScore()));
                });
    }

}