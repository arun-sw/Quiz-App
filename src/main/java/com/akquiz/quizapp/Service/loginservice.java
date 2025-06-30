package com.akquiz.quizapp.Service;

import com.akquiz.quizapp.model.loginuser;
import com.akquiz.quizapp.repo.loginrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class loginservice {

    @Autowired
    private loginrepo loginRepo;

    public boolean authenticate(String username, String password) {
        Optional<loginuser> user = loginRepo.findByUsernameAndPassword(username, password);
        return user.isPresent();
    }

    // Register a new user
    public String registerUser(loginuser user) {
        if (loginRepo.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists!";
        }
        loginRepo.save(user);
        return "User registered successfully!";
    }

}
