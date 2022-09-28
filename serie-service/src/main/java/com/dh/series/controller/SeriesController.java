package com.dh.series.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dh.series.domain.model.Serie;
import com.dh.series.service.SeriesService;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService service;

    @Autowired
    public SeriesController(SeriesService service) {
        this.service = service;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> getMovieByGenre(@PathVariable String genre) {
        List<Serie> series = service.findByGenre(genre);
        return series.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(series, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Serie> saveSeries(@RequestBody Serie series){
        return ResponseEntity.ok().body(service.save(series));
        }
    }

