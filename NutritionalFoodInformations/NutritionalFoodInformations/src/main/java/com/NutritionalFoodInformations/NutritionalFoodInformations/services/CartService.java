package com.NutritionalFoodInformations.NutritionalFoodInformations.services;


import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CartService {

    @Autowired
    NutritionalScoreService nutritionalScoreService;

    @Autowired
    CartSynthesis cartSynthesis;

    public CartSynthesis getCartSynthesis(Cart cart) throws JSONException, UnsupportedEncodingException, ParseException {

        List<Product> products = cart.getProducts();

        Double cartNutritionScore = 0.;
        int productQuantityTotal = 0;

        for (Product product : products)
        {
            Integer productQuantity = product.getQuantity();
            productQuantityTotal +=  products.size() * productQuantity;
            for (int i = 0; i < productQuantity; i++)
            {
                cartNutritionScore += nutritionalScoreService.getNutritionalInformations(product.getBarCode()).getNutritionScore();
            }
        }

        double result = cartNutritionScore / productQuantityTotal;

        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        double nutritionScore = bd.doubleValue();

        cartSynthesis.setNutritionScore(nutritionScore);
        cartSynthesis.setClasse();

        return cartSynthesis;
    }

}
