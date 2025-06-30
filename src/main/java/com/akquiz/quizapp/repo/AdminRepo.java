package com.akquiz.quizapp.repo;

import com.akquiz.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);
    Optional<Question> findById(Long id);

    @Query("SELECT DISTINCT q.category FROM Question q")
    List<String> findAllCategories();

}
