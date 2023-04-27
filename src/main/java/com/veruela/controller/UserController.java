package com.veruela.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veruela.dto.Login;
import com.veruela.model.Users;
import com.veruela.repositories.UsersRepository;
import com.veruela.util.JWTUtility;
import com.veruela.util.ResponseModel;


@RestController
@RequestMapping("api/user/")
@CrossOrigin(origins = "http://veruela.zione.tech/")
public class UserController {
	@Autowired
	private UsersRepository userRepo;

	
	@Autowired
	private JWTUtility jwtUtility;
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	
//////////////////////////////////////////////////////////////////////  GET   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@GetMapping("all")
    public List<Users> getAllUser() {
        return userRepo.findAll();
    }
	
	@GetMapping("{id}")
    public ResponseEntity<ResponseModel> getUser(@PathVariable Long id) {
		 Optional<Users> getUser = Optional.ofNullable(userRepo.findUserById(id));
		 if(getUser.isEmpty()) {
			 return ResponseEntity.ok().body(new ResponseModel(0,"No data found"));
		 }else {
			return ResponseEntity.ok().body(new ResponseModel(1, "Get user", null, getUser));
		 }
    }
	
	
	

	
	
////////////////////////////////////////////////////////////////////// POST   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 @PostMapping("register")
	 public ResponseEntity<ResponseModel> registerUser(@RequestBody Users user)
		{
		 Optional<Users> findUser = Optional.ofNullable(userRepo.findByUserName(user.getUsername()));
		 if(findUser.isPresent()) {
			 return ResponseEntity.ok().body(new ResponseModel(0, "Username already exists", "", null));
		 }else {
			 String hashedPassword = bcrypt.encode(user.getPassword());
			 	user.setPassword(hashedPassword);
				Users save = userRepo.save(user);
				return ResponseEntity.ok().body(new ResponseModel(1, "Added Successfully", null, save));
		 }
		 	
		}
	 
	 @PostMapping("login")
	 public ResponseEntity<ResponseModel> login(@RequestBody Login model){
		 try {
			 Optional<Users> user =Optional.ofNullable(userRepo.findByUserName(model.getUsername()));
			 Users getUser = user.get();
			 if(user.isPresent() && getUser.getUsername().equals(model.getUsername()) && bcrypt.matches(model.getPassword(), getUser.getPassword()) && getUser.getActive()) {
				 ResponseModel responseModel = new ResponseModel(1, "Login successful",jwtUtility.generateToken(getUser.getLastName()) ,getUser);
	                return ResponseEntity.ok().body(responseModel);
			 }else if(!getUser.getActive()) {
				 return ResponseEntity.ok().body(new ResponseModel(0, "Your account has been deactivated", "", null));
			 }else {
				 return ResponseEntity.ok().body(new ResponseModel(0, "Username and password is incorrect", "", null));
			 }
			 
		 }catch (NoSuchElementException e) {
	            return ResponseEntity.ok().body(new ResponseModel(0, "No record data", "", null));
	        }   
	 }
	 
	
	
//////////////////////////////////////////////////////////////////////  PATCH   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	 
	 
	 
	
	
	
//////////////////////////////////////////////////////////////////////   DELETE   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	
	

}
