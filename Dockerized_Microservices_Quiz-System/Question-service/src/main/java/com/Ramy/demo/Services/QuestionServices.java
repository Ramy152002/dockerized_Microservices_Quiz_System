package com.Ramy.demo.Services;



import com.Ramy.demo.Entity.Question;
import com.Ramy.demo.Entity.QuestionWrapper;
import com.Ramy.demo.Entity.Response;
import com.Ramy.demo.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServices {

    @Autowired
    QuestionRepository questionRepository;

/*  <--  public String all(){
        return " ";
    }
*/

    public ResponseEntity< List<Question>> getAllQuestionsFS() {
        try {
            return new ResponseEntity<> (questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();

            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public ResponseEntity< String >deleteQuestion(int id) {
        try {
            questionRepository.deleteById(id);
            return new ResponseEntity<>( "Question is deleted successfully", HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(("There is no id such that{id}"+id),HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Question> createQuestion(Question question) {

        try {
            return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
        }catch (Exception exception){exception.printStackTrace();}
        return new ResponseEntity<>(questionRepository.save(question),HttpStatus.BAD_REQUEST);


    }


    public ResponseEntity< Question> updateQuestion(Integer id, Question question)
    {
        try
        {
            if (!questionRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            question.setId(id); // make sure the ID is set
            Question updated = questionRepository.save(question);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity< Optional<Question>> getQuestion(Integer id) {
        try {
            return new ResponseEntity<>(questionRepository.findById(id),HttpStatus.OK);
        }catch (Exception exception)
        {
            exception.printStackTrace();

            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity< List<Question>> getQuestionWithCategory(String category) {
        return new ResponseEntity<>( questionRepository.findByCategory(category),HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQs) {
        List<Integer> questionList =questionRepository.getQuestionsByCategory(categoryName,numQs);

        return new ResponseEntity<>(questionList,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id :questionIds)
        {
            questions.add(questionRepository.findById(id).get());
        }
        for(Question q : questions)
        {
            QuestionWrapper wrapper= new QuestionWrapper();
            wrapper.setId(q.getId());
            wrapper.setQuestionTitle(q.getQuestionTitle());
            wrapper.setOption1(q.getOption1());
            wrapper.setOption2(q.getOption2());
            wrapper.setOption3(q.getOption3());
            wrapper.setOption4(q.getOption4());

            questionWrappers.add(wrapper);
        }

    return  new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer score=0;

        for(Response response : responses)
        {
            Question question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())) score++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK) ;
    }




}
