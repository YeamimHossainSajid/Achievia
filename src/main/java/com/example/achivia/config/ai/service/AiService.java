package com.example.achivia.config.ai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    @Autowired
    private CohereService aiService;

    public String titleFromDescription(String description){
        String title= aiService.Ai(description+"             make a short title from it");
        return title;
    }
}
