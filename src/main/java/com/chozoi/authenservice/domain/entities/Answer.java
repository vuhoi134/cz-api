package com.chozoi.authenservice.domain.entities;

import com.chozoi.authenservice.domain.entities.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "dbo_answer")
public class Answer extends BaseEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Id
  private int id;

  @Column(name = "text")
  private String text;

  @Column(name = "userID")
  private Integer userID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;
}
