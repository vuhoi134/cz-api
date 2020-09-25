package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("select count(c.id) from dbo_category c")
    long getTotalCategories();

    List<Category> findByNameIgnoreCaseContaining(String name);

    List<Category> findAllByParentIdGreaterThanEqual(Integer parenID);

    Category findCategoriesById(Integer categoryID);

    List<Category> findCategoryByIdNotNull();
}
