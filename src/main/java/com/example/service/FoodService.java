package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Food;
import com.example.repository.FoodDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class FoodService {
	
	public FoodService() {
		// TODO Auto-generated constructor stub
	}
	
	private FoodDao foodDao;

	
	public Food getFoodByName(String name) {
		return foodDao.findByFoodName(name);
	}
	
	public List<Food> getAllFood(){
		return foodDao.findAll();
	}
	
	public void insertFood(Food food) {
		foodDao.save(food);  //the Crud repo save method is actually saveOrUpdate (i.e. call the save method to update)
	}
	
	public List<Food> getByCalorie (int cal){
		return foodDao.findByCalories(cal);
	}
	
	public Food getFoodByNameAndCal (String name, int cal) {
		return foodDao.findByFoodNameAndCalories(name, cal);
	}
	
	public void deletFoodByName(Food food) {
		foodDao.delete(food);
	}
}
