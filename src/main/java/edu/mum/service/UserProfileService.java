package edu.mum.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import edu.mum.domain.UserProfile;
import edu.mum.repository.UserRepository;
@Service
public class UserProfileService  {
 
	@Autowired 
	UserRepository userRepository;
	 
	public UserProfile saveUser(UserProfile user)
	{    
		return userRepository.save(user);
	}
	public UserProfile getUserById(Long id)
	{
		return userRepository.findOne(id);
	}
	public List<UserProfile> getAllUser()
	{
		return (List<UserProfile>) userRepository.findAll();
	}
	
	public List<UserProfile> getAllActiveUser(String status)
	{
		return userRepository.getActiveUser(status);
	}
	
}
