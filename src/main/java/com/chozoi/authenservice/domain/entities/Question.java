package com.chozoi.authenservice.domain.entities;

import com.chozoi.authenservice.domain.entities.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "dbo_question")
public class Question extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answerList;

    @Column(name = "text")
    private String text;

    @Column(name = "userID")
    private Integer userID;

}
