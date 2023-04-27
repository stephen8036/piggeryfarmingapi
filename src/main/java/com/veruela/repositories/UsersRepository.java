package com.veruela.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veruela.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	@Query(nativeQuery = true, value="SELECT * FROM users WHERE username= ?1")
	Users  findByUserName(String userName);
	
	@Query(nativeQuery = true, value="SELECT * FROM users WHERE id = ?1")
	Users  findUserById(Long id);

}
