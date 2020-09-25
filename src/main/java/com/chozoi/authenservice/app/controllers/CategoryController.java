package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.CategoryDTO;
import com.chozoi.authenservice.domain.repositories.CategoryRepository;
import com.chozoi.authenservice.domain.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/category")
public class CategoryController {
  @Autowired private CategoryService categoryService;
  @Autowired private CategoryRepository categoryRepository;

  @GetMapping(value = "/fake")
  public ResponseEntity<?> createUser() {
    return categoryService.createListCategories();
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> createCategory(
          @RequestHeader("token") String token, @RequestBody CategoryDTO categoryDTO) {
    return categoryService.create(token, categoryDTO);
  }

  @DeleteMapping("{categoryID}")
  public ResponseEntity<?> deleteCategory(
          @RequestHeader("token") String token, @PathVariable int categoryID) {
    return categoryService.deleteCategory(token, categoryID);
  }

  @PutMapping(value = "{categoryID}")
  public ResponseEntity<?> editCategory(
          @RequestHeader("token") String token,
      @PathVariable int categoryID,
      @RequestBody CategoryDTO dto) {
    return categoryService.updateCategory(token, categoryID, dto);
  }

  @GetMapping(value = "/info/{categoryID}")
  public ResponseEntity<?> infoCategory(
          @RequestHeader("token") String token, @PathVariable int categoryID) {
    return categoryService.infoCategory(token, categoryID);
  }

  @GetMapping(value = "all-product-parent")
  private ResponseEntity<?> allParen(
          @RequestHeader("token") String token) {
    return categoryService.findAllByCategory(token);
  }

  @GetMapping(value = "listCategory")
  public ResponseEntity<?> listCategory(
          @RequestHeader("token") String token) {
    return categoryService.listCategory(token);
  }
}