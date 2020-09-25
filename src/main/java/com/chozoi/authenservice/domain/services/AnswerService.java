package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.app.dto.AnswerDTO;
import com.chozoi.authenservice.app.dto.QuestionDTO;
import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.Answer;
import com.chozoi.authenservice.domain.entities.Question;
import com.chozoi.authenservice.domain.entities.products.Product;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.AnswerRepository;
import com.chozoi.authenservice.domain.repositories.ProductRepository;
import com.chozoi.authenservice.domain.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class AnswerService extends BaseService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired private CacheManagerService cacheManagerService;
    @Autowired private ProductRepository productRepository;
    @Autowired private AnswerRepository answerRepository;
    @Autowired private QuestionRepository questionRepository;

    public ResponseEntity create(String token, AnswerDTO dto) {
        TokenInfo tokenInfo = isAuthen(token);
        Question question = questionRepository.getOne(dto.getQuestionID());

        Answer answer = new Answer();
        answer.setText(dto.getText());
        answer.setQuestion(question);
        answer.setUserID(tokenInfo.getUserId());
        answerRepository.save(answer);
        return ResponseEntity.ok(true);

    }
}
