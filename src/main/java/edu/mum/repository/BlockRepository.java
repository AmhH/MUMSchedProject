package edu.mum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.mum.domain.Block;




public interface BlockRepository extends CrudRepository<Block, Long> {
	@Query("select b from Block b where b.blockMonth = blockMonth")
	public Block findBlockByBlockMonth(@Param("blockMonth") String blockMonth);
}
