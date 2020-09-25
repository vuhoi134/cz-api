package com.chozoi.authenservice.app.base.reponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResult {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer categoryID;
    @JsonIgnore
    private Integer countryID;
    @JsonIgnore
    private Integer guaranteeID;
    @JsonIgnore
    private String description_Long;
    @JsonIgnore
    private String description_Short;
    @JsonIgnore
    private Integer userID;
    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private String updateBy;
    @JsonIgnore
    private Integer quantity;
    @JsonIgnore
    private Integer rate;

    private Double price;
    private String state;
    private String countryName;
    private Integer discount;
    private String mainImage;
    private String name;
}
