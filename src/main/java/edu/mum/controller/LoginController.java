package edu.mum.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import edu.mum.domain.CustomUserPrincipal;
import edu.mum.service.UserProfileService;


@Controller
	public class LoginController {
	
	/* @Autowired
	 UserProfileService userProfileService;
     @GetMapping("/login")
     public String login(){
    	 
    	 return "login";
     } */
     
  /*   @GetMapping(value="/login")
     public String homelogin(){
    	   @SuppressWarnings("unchecked")
		  List<CustomUserPrincipal> userLoggedRole=(List<CustomUserPrincipal>) userProfileService.getLoggedInUser().getAuthorities();
    	 
    	 
    			if (userLoggedRole.contains("ROLE_Faculty"))
    			{
    				return "factoryhome";
    			}
    			return "adminhome";
    			
     }*/
     
    			
    			
	
  }

