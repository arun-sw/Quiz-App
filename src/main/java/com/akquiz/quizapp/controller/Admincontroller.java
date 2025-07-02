package com.akquiz.quizapp.controller;

import com.akquiz.quizapp.Service.AdminQuestionService;
import com.akquiz.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin(origins = "*")
@RequestMapping("question")

public class Admincontroller {

    @Autowired
    AdminQuestionService servce;

    @GetMapping("/QA")
    public List<Question> allquestion() {
        return  servce.getallQuestion();
    }
    @GetMapping("/category/{category}")
    public  List<Question> getQuestionbycatogory(@PathVariable String category) {
        return servce.getQuestionByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addquestion(@RequestBody Question question) {
        return servce.adquestion(question);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletequestion(@PathVariable int id) {
        return servce.deleteQuestionById(id);
    }
    @GetMapping("/get/{id}")
    public Optional<Question> getQuestionbyid(@PathVariable Long id) {
      return servce.getquizbyid(id);
    }
//    get all question
    @GetMapping("/allquiz")
    public List<Question> getAllQuiz() {
        return servce.getallquiz();
    }
//    update the question
@GetMapping("edit/{id}")
public Optional<Question> editquestion(@PathVariable Long id) {
        return servce.showTheeditq(id);
}
@PutMapping("/update/{id}")
    public ResponseEntity<Question> updatequestion(@PathVariable Long id, @RequestBody Question Updatequestion) {
    Optional<Question> existingQuestion = servce.findById(id);
    if (existingQuestion.isPresent()) {
        Question question = existingQuestion.get();
        question.setQuestionTitle(Updatequestion.getQuestionTitle());
        question.setOption1(Updatequestion.getOption1());
        question.setOption2(Updatequestion.getOption2());
        question.setOption3(Updatequestion.getOption3());
        question.setOption4(Updatequestion.getOption4());
        question.setRightAnswer(Updatequestion.getRightAnswer());


        return ResponseEntity.ok(servce.save(question));
    }
    else {
        return ResponseEntity.notFound().build();
    }
    }
//    category
@GetMapping("/categories")
public ResponseEntity<List<String>> getCategories() {
    return ResponseEntity.ok(servce.getAllCategories());
}

}


