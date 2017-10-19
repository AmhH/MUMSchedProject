package edu.mum.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.UserProfile;
import edu.mum.service.RoleService;
import edu.mum.service.UserProfileService;

@Controller

public class UserController {

		@Autowired
		UserProfileService userService;
		@Autowired
		RoleService roleService;
	   
	  // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	   @GetMapping(value = "/addUser")
	   public String addUser(@ModelAttribute("Newuser") UserProfile user, Model model) {
	   model.addAttribute("userTypeList", roleService.getAll());
	   model.addAttribute("mode", "NEW");
	        return "manageuser";
       }
	  // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	   @RequestMapping(value = "/addUser", method = RequestMethod.POST)
	   public String saveUser(@Valid @ModelAttribute("Newuser") UserProfile user, BindingResult error,
			RedirectAttributes redirect, Model model) {
		System.out.println("hiiiiiiiiiiiiiiiiii");
		if (error.hasErrors()) {
			model.addAttribute("mode", "NEW");
			model.addAttribute("userTypeList",roleService.getAll());
			return "manageuser";
		}
		System.out.println("before");
		user.setUserStatus("Active");
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));  */
		/*System.out.println("password string:  "+passwordEncoder.encode(user.getPassword()));
		System.out.println("confirm password string:  "+passwordEncoder.encode(user.getConfirmpassword()));*/
		userService.saveUser(user);
		System.out.println("user" + user.getFirstName());
		redirect.addFlashAttribute("mode", "USERS");
		redirect.addFlashAttribute("userTypeList", roleService.getAll());
		return "redirect:/users";
	   }

	   @GetMapping(value = "/userDetail")
	   public String getDetail() {
		    return "UserDetail";
	   }
        
	    @GetMapping(value = "/users")
	    public String getAllUser(@ModelAttribute("Newuser") UserProfile user, Model model) {
		model.addAttribute("users", userService.getAllActiveUser("Active"));
		model.addAttribute("mode", "USERS");
		return "manageuser";
	   }

	   @GetMapping(value = "/userdelete/{id}")
	    public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		UserProfile u = userService.getUserById(id);
		u.setUserStatus("Deleted");
		userService.saveUser(u);
		return "redirect:/users";
	   }

	    @GetMapping(value = "/updateuser/{id}")
	    public String updateUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
		System.out.println("id" + id);
		redirect.addFlashAttribute("Newuser", userService.getUserById(id));
		     return "redirect:/addUser";
	    }
       
	    
	  

}
	