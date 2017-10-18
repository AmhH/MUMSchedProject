package edu.mum.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.mum.domain.Faculty;
import edu.mum.service.FacultyService;
import edu.mum.service.RoleService;
import edu.mum.service.SpecializationsService;

@Controller
public class FacultyContrller {

	@Autowired
	FacultyService facultyService;
	@Autowired
	RoleService roleService;
	@Autowired
	SpecializationsService specializationsService;

	// only admin can add new Faculty

	// @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/addfaculty")
	public String addFaculty(@ModelAttribute("newFaculty") Faculty faculty, Model model) {
		model.addAttribute("userTypeList", roleService.getAll());
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		return "addFaculty";
	}

	@PostMapping(value = "/addfaculty")
	public String saveFaculty(@Valid @ModelAttribute("newFaculty") Faculty faculty, BindingResult error,
			RedirectAttributes redirect, Model model) {
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
		facultyService.saveFaculty(faculty);
		return "redirect:/faculties";
	}

	@GetMapping(value = "/deleteFaculty/{id}")
	public String deleteFaculty(@PathVariable("id") Long id, Model model) {
		facultyService.deleteFaculty(id);

		return "redirect:/faculties";
	}

	@GetMapping(value = "/updateFaculty/{id}")
	public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("newFaculty", facultyService.getFacultyById(id));
		return "addFaculty";
	}

	@GetMapping(value = "/faculties")
	public String ManageStudent(Model model) {
		model.addAttribute("faculties", facultyService.getAllfaculty());
		return "manageFaculty";
	}
}
