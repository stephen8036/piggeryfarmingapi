package com.veruela.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

import com.veruela.model.GAD;
import com.veruela.repositories.GADRepository;
import com.veruela.util.ResponseModel;

@RestController
@RequestMapping("api/main/")

@CrossOrigin(origins = "http://veruela.zione.tech/")
public class GADController {
	
	@Autowired
	GADRepository gadRepo;
	
	@GetMapping("all")
	public ResponseEntity<ResponseModel> getAllData() {
		List<GAD> data = gadRepo.findAll();
		return ResponseEntity.ok().body(new ResponseModel(1,"Get all data",null, data));
	}
	
	@GetMapping("{id}")
    public ResponseEntity<ResponseModel> getData(@PathVariable Long id) {
		 Optional<GAD> data = gadRepo.findById(id);
		 if(data.isEmpty()) {
			 return ResponseEntity.ok().body(new ResponseModel(0,"No data found"));
		 }else {
			return ResponseEntity.ok().body(new ResponseModel(1, "Get data", null, data));
		 }
    }
	
	@PostMapping("add")
	 public ResponseEntity<ResponseModel> registerUser(@RequestBody GAD model){
			GAD data= gadRepo.save(model);
			return ResponseEntity.ok().body(new ResponseModel(1, "Added successfully", null, data));
	}
	
	
	@PatchMapping("edit/{id}")
	public ResponseEntity<ResponseModel> updateQuantity(@PathVariable("id") Long id, @RequestBody GAD model)
			throws URISyntaxException
	{
		Optional<GAD> dataExist = gadRepo.findById(id);
		if (dataExist.isEmpty()){
			 return ResponseEntity.ok().body(new ResponseModel(0, "No record data", "", null));
		}
		else {
			if(model.getType() != null) {
				dataExist.get().setType(model.getType());
			}if(model.getSubmittedBy() != null) {
				dataExist.get().setSubmittedBy(model.getSubmittedBy());
			}if(model.getSector() != null) {
				dataExist.get().setSector(model.getSector());
			}if(model.getSubSector() !=null) {
				dataExist.get().setSubSector(model.getSubSector());
			}
			GAD data =gadRepo.save(dataExist.get());
			return ResponseEntity.ok().body(new ResponseModel(1, "Update Successfully", null, data));
		}
	}
	
	
	
	@Transactional
	@DeleteMapping("delete/{id}")
	 public ResponseEntity<ResponseModel> deleteData(@PathVariable Long id) {
		Optional<GAD> dataExist = gadRepo.findById(id);
		if (dataExist.isEmpty()){
			 return ResponseEntity.ok().body(new ResponseModel(0, "No record data", "", null));
		}else {
			gadRepo.deleteById(id);
			return ResponseEntity.ok().body(new ResponseModel(1, "Deleted successfully", null, null));
		}
	}

}
