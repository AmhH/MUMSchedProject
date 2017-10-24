package edu.mum.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.Entry;
import edu.mum.domain.Student;
import edu.mum.domain.UserProfile;
import edu.mum.registersubsystem.impl.RegisterSubsystemFacade;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.EntryService;
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
	UserProfileService userprofileService;
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
	@Autowired
	EntryService entryService;
	@Autowired
	private ScheduleRepository scheduleDao;

	
	 @GetMapping(value = "/student")
	public String studentRegForm(Model model) {
		 UserProfile userProfile = userprofileService.LoggedInUser();
		
		Student student= studentService.getStudentByUserProfile(userProfile);
		 model.addAttribute("student",student);
		 
   	    return "studentmainpage";
    }
	 
	 @RequestMapping(value={"/student/viewschedule"},method=RequestMethod.GET)
		public String studentschedule(Model model) {
		 
		 UserProfile userProfile = userprofileService.LoggedInUser();
		 Student student = studentService.getStudentByUserProfile(userProfile);
		 
		 model.addAttribute("blocks", scheduleDao.findOne(student.getEntry().getId()).getEntry().getBlocks());
			model.addAttribute("entry",scheduleDao.findOne(student.getEntry().getId()).getEntry());
				
		//model.addAttribute("schedule", scheduleService.getScheduleByEntryId(student.getEntry().getId()));
	
			
	   	    return "viewSchedule";
	    }
	
	 
		@GetMapping(value = "admin/students")
		public String ManageStudent(Model model) {
			model.addAttribute("students", studentService.getAllstudents());
			return "managestudent";
		}
	 
	 @RequestMapping(value={"/student/register"},method=RequestMethod.GET)
		public String registerstudent(Model model) {
		 	//System.out.println("logged User:"+userprofileService.LoggedInUser().getFirstName());
		model.addAttribute("sections",regsubsystem.getListSection());
			System.out.println("size of section:"+regsubsystem.getListSection().size());
	   	    return "studentregister";
	    }
	 
	    @GetMapping(value = "admin/addstudent")
		public String addstudent(@ModelAttribute("Newstudent") Student student, Model model) {
			model.addAttribute("userTypeList", roleService.getAll());
			
			List<Entry> entries = entryService.getAllEntry();
		//	for(Entry entry: entries)
				//System.out.println(entry.getEntryMonth());
			model.addAttribute("entries", entries);
			
			return "addstudent";
		}

		@PostMapping(value = "admin/addstudent")
		public String savestudent(@Valid @ModelAttribute("Newstudent") Student student, BindingResult error,
				RedirectAttributes redirect, Model model) {
			//System.out.println(student.getEntry());
			/*List<Entry> entries = entryService.getAllEntry();
			for(Entry entry: entries)
				System.out.println(entry.getEntryMonth());*/
			
			
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
	 
	 @RequestMapping(value= {"student/updatestudent"},method=RequestMethod.GET)
		public String updateStudent(Model model){
				UserProfile userProfile = userprofileService.LoggedInUser();
				
					Student student = studentService.getStudentByUserProfile(userProfile);
				 
		 model.addAttribute("Newstudent", student);
		
			//studentService.save(student);
			 return "editstudent";
		}
	 
	 @RequestMapping(value= {"student/updatestudent"},method=RequestMethod.POST)
		public String updatedStudent(@ModelAttribute("Newstudent") Student Newstudent,Model model){
		 System.out.println("=======updateStudent Controller");
				UserProfile userProfile = userprofileService.LoggedInUser();
				
					Student student = studentService.getStudentByUserProfile(userProfile);
					//System.out.println("=======updateStudent Controller"+Newstudent.getUserprofile().getUserName());
					student.getUserprofile().setUserName(Newstudent.getUserprofile().getUserName());
					student.getUserprofile().setPassword(Newstudent.getUserprofile().getPassword());
					student.getUserprofile().setEmail(Newstudent.getUserprofile().getEmail());
				 
		 //model.addAttribute("Newstudent", student);
		
			studentService.save(student);
			model.addAttribute("student", student);
			 return "studentmainpage";
		}
	 

	
	@RequestMapping(value={"/student/register/{id}"}, method = RequestMethod.GET)
    public String registerStudent( @PathVariable Long id,  /*BindingResult bindingresult,*/ Model model) {
		
		/*if(bindingresult.hasErrors()){
			return "studentregister";
		}*/
		UserProfile userProfile = userprofileService.LoggedInUser();
		String str = regsubsystem.register(sectionservice.getSectionById(id));
		model.addAttribute("reason", str);
		model.addAttribute("sections",studentService.getStudentByUserProfile(userProfile).getSections());
		 	          
   	return "addsuccess";
    }
}
