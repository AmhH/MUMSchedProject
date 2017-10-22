package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.domain.Role;
import edu.mum.domain.Specialization;
import edu.mum.service.RoleService;
import edu.mum.service.SpecializationsService;

@Controller
public class UtilController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	SpecializationsService specialService;
	
	@GetMapping("/role/add")
	public String addRole(@ModelAttribute("newRole") Role role)
	{
		
      return "addRole";
	}
	
	@PostMapping("/role/add")
	public String saveRole(@Valid @ModelAttribute("newRole") Role role,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addRole";
		}
        roleService.save(role);
        return "redirect :/role/all";
		
	}
	
	@GetMapping("/role/all")
	public String allRole(Model model)
	{
	  model.addAttribute("roles",roleService.getAll());
      return "manageRole";
	}
	

	
	@GetMapping("/special/add")
	public String addSpecial(@ModelAttribute("newSpecial") Specialization special)
	{
	 
      return "addSpecial";
	}
	
	@PostMapping("/special/add")
	public String saveSpecail(@ModelAttribute("newSpecial") Specialization special,BindingResult error)
	{
		if(error.hasErrors())
		{
			return "addSpecial";
		}
        specialService.save(special);
        return "redirect:/special/all";
		
	}
	
	@GetMapping("/special/all")
	public String allSpecial(Model model)
	{
	  model.addAttribute("SpecialList", specialService.findAllspecalization());
      return "manageSpecial";
	}

}
