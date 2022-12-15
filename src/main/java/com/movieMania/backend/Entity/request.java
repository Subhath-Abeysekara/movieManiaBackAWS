package com.movieMania.backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.ToString;

import javax.persistence.*;

@Entity
public class request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long requestId;
    String adminStatus="pending";
    String customerName;
    String customerEmail;
    String contact;
    String code;
    String driverLink;
    String slipUrl;
    String payableStatus="notPayable";
    String adminShow="notShow";

    String multiCode="none";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "movie", foreignKey = @ForeignKey(name = "movie-request_fk1"))
    @JsonBackReference(value = "movie-request")
    @ToString.Exclude
    private com.movieMania.backend.Entity.movie movie;

    public request() {
    }

    public String getPayableStatus() {
        return payableStatus;
    }

    public void setPayableStatus(String payableStatus) {
        this.payableStatus = payableStatus;
    }

    public String getAdminShow() {
        return adminShow;
    }

    public void setAdminShow(String adminShow) {
        this.adminShow = adminShow;
    }

    public com.movieMania.backend.Entity.movie getMovie() {
        return movie;
    }

    public void setMovie(com.movieMania.backend.Entity.movie movie) {
        this.movie = movie;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public String getMultiCode() {
        return multiCode;
    }

    public void setMultiCode(String multiCode) {
        this.multiCode = multiCode;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDriverLink() {
        return driverLink;
    }

    public void setDriverLink(String driverLink) {
        this.driverLink = driverLink;
    }

    public String getSlipUrl() {
        return slipUrl;
    }

    public void setSlipUrl(String slipUrl) {
        this.slipUrl = slipUrl;
    }
}
