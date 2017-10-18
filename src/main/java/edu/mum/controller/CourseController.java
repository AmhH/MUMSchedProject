package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.domain.Course;
import edu.mum.service.CourseService;
import edu.mum.service.SpecializationsService;

@Controller
public class CourseController {
 
	@Autowired
	CourseService courseService;
	
	@Autowired
	SpecializationsService specializationsService;
	
	@GetMapping("/courses")
	public String courseList( @ModelAttribute("newCourse") Course course,Model model)
	{
		model.addAttribute("courses",courseService.getAllCourser());
		model.addAttribute("noPre","  ");
		return "manageCourse";
		
	}
	
	@GetMapping("/addcourse")
	public String addCourse(@ModelAttribute("newCourse") Course course,Model model)
	{
		model.addAttribute("courseList",courseService.getAllCourser());
		model.addAttribute("areaList",specializationsService.findAllspecalization());
		return "addCourse";
		
	}
	
	@PostMapping("/addcourse")
	public String saveCourse(@Valid @ModelAttribute("newCourse") Course course,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return "addCourse";
		}
		if(course.getPrerequisite()==null){
			course.setIsPreReq(true);
			System.out.println("True");
		}
		courseService.save(course);
		
		return "manageCourse";
		
	}
	
}
