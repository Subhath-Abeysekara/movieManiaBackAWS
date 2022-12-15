package com.movieMania.backend.Service;

import com.movieMania.backend.Entity.admin;
import com.movieMania.backend.Entity.logins;
import com.movieMania.backend.Repository.loginRepository;
import com.movieMania.backend.Repository.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class adminServiceImplement implements adminService {

    Random random = new Random();

    @Autowired
    private com.movieMania.backend.Repository.adminRepository adminRepository;

    @Autowired
    private otherService otherService;

    @Autowired
    private com.movieMania.backend.Repository.loginRepository loginRepository;

    public String getPw(String username) {
        admin admin = adminRepository.findByUsername(username);
        if(admin==null){
            return null;
        }
        return admin.getPassword();
    }

    private String makeHourCount(){
        Date date = new Date();
        SimpleDateFormat sdt5 = new SimpleDateFormat("hh");

        return sdt5.format(date);
    }
    private String makeFullString(String username, String password, String hour) {
        String word = "qwedf@#$#gfhfkld$$&*6djfjsn1314jgff";
        char[] wordArray = word.toCharArray();
        char[] usernameArray = username.toCharArray();
        char[] passwordArray = password.toCharArray();
        int userNameLetterCount = username.length();
        int passwordLetterCount = password.length();
        StringBuilder secretKey= new StringBuilder();
        for (int i=0; i<userNameLetterCount; i++){
            secretKey.append(wordArray[i]);
            secretKey.append(usernameArray[i]);

        }
        int i=30;
        while (secretKey.length()<35){
            secretKey.append(wordArray[i]);
            i--;
        }
        if (userNameLetterCount<10){
            secretKey.append("0");
        }
        secretKey.append(userNameLetterCount);
        for (int j=0; j<passwordLetterCount; j++){
            secretKey.append(wordArray[j]);
            secretKey.append(passwordArray[j]);

        }
        i=30;
        while (secretKey.length()<65){
            secretKey.append(wordArray[i]);
            i--;
        }
        if (passwordLetterCount<10){
            secretKey.append("0");
        }
        secretKey.append(passwordLetterCount);
        i=30;
        while (secretKey.length()<75){
            secretKey.append(wordArray[i]);
            i--;
        }
        secretKey.append(hour);
        return secretKey.toString();
    }

    private boolean checkPassword(String password, String passwordSend){
        if (password.equals(passwordSend)){
            return true;
        }
        return false;
    }
    private boolean checkTime(String hourCount){

        String hour = makeHourCount();
        int nowTime = Integer.parseInt(hour);
        int oldTime = Integer.parseInt(hourCount);
        if (oldTime>nowTime){
            return true;
        }
        return false;
    }
    @Override
    public String madeSecreteKey(String username, String password) {
        String passwordFromDb = getPw(username);
        if (passwordFromDb == null){
            return "Error username or password";
        }
        if(!passwordFromDb.equals(password)){
            return "Error username or password";
        }
        String hour = makeHourCount();
        int hourInt = Integer.parseInt(hour);
        hourInt+=10;
        hour = Integer.toString(hourInt);
        return makeFullString(username,password,hour);
    }

    @Override
    public boolean checkTokenValidity(String token) throws Exception {
        try {
            char[] secretKeyArray = token.toCharArray();
            String usernameLetterCount = "";
            String passwordLetterCount="";
            StringBuilder username = new StringBuilder();
            StringBuilder password = new StringBuilder();
            usernameLetterCount+= secretKeyArray[35];
            usernameLetterCount+=secretKeyArray[36];
            int usernameLetterAmount = Integer.parseInt(usernameLetterCount);
          //  System.out.println(usernameLetterAmount);
            passwordLetterCount+= secretKeyArray[65];
            passwordLetterCount+=secretKeyArray[66];
            int passwordLetterAmount = Integer.parseInt(passwordLetterCount);
          //  System.out.println(passwordLetterAmount);
            int i=1;
            while (i<usernameLetterAmount*2){
                username.append(secretKeyArray[i]);
                i+=2;
            }
          //  System.out.println(username.toString());
            i=38;
            while (i<passwordLetterAmount*2+38){
                password.append(secretKeyArray[i]);
                i+=2;
            }
           // System.out.println(password.toString());
            StringBuilder hour = new StringBuilder();
            for (int j=75; j<secretKeyArray.length;j++){
                hour.append(secretKeyArray[j]);
            }
           // System.out.println(hour.toString());
            String passwordFromDb = getPw(username.toString());
            if (passwordFromDb == null){
                return false;
            }
            if (checkPassword(passwordFromDb,password.toString())&&checkTime(hour.toString())){
                return true;
            }
            return false;
        }catch (Exception e){
            throw new Exception("error request");
        }
    }

    @Override
    public admin addAdmin(admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public String changeAdmin(String oldUsername,String username,String oldPassword,String password) {
        String passwordFromDb = getPw(oldUsername);
        if (passwordFromDb == null){
            return "Error username or password";
        }
        if(!passwordFromDb.equals(oldPassword)){
            return "Error username or password";
        }
        adminRepository.setUsername(oldUsername,username);
        adminRepository.setPassword(oldPassword,password);

        return "successfully changed";
    }

    @Override
    public boolean getLoginStatus(String ip) {
        Optional<logins> login = loginRepository.findByIpAddress(ip);

        return login.map(logins::isLoginStatus).orElse(true);

    }

    @Override
    public String setLoginStatus(String ip) {
        Optional<logins> login = loginRepository.findByIpAddress(ip);
        if (login.isPresent()){
            login.get().setLoginStatus(false);
            loginRepository.save(login.get());
            return "successfully";
        }
        else {
            logins login1 = new logins();
            login1.setIpAddress(ip);
            login1.setLoginStatus(false);
            loginRepository.save(login1);

            return "success";
        }


    }

    @Override
    public Long addLogin(logins login) {
        return loginRepository.save(login).getId();
    }

    @Override
    public List<logins> getLogin() {
        return loginRepository.findAll();
    }

    @Override
    public List<admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public String sendMailUsername(String email) {

        Optional<admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()){
            otherService.sendMails(admin.get().getEmail(),"UsernameUpdating",admin.get().getUsername());
            return "successfully sent";
        }
        return "error email";
    }

    @Override
    public List<String> getAllMails() {
        return adminRepository.getAdminMails();
    }

    private String createUsername(){
        String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        char[] wordArray = word.toCharArray();
        int wordLength = word.length();
        StringBuilder secretKey= new StringBuilder();

        int i=0;
        while (i<8){
            int j = random.nextInt(wordLength);
            secretKey.append(wordArray[j]);
            i++;
        }

        return secretKey.toString();
    }

    @Scheduled(fixedRate = 10000L)
    private void loginSetting(){
        List<logins> logins  = loginRepository.findAll();

        for (logins login1 : logins){
            if (!login1.isLoginStatus()) {
                    int loginCount = login1.getLoginCount();
                    loginCount++;
                    login1.setLoginCount(loginCount);
                    loginRepository.save(login1);
                    // System.out.println(login1.getLoginCount());
                    if (login1.getLoginCount() >= 120) {
                        login1.setLoginStatus(true);
                        login1.setLoginCount(0);
                        loginRepository.save(login1);
                    }
                }
        }


        List<admin> admins = adminRepository.findAll();

        for(admin admin1 : admins){
            int count = admin1.getUsernameCount();
            count++;
            admin1.setUsernameCount(count);
            adminRepository.save(admin1);

            if (admin1.getUsernameCount()>=20000){
                //createusername
                String username = createUsername();
                admin1.setUsername(username);
                admin1.setUsernameCount(0);
                adminRepository.save(admin1);


            }
        }
    }
}
