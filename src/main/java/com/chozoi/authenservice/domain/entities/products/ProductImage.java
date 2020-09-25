package com.chozoi.authenservice.domain.entities.products;

import com.chozoi.authenservice.domain.entities.BaseEntity;
import com.chozoi.authenservice.domain.entities.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_product_images")
public class ProductImage extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private int id;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "link")
    private String link;


}
