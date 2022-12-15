package com.movieMania.backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long characterId;
    String character;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "movie", foreignKey = @ForeignKey(name = "movie-character_fk3"))
    @JsonBackReference(value = "movie-characters")
    @ToString.Exclude
    private com.movieMania.backend.Entity.movie movie;

    public character() {
    }


    public com.movieMania.backend.Entity.movie getMovie() {
        return movie;
    }

    public void setMovie(com.movieMania.backend.Entity.movie movie) {
        this.movie = movie;
    }


    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
