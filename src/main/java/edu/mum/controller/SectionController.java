package edu.mum.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import edu.mum.domain.Section;
import edu.mum.service.SectionsService;

@Controller
public class SectionController {

	@Autowired
	private SectionsService sectionService;
	
	
	@RequestMapping({"/addSection"})
	public /*@ResponseBody*/ String viewSection(){
		return "addSection";
	}	
	@RequestMapping(value= {"/addSection"},method=RequestMethod.POST)
	public @ResponseBody RedirectView saveSectioin(@RequestParam String blockMonth, @ModelAttribute("section") Section newSection){
		sectionService.saveSection(newSection, blockMonth);
		return new RedirectView("/allEntry");
	}
	
	

}
