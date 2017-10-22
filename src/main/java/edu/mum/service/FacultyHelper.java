package edu.mum.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Faculty;
import edu.mum.repository.CourseRepository;
import edu.mum.repository.FacultyRepository;

@Service
public class FacultyHelper {

	@Autowired
	private CourseRepository courseDao;
	@Autowired
	private FacultyRepository facultyDao;
	private List<Course> courses;
	private List<Faculty> faculties;
	private Course FPP = new Course();
	private Course MPP = new Course();


	public void assignCourseAndFaculty(Block block) {
		courses = (List<Course>) courseDao.findAll();
		faculties = (List<Faculty>) facultyDao.findAll();
		for (int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getCourseName() == "FPP")
				this.FPP = courses.get(i);
			if(courses.get(i).getCourseName() == "MPP")
				this.MPP = courses.get(i);
		}
		
		Map<Faculty, Course> facultyCourseMap = new HashMap<>();
		if (block.getBlockOrder() > 1) {
			List<Course> choosen = getCourse(block.getBlockOrder(), block.getSections().size());
			facultyCourseMap = getFaculty(choosen, block.getBlockOrder());
			if (block.getBlockOrder() == 2) {
				for (int i = 0; i < Math.round(block.getEntry().getNumOfMpp()) / 25.0; i++) {
					choosen.add(this.MPP);
				}
			}
		}else if(block.getBlockOrder()==1) {
			facultyCourseMap = getFirstBlockFaculty((int)Math.round(block.getEntry().getNumOfFpp()/25.0), (int)Math.round(block.getEntry().getNumOfMpp()/25.0));
			}
			
			int cnt = 0;
			for (Faculty key : facultyCourseMap.keySet()) {
				block.getSections().get(cnt).setCourse(facultyCourseMap.get(key));
				block.getSections().get(cnt).setFaculty(key);
				cnt++;
			}
		
	}

	private List<Course> getCourse(long blkId, int noSection) {
		List<Course> selected = new ArrayList<>();
		if (blkId == 2) {
			selected.addAll(firstElective(noSection));

		} else if (blkId == 3) {
			selected.addAll(firstElective((int) Math.ceil(noSection / 2.0)));
			selected.addAll(get500Courses(noSection - (int) Math.ceil(noSection / 2.0)));
		} else if (blkId > 3) {
			selected.addAll(get500Courses(noSection / 2 + 1));
			selected.addAll(get400Courses(noSection - (noSection / 2 + 1)));
		}

		return selected;
	}

	private List<Course> firstElective(int noSection) {
		List<Course> selected = new ArrayList<>();
		courses.stream().filter(c -> c.getIsPreReq()).filter(c -> (c.getCourseCode() > 400 && c.getCourseCode() < 500))
				.forEach(selected::add);
		if (selected.size() > noSection) {
			for (int i = 0; i < (selected.size() - noSection); i++) {
				selected.remove(i);
			}
		} else {
			for (int i = 0, j = 0; i < courses.size() && j < (noSection - selected.size()); i++) {
				if (courses.get(i).getCourseCode() > 400 && courses.get(i).getCourseCode() < 500
						&& !selected.contains(courses.get(i))) {
					selected.add(courses.get(i));
					j++;
				}
			}
		}
		return selected;
	}

	private HashSet<Course> get500Courses(int number) {
		List<Course> course500 = courses.stream().filter(c -> c.getCourseCode() > 500).collect(Collectors.toList());
		HashSet<Course> choosen = new HashSet<>();
		Random rand = new Random();
		for (; choosen.size() < number;) {
			choosen.add(course500.get(rand.nextInt(course500.size())));
		}
		return choosen;
	}

	private HashSet<Course> get400Courses(int number) {
		List<Course> course400 = courses.stream().filter(c -> c.getCourseCode() < 500 && c.getCourseCode() > 400)
				.collect(Collectors.toList());
		HashSet<Course> choosen = new HashSet<>();
		Random rand = new Random();
		for (; choosen.size() < number;) {
			choosen.add(course400.get(rand.nextInt(course400.size())));
		}
		return choosen;
	}

	private Map<Faculty, Course> getFaculty(List<Course> selected, int blkId) {
		Map<Faculty, Course> map = new HashMap<>();
		selected.forEach(c -> {
			for (int j = 0; j < faculties.size(); j++) {
				if (faculties.get(j).getCourse().contains(c) /* && faculties.get(j).aet check availability also */) {
					if (!map.containsKey(faculties.get(j)) && !map.containsValue(c)
							&& !c.getCourseName().equalsIgnoreCase("MPP"))
						map.put(faculties.get(j), c);
				}
			}
		});

		return map;
	}
	
	private Map<Faculty, Course> getFirstBlockFaculty(int fppNo, int mppNo){
		Map<Faculty, Course> map = new HashMap<>();
		List<Faculty> mppFaculty = faculties.stream()
										   .filter(f ->f.getCourse().contains(this.MPP))
										   .collect(Collectors.toList());
		List<Faculty> fppFaculty = faculties.stream()
				   .filter(f ->f.getCourse().contains(this.FPP))
				   .collect(Collectors.toList());
		Random rand = new Random();
		for ( ;mppNo <= map.size();) {
			map.put(mppFaculty.get(rand.nextInt(mppFaculty.size())), this.MPP);
		}
		for ( ;mppNo+fppNo <= map.size();) {
			map.put(fppFaculty.get(rand.nextInt(fppFaculty.size())), this.FPP);
		}
		return map;
	}

}