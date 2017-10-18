package edu.mum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Course;
import edu.mum.repository.CourseRepository;

@Service
public class CourserService {
  @Autowired
  CourseRepository courseRepository;
  public Course save(Course course)
  {
	return courseRepository.save(course);
  }
  
}
