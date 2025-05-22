package com.Ramy.demo.Repository;

import com.Ramy.demo.Entity.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {


  //  @Query(value="select * from question q where q.category=:category order by rand() limit :numQ", nativeQuery = true)
    //List<Question> getQuestionsByCategory(String category, Integer numQ);
  List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> getQuestionsByCategory( String category,  Integer numQ);

}
