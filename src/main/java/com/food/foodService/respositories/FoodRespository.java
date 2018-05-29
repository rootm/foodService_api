package com.food.foodService.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.food.foodService.models.Food;

@Repository
public interface FoodRespository extends MongoRepository<Food, String> {

}
