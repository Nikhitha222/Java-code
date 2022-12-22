package com.example.Frontend.Service;

import java.util.List;

import com.example.Frontend.Blueprint.Zomato;

public interface ZomatoService {
	Zomato saveRestaurents(Zomato obj);
	List<Zomato> fetchAllRestaurents();
	Zomato fetchById(int restaurantId) throws Exception;
	Zomato updateRestaurents(int restaurantId, Zomato newVal);
	void deleteRestaurents(int id);
	
}
