package edu.mum.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import edu.mum.domain.Block;
import edu.mum.domain.Entry;
import edu.mum.service.BlockService;
import edu.mum.service.EntryService;




@Controller
public class BlockController {
	
	Long id_new, id_new_entry;
	@Autowired
	private BlockService blockService;
	@Autowired
	private EntryService entryService;
	
	@RequestMapping(value= {"/addBlockForm"},method=RequestMethod.POST)
	public  String saveBlockForm(@RequestParam String id, Model model){
		
		System.out.println(id);
		Long entry_id  = Long.parseLong(id);
		System.out.println(id);
		model.addAttribute("entry_id", entry_id);
		return "addBlock";
	}	
	@RequestMapping(value= {"/addBlock"},method=RequestMethod.POST)
	public  String saveBlock(@RequestParam String blockMonth, @RequestParam Date blockStartDate, 
			@RequestParam Date blockEndDate, @RequestParam int numOfStudents, @RequestParam int blockOrder,
			@RequestParam String entry_id, Model model){
		
		
		Long ent_id = Long.parseLong(entry_id);
		Block newBlock = new Block();
		newBlock.setBlockMonth(blockMonth); newBlock.setBlockStartDate(blockStartDate); 
		newBlock.setBlockEndDate(blockEndDate);newBlock.setNumOfStudents(numOfStudents);
		newBlock.setBlockOrder(blockOrder); 
		
		
		blockService.saveBlock(newBlock, ent_id);
		
		model.addAttribute("blocks", entryService.getEntry(ent_id).getBlocks());
		
		model.addAttribute("entry", entryService.getEntry(ent_id));
		return "blockList";
		
	}
	
	@RequestMapping(value= {"/blockList"},method=RequestMethod.POST)
	public String /*@ResponseBody RedirectView*/ blockList(@RequestParam Long id, Model model){
		Entry entry = entryService.getEntry(id);
		List<Block> blocks = entry.getBlocks();
		model.addAttribute("blocks", blocks);
		
		model.addAttribute("entry", entry);
		return "blockList";
	}
	
	
	@RequestMapping(value= {"/deleteBlock"},method=RequestMethod.POST)
	public String deleteBlock(@RequestParam String id, @RequestParam String entry_id, Model model){
		
		id_new = Long.valueOf(id);
		id_new_entry = Long.valueOf(entry_id);
		System.out.println(id_new);
		Entry currentEntry = entryService.getEntry(id_new_entry);
		List<Block> blocks = currentEntry.getBlocks();
		for(int i=0; i<blocks.size();i++) {
			if(blocks.get(i).getId() == id_new ) {
				blocks.remove(i);
				break;
			}
		}
		currentEntry.setBlocks(blocks);
		entryService.saveEntry(currentEntry);
		blockService.deleteBlock(id_new);
		//return new RedirectView("/allEntry");
		model.addAttribute("blocks", blocks);
		
		model.addAttribute("entry", currentEntry);
		return "blockList";
	}
}
