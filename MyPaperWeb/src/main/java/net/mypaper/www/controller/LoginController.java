package net.mypaper.www.controller;

import JwtUtils.JwtUtils;
import net.mypaper.www.pojo.webusers;
import net.mypaper.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public String login(@RequestBody webusers user){
        /*webusers user = new webusers();
        user.setEmail(email);
        user.setPassword(password);*/
        user = userService.login(user);
        System.out.println(user.getEmail());
        if (user == null) return null;
        String token = null;
        try {
            token = JwtUtils.createToken(user.getUid());
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    @PostMapping("/register")
    public String register(@RequestBody webusers user)
    {
        /*if(email == null|| account ==null || password ==null) return null;
        webusers user =new webusers();
        user.setPassword(password);
        user.setEmail(email);
        user.setAccount(account);*/
        webusers newU = userService.registerUser(user);
        if(newU ==null) return null;
        String token = null;
        try {
            token = JwtUtils.createToken(newU.getUid());
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    @PostMapping("/test")
    public String tst(){
        return "okkkkkkkkkkkkkkkkkkkkkkkkkkk";
    }
}
