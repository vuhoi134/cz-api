package com.chozoi.authenservice.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryAttributeDTO {
  private int id;
  @NotBlank
  private int categoryID;
  @NotBlank
  @Length(min =0,max = 50)
  private String name;

  private List<CategoryAttributeValueDTO> value;
}
