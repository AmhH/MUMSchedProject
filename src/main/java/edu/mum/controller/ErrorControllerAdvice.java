package edu.mum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView accessDenied(){
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("error","Sorry! You don't have Access previlage for this page");
		mav.setViewName("error/403");
		return mav;
	}
	
}
