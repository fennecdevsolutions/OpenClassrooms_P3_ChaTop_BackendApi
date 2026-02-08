package com.oc.chatopapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oc.chatopapi.model.Rental;

public interface RentalRepository extends CrudRepository <Rental, Integer>{

	Optional<Rental> findByName(String name);

}
