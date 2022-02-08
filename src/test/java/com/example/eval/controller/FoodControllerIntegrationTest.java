package com.example.eval.controller;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.model.Food;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodControllerIntegrationTest {

	private MockMvc mock;
	
	private Food food;
	
	@Autowired
	private WebApplicationContext wac;
	
	
	@Before
	public void initBeforeTest() throws Exception{
		food = new Food(1, "Pizza", 1500);
		mock = MockMvcBuilders.webAppContextSetup(wac).build();
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
