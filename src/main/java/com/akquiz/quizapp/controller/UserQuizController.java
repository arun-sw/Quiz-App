package com.akquiz.quizapp.controller;


import com.akquiz.quizapp.Service.UserQuizservice;
import com.akquiz.quizapp.model.QuestionDTO;
import com.akquiz.quizapp.model.UserAnswerDTO;
import com.akquiz.quizapp.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("User")
@CrossOrigin(origins = "http://localhost:63342")
public class UserQuizController {

    @Autowired
    UserQuizservice userQuiz;
    @Autowired
    userRepo quizrepo;

    @GetMapping("Take/Quiz/{category}")
    public List<QuestionDTO> TakeQuiz(@PathVariable String category){
        return userQuiz.takequestion(category);
    }
    @PostMapping("/submit/Quiz")
    public List<Map<String, Object>> submitQuiz(@RequestBody List<UserAnswerDTO> answers) {
        return userQuiz.evaluateQuiz(answers);
    }
    @GetMapping("Take/Question")
    public List<QuestionDTO> TakeQuestion(){
        return userQuiz.takeallquestion();
    }

}
