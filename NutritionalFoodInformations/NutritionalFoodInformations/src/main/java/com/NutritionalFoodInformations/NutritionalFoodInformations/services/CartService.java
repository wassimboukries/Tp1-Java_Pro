package com.NutritionalFoodInformations.NutritionalFoodInformations.services;


import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
public class CartService {

    @Autowired
    NutritionalScoreService nutritionalScoreService;

    @Autowired
    CartSynthesis cartSynthesis;

    public CartSynthesis getCartSynthesis(Cart cart) throws JSONException, UnsupportedEncodingException, ParseException {

        List<Product> products = cart.getProducts();

        if (products.isEmpty())
            return cartSynthesis;

        Double productsNutritionalScore = 0.;
        Integer productQuantityTotal = 0;

        for (Product product : products) {
            productQuantityTotal += product.getQuantity();

            try {
                productsNutritionalScore += nutritionalScoreService.getNutritionalInformations(product.getBarCode()).getNutritionScore() * product.getQuantity();
            } catch (JSONException e) {
                log.error("Product " + product.getBarCode() + " not found !");
                throw new JSONException("Product " + product.getBarCode() + " not found !");
            }
        }

        double result = productsNutritionalScore / productQuantityTotal;

        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        double nutritionScore = bd.doubleValue();

        cartSynthesis.setNutritionScore(nutritionScore);
        cartSynthesis.setClasse();

        return cartSynthesis;
    }

}
