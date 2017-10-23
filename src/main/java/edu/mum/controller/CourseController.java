package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.domain.Course;
import edu.mum.service.CourseService;
import edu.mum.service.SpecializationsService;

@Controller
@RequestMapping("/course")
public class CourseController {
 
	@Autowired
	CourseService courseService;
	
	@Autowired
	SpecializationsService specializationsService;
	
	@GetMapping("/all")
	public String courseList( @ModelAttribute("newCourse") Course course,Model model)
	{
		model.addAttribute("courses",courseService.getAllCourser());
		model.addAttribute("noPre","  ");
		return "manageCourse";
		
	}
	
	@GetMapping("/add")
	public String addCourse(@ModelAttribute("newCourse") Course course,Model model)
	{
		model.addAttribute("courseList",courseService.getAllCourser());
		model.addAttribute("areaList",specializationsService.findAllspecalization());
		return "addCourse";
		
	}
	
	@PostMapping("/add")
	public String saveCourse(@Valid @ModelAttribute("newCourse") Course course,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return "addCourse";
		}
		course.setIsPreReq(false);
		if(course.getPrerequisite()!=null){
			course.getPrerequisite().forEach(c->c.setIsPreReq(true));
		}
		courseService.save(course);
		
		return "redirect:/course/all";
		
	}
	
	@GetMapping(value = "/update/{id}")
	public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("newCourse", courseService.getCourseById(id));
		model.addAttribute("courseList",courseService.getAllCourser());
		model.addAttribute("areaList",specializationsService.findAllspecalization());
		return "addCourse";
	}
	
}
