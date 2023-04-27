package com.piggery.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggery.dto.PigWrapper;
import com.piggery.models.Feeds;
import com.piggery.models.Pigs;
import com.piggery.repo.PigsRepository;
import com.piggery.repo.UserRepository;
import com.piggery.service.ConverterList;
import com.piggery.util.ResponseModel;

@RestController
@RequestMapping("api/pig/")
@CrossOrigin(origins = "http://localhost:3000/")
public class PigController {

	@Autowired
	private PigsRepository pigsRepo;

	@Autowired
	private ConverterList converter;

//////////////////////////////////////////////////////////////////////GET   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@GetMapping("all")
	public List<Pigs> getAllPigs() {
		return pigsRepo.findAll();
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ResponseModel> getPigbyID(@PathVariable Long id) {
		Optional<Pigs> getPig = Optional.ofNullable(pigsRepo.findPigID(id));
		if (getPig.isEmpty()) {
			return ResponseEntity.ok().body(new ResponseModel(0, "No data found"));
		} else {
			return ResponseEntity.ok().body(new ResponseModel(1, "Get pig", null, getPig));
		}

	}

////////////////////////////////////////////////////////////////////// POST   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping("add")
	public ResponseEntity<ResponseModel> addPig(@RequestBody Pigs model) {
		if(model.getWeightDifference()== null) {
			model.setWeightDifference(null);
		}if(model.getWeightMonitoring() == null) {
			model.setWeightMonitoring(null);
		}
		pigsRepo.save(model);
		
		return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, model));
	}

////////////////////////////////////////////////////////////////////// PATCH   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PatchMapping("edit/{id}")
	public ResponseEntity<ResponseModel> editPig(@PathVariable Long id, @RequestBody Pigs model) {
		Optional<Pigs> getPig = pigsRepo.findById(id);
		if (getPig.isEmpty()) {
			return ResponseEntity.ok().body(new ResponseModel(0, "No data found"));
		} else {
			if (model.getWeight() != null) {
				Double getWeight = model.getWeight();
				String getMonitoring = getPig.get().getWeightMonitoring();
				List<Double> monitoringWeight = converter.convertStringToList(getWeight, getMonitoring, 0,
						getMonitoring);
				List<Double> monitoringDifference = converter.convertStringToList(getWeight, getMonitoring, 1,
						getPig.get().getWeightDifference());
				getPig.get().setWeightMonitoring(converter.convertListToString(monitoringWeight));
				getPig.get().setWeightDifference(converter.convertListToString(monitoringDifference));
				getPig.get().setWeight(model.getWeight());
			}
			if (model.getName() != null) {
				getPig.get().setName(model.getName());
			}
			if (model.getBreed() != null) {
				getPig.get().setBreed(model.getBreed());
			}
			getPig.get().setDate_modified(LocalDate.now());
			Pigs pig = pigsRepo.save(getPig.get());
			return ResponseEntity.ok().body(new ResponseModel(1, "Edit Successfully", null, pig));
		}
	}

	@PatchMapping("setstatus")
	public ResponseEntity<ResponseModel> setStatus(@RequestBody PigWrapper model) {
		Optional<Pigs> getPig = Optional.ofNullable(pigsRepo.findPigID(model.getId()));
		if (getPig.isEmpty()) {
			return ResponseEntity.ok().body(new ResponseModel(0, "No data found"));
		} else {
			getPig.get().setStatus(model.getStatus());
			getPig.get().setDate_modified(LocalDate.now());
			Pigs pig = pigsRepo.save(getPig.get());
			return ResponseEntity.ok().body(new ResponseModel(1, "Edit Successfully", null, pig));
		}
	}

//////////////////////////////////////////////////////////////////////DELETE   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@DeleteMapping("deletepig/{id}")
	public ResponseEntity<ResponseModel> deleteStudent(@PathVariable Long id) {
		Optional<Pigs> getPig = Optional.ofNullable(pigsRepo.findPigID(id));
		if (getPig.isEmpty()) {
			return ResponseEntity.ok().body(new ResponseModel(0, "No data found"));
		} else {
			pigsRepo.deleteById(getPig.get().getId());
			return ResponseEntity.ok().body(new ResponseModel(1, "Successfully deleted", null, null));
		}

	}

}
