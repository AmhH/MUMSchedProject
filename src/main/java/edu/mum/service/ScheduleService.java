package edu.mum.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;
import edu.mum.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private EntryService entryService;
    @Autowired
	private FacultyHelper facultyHelper;
	@Autowired
	private ScheduleSectionCreator sectionHelper;
	@Autowired
	private ScheduleRepository scheduleDao;
	
	
	private Schedule schedule = new Schedule();
	

	public Schedule generateSched(String entryMonth) {
		
		
		System.out.println("========>Generate schedule service called "+entryMonth);
		Entry entry = (Entry) entryService.getEntryByMonth(entryMonth);
		System.out.println("==== generate Sched entry "+entry.getNumOfFpp());

		entry.getBlocks().forEach(sectionHelper::createSectionForBlock);

		//entry.getBlocks().forEach(facultyHelper::assignCourseAndFaculty);
		
		schedule.setEntry(entry);
		schedule.setGeneratedDate(new Date(new java.util.Date().getTime()));
		schedule.setStatus("Draft");
		scheduleDao.save(schedule);
        

		return schedule;
	}
	
	public Schedule getScheduleByEntryId(Long id) {
		return scheduleDao.findScheduleByEntryId(id);
	}
	

}
