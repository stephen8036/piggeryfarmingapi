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

import com.piggery.models.BreedTypes;
import com.piggery.repo.BreedTypesRepository;
import com.piggery.util.ResponseModel;

@RestController
@RequestMapping("api/breedtypes/")
@CrossOrigin(origins = "http://localhost:3000/")
public class BreedTypesController {
	
	@Autowired
	private BreedTypesRepository breedTypesRepo;

	@GetMapping("list")
	public List<BreedTypes> getAllBreedTypes() {
		return breedTypesRepo.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<ResponseModel> addBreedType(@RequestBody BreedTypes model) {
		breedTypesRepo.save(model);
		return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, model));
	}

	@DeleteMapping("deleteitem/{id}")
	public ResponseEntity<ResponseModel> deleteStudent(@PathVariable Long id) {
		Optional<BreedTypes> getBreedType = Optional.ofNullable(breedTypesRepo.findBreedTypeID(id));
		if (getBreedType.isEmpty()) {
			return ResponseEntity.ok().body(new ResponseModel(0, "No data found"));
		} else {
			breedTypesRepo.deleteById(getBreedType.get().getKey());
			return ResponseEntity.ok().body(new ResponseModel(1, "Successfully deleted", null, null));
		}
	}

}
