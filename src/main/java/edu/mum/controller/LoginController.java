package edu.mum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class LoginController {
	

     @GetMapping("/guestPage")
     public String noRoleUserPage(){
    	 
    	 return "guestPage";
     } 
     

  }

