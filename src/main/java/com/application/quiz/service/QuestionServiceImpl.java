package com.application.quiz.service;

import com.application.quiz.entity.Question;
import com.application.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> fetchQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> fetchAllQuestions() {
        return questionRepository.findAll();
    }

}
