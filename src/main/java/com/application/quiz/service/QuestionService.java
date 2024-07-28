package com.application.quiz.service;

import com.application.quiz.entity.Question;

import java.util.List;

public interface QuestionService {

    Question addQuestion(Question question);

    List<Question> fetchQuestionByCategory(String category);

    List<Question> fetchAllQuestions();
}
