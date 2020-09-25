package com.chozoi.authenservice.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {

      @JsonIgnore
      private int id;
      @NotBlank
      @Length(min =0,max = 5)
      private String userName;

      @NotBlank
      @Length(min =0,max = 10)
      private String passWord;

      @NotBlank
      @Length(min =0,max = 10)
      private Integer profileID;

}
