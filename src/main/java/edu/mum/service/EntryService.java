package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Entry;
import edu.mum.repository.EntryRepository;



@Service
public class EntryService{
	
	@Autowired
	private EntryRepository entryRepository;


	public void saveEntry(Entry entry) {
		entryRepository.save(entry);
	}


	public Entry getEntry(Long id) {
		return entryRepository.findOne(id);
	}


	public List<Entry> getAllEntry() {
		return (List<Entry>) entryRepository.findAll();
	}

	
	public Entry getEntryByMonth(String entryMonth) {
		return entryRepository.findEntryByEntryMonth(entryMonth);
	}

	
	public void deleteEntry(Long id) {
		entryRepository.delete(id);
		
	}

	public void updateEntry(String entryMonth, int numOfFpp, int numOfMpp, int numOfUSstudents, Long id) {
		entryRepository.update(entryMonth, numOfFpp, numOfMpp, numOfUSstudents, id);
	}
}
