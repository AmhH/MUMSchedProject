package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.mum.domain.Faculty;
import edu.mum.repository.FacultyRepository;

@Service
public class FacultyService {
	
	Authentication auth;
	@Autowired
    FacultyRepository facultyRepository;
	public List<Faculty> getAllfaculty()
	{
		return (List<Faculty>) facultyRepository.findAll();
	}
	
	public Faculty saveFaculty(Faculty faculty)
	{
		return facultyRepository.save(faculty);
	}
	
	public Faculty getFacultyById(Long id)
	{
		return facultyRepository.findOne(id);
	}
	public void deleteFaculty(Long id)
	{
		facultyRepository.delete(id);
	}
	
	public Faculty getFacultyByName(String firstName){
		return facultyRepository.findByUserProfileFirstName(firstName);
	}
	 public void LoggedInUser(){

		    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    System.out.println(auth.getName());
		}
}
