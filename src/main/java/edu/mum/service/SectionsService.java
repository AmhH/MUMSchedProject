package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Section;
import edu.mum.repository.SectionRepository;
@Service 
public class SectionsService {
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private BlockService blockService;

	public void saveSection(Section section, String blockMonth) {
		Block currentBlock = blockService.getBlock(blockMonth);
		section.setBlock(currentBlock);
		currentBlock.getSections().add(section);
		blockService.saveBlock(currentBlock);
		sectionRepository.save(section);
	}

	
	
	
	public List<Section> getAllSection() {
		return (List<Section>) sectionRepository.findAll();
	}

	public Section getSectionById(Long id) {
		return sectionRepository.findOne(id);
	}

	
	public void deleteSection(Long id) {
		sectionRepository.delete(id);
	}

	
	public void updateSection(Section section) {
		sectionRepository.save(section);
		
	}
}
