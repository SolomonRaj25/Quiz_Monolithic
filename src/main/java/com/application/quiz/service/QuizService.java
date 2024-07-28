package com.application.quiz.service;

import com.application.quiz.model.QuestionModel;
import com.application.quiz.model.UserResponse;

import java.util.List;

public interface QuizService {

    void createQuiz(String category, int numQstn, String title);

    List<QuestionModel> getQuizQuestions(int id);

    int calculateScore(Integer id, List<UserResponse> userResponse);
}
