package com.example.newquizogtay.repository;


import com.example.newquizogtay.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}