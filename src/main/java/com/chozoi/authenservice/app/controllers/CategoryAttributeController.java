package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.CategoryAttributeDTO;
import com.chozoi.authenservice.domain.services.category.CategoryAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/category-attribute")
public class CategoryAttributeController {
  @Autowired private CategoryAttributeService categoryAttributeService;

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(
      @RequestHeader("token") String token, @Valid @RequestBody CategoryAttributeDTO dto) {
    return categoryAttributeService.create(token, dto);
  }

  @DeleteMapping("{attributeID}")
  public ResponseEntity<?> delete(
      @RequestHeader("token") String token, @PathVariable Integer attributeID) {
    return categoryAttributeService.delete(token, attributeID);
  }

  @PutMapping("{attributeID}")
  public ResponseEntity<?> edit(
      @RequestHeader("token") String token,
      @PathVariable Integer attributeID,
      @Valid @RequestBody CategoryAttributeDTO dto) {
    return categoryAttributeService.edit(token, attributeID, dto);
  }
}
