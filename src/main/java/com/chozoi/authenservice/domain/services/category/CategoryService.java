package com.chozoi.authenservice.domain.services.category;

import com.chozoi.authenservice.app.dto.*;
import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.exceptions.UserNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.CategoryRepository;
import com.chozoi.authenservice.domain.repositories.ProductRepository;
import com.chozoi.authenservice.domain.services.BaseService;
import com.chozoi.authenservice.domain.services.CacheManagerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService extends BaseService {
  @Autowired private CacheManagerService cacheManagerService;
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private ProductRepository productRepository;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public ResponseEntity createListCategories() {
    try {
      long totalCategories = getTotalCategories();
      List<Category> categoryList = new ArrayList<>();
      for (long i = totalCategories + 1; i <= totalCategories + 10; i++) {
        Category category =
            Category.builder().name("Category" + i).state("Còn hàng").createAt(new Date()).build();
        categoryList.add(category);
      }
      categoryRepository.saveAll(categoryList);
      return new ResponseEntity<>("Fake Data thành công", HttpStatus.CREATED);
    } catch (Exception e) {
      new ResponseEntity<>("Fake Data thất bại", HttpStatus.NOT_ACCEPTABLE);
    }
    return null;
  }

  @SneakyThrows
  public ResponseEntity<?> create(String token, CategoryDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Category category = new Category();
    category.setName(dto.getName());
    category.setLevel(dto.getLevel());
    category.setState(dto.getState());
    category.setCreateAt(new Date());
    if (Objects.nonNull(dto.getParentId())) {
      Category parent = categoryRepository.findCategoriesById(dto.getParentId());
      if (parent != null) {
        category.setParentId(parent);
      }
    }
    categoryRepository.save(category);
    return ResponseEntity.ok("Success!");
  }

  public ResponseEntity deleteCategory(String token, int categoryID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Category category = categoryRepository.findById(categoryID).orElse(null);
    if (Objects.isNull(category)) {
      throw new UserNotFoundException("Category not found");
    }
    categoryRepository.deleteById(categoryID);
    return new ResponseEntity<>("Delete success category", HttpStatus.OK);
  }

  public ResponseEntity infoCategory(String token, int categoryID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Category category = categoryRepository.findById(categoryID).orElse(null);
    if (Objects.isNull(category)) {
      throw new UserNotFoundException("Category not found");
    }
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setName(category.getName());
    categoryDTO.setState(category.getState());
    return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
  }

  public ResponseEntity<?> updateCategory(String token, Integer categoryID, CategoryDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Category category = categoryRepository.findById(categoryID).orElse(null);
    if (Objects.isNull(category)) {
      throw new UserNotFoundException("Category not found");
    }
    category.setName(dto.getName());
    category.setUpdateAt(new Date());
    category.setState(dto.getState());
    categoryRepository.save(category);
    return new ResponseEntity<>("Update success category", HttpStatus.OK);
  }

  public ResponseEntity listCategory(String token) {

    List<Category> categoryList = categoryRepository.findAll();
    List<CategoryDTO> dtoList = new ArrayList<>();
    for (Category category : categoryList) {
      CategoryDTO dto = modelMapper.categoryToResponse(category);
      if (dto.getLevel().equals(0)) {
        dtoList.add(dto);
      }
    }
    return ResponseEntity.ok(dtoList);
  }

  public ResponseEntity<?> findAllByCategory(String token) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    List<Category> categoryList = categoryRepository.findCategoryByIdNotNull();
    if (Objects.isNull(categoryList)) {
      throw new TokenNotFoundException("List trống");
    }
    return ResponseEntity.ok(convertToCategoriesDTOs(categoryList));
  }

  public long getTotalCategories() {
    return categoryRepository.getTotalCategories();
  }

  public Category findOne(int categoryId) {
    return categoryRepository.findById(categoryId).orElse(null);
  }

  public List<Category> getListAllCategories() {
    try {
      return categoryRepository.findAll();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
