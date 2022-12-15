package com.movieMania.backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class actors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public actors(){

    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "movie", foreignKey = @ForeignKey(name = "movie-actor_fk2"))
    @JsonBackReference(value = "movie-actor")
    @ToString.Exclude
    private movie movie;
}
