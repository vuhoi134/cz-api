package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.categories.CategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryAttributeRepository extends JpaRepository<CategoryAttribute,Integer> {

}
