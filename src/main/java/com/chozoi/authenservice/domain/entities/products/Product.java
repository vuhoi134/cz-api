package com.chozoi.authenservice.domain.entities.products;

import com.chozoi.authenservice.domain.entities.*;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttributeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_product")
public class Product extends BaseEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Id
  private Integer id;



  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
  private List<ProductImage> productImageList = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
  private List<Rate> rateList = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
  private List<Question> questionList = new ArrayList<>();


  @ManyToMany
  @JoinTable(name = "dbo_product_attribute_value",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "category_attribute_value_id")
  )
  private List<CategoryAttributeValue> categoryAttributeValues;



  @Column(name = "name")
  private String name;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price")
  private Double price;

  @Column(name = "rate")
  private Integer rate;

  @Column(name = "description_Short")
  private String descriptionShort;

  @Column(name = "description_long")
  private String descriptionLong;

  @Column(name = "state")
  private String state;

  @Column(name = "discount")
  private Integer discount;

  @Column(name = "create_By")
  private String createBy;

  @Column(name = "update_By")
  private String updateBy;

  @Column(name = "main_Image")
  private String mainImage;
}
