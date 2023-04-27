package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piggery.models.Events;



public interface EventsRepository extends JpaRepository<Events, Long> {
	

}

