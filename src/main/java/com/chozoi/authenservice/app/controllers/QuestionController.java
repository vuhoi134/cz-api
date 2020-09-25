package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.CategoryDTO;
import com.chozoi.authenservice.app.dto.QuestionDTO;
import com.chozoi.authenservice.domain.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/question")
public class QuestionController {
  @Autowired private QuestionService questionService;

  @GetMapping(value = "/list")
  public ResponseEntity<?> listCategory(@RequestHeader("token") String token) {
    return questionService.listQuestions(token);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(
      @RequestHeader("token") String token, @Valid @RequestBody QuestionDTO dto) {
    return questionService.create(token, dto);
  }

  @DeleteMapping(value = "{questionID}")
  public ResponseEntity<?> delete(
      @RequestHeader("token") String token, @PathVariable Integer questionID) {
    return questionService.delete(token, questionID);
  }

  @PutMapping(value = "{questionID}")
  public ResponseEntity<?> edit(
      @RequestHeader("token") String token,
      @PathVariable Integer questionID,
      @Valid @RequestBody QuestionDTO dto) {
    return questionService.edit(token, dto, questionID);
  }
}
