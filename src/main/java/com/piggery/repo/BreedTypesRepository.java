package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piggery.models.BreedTypes;

public interface BreedTypesRepository extends JpaRepository<BreedTypes, Long> {
	
	@Query(nativeQuery = true, value="SELECT * FROM breed_types WHERE key = ?1")
	BreedTypes  findBreedTypeID(Long key);

}
