package com.NutritionalFoodInformations.NutritionalFoodInformations.tools;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ApiRestTemplate {

    @Autowired
    TestRestTemplate testRestTemplate;

    @SneakyThrows
    public <T>ResponseEntity<T> getRequestToComputeScore(String url, Class<T> responseType)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(asList(APPLICATION_JSON));

        HttpEntity request = new HttpEntity<>(null, headers);

        return testRestTemplate.exchange(url, HttpMethod.GET, request, responseType);
    }
}
