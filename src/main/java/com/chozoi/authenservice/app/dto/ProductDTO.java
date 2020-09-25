package com.chozoi.authenservice.app.dto;

import com.chozoi.authenservice.domain.entities.categories.CategoryAttributeValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {
    @JsonIgnore
    private Integer id;
    @NotBlank
    @Length(min =0,max =20)
    private String name;
    @NotBlank
    @Length(min =0)
    private Integer categoryID;


    @NotBlank
    @Length(min =0,max =5)
    private Double price;
    @NotBlank
    @Length(min =0,max =5)
    private Integer quantity;
    @NotBlank
    @Length(min =0,max =2)
    private Integer rate;
    private String state;
    @NotBlank
    @Length(min =0)
    private String description_Long;
    @NotBlank
    @Length(min =0)
    private String description_Short;

    private String mainImage;
    @JsonIgnore
    private Integer userID;
    private String createBy;
    private String updateBy;
    private List<QuestionDTO> questionDTOS;

    private List<CategoryAttributeValueDTO> value;

    private List <Integer> categoryAttributeValueID;
}
