package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
