package com.NutritionalFoodInformations.NutritionalFoodInformations.services;


import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

        Double nutritionScore = null;
        double productsNutritionalScore = 0.;
        Integer productQuantityTotal = 0;


        if (products == null ) {
            return cartSynthesis;
        }

        // Computing nutritional score for all products and summing their quantities
        for (Product product : products) {

            try {
                productsNutritionalScore += nutritionalScoreService.getNutritionalInformation(product.getBarCode()).getNutritionScore() * product.getQuantity();
                productQuantityTotal += product.getQuantity();
            } catch (JSONException e) {
                log.error("Product " + product.getBarCode() + " not found !");
            }
        }

        // if cart contains products that are not found
        if (productQuantityTotal != 0) {
            double result = productsNutritionalScore / productQuantityTotal;

            // taking only two decimals digits form the score
            BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
            nutritionScore = bd.doubleValue();

            cartSynthesis.setNutritionScore(nutritionScore);
            cartSynthesis.setClasse();

        }


        return cartSynthesis;
    }

}
