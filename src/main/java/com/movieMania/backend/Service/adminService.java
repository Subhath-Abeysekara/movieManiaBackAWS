package com.movieMania.backend.Service;

import com.movieMania.backend.Entity.*;

import java.util.List;

public interface adminService {
    String madeSecreteKey(String username, String password);
    boolean checkTokenValidity(String token) throws Exception;
    admin addAdmin(admin admin);
    String changeAdmin(String oldUsername,String username,String oldPassword,String password);
    boolean getLoginStatus(String ip);
    String setLoginStatus(String ip);
    Long addLogin(logins login);
    List<logins> getLogin();
    List<admin> getAllAdmin();
    String sendMailUsername(String email);
    List<String> getAllMails();




}
