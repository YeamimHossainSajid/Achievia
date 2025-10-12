package com.example.achivia.config.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CohereService {

    @Value("${cohere.api.key}")
    private String cohereApiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CohereService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String Ai(String question) {
        String response = "I'm not sure how to answer that.";


        if (response.equals("I'm not sure how to answer that.")) {
            try {
                String url = "https://api.cohere.ai/v1/generate";
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + cohereApiKey);
                headers.set("Content-Type", "application/json");

                ObjectNode requestBody = objectMapper.createObjectNode();
                requestBody.put("prompt", question);
                requestBody.put("max_tokens", 250);
                requestBody.put("temperature", 0.7);

                HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

                if (responseEntity.getBody() != null) {
                    JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());

                    System.out.println("API Response: " + responseEntity.getBody());

                    JsonNode generationsNode = responseJson.path("generations");
                    if (generationsNode.isArray() && generationsNode.size() > 0) {
                        response = generationsNode.get(0).path("text").asText("No response from Cohere API.");
                    } else {
                        response = "No valid response from Cohere API.";
                    }
                } else {
                    response = "No response from API.";
                }
            } catch (Exception e) {
                response = "An error occurred while processing your request: " + e.getMessage();
            }
        }

        return response;
    }


}