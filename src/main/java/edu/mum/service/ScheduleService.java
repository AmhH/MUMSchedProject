package edu.mum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;

@Service
public class ScheduleService {

	@Autowired
	private EntryService entryService;

	@Autowired
	private FacultyHelper facultyHelper;
	@Autowired
	private ScheduleSectionCreator sectionHelper;

	public Schedule generateSched(String entryMon) {
		Entry entry = (Entry) entryService.getEntryByMonth(entryMon);

		entry.getBlocks().forEach(sectionHelper::createSectionForBlock);

		entry.getBlocks().forEach(facultyHelper::assignCourseAndFaculty);

		return null;
	}

}
