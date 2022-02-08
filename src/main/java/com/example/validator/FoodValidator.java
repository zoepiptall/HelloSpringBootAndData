package com.example.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.example.model.Food;

public class FoodValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Food.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "foodName", "foodName.empty", "foodName must have a value");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "calories", "field.required", "Calorises must have a value");
		Food food = (Food) target;
		if (food.getCalories()<0) {
			errors.rejectValue("calories", "negative.value", "wishful thinking");
		}else if(food.getCalories() > 3000) {
			errors.rejectValue("calories", "too.dang.much", "Are you sure about that number");
		}
		
	}

}
