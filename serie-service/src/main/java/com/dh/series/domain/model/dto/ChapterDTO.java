package com.dh.series.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ChapterDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer number;
    private String urlStream;
}