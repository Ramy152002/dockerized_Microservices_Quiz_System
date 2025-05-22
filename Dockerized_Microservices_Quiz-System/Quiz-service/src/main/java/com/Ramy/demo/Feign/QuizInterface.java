package com.Ramy.demo.Feign;

import com.Ramy.demo.Entity.QuestionWrapper;
import com.Ramy.demo.Entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//QUESTIONS-MICROSERVICES
@FeignClient("question-service")
public interface QuizInterface {

    @GetMapping("api/v1/questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQs);
    @PostMapping("api/v1/questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQestionsFromId(@RequestBody List<Integer>questionIds);
    @PostMapping("api/v1/questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);


}
