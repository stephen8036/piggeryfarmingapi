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
import com.piggery.models.Feeds;
import com.piggery.models.Transactions;
import com.piggery.repo.TransactionsRepository;
import com.piggery.util.ResponseModel;

@RestController
@RequestMapping("api/transactions/")
@CrossOrigin(origins = "http://localhost:3000/")
public class TransactionsController {
	
	@Autowired
	private TransactionsRepository transacRepo;
	
	
//////////////////////////////////////////////////////////////////////GET   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@GetMapping("list")
	public List<Transactions> getAllFeeds() {
		return transacRepo.findAll();
	}

	
	
	
	
	
	
	
	
	
	
	
	


//////////////////////////////////////////////////////////////////////POST   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@PostMapping("add")
	public ResponseEntity<ResponseModel> addPig(@RequestBody Transactions model) {
		transacRepo.save(model);
		return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, model));
	}



















//////////////////////////////////////////////////////////////////////PATCH   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\




















//////////////////////////////////////////////////////////////////////DELETE   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\














}
