package com.example.controller;

import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Food;
import com.example.service.FoodService;
import com.example.validator.FoodValidator;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/foods")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class FoodController {
	
	private FoodService foodServ;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new FoodValidator());
	}
	
	public FoodController() {
		// TODO Auto-generated constructor stub
	}
	
//	@PostMapping()
//	public ResponseEntity<String> insertFood(@RequestBody LinkedHashMap<String, String> fMap){
//		Food food = new Food(fMap.get("foodName"), Integer.parseInt(fMap.get("calories")));
//		foodServ.insertFood(food);
//		return new ResponseEntity<>("Resource was created: ", HttpStatus.CREATED);
//	}
	
	@PostMapping("/validate")
	public ResponseEntity<String> insertFood2(@RequestBody @Valid Food food, BindingResult result){
		System.out.println(food);
		if(result.hasErrors()) {
			System.out.println("There were some errors");
			System.out.println(result.getFieldErrors());
			return new ResponseEntity<>(result.getFieldError().getCode() + " " + result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		foodServ.insertFood(food);
		return new ResponseEntity<>("Resource created", HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Food> getFoodByName(@PathVariable("name") String name){
		return new ResponseEntity<>(foodServ.getFoodByName(name), HttpStatus.OK);
	}

	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteFoodByName(@PathVariable("name") String name){
		Food food = foodServ.getFoodByName(name);
		foodServ.deletFoodByName(food);
		return new ResponseEntity<>("Resource Deleted", HttpStatus.GONE);
	}
}
