package com.Ramy.demo.Controller;



import com.Ramy.demo.Entity.Question;
import com.Ramy.demo.Entity.QuestionWrapper;
import com.Ramy.demo.Entity.Response;
import com.Ramy.demo.Services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

    @Autowired
    QuestionServices questionServices;

    @Autowired
    Environment environment;

    @GetMapping("/{id}")
    public ResponseEntity< Optional<Question>> addQuestion(@PathVariable Integer id){
        return questionServices.getQuestion(id);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionServices.getAllQuestionsFS();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity< String> deleteQuestion(@PathVariable Integer id)
    {
        return questionServices.deleteQuestion(id);
    }

    @PostMapping
    public ResponseEntity< Question> createQuestion(@RequestBody Question question){
        return questionServices.createQuestion(question);
    }

    @PutMapping("{id}")
    public ResponseEntity< Question >updateQuestion(@RequestBody Question question,@PathVariable Integer id){
        return questionServices.updateQuestion(id,question);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsWithCategory(@PathVariable String category)
    {
        return questionServices.getQuestionWithCategory(category);
    }

    //generate

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQs){

        return questionServices.getQuestionsForQuiz(categoryName,numQs);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQestionsFromId(@RequestBody List<Integer>questionIds)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionServices.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
    return questionServices.getScore(responses);
    }

}
