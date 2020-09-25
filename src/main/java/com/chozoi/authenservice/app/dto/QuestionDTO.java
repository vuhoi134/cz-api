package com.chozoi.authenservice.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuestionDTO {
  private Integer id;

  private Integer productID;
  @NotBlank
  @Length(min =0,max = 5)
  private String text;
  private Integer userID;
  private List<AnswerDTO> answerListDTO;
}
