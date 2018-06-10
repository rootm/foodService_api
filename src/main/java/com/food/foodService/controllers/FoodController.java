package com.food.foodService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.food.foodService.models.Food;
import com.food.foodService.respositories.FoodRespository;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class FoodController {
	
	@Autowired
	FoodRespository foodRepo;
	
	@RequestMapping(method=RequestMethod.POST, value="/")
	public String addFood(@RequestBody Food food) {
		try {
			foodRepo.save(food);
		return "{ \"status\":200, \"message\": \"saved sucessfuly\" }";
		} catch (Exception e) {
			return "{ \"status\":500, \"message\": \"Error Saving\" }";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public List<Food> getAll() {
		
		
			return foodRepo.findAll();
		
		
	}
}
