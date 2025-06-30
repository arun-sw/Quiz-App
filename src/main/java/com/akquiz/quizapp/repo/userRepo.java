package com.akquiz.quizapp.repo;

import com.akquiz.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface userRepo extends JpaRepository<Question,Integer> {


    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Question> findByCategory(String category);


    @Query(value = "SELECT * FROM question q  ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Question> findByAll();
}
