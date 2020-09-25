package com.chozoi.authenservice.app.controllers;

import com.chozoi.authenservice.app.dto.ProductDTO;
import com.chozoi.authenservice.domain.repositories.ProductRepository;
import com.chozoi.authenservice.domain.services.category.CategoryService;
import com.chozoi.authenservice.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/products")
public class ProductController {
  @Autowired private ProductService productService;
  @Autowired private CategoryService categoryService;
  @Autowired private ProductRepository productRepository;

  @GetMapping("/fake")
  public ResponseEntity<?> fake(@RequestHeader("token") String token) {
    return productService.createListProduct(token);
  }

  @PostMapping()
  public ResponseEntity<?> create(
      @RequestHeader("token") String token, @RequestBody ProductDTO productDTO) {
    return productService.createProduct(token, productDTO);
  }

  @DeleteMapping("{productID}")
  public ResponseEntity<?> delete(
      @RequestHeader("token") String token, @PathVariable Integer productID) {
    return productService.deleteProduct(token, productID);
  }

  @GetMapping(value = "{productID}")
  public ResponseEntity<?> infoProduct(
      @RequestHeader("token") String token, @PathVariable Integer productID) {
    return productService.infoProduct(token, productID);
  }

  @PutMapping(value = "{productID}")
  public ResponseEntity<?> update(
      @RequestHeader("token") String token,
      @PathVariable Integer productID,
      @RequestBody ProductDTO dto) {
    return productService.update(token, productID, dto);
  }

  @GetMapping(value = "/list")
  public ResponseEntity<?> list(@RequestHeader("token") String token) {
    return productService.allProduct(token);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<?> page(
      @RequestParam(name = "curunpage", required = false, defaultValue = "0") Integer curunpage,
      @RequestParam(name = "totalpage", required = false, defaultValue = "5") Integer totalpage) {
    return productService.pageProduct(curunpage, totalpage);
  }

  @GetMapping(value = "findCategoryID/{categoryID}")
  private ResponseEntity<?> paren(
      @RequestHeader("token") String token, @PathVariable int categoryID) {
    return productService.findByCategory(token, categoryID);
  }
}
