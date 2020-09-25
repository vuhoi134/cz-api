package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Query("select count (p.id) from dbo_product p")
  long getTotalProduct();

  //    @Query("select count (p.id) from dbo_product p")
  //    Product getTotalProducts();

  Product findProductByIdAndUserId(int productId, int userId);

  List<Product> findAllByCategory_Id(Integer categoryID);

}
