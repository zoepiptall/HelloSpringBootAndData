package com.example.eval.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Food;
import com.example.repository.FoodDao;
import com.example.service.FoodService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceTest {

	@MockBean
	private FoodDao fDao;
	
	private FoodService fServ;
	
	private Food food;
	
	@Before
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
