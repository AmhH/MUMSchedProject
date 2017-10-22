package edu.mum.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.service.BlockService;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;


@Controller
public class SectionController {


	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private FacultyService facultyService;
	@RequestMapping({"/addSectionForm"})
	public  String viewSection(Model model){
		List<Course> courses = courseService.getAllCourser();
		List<Faculty> faculties = facultyService.getAllfaculty();
		List<Block> blocks = blockService.getAllBlock();
		model.addAttribute("courses", courses);
		model.addAttribute("faculties", faculties);
		model.addAttribute("blocks", blocks);
		return "addSection";
	}	
	@RequestMapping(value= {"/addSection"},method=RequestMethod.POST)
	public @ResponseBody RedirectView saveSectioin(@RequestParam String sectionCode, @RequestParam String course, 
			@RequestParam String faculty, @RequestParam String blockMonth,  @RequestParam int limitCapacity){	
		
		Block block = blockService.getBlock(blockMonth);
		Faculty facultyAssigned = facultyService.getFacultyByName(faculty);
		Course courseOffered = courseService.getCourseByName(course);
		
		Section newSection = new Section();
		newSection.setBlock(block);
		newSection.setCourse(courseOffered);
		newSection.setFaculty(facultyAssigned);
	
		newSection.setSectionCode(sectionCode);
		newSection.setLimitCapacity(limitCapacity);
		
		block.getSections().add(newSection);		
		blockService.saveBlock(block, block.getEntry().getId());
			
		return new RedirectView("/allEntry");
	}
}
