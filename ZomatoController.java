package com.example.Frontend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Frontend.Blueprint.Administrator;
import com.example.Frontend.Blueprint.Zomato;
import com.example.Frontend.Service.ZomatoService;

@Controller
public class ZomatoController {
private ZomatoService restaurantService;
	
	public ZomatoController(ZomatoService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping("/viewAllRestaurants")
	public String listOfRestaurants(Model model) {
	model.addAttribute("restaurantsList",restaurantService.fetchAllRestaurents());
	return "Restaurant"; //html file name
	}
	
	@GetMapping("/createNewRestaurant")
	public String createRestaurant(Model model) {
		Zomato restobject=new Zomato();
		model.addAttribute("restaurantObject",restobject);
		return "InsertRestaurant";
	}
	
	@PostMapping("/insertRestaurants")
	public String insertRestaurant (@ModelAttribute("restaurantObject") Zomato obj) {
	restaurantService.saveRestaurents(obj); //object insert into db by using save
	return "redirect:/viewAllRestaurants";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRestaurant(@PathVariable int id) {
		restaurantService.deleteRestaurents(id); 
		return "redirect:/viewAllRestaurants";
	}
	@PostMapping("/updateAndSave/{id}")
	public String updateNewValIntoDb(@PathVariable int id,
			@ModelAttribute("updateRestaurant") Zomato newValFrmFE) throws Exception {
		Zomato existingRestaurant = restaurantService.fetchById(id);
		 existingRestaurant.setRestaurantName((newValFrmFE.getRestaurantName()));
		 existingRestaurant.setRating(newValFrmFE.getRating());
		 existingRestaurant.setAverageCost(newValFrmFE.getAverageCost());
	
		 restaurantService.saveRestaurents( existingRestaurant);
	return "redirect:/viewAllRestaurants";
	}
	
	@GetMapping("/update/{id}")
	public String updateRestaurantRecord(@PathVariable int id, Model model) throws Exception {
		model.addAttribute("updateRestaurant", restaurantService.fetchById(id));
		return "UpdateRestaurant";
	}

	@GetMapping("/contact")
	public String ContactPage() {
		return "Contact";

}

	@GetMapping("/register")
	public String registerPage() {
		return "Register";
}
	@GetMapping("/logout")
	public String logoutPage() {
		return "Logout";
}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		Administrator admin= new Administrator();
	    model.addAttribute("adminObj",admin);
		return "Login";
	}
	@GetMapping("/validatingData")
	public String validateLogin(@ModelAttribute("adminObj")Administrator adminDetails) {
	if(adminDetails.getUserName().equals("adminlogin")&& adminDetails.getPassword().equals("loginPassword")) {
		return "redirect:/viewAllRestaurants";
	}
	else {
		return "redirect:/login";
	}
	}
	}
