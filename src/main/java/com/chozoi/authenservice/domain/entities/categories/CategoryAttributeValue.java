package com.chozoi.authenservice.domain.entities.categories;

import com.chozoi.authenservice.domain.entities.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_category_attribute_value")
public class CategoryAttributeValue {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Id
  private int id;

  @Column(name = "value")
  private String value;

  @ManyToMany(mappedBy = "categoryAttributeValues")
  private List<Product> product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_attribute_id")
  private CategoryAttribute categoryAttribute;

}
