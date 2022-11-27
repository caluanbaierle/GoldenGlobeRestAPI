package br.com.caluan.GoldenGlobeRestAPI.controller;

import br.com.caluan.GoldenGlobeRestAPI.config.ApplicationConfig;
import br.com.caluan.GoldenGlobeRestAPI.dto.MovieRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class MovieControllerTest {
    private static final String MOVIE_PATH = "/movie";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFetchAllMovies() throws Exception {
        this.mockMvc.perform(get(MOVIE_PATH)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[0].year", Is.is("1980")))
                .andExpect(jsonPath("$[0].title", Is.is("Can't Stop the Music")))
                .andExpect(jsonPath("$[0].studios", Is.is("Associated Film Distribution")))
                .andExpect(jsonPath("$[0].producers", Is.is("Allan Carr")))
                .andExpect(jsonPath("$[0].winner", Is.is("T")));
    }

    @Test
    void shouldFetchOneMovieById() throws Exception {
        long id = 1L;
        this.mockMvc.perform(get(MOVIE_PATH + "/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Is.is("Can't Stop the Music")));
    }


    @Test
    void shouldGetErrorWhenFetchMovieById() throws Exception {
        long id = 99999L;
        this.mockMvc.perform(get(MOVIE_PATH + "/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewMovie() throws Exception {
        MovieRequestDTO resource = new MovieRequestDTO();
        resource.setProducers("Caluan Baierle");
        resource.setStudios("Caluan Baierle LTDA");
        resource.setTitle("Caluan's Life");
        resource.setYear("1990");

        this.mockMvc.perform(post(MOVIE_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Is.is(resource.getTitle())));
    }

    @Test
    void shouldFetchProducers() throws Exception {
        this.mockMvc.perform(get(MOVIE_PATH + "/product")).andExpect(status().isOk())
                .andExpect(jsonPath("min[0].producer", Is.is("Joel Silver")))
                .andExpect(jsonPath("max[0].producer", Is.is("Buzz Feitshans")));

    }


    @Test
    void shouldUpdateMovie() throws Exception {
        MovieRequestDTO resource = new MovieRequestDTO();
        resource.setId(2);
        resource.setProducers("Caluan Baierle");
        resource.setStudios("Caluan Baierle LTDA");
        resource.setTitle("Caluan's Life");
        resource.setYear("1990");


        this.mockMvc.perform(put(MOVIE_PATH + "/2").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isOk());
    }


}
