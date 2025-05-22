package com.Ramy.demo.Services;


import com.Ramy.demo.Entity.Question;
import com.Ramy.demo.Entity.QuestionWrapper;
import com.Ramy.demo.Entity.Quiz;
import com.Ramy.demo.Entity.Response;
import com.Ramy.demo.Feign.QuizInterface;
import com.Ramy.demo.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServices {
    //all logics are here
    // first create an object from repository
    @Autowired
    private QuizRepository quizRepository;

   /* @Autowired
    QuestionRepository questionRepository;
*/
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<Quiz> createQuiz(String title, String category, Integer numQ) {

        List<Integer> questionsId =quizInterface.getQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionsId);
        quizRepository.save(quiz);

        return new ResponseEntity<>(quiz,HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Long id) {

        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionsIds =quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQestionsFromId(questionsIds);

        return questions;


    }


/*
    public ResponseEntity<List<Quiz>> getAllQuizes() {
        try {
            return new ResponseEntity<>(quizRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }*/

    // deepseek
    public ResponseEntity<List<Quiz>> getAllQuizes() {
        try {
            List<Quiz> quizzes = quizRepository.findAll();

            if (quizzes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // أفضل من إرسال قائمة فاضية مع OK
            }

            return ResponseEntity.ok(quizzes); // أبسط وأوضح
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // أدق من BAD_REQUEST
        }
    }

    public ResponseEntity<Integer> calculateScore(Long id, List<Response> responses) {
        System.out.println("✅ Endpoint /Quiz/submit/" + id + " was hit successfully!");

        ResponseEntity<Integer> score = quizInterface.getScore(responses);

        return score ;

    }
}
