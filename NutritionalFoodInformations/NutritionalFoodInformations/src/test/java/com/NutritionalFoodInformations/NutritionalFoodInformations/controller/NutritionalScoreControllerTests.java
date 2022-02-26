package com.NutritionalFoodInformations.NutritionalFoodInformations.controller;

import com.NutritionalFoodInformations.NutritionalFoodInformations.models.NutritionalInformations;
import com.NutritionalFoodInformations.NutritionalFoodInformations.tools.ApiRestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NutritionalScoreControllerTests {

	@Autowired
	ApiRestTemplate apiRestTemplate;

	@Test
	void souldHaveCorrectInfos() {

		ResponseEntity<NutritionalInformations> response =  apiRestTemplate.getRequestToComputeScore(
				"/product/7622210449283"
				, NutritionalInformations.class);

		assertThat(response.getStatusCode()).isEqualTo(OK);

		NutritionalInformations nutritionalInformations = response.getBody();

		assertThat(nutritionalInformations.getNutritionScore()).isEqualTo(10);
		assertThat(nutritionalInformations.getClasse()).isEqualTo("Mangeable");
		assertThat(nutritionalInformations.getColor()).isEqualTo("yellow");

	}

	@Test
	void shouldSendErrorIfProductDoesntExist() throws JSONException
	{
		ResponseEntity<NutritionalInformations> response =  apiRestTemplate.getRequestToComputeScore(
				"/product/111111"
				, NutritionalInformations.class);

		assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
	}

}
