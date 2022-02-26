package com.NutritionalFoodInformations.NutritionalFoodInformations.services;


import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Cart;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.CartSynthesis;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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

        cartSynthesis.setNutritionScore(Double.valueOf(new DecimalFormat("##.##").format(productsNutritionalScore / productQuantityTotal)));
        setCartClass(cartSynthesis);

        return cartSynthesis;
    }

    public void setCartClass(CartSynthesis cartSynthesis)
    {
        Double nutritionScore = cartSynthesis.getNutritionScore();
        if (-10 <= nutritionScore && nutritionScore <= -1) {
            cartSynthesis.setClasse("Trop Bon");
        } else if (-1 < nutritionScore && nutritionScore <= 2) {
            cartSynthesis.setClasse("Bon");
        }else if (2 < nutritionScore && nutritionScore <= 10) {
            cartSynthesis.setClasse("Mangeable");
        }else if (10 < nutritionScore && nutritionScore <= 18) {
            cartSynthesis.setClasse("Mouai");
        }else if (18 < nutritionScore && nutritionScore <= 40) {
            cartSynthesis.setClasse("Degueu");
        }
    }
}
