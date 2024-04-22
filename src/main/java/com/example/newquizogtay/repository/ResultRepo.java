package com.example.newquizogtay.repository;

import com.example.newquizogtay.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface  ResultRepo extends JpaRepository<Result, Integer> {
	
}
