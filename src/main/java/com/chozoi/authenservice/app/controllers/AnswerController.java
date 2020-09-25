package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.AnswerDTO;
import com.chozoi.authenservice.app.dto.QuestionDTO;
import com.chozoi.authenservice.domain.services.AnswerService;
import com.chozoi.authenservice.domain.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/answer")
public class AnswerController {
    @Autowired private AnswerService answerService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(
            @RequestHeader("token") String token, @RequestBody AnswerDTO dto) {
        return answerService.create(token, dto);
    }
}
