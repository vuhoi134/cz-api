package com.chozoi.authenservice.app.dto;

import com.chozoi.authenservice.domain.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDTO {
  private Integer id;
  private Integer level;
  private Integer parentId;

  @NotBlank
  @Length(min = 0, max = 20)
  private String name;

  @NotBlank
  @Length(min = 0, max = 10)
  private String state;

  private Date createAt;
  private Date updateAt;

  private List<ProductDTO> productDTOList;

  private List<CategoryAttributeDTO> attribute;

  private List<CategoryDTO> childen;
}
