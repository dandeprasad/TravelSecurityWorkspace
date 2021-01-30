package com.chamanthi.travelSecurity.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chamanthi.travelSecurity.entity.User;





@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	User findByEmail(String email);

}


