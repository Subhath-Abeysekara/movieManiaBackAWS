package com.movieMania.backend.Entity;

import lombok.Data;

@Data
public class addUrlDto {

    private String url;
    private String code;

    public addUrlDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
