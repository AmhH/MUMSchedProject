package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.Student;
import edu.mum.registersubsystem.impl.RegisterSubsystemFacade;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.RoleService;
import edu.mum.service.ScheduleService;
import edu.mum.service.SectionsService;
import edu.mum.service.StudentService;
import edu.mum.service.UserProfileService;



@Controller
public class StudentRegController {
	
	@Autowired
	StudentService studentService;
	@Autowired
	UserProfileService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ScheduleRepository schedulerep;
	@Autowired
	RegisterSubsystemFacade regsubsystem;
	@Autowired
	SectionsService sectionservice;

	
	 @GetMapping(value = "/student")
	public String studentRegForm(@ModelAttribute("student") Student student, Model model) {
		 Long id = Long.valueOf(8);
		
		student= studentService.getStudentById(id);
		 model.addAttribute(student);
		 
   	    return "studentmainpage";
    }
	 
	 @RequestMapping(value={"/student/viewschedule"},method=RequestMethod.GET)
		public String studentschedule(Model model) {
		// model.addAttribute("schedule", schedulerep.getScheduleByEntry(student.getEntry().getId()));
	
			
			 
	   	    return "studentschedule";
	    }
	 
		@GetMapping(value = "/students")
		public String ManageStudent(Model model) {
			model.addAttribute("students", studentService.getAllstudents());
			return "managestudent";
		}
	 
	 @RequestMapping(value={"/student/register"},method=RequestMethod.GET)
		public String registerstudent(Model model) {
		 	
		model.addAttribute("sections",regsubsystem.getListSection());
			 
	   	    return "studentregister";
	    }
	 
	    @GetMapping(value = "/addstudent")
		public String addstudent(@ModelAttribute("Newstudent") Student student, Model model) {
			model.addAttribute("userTypeList", roleService.getAll());
			
			return "addstudent";
		}

		@PostMapping(value = "/addstudent")
		public String savestudent(@Valid @ModelAttribute("student") Student student, BindingResult error,
				RedirectAttributes redirect, Model model) {
			/*if (error.hasErrors())

			{
				return "addstudent";
			}*/

			System.out.println("before");
			student.getUserprofile().setUserStatus("Active");
			System.out.println("student" + student.getUserprofile().getFirstName());

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			student.getUserprofile().setPassword(passwordEncoder.encode(student.getUserprofile().getPassword()));

			System.out.println("password string:  " + passwordEncoder.encode(student.getUserprofile().getPassword()));
			studentService.save(student);
			return "redirect:/students";
		}
	 
	 @RequestMapping(value= {"student/updatestudent"},method=RequestMethod.POST)
		public String updateStudent(@RequestParam String id,Model model){
				Long new_id= Long.valueOf(3);
					Student st = new Student();
				 st = studentService.getStudentById(new_id);
		 model.addAttribute("Newstudent", st);
		
			//studentService.save(student);
			 return "editstudent";
		}
	 
	
	
	@RequestMapping(value={"/student/register/addsection"}, method = RequestMethod.POST)
    public String registerStudent(@Valid @ModelAttribute("newstudent") Student student, BindingResult bindingresult, Model model) {
		
		if(bindingresult.hasErrors()){
			return "studentregister";
		}
		 
		sectionservice
	//STUDENT SAVED IN PERSISTENCE
		studentService.save(student);
		
 	//GET STUDENT FROM PERSISTENCE	
 		model.addAttribute(studentService.getStudentByEmail(student.getUserprofile().getEmail()));
          
   	return "addsuccess";
    }
}
