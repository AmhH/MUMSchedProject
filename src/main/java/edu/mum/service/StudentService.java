package edu.mum.service;

import edu.mum.repository.StudentRepository;

import edu.mum.domain.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



 @Service
 public class StudentService   {
	
	    @Autowired
		StudentRepository studentDAO;
		
		
		public void save(Student student){
			studentDAO.save(student);
			return ;
		}

	
		public Student getStudentByEmail(String email) {
			return studentDAO.findStudentByEmail(email);
		}

		
		public Student getStudentById(Long studentId) {
			return studentDAO.findStudentById(studentId);
		}
		
		
		public List<Student> getAllstudents()
		{
			return (List<Student>) studentDAO.findAll();
		}
		
				
		public void deleteStudent(Long id)
		{
			studentDAO.delete(id);
		}
}
 
