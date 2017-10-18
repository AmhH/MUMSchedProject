package edu.mum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@RequestMapping({"/addBlock"})
	public /*@ResponseBody*/ String saveBlock(){
		return "addBlock";
	}	
	@RequestMapping(value= {"/addBlock"},method=RequestMethod.POST)
	public @ResponseBody RedirectView saveBlock(@ModelAttribute("block") Block newBlock){
		blockService.saveBlock(newBlock);
		return new RedirectView("/allEntry");
	}
	
	@RequestMapping(value= {"/blockList"},method=RequestMethod.POST)
	public @ResponseBody RedirectView blockList(@RequestParam Long id, Model model){
		Entry entry = entryService.getEntry(id);
		List<Block> blocks = entry.getBlocks();
		model.addAttribute("blocks", blocks);
		return new RedirectView("/allEntry");
	}
	
	
	@RequestMapping(value= {"/deleteBlock"},method=RequestMethod.POST)
	public RedirectView deleteBlock(@RequestParam String id, @RequestParam String entry_id){
		
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
		return new RedirectView("/allEntry");
	}
}
