package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.categories.CategoryAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryAttributeValueRepository extends JpaRepository<CategoryAttributeValue,Integer> {
}
