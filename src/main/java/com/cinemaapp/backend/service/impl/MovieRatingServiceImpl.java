package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.MovieRatingService;
import com.cinemaapp.backend.service.domain.response.MovieRatingsResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    private final WebClient webClient;

    @Value("${omdb.api.key}")
    private String apiKey;

    public MovieRatingServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://www.omdbapi.com").build();
    }

    @Override
    public MovieRatingsResponse getMovieRatings(String title) {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("t", title)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractRatings(response);
    }

    private MovieRatingsResponse extractRatings(String jsonResponse) {
        MovieRatingsResponse ratings = new MovieRatingsResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            JsonNode ratingsArray = root.get("Ratings");

            if (ratingsArray != null && ratingsArray.isArray()) {
                for (JsonNode rating : ratingsArray) {
                    String source = rating.get("Source").asText();
                    String value = rating.get("Value").asText();

                    if ("Internet Movie Database".equals(source)) {
                        ratings.setImdbRating(value);
                    } else if ("Rotten Tomatoes".equals(source)) {
                        ratings.setRottenTomatoesRating(value);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ratings.setImdbRating("Undefined");
            ratings.setRottenTomatoesRating("Undefined");
        }

        return ratings;
    }
}
