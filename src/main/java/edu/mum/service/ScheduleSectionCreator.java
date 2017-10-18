package edu.mum.service;

import org.springframework.stereotype.Service;

import edu.mum.domain.Block;

@Service
public class ScheduleSectionCreator {

	public void createSectionForBlock(Block block) {
		switch (block.getBlockMonth()) {
		case "A":  zeroBlock(block);
		break;
		case "B":  firstBlock(block);
		break;
		case "C":  secondBlock(block);
		break;
		default: otherBlocks(block);
		}
		 
	}

	private void otherBlocks(Block block) {
//		//int noTotalStudents = block.getNoStudents()
//		//for(int i=0;  i<Math.round(noTotalStudents/25.0); i++) {
//			//block.getSections.add(new Section(block.getId()));
//		}
	}

	private void secondBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for(int i=0;  i<Math.round(noFPP/25.0); i++) {
			//block.getSections.add(new Section(block.getId(), MPP));
		}
		int noMPP = block.getEntry().getNumOfFpp();
		for(int i=0;  i<Math.round(noMPP/25.0); i++) {
			//block.getSections.add(new Section(block.getId()));
		}
	}

	private void firstBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for(int i=0;  i<Math.round(noFPP/25.0); i++) {
			//block.getSections.add(new Section(block.getId(), FPP));
		}
		int noMPP = block.getEntry().getNumOfMpp();
		for(int i=0;  i<Math.round(noMPP/25.0); i++) {
			//block.getSections.add(new Section(block.getId(), MPP));
		}
	}

	private void zeroBlock(Block block) {
		//block.getSections().add((new Section(block.getId(),SCI));
	}
}
