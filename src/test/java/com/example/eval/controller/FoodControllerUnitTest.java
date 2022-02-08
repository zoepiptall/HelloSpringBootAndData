package com.example.eval.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.FoodController;
import com.example.model.Food;
import com.example.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodControllerUnitTest {

	@MockBean
	private FoodService fServ;
	
	private FoodController fCont;
	
	private Food food;
	
	private MockMvc mock;
	
	@Before
	public void setup() throws Exception{
		food = new Food (3, "Pizza", 1500);
		fCont = new FoodController(fServ);
		mock = MockMvcBuilders.standaloneSetup(fCont).build();
		doNothing().when(fServ).insertFood(food);
		doNothing().when(fServ).deletFoodByName(food);
		when(fServ.getFoodByName("Pizza")).thenReturn(food);
	}
	
	@Test
	public void postFoodTest() throws Exception{
		mock.perform(post("/foods/validate").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(food)))
		.andExpect(status().isCreated()).andExpect(jsonPath("$").value("Resource created"));
		
	}
	
	@Test
	public void deleteFoodTest() throws Exception{
		mock.perform(delete("/foods/{foodName}", food.getFoodName())).andExpect(status().isGone())
		.andExpect(jsonPath("$").value("Resource Deleted"));
	}
	
	@Test
	public void getFoodTest() throws Exception{
		mock.perform(get("/foods/{foodName}", food.getFoodName())).andExpect(status().isOk())
		.andExpect(jsonPath("$.foodName", is(food.getFoodName())))
		.andExpect(jsonPath("$.calories", is(food.getCalories())));
	}
	
	@Test
	public void putNotAllowedTest() throws Exception{
		mock.perform(put("/foods/validate")).andDo(print()).andExpect(status().isMethodNotAllowed());
	}
	
	

	
}
