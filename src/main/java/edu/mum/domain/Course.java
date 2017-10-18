package edu.mum.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Course {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private long id;
   private String courseCode;
   private String courseName;
   @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
   private List<Faculty> facultyName;
   @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
   Course preRequestCourse;
   
   public String getCourseName() { 
	return courseName;
	
   }
  public void setCourseName(String courseName) {
	this.courseName = courseName;
  }
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCourseCode() {
	return courseCode;
}
public void setCourseCode(String courseCode) {
	this.courseCode = courseCode;
}

public Course getPreRequestCourse() {
	return preRequestCourse;
}
public void setPreRequestCourse(Course preRequestCourse) {
	this.preRequestCourse = preRequestCourse;
}
 

}
