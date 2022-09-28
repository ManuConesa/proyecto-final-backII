package com.dh.series.service.impl;


import com.dh.series.domain.model.Serie;
import com.dh.series.repository.SeriesRepository;
import com.dh.series.service.SeriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SeriesService {

    private SeriesRepository seriesRepository;

    private static final Logger LOG = LoggerFactory.getLogger(SeriesService.class);

    @Value("${queue.series.name}")
    private String seriesQueue;

    @Autowired
    public SerieServiceImpl(SeriesRepository seriesRepository){
        this.seriesRepository = seriesRepository;
    }

    @Override
    public List<Serie> findByGenre(String genre) {
        return seriesRepository.findByGenre(genre);
    }

    @Override
    public Serie save(Serie series) {
        return seriesRepository.save(series);
    }

    @RabbitListener(queues = "${queue.series.name}")
    public Serie saveSeries(Serie serie) {
        LOG.info("Saving the new series");
        return seriesRepository.save(serie);
    }


}
