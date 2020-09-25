package com.chozoi.authenservice.domain.services.category;

import com.chozoi.authenservice.app.dto.CategoryAttributeValueDTO;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttribute;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttributeValue;
import com.chozoi.authenservice.domain.exceptions.ResourceNotFoundException;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.exceptions.UserNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.CategoryAttributeRepository;
import com.chozoi.authenservice.domain.repositories.CategoryAttributeValueRepository;
import com.chozoi.authenservice.domain.services.BaseService;
import com.chozoi.authenservice.domain.services.CacheManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryAttributeValueService extends BaseService {
  @Autowired private CacheManagerService cacheManagerService;
  @Autowired private CategoryAttributeRepository categoryAttributeRepository;
  @Autowired private CategoryAttributeValueRepository categoryAttributeValueRepository;

  public ResponseEntity<?> create(String token, CategoryAttributeValueDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    CategoryAttribute attribute =
        categoryAttributeRepository.findById(dto.getCategoryAttributeID()).orElse(null);
    if (Objects.isNull(attribute)) {
      throw new UserNotFoundException("Không có đâu bạn");
    }
    CategoryAttributeValue categoryAttributeValue = new CategoryAttributeValue();
    categoryAttributeValue.setValue(dto.getValue());
    categoryAttributeValue.setCategoryAttribute(attribute);
    categoryAttributeValueRepository.saveAndFlush(categoryAttributeValue);
    return ResponseEntity.ok("Success!");
  }

  public ResponseEntity<?> delete(String token, Integer categoryAttributeValueID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    CategoryAttributeValue categoryAttributeValue =
        categoryAttributeValueRepository.findById(categoryAttributeValueID).orElse(null);
    if (Objects.isNull(categoryAttributeValue)) {
      throw new ResourceNotFoundException(
          "AttributeValue not found ID : " + categoryAttributeValueID);
    }
    categoryAttributeValueRepository.deleteById(categoryAttributeValueID);
    return ResponseEntity.ok(true);
  }

  public ResponseEntity<?> edit(
      String token, Integer categoryAttributeValueID, CategoryAttributeValueDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    CategoryAttribute attribute = categoryAttributeRepository.getOne(dto.getCategoryAttributeID());
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    CategoryAttributeValue categoryAttributeValue =
        categoryAttributeValueRepository.findById(categoryAttributeValueID).orElse(null);
    if (Objects.isNull(categoryAttributeValue)) {
      throw new ResourceNotFoundException(
          "AttributeValue not found ID : " + categoryAttributeValueID);
    }
    categoryAttributeValue.setValue(dto.getValue());
    categoryAttributeValue.setCategoryAttribute(attribute);
    categoryAttributeValueRepository.saveAndFlush(categoryAttributeValue);
    return ResponseEntity.ok(true);
  }
}
