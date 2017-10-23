package edu.mum.registersubsystem.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Course;
import edu.mum.domain.Section;
import edu.mum.domain.Student;
import edu.mum.domain.Transcript;
import edu.mum.registersubsystem.RegisterSubsystem;
import edu.mum.service.EntryService;
import edu.mum.service.SectionsService;
import edu.mum.service.StudentService;
import edu.mum.service.UserProfileService;

@Service
public class RegisterSubsystemFacade implements RegisterSubsystem{
	
	@Autowired
	private EntryService entryservice;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SectionsService sectionservice;
	@Autowired
	UserProfileService userProfileService;

	@Override
	public List<Section> getListSection() {
				
		//Long id = userProfileService.LoggedInUser().getId();
		Long id=Long.valueOf(1);
		
		//Student student = studentService.getStudentById(id);
		
		List<Section> sections = entryservice.getAllSectionsByEntryId(id /*student.getEntry().getId()*/);
		return sections;
	}

	@Override
	public String register(Section section) {
		List<Course> prerequisites = section.getCourse().getPrerequisite();
		
		//Long id = userProfileService.LoggedInUser().getId();
		Long id = Long.valueOf(8);
		Student student = studentService.getStudentById(id);
		
		if(section.getStudents().size()==section.getLimitCapacity()){
			return "Section is full";
		} else if(checkTranscript(prerequisites,id).equalsIgnoreCase(checkregisteredSections(prerequisites,student.getId()))){
			section.getStudents().add(student);
			sectionservice.saveSection(section);
			studentService.save(student);
			return "Success";
		} else if(!checkTranscript(prerequisites,student.getId()).equalsIgnoreCase("Success")){
			return checkTranscript(prerequisites,student.getId());
		} else if(checkTranscript(prerequisites,student.getId()).equalsIgnoreCase("Success")){
			if(!checkregisteredSections(prerequisites,student.getId()).equalsIgnoreCase("Success")){
				return "Not Registered for PreRequisite";
			}
		}
			
		return "Unsatsfactory Grade and Not Registered for PreRequisite";
	}
	
	private String checkTranscript(List<Course> preReq, long studentId){
		Set<Transcript> transcript = studentService.getStudentById(studentId).getTranscript();
		if(transcript.size()>0){
		List<Transcript> failCourses = transcript.stream()
									  .filter(t -> preReq.contains(t.getCourse()))
									  .filter(t-> t.getGrade().compareToIgnoreCase("B")>0)
									  .collect(Collectors.toList());
		if(failCourses.isEmpty())
			return "Success";		
		return "Unsatsfactory Grade";
		}
		return "Success";
	}
	
	private String checkregisteredSections(List<Course> preReq, long studentId){
		List<Course> registeredCourse = new ArrayList<>();
		//Long id = userProfileService.LoggedInUser().getId();
		Long id = Long.valueOf(8);
		
		studentService.getStudentById(id).getSections().forEach(s->registeredCourse.add(s.getCourse()));
		if(registeredCourse.containsAll(preReq))
			return "Success";
		return "Not Registered for PreRequisite";
	}

}
