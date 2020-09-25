package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.UserDTO;
import com.chozoi.authenservice.domain.services.CacheManagerService;
import com.chozoi.authenservice.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CacheManagerService cacheManagerService;

    @GetMapping(value = "info")
    public ResponseEntity<?> Info(@RequestHeader("token") String token) {
        return userService.getUserInfo(token);

    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestHeader("token") String token) {
        return userService.deleteUser(token);

    }
    @PutMapping(value = "/edit")
    public ResponseEntity<?> edit(@RequestHeader("token") String token, @RequestBody UserDTO userDTO) {
        return userService.update(userDTO, token);

    }

}
