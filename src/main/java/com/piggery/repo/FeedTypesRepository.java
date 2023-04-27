package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piggery.models.FeedTypes;

public interface FeedTypesRepository extends JpaRepository<FeedTypes, Long> {

	@Query(nativeQuery = true, value="SELECT * FROM feed_types WHERE key = ?1")
	FeedTypes  findFeedTypeID(Long key);

}
