package com.chozoi.authenservice.domain.services.category;

import com.chozoi.authenservice.app.dto.CategoryAttributeDTO;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttribute;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.exceptions.UserNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.CategoryAttributeRepository;
import com.chozoi.authenservice.domain.repositories.CategoryRepository;
import com.chozoi.authenservice.domain.services.CacheManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryAttributeService {

  @Autowired private CacheManagerService cacheManagerService;
  @Autowired private CategoryAttributeRepository categoryAttributeRepository;
  @Autowired private CategoryRepository categoryRepository;

  public ResponseEntity<?> create(String token, CategoryAttributeDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Category category = categoryRepository.findById(dto.getCategoryID()).orElse(null);
    if (Objects.isNull(category)) {
      throw new UserNotFoundException("Không có đâu bạn");
    }
    CategoryAttribute categoryAttribute = new CategoryAttribute();
    categoryAttribute.setName(dto.getName());
    categoryAttribute.setCategory(category);
    categoryAttributeRepository.saveAndFlush(categoryAttribute);
    return ResponseEntity.ok("Success!");
  }

  public ResponseEntity<?> delete(String token, Integer attributeID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    CategoryAttribute categoryAttribute =
        categoryAttributeRepository.findById(attributeID).orElse(null);
    if (Objects.isNull(categoryAttribute)) {
      throw new UserNotFoundException("Không tìm thấy");
    }
    categoryAttributeRepository.delete(categoryAttribute);
    return new ResponseEntity<>("Delete success", HttpStatus.OK);
  }

  public ResponseEntity<?> edit(String token, Integer attributeID, CategoryAttributeDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    Category category = categoryRepository.getOne(dto.getCategoryID());
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    CategoryAttribute attribute = categoryAttributeRepository.findById(attributeID).orElse(null);
    if (Objects.isNull(attribute)) {
      throw new UserNotFoundException("Không tìm thấy");
    }
    attribute.setName(dto.getName());
    attribute.setCategory(category);
    categoryAttributeRepository.saveAndFlush(attribute);
    return new ResponseEntity<>("Edit success", HttpStatus.OK);
  }
}
