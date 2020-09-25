package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.app.dto.CategoryDTO;
import com.chozoi.authenservice.app.dto.QuestionDTO;
import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.Question;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.entities.products.Product;
import com.chozoi.authenservice.domain.exceptions.ResourceNotFoundException;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.ProductRepository;
import com.chozoi.authenservice.domain.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService extends BaseService {
  @Autowired private QuestionRepository questionRepository;
  @Autowired private ModelMapper modelMapper;
  @Autowired private CacheManagerService cacheManagerService;
  @Autowired private ProductRepository productRepository;

  public ResponseEntity listQuestions(String token) {

    List<Question> questionList = questionRepository.findAll();
    List<QuestionDTO> dtoList = new ArrayList<>();
    for (Question question : questionList) {
      QuestionDTO dto = modelMapper.questionToResponse(question);
      dtoList.add(dto);
    }
    return ResponseEntity.ok(dtoList);
  }

  public ResponseEntity create(String token, QuestionDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Product product = productRepository.getOne(dto.getProductID());

    Question question = Question.builder()
                        .text(dto.getText())
                        .product(product)
                        .userID(tokenInfo.getUserId())
                        .build();
    questionRepository.save(question);
    return ResponseEntity.ok(true);
//
//
//    Question question = new Question();
//    question.setText(dto.getText());
//    question.setProduct(product);
//    question.setUserID(tokenInfo.getUserId());
//    questionRepository.save(question);
//    return ResponseEntity.ok(true);
  }

  public ResponseEntity delete(String token, Integer questionID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Sai token rồi");
    }
    Question question =
        questionRepository.findQuestionByIdAndUserID(questionID, tokenInfo.getUserId());
    if (Objects.isNull(question)) {
      throw new ResourceNotFoundException("Bạn không thể xóa");
    }
    questionRepository.deleteById(questionID);
    return ResponseEntity.ok(true);
  }

  public ResponseEntity edit(String token, QuestionDTO dto, Integer questionID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    Product product =productRepository.getOne(dto.getProductID());
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Sai token rồi");
    }
    Question question =
        questionRepository.findQuestionByIdAndUserID(questionID, tokenInfo.getUserId());
    if (Objects.isNull(question)) {
      throw new ResourceNotFoundException("Bạn không thể sửa");
    }
    question.setText(dto.getText());
    question.setProduct(product);
    questionRepository.saveAndFlush(question);
    return ResponseEntity.ok(true);
  }
}
