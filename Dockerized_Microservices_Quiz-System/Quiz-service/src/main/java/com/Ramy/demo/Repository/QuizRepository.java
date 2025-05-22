package com.Ramy.demo.Repository;

import com.Ramy.demo.Entity.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long > {
}
