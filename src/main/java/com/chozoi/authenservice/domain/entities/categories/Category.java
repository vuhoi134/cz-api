package com.chozoi.authenservice.domain.entities.categories;

import com.chozoi.authenservice.domain.entities.BaseEntity;
import com.chozoi.authenservice.domain.entities.products.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_category")
public class Category extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Product> productList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<CategoryAttribute> listAttribute = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id", nullable = true)
    @JsonBackReference
    private Category parentId;

    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<Category> children = new ArrayList<>();



    @Column(name = "level")
    private Integer level;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;
}
