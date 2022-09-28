package com.dh.series.service;

import java.util.List;

import com.dh.series.domain.model.Serie;


public interface SeriesService {

    List<Serie> findByGenre(String genre);

    Serie save(Serie series);
}
