package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.base.BaseApiResult;
import com.chozoi.authenservice.app.dto.UserDTO;
import com.chozoi.authenservice.domain.entities.Profile;
import com.chozoi.authenservice.domain.entities.User;
import com.chozoi.authenservice.domain.services.BaseService;
import com.chozoi.authenservice.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/authens")
public class AuthenController {

    @Autowired
    private UserService userService;
    @Autowired
    private BaseService baseService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
           return userService.createUser(userDTO);
    }

        @PostMapping(value = "/login")
        public ResponseEntity<?> login (@RequestBody UserDTO userDTO){
            return userService.login(userDTO.getUserName(), userDTO.getPassWord());
        }

        @DeleteMapping(value = "/logout")
        public ResponseEntity<?> logout (@RequestHeader("token") String token){
             return userService.logout(token);
        }
    }
