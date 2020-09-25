package com.chozoi.authenservice.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDTO {
    @JsonIgnore
    private Integer id;
    @NotBlank
    @Length(min =0,max =20)
    private String fullName;
    @NotBlank
    @Length(min =0)
    private Integer age;
    @NotBlank
    @Length(min =0)
    private Integer gender;
    private Date birthDay;
    @NotBlank
    @Length(min =0,max =20)
    private String email;

}
