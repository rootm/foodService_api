package com.food.foodService.respositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.food.foodService.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ 'username' : ?0, 'password' : ?1 }")
    User findForAuth(String user, String pass);
	
	@Query("{ 'username' : ?0}")
    List<User> findByUserName(String user);
}
