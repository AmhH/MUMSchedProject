package edu.mum.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.domain.Faculty;
import edu.mum.domain.Specialization;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;
import edu.mum.service.RoleService;
import edu.mum.service.SpecializationsService;
import edu.mum.service.UserProfileService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    Specialization spe;
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
		return "addFaculty";
	}

	@PostMapping(value = "/add")
	public String saveFaculty(@Valid @ModelAttribute("newFaculty") Faculty faculty, BindingResult error, Model model) {
		if (error.hasErrors()) {
			if (!model.containsAttribute("specializations")) {
				model.addAttribute("specializations", specializationsService.findAllspecalization());
			}
			if (!model.containsAttribute("userTypeList")) {
				model.addAttribute("userTypeList", roleService.getAll());
			}
			if (!model.containsAttribute("courseList")) {
				model.addAttribute("courseList", courseService.getAllCourser());
			}

			return "addFaculty";
		}

		System.out.println("before");
		faculty.getUserProfile().setUserStatus("Active");
		System.out.println("faculty" + faculty.getUserProfile().getFirstName());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		faculty.getUserProfile().setPassword(passwordEncoder.encode(faculty.getUserProfile().getPassword()));

		System.out.println("password string:  " + passwordEncoder.encode(faculty.getUserProfile().getPassword()));
		System.out.println("id f:" + faculty.getId());
		System.out.println("id user :" + faculty.getUserProfile().getId());
		facultyService.saveFaculty(faculty);

		return "redirect:/faculty/all";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteFaculty(@PathVariable("id") Long id, Model model) {
		facultyService.deleteFaculty(id);

		return "redirect:/faculty/all";
	}

	@GetMapping(value = "/update/{id}")
	public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("newFaculty", facultyService.getFacultyById(id));
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		model.addAttribute("courseList", courseService.getAllCourser());
		model.addAttribute("userTypeList", roleService.getAll());
		return "adminEditFaculty";
	}

	@GetMapping(value = "/all")
	public String ManageStudent(Model model) {
		model.addAttribute("faculties", facultyService.getAllfaculty());
		return "manageFaculty";
	}

	@GetMapping(value = "/home")
	public String facultyHome(Model model, Map map) {
		model.addAttribute("loggedInUser", map.get("username"));
		return "facultyhome";
	}

	@GetMapping(value = "/nav")
	public String nav(Model model) {

		return "sidebar";
	}

	
	@GetMapping("/update")
	public String updateFacultyProfile(@ModelAttribute("newFaculty")Faculty faculty,Model model) {
		faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		
		model.addAttribute("newFaculty", faculty);
        model.addAttribute("facultySpecialList",faculty.getSpecializations());
        model.addAttribute("facultyCourseList",faculty.getCourse());
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		model.addAttribute("courseList", courseService.getAllCourser());

		return "editFaculty";

	}
	
	@PostMapping("/updatePersonaInfo")
	public String saveUpdateFacultyProfile(@Valid @ModelAttribute("newFaculty")Faculty editedFaculty,
			       BindingResult error,Model model) {
	     System.out.println("POST");
           if (error.hasErrors()) {
			
	     	     return "editFaculty";
           }
       	 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	     Faculty faculty=facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
	     faculty.getUserProfile().setUserName(editedFaculty.getUserProfile().getUserName());
	 	 faculty.getUserProfile().setPassword(passwordEncoder.encode(editedFaculty.getUserProfile().getPassword()));
	     faculty.getUserProfile().setEmail(editedFaculty.getUserProfile().getEmail());
	     facultyService.saveFaculty(faculty);
	     return "redirect:/faculty/home";
        
       }
	
	 @PostMapping("/updateSpecialization")
	  public String updateSpecialization(@Valid @ModelAttribute("newFaculty")Faculty editedFaculty,
		       BindingResult error,Model mode){
	
		   Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		  faculty.addSpecalization(editedFaculty.getSpecializations().get(0));
		   facultyService.saveFaculty(faculty);
		   System.out.println("Saved");
		   return "redirect:/faculty/update";
		   
	  }
	 
	 
	 @PostMapping("/updateCourse")
	  public String updateCourse(@Valid @ModelAttribute("newFaculty")Faculty editedFaculty,
		       BindingResult error,Model mode){
	
		   Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		  faculty.addCourse(editedFaculty.getCourse().get(0));
		   facultyService.saveFaculty(faculty);
		   System.out.println("Saved");
		   return "redirect:/faculty/update";
		   
	  }

	
}
