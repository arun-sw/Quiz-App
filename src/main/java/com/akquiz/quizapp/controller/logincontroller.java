package com.akquiz.quizapp.controller;


import com.akquiz.quizapp.Service.loginservice;
import com.akquiz.quizapp.model.loginuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class logincontroller {

    @Autowired
    private loginservice loginService;
    @PostMapping("/login")
    public String login(@RequestBody loginuser user) {
        boolean valid = loginService.authenticate(user.getUsername(), user.getPassword());
        return valid ? "Login Successful" : "Invalid Username or Password";
    }

    // POST: /api/register
    @PostMapping("/register")
    public String register(@RequestBody loginuser user) {
        return loginService.registerUser(user);
    }
}
