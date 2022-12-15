package com.movieMania.backend.Entity;

import lombok.Data;

import java.util.List;

@Data
public class movieData {

    Long movieId;
    String story;
    String addDate;
    String actors;
    String category;
    String launchingImageUrl;
    String name;
    String trailerLink;
    String imageUrl;
    Long rate = 0L;
    double price=300.0;
    List<character> character;

    public movieData() {
    }

    public List<character> getCharacter() {
        return character;
    }

    public void setCharacter(List<character> character) {
        this.character = character;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLaunchingImageUrl() {
        return launchingImageUrl;
    }

    public void setLaunchingImageUrl(String launchingImageUrl) {
        this.launchingImageUrl = launchingImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
