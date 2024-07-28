package com.application.quiz.controller;

import com.application.quiz.entity.Question;
import com.application.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> fetchAllQuestions() {
        List<Question> questions = questionService.fetchAllQuestions();
        if (Optional.ofNullable(questions).isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> fetchQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = questionService.fetchQuestionByCategory(category);
        if (Optional.ofNullable(questions).isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

}
