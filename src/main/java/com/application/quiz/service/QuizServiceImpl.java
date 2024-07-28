package com.application.quiz.service;

import com.application.quiz.entity.Question;
import com.application.quiz.entity.Quiz;
import com.application.quiz.model.QuestionModel;
import com.application.quiz.model.UserResponse;
import com.application.quiz.repository.QuestionRepository;
import com.application.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void createQuiz(String category, int numQstn, String title) {
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQstn);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
    }

    @Override
    public List<QuestionModel> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionModel> questionsToUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionModel questionModel = QuestionModel.builder()
                    .id(q.getId()).category(q.getCategory()).questionTitle(q.getQuestionTitle())
                    .option1(q.getOption1()).option2(q.getOption2()).option3(q.getOption3())
                    .option4(q.getOption4()).build();
            questionsToUser.add(questionModel);
        }
        return questionsToUser;
    }

    @Override
    public int calculateScore(Integer id, List<UserResponse> userResponse) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int accepted=0;
        int index=0;
        for (UserResponse response : userResponse) {
            if (response.getResponse().equalsIgnoreCase(questions.get(index).getRightAnswer())) {
                accepted++;
            }
            index++;
        }
        return accepted;
    }

}
