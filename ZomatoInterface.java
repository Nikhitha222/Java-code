package com.example.Frontend.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Frontend.Blueprint.Zomato;

public interface ZomatoInterface extends JpaRepository<Zomato, Integer> {

}
