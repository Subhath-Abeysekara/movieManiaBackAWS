package com.movieMania.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class admin {

    @Id
    private int id;
    private String username="subhath";
    private String password="12345678";
    private String email;
    private String contact;
    private int usernameCount=0;


    public admin() {
    }


    public int getUsernameCount() {
        return usernameCount;
    }

    public void setUsernameCount(int usernameCount) {
        this.usernameCount = usernameCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
