package com.oc.chatopapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oc.chatopapi.dto.RentalCreateDto;
import com.oc.chatopapi.dto.RentalDto;
import com.oc.chatopapi.dto.RentalUpdateDto;
import com.oc.chatopapi.dto.RentalsResponseDto;
import com.oc.chatopapi.dto.SuccessMessageDto;
import com.oc.chatopapi.exception.NotFoundException;
import com.oc.chatopapi.exception.RentalCreateUpdateException;
import com.oc.chatopapi.mapper.RentalMapper;
import com.oc.chatopapi.model.Rental;
import com.oc.chatopapi.repository.RentalRepository;



@Service
public class RentalService {
	
	
	private String uploadDirectory = "assets/images/";
	
	
	
	private String serverPort = "3001";
	
	@Autowired
	private RentalRepository rentalRepo;
	
	@Autowired
	private RentalMapper rentalMapper;
	
	@Autowired
	private UserService userService;
	
	
	// get all existing rentals
	public RentalsResponseDto getAllRentals () {
		return rentalMapper.toRentalsResponseDto(rentalRepo.findAll());
	}
	
	// get rental by Id
		public RentalDto getRental (Integer id) {
			Rental fetchedRental = rentalRepo.findById(id)
					.orElseThrow(()-> new NotFoundException("Rental not found!"));
			return rentalMapper.toRentalDto(fetchedRental);
		}
		

	//Create new rental
		public SuccessMessageDto createRental (RentalCreateDto rentalCreateDto, Principal principal) {
			System.out.println("uploadDirectory path: " + uploadDirectory); // Temporary debug line
			try {
			// Get picture file	
			MultipartFile file = rentalCreateDto.getPicture();
			// create unique filename
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			 System.out.println("file name: " + fileName); // Temporary debug line
			//set file path
			Path path = Paths.get(uploadDirectory + fileName);
			System.out.println("file path: " + path); // Temporary debug line
			// Create folder if not existing
			Files.createDirectories(path.getParent());
			// save image file
			Files.copy(file.getInputStream(), path);
			Rental rental = rentalMapper.toRentalEntity(rentalCreateDto);
			// create URL to store in database
			String imageUrl = "http://localhost:" + serverPort + "/images/" + fileName;
			rental.setPicture(imageUrl);
			// get creator/owner id
			rental.setOwner_id(userService.findUserByEmail(principal.getName()).getId());
			Rental createdRental = rentalRepo.save(rental);
			if (createdRental.getId()==null) {
				throw new RentalCreateUpdateException("Error during rental creation!");
			}
			return new SuccessMessageDto ("Rental created !");
			}catch (IOException ex) {
				throw new RentalCreateUpdateException("Could not store image");
			}
		}
		
	//Update existing rental
		public SuccessMessageDto updateRental (RentalUpdateDto rentalUpdateDto) {
			Rental fetchedRental = rentalRepo.findByName(rentalUpdateDto.getName())
					.orElseThrow(()-> new NotFoundException("Rental does not exist!"));
			rentalRepo.save(rentalMapper.updateRental(rentalUpdateDto, fetchedRental));
			return new SuccessMessageDto ("Rental updated!");
			}
	
	

}
