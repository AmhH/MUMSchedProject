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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.Admin;
import edu.mum.domain.UserProfile;
import edu.mum.service.AdminService;
import edu.mum.service.RoleService;
import edu.mum.service.UserProfileService;

@Controller
@RequestMapping("/admin")
public class UserController {

		@Autowired
		AdminService adminService;
		@Autowired
		RoleService roleService;
	   
	  // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	   @GetMapping(value = "/add")
	   public String addUser(@ModelAttribute("newAdmin") Admin admin, Model model) {
	   model.addAttribute("userTypeList", roleService.getAll());
	        return "addAdmin";
       }
	  // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	   @RequestMapping(value = "/add", method = RequestMethod.POST)
	   public String saveUser(@Valid @ModelAttribute("newAdmin") Admin admin, BindingResult error,Model model){
		
		if (error.hasErrors()) {
			if(!model.containsAttribute("userTypeList")){
			model.addAttribute("userTypeList",roleService.getAll());
			return "addAdmin";
			}
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		admin.getUserProfile().setPassword(passwordEncoder.encode(admin.getUserProfile().getPassword())); 
		 adminService.save(admin);
		return "redirect:/admin/all";
	   }
        
	    @GetMapping(value = "/admin/all")
	    public String getAllUser(Model model) {
		model.addAttribute("users", adminService.getAll());
	
		return "manageAdmin";
	   }

	   @GetMapping(value = "/userdelete/{id}")
	    public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		Admin admin = adminService.getAdminById(id);
		admin.getUserProfile().setUserStatus("Deleted");
		adminService.save(admin);
		return "redirect:/users";
	   }

	    @GetMapping(value = "/updateuser/{id}")
	    public String updateUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
		System.out.println("id" + id);
		redirect.addFlashAttribute("Newuser", adminService.getAdminById(id));
		     return "redirect:/addUser";
	    }
       
	    
	  

}
	