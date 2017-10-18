package edu.mum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Faculty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToMany
	private List<Specialization> specalizations=new ArrayList<>();
	@ManyToMany
	private List<Course> course=new ArrayList<>();
	@OneToOne
	private UserProfile userProfile;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Specialization> getSpecalizations() {
		return specalizations;
	}
	public void setSpecalizations(List<Specialization> specalizations) {
		this.specalizations = specalizations;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
}
