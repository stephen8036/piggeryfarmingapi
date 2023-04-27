package com.piggery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggery.models.Events;
import com.piggery.models.Pigs;
import com.piggery.repo.EventsRepository;
import com.piggery.util.ResponseModel;

@RestController
@RequestMapping("api/events/")
@CrossOrigin(origins = "http://localhost:3000/")
public class EventsController {
	
	@Autowired
	private EventsRepository eventsRepo;
	
//////////////////////////////////////////////////////////////////////GET   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


	@GetMapping("list")
	public List<Events> getAllPigs() {
		return eventsRepo.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	


//////////////////////////////////////////////////////////////////////POST   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@PostMapping("add")
	public ResponseEntity<ResponseModel> addPig(@RequestBody Events model) {
		eventsRepo.save(model);
		return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, model));
	}











	
	
	




//////////////////////////////////////////////////////////////////////PATCH   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\




















//////////////////////////////////////////////////////////////////////DELETE   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\














}
