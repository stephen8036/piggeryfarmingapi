package com.piggery.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import com.piggery.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	@Query(nativeQuery = true, value="SELECT * FROM users WHERE username= ?1")
	Users  findByUserName(String userName);
	
	@Query(nativeQuery = true, value="SELECT * FROM users WHERE id = ?1")
	Users  findUserById(Long id);
	
	

}
