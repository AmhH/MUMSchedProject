package edu.mum.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import edu.mum.validation.EqualPasswords;


@Entity
@EqualPasswords(message = "Password and Conform password is not match!")
public class UserProfile {
 
	 
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 @Size(min=4 ,max=50)
	 private String firstName;
	 @Size(min=4 ,max=50)
	 private String lastName;
	 @Size(min=4 ,max=50 )
	 private String userName;
	 private String password;
	 @Transient
	 @NotNull
	 private String confirmpassword;
	 @OneToMany(fetch=FetchType.EAGER)
	 private List<Role> roles=new ArrayList<Role>() ;
	 @Email
	 private String email;
	 private String userStatus;
	 private int isActive;
	 
	 public UserProfile() {
			
		}
	 
	 public UserProfile(UserProfile user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.roles=user.getRoles();
		this.email = user.getEmail();
		this.userStatus = user.getUserStatus();
		this.birthdate = user.getBirthdate();
		this.id=user.getId();
	}

	public String getUserStatus() {
		return userStatus;
	 }

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/*@NotNull
	 @Past
	 @DateTimeFormat(pattern = "dd/MM/yyyy")*/
     private Date birthdate;
	 
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	 public Long getId() {
		return id;
	 }
	 public void setId(Long id) {
		this.id = id;
	 }
	 public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	 
}
