package edu.mum.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.domain.CustomUserPrincipal;
import edu.mum.domain.Faculty;
import edu.mum.domain.UserProfile;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;
import edu.mum.service.RoleService;
import edu.mum.service.SpecializationsService;
import edu.mum.service.UserProfileService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	FacultyService facultyService;
	@Autowired
	RoleService roleService;
	@Autowired
	SpecializationsService specializationsService;
	@Autowired
	CourseService courseService;
	@Autowired
	UserProfileService userProfileService;
	

	// only admin can add new Faculty

	// @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/add")
	public String addFaculty(@ModelAttribute("newFaculty") Faculty faculty, Model model) {
		model.addAttribute("userTypeList", roleService.getAll());
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		model.addAttribute("courseList",courseService.getAllCourser());
         System.out.println("userName "+userProfileService.LoggedInUser().getFirstName());
         System.out.println("loggedUser Id: "+userProfileService.LoggedInUser().getId());
		return "addFaculty";
	}

	@PostMapping(value ="/add")
	public String saveFaculty(@Valid @ModelAttribute("newFaculty") Faculty faculty, BindingResult error)
	{
		if (error.hasErrors())

		{
			return "addFaculty";
		}

		System.out.println("before");
		faculty.getUserProfile().setUserStatus("Active");
		System.out.println("faculty" + faculty.getUserProfile().getFirstName());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		faculty.getUserProfile().setPassword(passwordEncoder.encode(faculty.getUserProfile().getPassword()));

		System.out.println("password string:  " + passwordEncoder.encode(faculty.getUserProfile().getPassword()));
		System.out.println("id f:"+faculty.getId());
		System.out.println("id user :"+faculty.getUserProfile().getId());
		facultyService.saveFaculty(faculty);
		System.out.println(faculty.getUserProfile().getFirstName());
		return "redirect:/all";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteFaculty(@PathVariable("id") Long id, Model model) {
		facultyService.deleteFaculty(id);

		return "redirect:/all";
	}

	@GetMapping(value = "/update/{id}")
	public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("newFaculty", facultyService.getFacultyById(id));
		return "addFaculty";
	}

	@GetMapping(value = "/all")
	public String ManageStudent(Model model) {
		model.addAttribute("faculties", facultyService.getAllfaculty());
		
		return "manageFaculty";
	}
	
	
	
	@GetMapping(value = "/home")
	public String facultyHome(Model model,Map map) {
		model.addAttribute("loggedInUser",map.get("username"));
		return "facultyhome";
	}
	
	@GetMapping(value = "/nav")
	public String nav(Model model) {
		
		return "sidebar";
	}
}