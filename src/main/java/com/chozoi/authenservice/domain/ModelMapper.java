package com.chozoi.authenservice.domain;

import com.chozoi.authenservice.app.base.reponse.ProductResult;
import com.chozoi.authenservice.app.dto.*;
import com.chozoi.authenservice.domain.entities.*;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttribute;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttributeValue;
import com.chozoi.authenservice.domain.entities.products.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({
    @Mapping(target = "categoryID", source = "category.id"),
    @Mapping(target = "userID", source = "user.id", ignore = true),
    @Mapping(target = "value", source = "categoryAttributeValues"),
    @Mapping(target = "questionDTOS", source = "questionList")
  })
  ProductDTO productToResponse(Product product);

  @Mappings({
    @Mapping(target = "categoryID", source = "category.id"),
    @Mapping(target = "userID", source = "user.id"),
  })
  ProductResult productResultToResponse(Product product);

  @Mappings({
    @Mapping(target = "parentId", source = "parentId.id"),
    @Mapping(target = "childen", source = "children"),
    @Mapping(target = "productDTOList", source = "productList"),
    @Mapping(target = "attribute", source = "listAttribute")
  })
  CategoryDTO categoryToResponse(Category category);

  @Mappings({
    @Mapping(target = "categoryAttributeID", source = "categoryAttribute.id"),
    @Mapping(target = "attribute", source = "categoryAttribute.name")
  })
  CategoryAttributeValueDTO valueToResponse(CategoryAttributeValue categoryAttributeValue);

  @Mappings({
    @Mapping(target = "value", source = "listValue"),
    @Mapping(target = "categoryID", source = "category.id")
  })
  CategoryAttributeDTO attributeToResponse(CategoryAttribute categoryAttribute);

  @Mappings({
    @Mapping(target = "productID", source = "product.id"),
    @Mapping(target = "answerListDTO", source = "answerList")
  })
  QuestionDTO questionToResponse(Question question);

  @Mappings({@Mapping(target = "questionID", source = "question.id")})
  AnswerDTO answerToResponse(Answer answer);

  @Mappings({@Mapping(target = "profileID", source = "profile.id")})
  UserDTO userToResponse(User user);

  ProfileDTO profileToResponse(Profile profile);
}
