package com.piggery.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggery.models.FeedTypes;
import com.piggery.repo.FeedTypesRepository;
import com.piggery.util.ResponseModel;

@RestController
@RequestMapping("api/feedtypes/")
@CrossOrigin(origins = "http://localhost:3000/")
public class FeedTypesController {

	@Autowired
	private FeedTypesRepository feedTypesRepo;

	@GetMapping("list")
	public List<FeedTypes> getAllFeedTypes() {
		return feedTypesRepo.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<ResponseModel> addFeedType(@RequestBody FeedTypes model) {
		feedTypesRepo.save(model);
		return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, model));
	}

	@DeleteMapping("deleteitem/{id}")
	public ResponseEntity<ResponseModel> deleteFeedType(@PathVariable Long id) {
		Optional<FeedTypes> getFeedType = Optional.ofNullable(feedTypesRepo.findFeedTypeID(id));
		if (getFeedType.isEmpty()) {
			return ResponseEntity.ok().body(new ResponseModel(0, "No data found"));
		} else {
			feedTypesRepo.deleteById(getFeedType.get().getKey());
			return ResponseEntity.ok().body(new ResponseModel(1, "Successfully deleted", null, null));
		}
	}

}
