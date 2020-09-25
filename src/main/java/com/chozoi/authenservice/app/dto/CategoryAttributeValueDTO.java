package com.chozoi.authenservice.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryAttributeValueDTO {
  private Integer id;
  private String attribute;
  @NotBlank
  @Length(min =0,max = 50)
  private String value;
  private Integer categoryAttributeID;

}
