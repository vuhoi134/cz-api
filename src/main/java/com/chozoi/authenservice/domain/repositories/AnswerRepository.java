package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.Answer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

}
