package com.chozoi.authenservice.domain.entities.categories;

import com.chozoi.authenservice.app.dto.CategoryAttributeValueDTO;
import com.chozoi.authenservice.domain.entities.categories.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_category_attribute")
public class CategoryAttribute {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "categoryAttribute")
    private List<CategoryAttributeValue> listValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
