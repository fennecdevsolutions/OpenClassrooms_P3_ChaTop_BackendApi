package com.oc.ChatopApi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oc.ChatopApi.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);

}
