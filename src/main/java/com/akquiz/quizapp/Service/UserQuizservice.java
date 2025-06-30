package com.akquiz.quizapp.Service;

import com.akquiz.quizapp.model.Question;
import com.akquiz.quizapp.model.QuestionDTO;
import com.akquiz.quizapp.model.UserAnswerDTO;
import com.akquiz.quizapp.repo.AdminRepo;
import com.akquiz.quizapp.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserQuizservice {

    @Autowired
    userRepo quizrepo;
    @Autowired
    AdminRepo questionrepo;


    public List<QuestionDTO> takequestion(String category) {
        List<Question> questions = quizrepo.findByCategory(category);
        List<QuestionDTO> result = new ArrayList<>();

        for (Question q : questions) {
            QuestionDTO dto = new QuestionDTO(
                    (long) q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4(),
                    q.getCategory(),
                    q.getDifficultyLevel()
            );
            result.add(dto);
        }

        Collections.shuffle(result);
        return result;
    }


    public List<Map<String, Object>> evaluateQuiz(List<UserAnswerDTO> userAnswers) {
        List<Map<String, Object>> results = new ArrayList<>();

        for (UserAnswerDTO ans : userAnswers) {
            Optional<Question> questionOpt = quizrepo.findById(ans.getQuestionId());

            if (questionOpt.isPresent()) {
                Question question = questionOpt.get();

                boolean isCorrect = question.getRightAnswer().equalsIgnoreCase(ans.getSelectedAnswer());

                Map<String, Object> result = new HashMap<>();
                result.put("questionId", question.getId());
                result.put("correctAnswer", question.getRightAnswer());
                result.put("selectedAnswer", ans.getSelectedAnswer());
                result.put("correct", isCorrect);

                results.add(result);
            } else {
                Map<String, Object> result = new HashMap<>();
                result.put("questionId", ans.getQuestionId());
                result.put("error", "Question not found");
                results.add(result);
            }
        }

        return results;
    }

    public List<QuestionDTO> takeallquestion() {
        List<Question> questions = quizrepo.findByAll();
        List<QuestionDTO> result = new ArrayList<>();

        for (Question q : questions) {
            QuestionDTO dto = new QuestionDTO(
                    (long) q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4(),
                    q.getCategory(),
                    q.getDifficultyLevel()
            );
            result.add(dto);
        }

        Collections.shuffle(result);
        return result;
    }
}

