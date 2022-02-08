package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name="Food")
public class Food {

	public Food() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int foodId;
	
	@Column(name="food_name", unique=true, nullable=false)
	private String foodName;
	
	@Column(name="calories", unique=true, nullable=false)
	private int calories;

	public Food(String foodName, int calories) {
		super();
		this.foodName = foodName;
		this.calories = calories;
	}
	
	
}
