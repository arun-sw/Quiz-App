package com.akquiz.quizapp.Service;

import com.akquiz.quizapp.model.Question;
import com.akquiz.quizapp.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminQuestionService {
    @Autowired
    AdminRepo quiz;



//   find the all question
    public List<Question> getallQuestion() {
        return quiz.findAll();
    }
//    find by category
    public List<Question> getQuestionByCategory(String category) {
      return  quiz.findByCategory(category);
    }
//    find by id
    public Optional<Question> getquizbyid(long id) {
        return quiz.findById(id);
    }


//    adding the question
    public ResponseEntity<String> adquestion(Question question) {
         quiz.save(question);
        return new ResponseEntity<>("The question was successfully added to the database", HttpStatus.CREATED);
    }
//delete the question
    public ResponseEntity<String> deleteQuestionById(long id) {
        quiz.deleteById(id);
        return new ResponseEntity<>("The question was successfully deleted from the database", HttpStatus.OK);
    }
//find all question
    public List<Question> getallquiz() {
        return quiz.findAll();
    }
//show the update question
public Optional<Question> showTheeditq(Long id) {
        return quiz.findById(id);
    }


    public Optional<Question> findById(Long id) {
        return quiz.findById(id);
    }

    public Question save(Question question) {
        return quiz.save(question);
    }
//    category
    public List<String> getAllCategories() {
        return quiz.findAllCategories();
    }



}
