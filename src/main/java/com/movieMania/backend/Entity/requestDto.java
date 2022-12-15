package com.movieMania.backend.Entity;

import lombok.Data;

import java.util.List;

@Data
public class requestDto {

    private Long movie;
    String customerName;
    String customerEmail;
    String contact;
    String driverLink;
    String payableStatus="notPayable";

    public requestDto() {
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDriverLink() {
        return driverLink;
    }

    public void setDriverLink(String driverLink) {
        this.driverLink = driverLink;
    }

    public String getPayableStatus() {
        return payableStatus;
    }

    public void setPayableStatus(String payableStatus) {
        this.payableStatus = payableStatus;
    }
}
