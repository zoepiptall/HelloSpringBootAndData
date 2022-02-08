package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Food;

public interface FoodDao extends JpaRepository<Food, Integer> {
	
	public List<Food> findAll();
	public Food findByFoodName(String foodName);
	public List<Food> findByCalories (int cal);
	public Food findByFoodNameAndCalories (String name, int cal);

}
