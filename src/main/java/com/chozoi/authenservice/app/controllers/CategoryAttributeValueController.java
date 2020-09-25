package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.CategoryAttributeValueDTO;
import com.chozoi.authenservice.domain.services.category.CategoryAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/category-attribute-value")
public class CategoryAttributeValueController {
  @Autowired private CategoryAttributeValueService categoryAttributeValueService;

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(
      @RequestHeader("token") String token, @Valid @RequestBody CategoryAttributeValueDTO dto) {
    return categoryAttributeValueService.create(token, dto);
  }

  @PutMapping(value = "{categoryAttributeValueID}")
  public ResponseEntity<?> edit(
      @RequestHeader("token") String token,
      @Valid @RequestBody CategoryAttributeValueDTO dto,
      @PathVariable Integer categoryAttributeValueID) {
    return categoryAttributeValueService.edit(token, categoryAttributeValueID, dto);
  }

  @DeleteMapping(value = "{categoryAttributeValueID}")
  public ResponseEntity<?> delete(
      @RequestHeader("token") String token, @PathVariable Integer categoryAttributeValueID) {
    return categoryAttributeValueService.delete(token, categoryAttributeValueID);
  }
}
