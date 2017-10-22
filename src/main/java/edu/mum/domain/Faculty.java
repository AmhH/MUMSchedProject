package edu.mum.domain;

import java.util.ArrayList;
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
import javax.validation.Valid;


@Entity
public class Faculty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToMany
	private List<Specialization> specializations=new ArrayList<>();
	@OneToMany
	@Valid
	private List<Course> course=new ArrayList<>();
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@Valid
	private UserProfile userProfile;
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Section> sections=new ArrayList<Section>();
	private boolean isAvailability;
	
	
	public List<Specialization> getSpecializations() {
		return specializations;
	}
	public void setSpecializations(List<Specialization> specializations) {
		this.specializations = specializations;
	}
	public List<Course> getCourse() {
		return course;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
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
	public boolean isAvailability() {
		return isAvailability;
	}
	public void setAvailability(boolean isAvailability) {
		this.isAvailability = isAvailability;
	}
	public void addSpecalization(Specialization specialization)
	{
		specializations.add(specialization);
	}
	public void addCourse(Course course)
	{
		this.course.add(course);
	}
}
