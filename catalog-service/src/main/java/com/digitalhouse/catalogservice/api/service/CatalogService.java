package com.digitalhouse.catalogservice.api.service;

import java.util.ArrayList;
import java.util.List;

import com.digitalhouse.catalogservice.domain.model.SerieDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.digitalhouse.catalogservice.api.client.MovieClient;
import com.digitalhouse.catalogservice.domain.model.MovieDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

@Service
public class CatalogService {

    // cola de movies
    @Value("${queue.movie.name}")
    private String movieQueue;

    //cola de series
    @Value("${queue.series.name}")
    private String queueSeriesName;

    private final Logger LOG = LoggerFactory.getLogger(CatalogService.class);

    private final MovieClient movieClient;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CatalogService(MovieClient movieClient, RabbitTemplate rabbitTemplate) {
        this.movieClient = movieClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre) {
        LOG.info("Searching movies by genre" + genre);
        return movieClient.getMovieByGenre(genre);
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "moviesFallbackMethod")
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre, Boolean throwError) {
        LOG.info("Searching movies by genre" + genre);
        return movieClient.getMovieByGenreWithThrowError(genre, throwError);
    }

    private ResponseEntity<List<MovieDTO>> moviesFallbackMethod(CallNotPermittedException exception) {
        LOG.info("Circuit breaker en estado abierto");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public void saveMovie(MovieDTO movieDTO) {
        rabbitTemplate.convertAndSend(movieQueue, movieDTO);
    }

    public void saveSeries(SerieDTO serieDTO){
        rabbitTemplate.convertAndSend(queueSeriesName, serieDTO);
    }
}
