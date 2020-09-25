package com.chozoi.authenservice.app.dto;

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
public class AnswerDTO {
    private Integer id;
    @NotBlank
    @Length(min =0,max = 50)
    private String text;
    private Integer userID;
    private Integer questionID;
}
