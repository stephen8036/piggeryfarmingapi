package com.piggery.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggery.models.Feeds;
import com.piggery.models.FeedsAuditor;
import com.piggery.models.Feeds;
import com.piggery.repo.FeedsAuditorRepository;
import com.piggery.repo.FeedsRepository;
import com.piggery.util.ResponseModel;


@RestController
@RequestMapping("api/feed/")
@CrossOrigin(origins = "http://localhost:3000/")
public class FeedsController {
	@Autowired
	private FeedsRepository feedsRepo;
	
	@Autowired
	private FeedsAuditorRepository feedsAuditor;
	
	
//////////////////////////////////////////////////////////////////////GET   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@GetMapping("list")
	public List<Feeds> getAllFeeds() {
		return feedsRepo.findAll();
	}
	@GetMapping("audit/all")
	public List<FeedsAuditor> getAllAudits() {
		return feedsAuditor.findAll();
	}
	
	 @GetMapping(value = "/findbyid/{id}")
	 public ResponseEntity<ResponseModel> getFeedsById(@PathVariable Long id) {
		 Optional<Feeds> getFeeds = feedsRepo.findById(id);
		 if(getFeeds.isEmpty()) {
			 return ResponseEntity.ok().body(new ResponseModel(0,"No data found"));
		 }else {
			return ResponseEntity.ok().body(new ResponseModel(1, "Get Feeds", null, getFeeds));
		 }
		 
	 }
	 
	 

	
	
	
	
	
	
	
	
	
	
	
	


//////////////////////////////////////////////////////////////////////POST   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	 @PostMapping("add")
		public ResponseEntity<ResponseModel> addPig(@RequestBody Feeds model) {
		 feedsRepo.save(model);
			return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, model));
		}





















//////////////////////////////////////////////////////////////////////PATCH   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


		@PatchMapping("edit/{id}")
		public ResponseEntity<ResponseModel> editPig(@PathVariable Long id, @RequestBody Feeds model) {
			 Optional<Feeds> getFeeds = feedsRepo.findById(id);
			 Feeds feed =getFeeds.get();
			 if(getFeeds.isEmpty()) {
				 return ResponseEntity.ok().body(new ResponseModel(0,"No data found"));
			 }else {
				 Double check = model.getQuantity() - feed.getQuantity();
				 if(check >0) {
					 feedsAuditor.save(new FeedsAuditor(feed.getInventory(), feed.getNotes(), feed.getQuantity(), feed.getCost(),"Addition", Math.abs(check)));
				 }else if(check <0) {
					 feedsAuditor.save(new FeedsAuditor(feed.getInventory(), feed.getNotes(), feed.getQuantity(), feed.getCost(),"Reduction",Math.abs(check)));
				 }else {
					 feedsAuditor.save(new FeedsAuditor(feed.getInventory(), feed.getNotes(), feed.getQuantity(), feed.getCost(),null,0.0));
				 }
				 
				 if(model.getQuantity()!=null) {
					 getFeeds.get().setQuantity(model.getQuantity());
				 }
				 if(model.getInventory()!=null) {
					 getFeeds.get().setInventory(model.getInventory());
				 }
				 if(model.getCost()!=null) {
					 getFeeds.get().setCost(model.getCost());
				 }
				 getFeeds.get().setDate_modified(LocalDate.now());
				 Feeds feeds = feedsRepo.save(getFeeds.get());
				return ResponseEntity.ok().body(new ResponseModel(1, "Edit Successfully", null, feeds));
			 }
		}
		
		
		@PatchMapping("setstatus/{id}")
		public ResponseEntity<ResponseModel> setStatus(@PathVariable Long id, @RequestBody String notes) {
			Optional<Feeds> getFeeds = feedsRepo.findById(id);
			 if(getFeeds.isEmpty()) {
				 return ResponseEntity.ok().body(new ResponseModel(0,"No data found"));
			 }else {
				 getFeeds.get().setNotes(notes);
				 getFeeds.get().setDate_modified(LocalDate.now());
				 Feeds feeds = feedsRepo.save(getFeeds.get());
				return ResponseEntity.ok().body(new ResponseModel(1, "Edit Successfully", null, feeds ));
			 }
		}















//////////////////////////////////////////////////////////////////////DELETE   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\














}
