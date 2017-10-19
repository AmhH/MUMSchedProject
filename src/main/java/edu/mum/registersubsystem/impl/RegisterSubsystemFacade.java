package edu.mum.registersubsystem.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.domain.Section;
import edu.mum.registersubsystem.RegisterSubsystem;
import edu.mum.service.SectionsService;

public class RegisterSubsystemFacade implements RegisterSubsystem{
	
	@Autowired
	SectionsService sectionservice;

	@Override
	public List<Section> getListSection() {
		
		List<Section> sections = sectionservice.getAllSection();
		return sections;
	}

	@Override
	public String register(Section section) {
		return null;
	}

}
