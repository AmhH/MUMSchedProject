package edu.mum.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class LoginController {
	

     @GetMapping("/guestPage")
     public String noRoleUserPage(){
    	 
    	 return "guestPage";
     } 
     @GetMapping(value = "/home")
 	public String facultyHome(Model model, Map<?, ?> map) {
 		model.addAttribute("loggedInUser", map.get("username"));
 		return "home";
 	}
     
     @GetMapping(value = "/403")
  	public String accessDeniedError(Model model, Map<?, ?> map) {
  		
  		return "error/403";
  	}

  }

