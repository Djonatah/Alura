package com.alura.djonatah.ScreenMatch.Domain;

import jakarta.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private Integer duration;
    private Integer releaseDate;

    public Movie(){}

    public Movie(MovieFormData movieFormData){
        this.name = movieFormData.name();
        this.genre = movieFormData.genre();
        this.duration = movieFormData.duration();
        this.releaseDate = movieFormData.releaseDate();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void update(MovieUpdateData movieUpdateData){
        this.name = movieUpdateData.name();
        this.genre = movieUpdateData.genre();
        this.duration = movieUpdateData.duration();
        this.releaseDate = movieUpdateData.releaseDate();
    }
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
