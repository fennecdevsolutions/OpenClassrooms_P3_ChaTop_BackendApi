package com.oc.chatopapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	private String uploadDirectory = "uploads/images/";
	
	
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
			Integer userId = userService.findUserByEmail(principal.getName()).getId();
			//create unique Id for image
			String uniqueId = UUID.randomUUID().toString().substring(0,8);
			try {
			// Get picture file	
			MultipartFile file = rentalCreateDto.getPicture();
			// create unique filename
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			String extention = ext.isEmpty() ? "" : "." + ext;
			String fileName = userId.toString() + "_" + uniqueId + extention;
			//set file path
			Path path = Paths.get(uploadDirectory + fileName);
			// Create folder if not existing
			Files.createDirectories(path.getParent());
			// save image file
			Files.copy(file.getInputStream(), path);
			Rental rental = rentalMapper.toRentalEntity(rentalCreateDto);
			// create URL to store in database
			String imageUrl = "http://localhost:" + serverPort + "/images/" + fileName;
			rental.setPicture(imageUrl);
			// get creator/owner id
			rental.setOwner_id(userId);
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
		public SuccessMessageDto updateRental (Integer id,RentalUpdateDto rentalUpdateDto) {
			Rental fetchedRental = rentalRepo.findById(id)
					.orElseThrow(()-> new NotFoundException("Rental does not exist!"));
			rentalRepo.save(rentalMapper.updateRental(rentalUpdateDto, fetchedRental));
			return new SuccessMessageDto ("Rental updated!");
			}
		
		
		
	
	

}
