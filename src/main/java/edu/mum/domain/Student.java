package edu.mum.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
		
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType .ALL)
	private UserProfile userprofile;
	
//	@OneToMany
//	private Section section;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Transcript transcript;
	
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	private String idNumber;
	
	private String availability;
	private String degree;
	private String field;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserProfile getUserprofile() {
		return userprofile;
	}
	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}
	public Transcript getTranscript() {
		return transcript;
	}
	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
	

}
