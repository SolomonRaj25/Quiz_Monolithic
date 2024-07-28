package com.application.quiz.controller;

import com.application.quiz.model.QuestionModel;
import com.application.quiz.model.UserResponse;
import com.application.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQstn,@RequestParam String title) {
        quizService.createQuiz(category, numQstn, title);
        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    @GetMapping("/getQuestions/{id}")
    public ResponseEntity<List<QuestionModel>> getQuizQuestions(@PathVariable int id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id), HttpStatus.OK);
    }

    @PostMapping("/calculate/{id}")
    public ResponseEntity<String> calculateScore(@PathVariable Integer id, @RequestBody List<UserResponse> userResponse) {
        int score = quizService.calculateScore(id,userResponse);
        return new ResponseEntity<>("Your Score "+score,HttpStatus.ACCEPTED);
    }
}
