package com.akquiz.quizapp.repo;

import com.akquiz.quizapp.model.loginuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface loginrepo extends JpaRepository<loginuser, String> {
    Optional<loginuser> findByUsernameAndPassword(String username, String password);

    Optional<loginuser> findByUsername(String username);

}
