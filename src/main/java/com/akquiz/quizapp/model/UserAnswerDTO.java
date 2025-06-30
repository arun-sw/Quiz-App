package com.akquiz.quizapp.model;

public class UserAnswerDTO {

    private Long questionId;
    private String selectedAnswer;

        public UserAnswerDTO(Long questionId, String selectedAnswer) {
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
    }

    public Integer getQuestionId() {
        return Math.toIntExact(questionId);
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    @Override
    public String toString() {
        return "UserAnswerDTO{" +
                "questionId=" + questionId +
                ", selectedAnswer='" + selectedAnswer + '\'' +
                '}';
    }
}
