package com.digitalhouse.catalogservice.domain.model;

import java.io.Serializable;
import java.util.List;

public class SeasonDTO implements Serializable {

    private String Id;
    private Integer seasonNumber;
    private List<ChapterDTO> chapters;

    public SeasonDTO() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public List<ChapterDTO> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterDTO> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "Id='" + Id + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", chapters=" + chapters +
                '}';
    }
}
