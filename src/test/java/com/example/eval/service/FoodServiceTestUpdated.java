package com.example.eval.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.model.Food;
import com.example.repository.FoodDao;
import com.example.service.FoodService;

@SpringBootTest
public class FoodServiceTestUpdated {
	
	@Mock
	private FoodDao fDao;
	
	@InjectMocks
	private FoodService fServ;
	
	private Food food;
	
	@BeforeEach
	public void setUp() throws Exception {
		fServ = new FoodService(fDao);
		food = new Food(3, "Pizza", 1500);
		when(fDao.findByFoodName("Pizza")).thenReturn(food);
		when(fDao.findByFoodName("nope")).thenReturn(null);
	}
	
	@Test
	public void testFindByNameSuccess() {
		assertEquals(fServ.getFoodByName("Pizza"), food);
	}
	
	@Test
	public void testFindByNameFailure() {
		assertEquals(fServ.getFoodByName("nope"), null);
	}
	
	
	
}

