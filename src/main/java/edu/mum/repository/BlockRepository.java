package edu.mum.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.mum.domain.Block;




public interface BlockRepository extends CrudRepository<Block, Long> {
	
	public Block findBlockByBlockMonth(@Param("blockMonth") String blockMonth);
}
