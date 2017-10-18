package edu.mum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Faculty;
import edu.mum.repository.CourseRepository;

@Service
public class FacultyHelper {

	private List<Course> courses;
	private List<Faculty> faculties;
	@Autowired
	private CourseRepository courseDao;
	// @Autowired
	// private FacultyRepository facultyDao;

	public void assignCourseAndFaculty(Block block) {
		courses = (List<Course>) courseDao.findAll();
		// faculties = (List<Faculty>) facultyDao.findAll();
		switch (block.getBlockMonth()) {
		case "A":
			firstBlock(block);
			break;
		case "B":
			secondBlock(block);
			break;
		case "C":
			thirdBlock(block);
			break;
		default:
			otherBlocks(block);
		}

	}

	private void otherBlocks(Block block) {
		// //int noTotalStudents = block.getNoStudents()
		// //for(int i=0; i<Math.round(noTotalStudents/25.0); i++) {
		// //block.getSections.add(new Section(block.getId()));
		// }

	}

	private void thirdBlock(Block block) {
		double noFPP = Math.round(block.getEntry().getNumOfFpp()) / 25.0;
		for (int i = 0; i < Math.round(noFPP / 25.0); i++) {
			// block.getSections.add(new Section(block.getId(), MPP));
		}
		int noMPP = block.getEntry().getNumOfFpp();
		for (int i = 0; i < Math.round(noMPP / 25.0); i++) {
			// block.getSections.add(new Section(block.getId()));
		}
	}

	private void secondBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for (int i = 0; i < Math.round(noFPP / 25.0); i++) {
			// block.getSections.add(new Section(block.getId(), FPP));
		}
		int noMPP = block.getEntry().getNumOfMpp();
		for (int i = 0; i < Math.round(noMPP / 25.0); i++) {
			// block.getSections.add(new Section(block.getId(), MPP));
		}
	}

	private void firstBlock(Block block) {
		// block.getSections().get(0).setFaculty(new Faculty());
	}

	private List<Course> getCourse(long blkId, int noSection) {
		List<Course> selected = new ArrayList<>();
		if (blkId == 2) {
			return firstElective(noSection);
		} else if(blkId == 3) {
			selected.addAll(firstElective((int)Math.ceil(noSection/2.0)));
		}

		return null;
	}

	private List<Course> firstElective(int noSection) {
		List<Course> selected = new ArrayList<>();
		courses.stream()
				/*
				 * .filter(c -> c.isPrerequiste()) .filter(c -> (c.getCourseCode()>400 &&
				 * c.getCourseCode()<500))
				 */
				.forEach(selected::add);
		if (selected.size() > noSection) {
			for (int i = 0; i < (selected.size() - noSection); i++) {
				selected.remove(i);
			}
		} else {
			for (int i = 0, j = 0; i < courses.size() && j < (noSection - selected.size()); i++) {
				if (true/*
						 * courses.get(i).getCourseCode()>400 && courses.get(i).getCourseCode()<500 &&
						 * !selected.contains(courses.get(i))
						 */) {
					selected.add(courses.get(i));
					j++;
				}
			}
		}
		return selected;
	}
}
