package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.repository.CourseRepository;
import edu.mum.repository.FacultyRepository;

@Service
public class ScheduleSectionCreator {
	
	@Autowired
	private CourseRepository courseDao;
	private List<Course> courses;
	private Course FPP = new Course();
	private Course MPP = new Course();
	private Course SCI = new Course();
	
	public ScheduleSectionCreator() {
		courses = (List<Course>) courseDao.findAll();
		for (int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getCourseName() == "FPP")
				this.FPP = courses.get(i);
			if(courses.get(i).getCourseName() == "MPP")
				this.MPP = courses.get(i);
			if(courses.get(i).getCourseName() == "SCI")
				this.SCI = courses.get(i);
		}
	}

	public void createSectionForBlock(Block block) {
		switch (block.getBlockOrder()) {
		case 0:  zeroBlock(block);
		break;
		case 1:  firstBlock(block);
		break;
		case 2:  secondBlock(block);
		break;
		default: otherBlocks(block);
		}
		 
	}

	private void otherBlocks(Block block) {
//		//int noTotalStudents = block.getNoStudents()
//		//for(int i=0;  i<Math.round(noTotalStudents/25.0); i++) {
//			//block.getSections.add(new Section(block));
//		}
	}

	private void secondBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for(int i=0;  i< Math.round(noFPP/25.0); i++) {
			//block.getSections.add(new Section(block, this.MPP));
		}
		int noMPP = block.getEntry().getNumOfFpp();
		for(int i=0;  i< Math.round(noMPP/25.0); i++) {
			//block.getSections().add(new Section(block));
		}
	}

	private void firstBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for(int i=0;  i< Math.round(noFPP/25.0); i++) {
			//block.getSections.add(new Section(block, this.FPP));
		}
		int noMPP = block.getEntry().getNumOfMpp();
		for(int i=0;  i< Math.round(noMPP/25.0); i++) {
			//block.getSections.add(new Section(block, this.MPP));
		}
	}

	private void zeroBlock(Block block) {
		//block.getSections().add((new Section(block,this.SCI));
	}
}
