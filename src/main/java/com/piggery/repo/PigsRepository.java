package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piggery.models.Pigs;


public interface PigsRepository extends JpaRepository<Pigs, Long>  {
	
	@Query(nativeQuery = true, value="SELECT * FROM pigs WHERE id = ?1")
	Pigs  findPigID(Long id);
}
