package com.cinemaapp.backend.integration;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ActiveMoviesFeatureTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getActiveMovies() throws Exception {
        Page<Movie> moviePage = executeGet(0, 10, "Avatar");
        assertFalse(moviePage.isEmpty());
        assertEquals(1, moviePage.getTotalElements());
        assertTrue(moviePage.isLast());
        Movie targetMovie = moviePage.getContent().get(0);
        assertEquals("Avatar", targetMovie.getTitle());
    }

    private <T> T executeGet(int page, int size, String title) throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies/active")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("title", title != null ? title : ""))
                .andExpect(MockMvcResultMatchers.status().isOk()) // assert status is 200
                .andReturn().getResponse().getContentAsString();  // get response as string
        JavaType pageType = objectMapper.getTypeFactory()
                .constructParametricType(Page.class, Movie.class);
        return objectMapper.readValue(response, pageType);
    }
}
