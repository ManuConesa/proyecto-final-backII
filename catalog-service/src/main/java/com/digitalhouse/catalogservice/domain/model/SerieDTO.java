package com.digitalhouse.catalogservice.domain.model;


import java.io.Serializable;
import java.util.List;

public class SerieDTO implements Serializable {
    private String id;
    private String name;
    private String genre;
    private List<SeasonDTO> seasons;

    public SerieDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<SeasonDTO> getSeason() {
        return seasons;
    }

    public void setSeason(List<SeasonDTO> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "SerieDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }


}
