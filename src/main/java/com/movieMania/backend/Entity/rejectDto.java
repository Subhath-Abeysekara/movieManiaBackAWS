package com.movieMania.backend.Entity;

import lombok.Data;

@Data
public class rejectDto {

    private Long id;
    private String reason;

    public rejectDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
