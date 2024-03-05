package com.practice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	
	@RequestMapping(value="/about",method=RequestMethod.GET)
	public String about(Model model) {
		System.out.println("Inside about Handler");
		model.addAttribute("name","Durgesh Tiwari");
		model.addAttribute("currentDate",new Date().toLocaleString());
		
		
		return "about";
	}
	
	//handling iteration
   @GetMapping("/example-loop")
     public String iterateHandler(Model m) {
	   //create a list, send collection
	   List<String> names = List.of("Ankit","Laxmi","Karan","John");
	   m.addAttribute("names",names);
	   return "iterate";
		
	}
   
   //handling for conditional statements
   @GetMapping("/condition")
   public String conditionHandler(Model m) {
	   
	   System.out.println("Conditional handler executed..");
	   m.addAttribute("isActive",false);
	   m.addAttribute("gender","F");
	   List<Integer> list = List.of(12);
	   m.addAttribute("mylist",list);
	   return "condition";
   }
   
   //handler for including fragments
   @GetMapping("/service")
   public String servicesHandler(Model m) {
	   m.addAttribute("title","I like to eat samosa");
	   m.addAttribute("substitle",LocalDateTime.now().toString());
	   return "service";
   }
	
   //for new about
   @GetMapping("/newabout")
   public String newAbout() {
	   return "aboutnew";
   }
   
   @GetMapping("/contact")
   public String contact() {
	   return "contact";
   }
	

}
