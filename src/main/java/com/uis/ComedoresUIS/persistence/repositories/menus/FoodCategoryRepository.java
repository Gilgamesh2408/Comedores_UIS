package com.uis.ComedoresUIS.persistence.repositories.menus;

import com.uis.ComedoresUIS.persistence.models.menus.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

}
