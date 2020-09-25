package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.Question;
import com.chozoi.authenservice.domain.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    Question findQuestionByIdAndUserID(int questionId,int userId);

}
