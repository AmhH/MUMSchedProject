package edu.mum.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.domain.Schedule;
import edu.mum.service.EntryService;
import edu.mum.service.ScheduleService;

@Controller
public class ScheduleController {

	// inject via application.properties
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	EntryService entryService;
	
	@RequestMapping("/schedule/list")
	public String scheduleList(Map<String, Object> model) {
		
		//model.put("schedules", scheduleService.getAllSchedule());
		return "schedules";
	}
	
	@RequestMapping("/schedule/create")
	public String createSchedule(Model model) {
		
		model.addAttribute("entries", entryService.getAllEntry());
		
		return "generateSched";
	}
	
	@RequestMapping(value="/schedule/create", method = RequestMethod.POST)
	public String generateSchedule(@RequestParam("entry") String entry) {
		 Schedule schedule = scheduleService.generateSched(entry);
		 System.out.println("========>Controller MEra"+entry);
		//model.put("entries", entryService.getAllEntry());
		return "schedules";
	}
	
	@RequestMapping(value="/schedule/edit", method = RequestMethod.POST)
	//,@ModelAttribute("newEntry") Entry entry
	public String editSchedule(Map<String, Object> model) {
		// Schedule schedule = scheduleService.generateSched(entry)
		//model.put("entries", entryService.getAllEntry());
		return "schedules";
	}
	


}

