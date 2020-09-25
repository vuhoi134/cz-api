package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.ProfileDTO;
import com.chozoi.authenservice.app.dto.UserDTO;
import com.chozoi.authenservice.domain.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/info")
    public ResponseEntity<?> info(@RequestHeader("token") String token) {
        return profileService.getInfo(token);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<?> edit(@RequestHeader("token") String token, @RequestBody ProfileDTO dto) {
       return profileService.update(dto, token);

    }

}
