package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piggery.models.FeedTypes;
import com.piggery.models.Feeds;

public interface FeedsRepository extends JpaRepository<Feeds, Long> {
	


}
