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
import edu.mum.service.StudentService;

@Service
public class RegisterSubsystemFacade implements RegisterSubsystem{
	
	@Autowired
	private EntryService entryservice;
	@Autowired
	private StudentService studentService;

	@Override
	public List<Section> getListSection() {
		Student student = new Student();//get the student from the proncipal 
		List<Section> sections = entryservice.getAllSectionsByEntryId(student.getEntry().getId());
		return sections;
	}

	@Override
	public String register(Section section) {
		List<Course> prerequisites = section.getCourse().getPrerequisite();
		Student student = new Student();//get the student from the proncipal
		if(section.getStudents().size()==section.getLimitCapacity()){
			return "Section is full";
		} else if(checkTranscript(prerequisites,student.getId()).equalsIgnoreCase(checkregisteredSections(prerequisites,student.getId()))){
			section.getStudents().add(student);
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
		List<Transcript> failCourses = transcript.stream()
									  .filter(t -> preReq.contains(t.getCourse()))
									  .filter(t-> t.getGrade().compareToIgnoreCase("B")>0)
									  .collect(Collectors.toList());
		if(failCourses.isEmpty())
			return "Success";		
		return "Unsatsfactory Grade";
	}
	
	private String checkregisteredSections(List<Course> preReq, long studentId){
		List<Course> registeredCourse = new ArrayList<>();
		studentService.getStudentById(studentId).getSections().forEach(s->registeredCourse.add(s.getCourse()));;
		if(registeredCourse.containsAll(preReq))
			return "Success";
		return "Not Registered for PreRequisite";
	}

}
