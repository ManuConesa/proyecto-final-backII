package com.dh.series.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class SerieDTO implements Serializable {
    private Integer id;
    private String name;
    private String genre;
    private List<SeasonDTO> seasons;
}
