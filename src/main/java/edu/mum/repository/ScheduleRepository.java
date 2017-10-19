package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Schedule;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long> {
	
	

}
