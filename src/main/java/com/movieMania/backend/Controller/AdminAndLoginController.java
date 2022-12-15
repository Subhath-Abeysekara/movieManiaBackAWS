package com.movieMania.backend.Controller;

import com.movieMania.backend.Entity.*;
import com.movieMania.backend.Repository.adminRepository;
import com.movieMania.backend.Service.adminService;
import com.movieMania.backend.Service.otherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class AdminAndLoginController {

    @Autowired
    private adminService adminService;

    @Autowired
    private otherService otherService;

    @Autowired
    private adminRepository adminRepository;

    /**Security **/

    @PostMapping("/getKey/{ip}")
    private String getKey(@RequestBody admin admin,@PathVariable String ip){
        if(adminService.getLoginStatus(ip)){

            return adminService.madeSecreteKey(admin.getUsername(),admin.getPassword());
        }

        return "Error username or password";

    }
    @PostMapping("/checkAdmin")
    private String checkAdmin(@RequestBody admin admin){
        return adminService.madeSecreteKey(admin.getUsername(),admin.getPassword());
    }

    @PostMapping("/addAdmin")
    private String addAdmin(@RequestBody admin admin){
        adminService.addAdmin(admin);
        return "added successfully";
    }

    @GetMapping("/getMails")
    private List<String> getMail(){
        return adminService.getAllMails();
    }

    @PostMapping("/sendMail/{email}")
    private String mailSend(@PathVariable String email){
       return adminService.sendMailUsername(email);
    }
    
    @PutMapping ("/changeAdmin")
    private String changeAdmin(@RequestBody oldadmin admin){
        return adminService.changeAdmin(admin.getOldUsername(),admin.getUsername(),admin.getOldPassword(),admin.getPassword());
    }

    @GetMapping("/getAdminAll")
    private List<admin> getAdmins(){
        return adminService.getAllAdmin();
    }

    @GetMapping("/getLogin/status")
    private String getLogin(@RequestParam String ip){
        
        if(adminService.getLoginStatus(ip)){
        
            return "true";
        }
            
        return "false";
    }

    @PostMapping("/addLogin")
    private Long addLogin(logins login){
        return adminService.addLogin(login);
    }

    @GetMapping("/getLogin")
    private List<logins> getLoginFull(){
        return adminService.getLogin();
    }

    @PutMapping("/setLogin/status")
    private String setLogin(@RequestParam String ip){
        return adminService.setLoginStatus(ip);
    }

    @GetMapping("/test")
    private String test(@RequestParam String name,@RequestParam String age){

//        response response = new response();
//        response.setResponse("test pass");
//        return response;
        return name+age;
    }
    
    @GetMapping("/getvalidity")
    private String validityCheck(@RequestHeader String header) throws Exception {

//        response response = new response();
//        response.setResponse("test pass");
//        return response;
        if(adminService.checkTokenValidity(header)){
            return "successful";
        }
        return "wrong token";
    }

}
