package edu.mum.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import edu.mum.domain.Course;
import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;
import edu.mum.service.SectionsService;

@Controller
public class SectionController {

	@Autowired
	private SectionsService sectionService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FacultyService facultyService;
	@RequestMapping({"/addSection"})
	public  String viewSection(Model model){
		List<Course> courses = courseService.getAllCourser();
		List<Faculty> faculties = facultyService.getAllfaculty();
		model.addAttribute("courses", courses);
		model.addAttribute("faculties", faculties);
		return "addSection";
	}	
	@RequestMapping(value= {"/addSection"},method=RequestMethod.POST)
	public @ResponseBody RedirectView saveSectioin(@RequestParam String blockMonth, @ModelAttribute("section") Section newSection){
		sectionService.saveSection(newSection, blockMonth);
		return new RedirectView("/allEntry");
	}
	
	

}
