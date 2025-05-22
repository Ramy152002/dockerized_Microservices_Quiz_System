package com.Ramy.demo.Controller;


import com.Ramy.demo.Entity.*;
import com.Ramy.demo.Services.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {
    @Autowired
    QuizServices quizServices;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto){
        return quizServices.createQuiz(quizDto.getTitle(),quizDto.getCategory(),quizDto.getNumQ());
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Long id )
    {
        return quizServices.getQuiz(id);
    }


    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizes()
    {
        return quizServices.getAllQuizes();
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> response)
    {
        return quizServices.calculateScore(id, response);
    }

}
