package com.leo.users.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leo.users.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM users WHERE first_name like (%:firstName%)", nativeQuery = true)
	Page<User> searchByName(Pageable pageable, String firstName);

}
