package com.digitalhouse.catalogservice.api.controller;

import java.util.List;

import com.digitalhouse.catalogservice.domain.model.SerieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.digitalhouse.catalogservice.api.service.CatalogService;
import com.digitalhouse.catalogservice.domain.model.MovieDTO;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    //obtengo la lista de películas by genre, me lo comunica feign
    @GetMapping("/{genre}")
    public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre) {
        return catalogService.findMovieByGenre(genre);
    }

    @GetMapping("/withErrors/{genre}")
    public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre, @RequestParam("throwError") Boolean throwError) {
        return catalogService.findMovieByGenre(genre, throwError);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveMovie(@RequestBody MovieDTO movieDTO) {
        catalogService.saveMovie(movieDTO);
        return ResponseEntity.ok("The movie was sent to the queue");
    }

    //Guardar serie usando RabbitMQ
    @PostMapping("/save/series")
    public ResponseEntity<String> saveSeries(@RequestBody SerieDTO serieDTO){
        catalogService.saveSeries(serieDTO);
        return ResponseEntity.ok("Series was sent to queue");
    }
}
