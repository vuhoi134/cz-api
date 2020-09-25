package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.app.dto.CategoryDTO;
import com.chozoi.authenservice.app.dto.ProductDTO;
import com.chozoi.authenservice.app.dto.UserDTO;
import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.*;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.entities.products.Product;
import com.chozoi.authenservice.domain.exceptions.UserNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.ProductRepository;
import com.chozoi.authenservice.domain.repositories.ProfileRepository;
import com.chozoi.authenservice.domain.repositories.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BaseService {

  @Autowired private UserRepository userRepository;
  @Autowired private ProfileRepository profileRepository;
  @Autowired private ProductRepository productRepository;
  @Autowired CacheManagerService cacheManager;
  @Autowired private ModelMapper modelMapper;

  protected TokenInfo isAuthen(String token) {
    TokenInfo tokenInfo = cacheManager.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new UserNotFoundException("Token not Fount!");
    }
    return tokenInfo;
  }

  protected List<ProductDTO> convertProductToDTOs(List<Product> models) {
    List<ProductDTO> responses = new ArrayList<>();

    if (models.size() > 0) {
      for (Product product : models) {
        ProductDTO productDTO = modelMapper.productToResponse(product);
        responses.add(productDTO);
      }
      return responses;
    }else return null;
  }


  protected List<CategoryDTO> convertToCategoriesDTOs(List<Category> models) {
    List<CategoryDTO> responses = new ArrayList<>();
    if (models.size() > 0) {
      for (Category categories: models) {
        CategoryDTO categoriesDTO = modelMapper.categoryToResponse(categories);
        responses.add(categoriesDTO);
      }
      return responses;
    }else return null;
  }

  protected boolean matching(String password, String otherPassword) {
    return BCrypt.checkpw(password, otherPassword);
  }

  public String convertPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(10));
  }

  protected String generateToken() {
    String token = RandomStringUtils.randomAlphabetic(8);
    return token;
  }
}
