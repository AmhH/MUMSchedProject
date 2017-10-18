package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mum.domain.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, Long>{

}
