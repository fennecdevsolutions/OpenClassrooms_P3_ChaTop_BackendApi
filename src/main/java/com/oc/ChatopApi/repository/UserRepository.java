package com.oc.chatopapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oc.chatopapi.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	

}
