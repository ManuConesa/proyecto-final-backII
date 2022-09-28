package com.dh.series.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class SeasonDTO implements Serializable {
    private Integer id;
    private Integer seasonNumber;
    private List<ChapterDTO> chapters;
}

