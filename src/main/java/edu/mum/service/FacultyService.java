package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Faculty;
import edu.mum.repository.FacultyRepository;

@Service
public class FacultyService {
	
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
}
