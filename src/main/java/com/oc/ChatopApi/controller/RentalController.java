package com.oc.chatopapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.chatopapi.dto.ApiErrorDto;
import com.oc.chatopapi.dto.RentalCreateDto;
import com.oc.chatopapi.dto.RentalDto;
import com.oc.chatopapi.dto.RentalUpdateDto;
import com.oc.chatopapi.dto.RentalsResponseDto;
import com.oc.chatopapi.dto.SuccessMessageDto;
import com.oc.chatopapi.dto.TokenDto;
import com.oc.chatopapi.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rentals", description = "Endpoints for rentals management")
public class RentalController {
	
	
	@Autowired
	private RentalService rentalService;
	
	
	//get all rentals
	@Operation (summary = "Get All Rentals", description = "Get all available rentals from database")
	@GetMapping
	public RentalsResponseDto getRentals(){
		return rentalService.getAllRentals();
				
	}
	
	
	// Get rental by Id
	@Operation (summary = "Get Rental", description = "Get Rental information by ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Rental information retrieved successfully",
	                content = @Content(schema = @Schema(implementation = RentalDto.class))),
	    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token",
	                content = @Content(schema = @Schema(implementation = ApiErrorDto.class))),
	    @ApiResponse(responseCode = "404", description = "Rental not found",
	                content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
		})
		@GetMapping("/{id}")
		public RentalDto getRental (@PathVariable Integer id){
			
			return rentalService.getRental(id);
		
	}
	
	// Create Rental 
	@Operation (summary = "Create Rental", description = "Create new rental and return success message")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Rental created successfully",
	    			content = @Content(schema = @Schema(implementation = SuccessMessageDto.class))),
	    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token",
                	content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
			})
		@PostMapping
		public SuccessMessageDto createRental (@ModelAttribute RentalCreateDto rentalCreateDto, Principal principal) {
		return rentalService.createRental(rentalCreateDto, principal);
		}
	
	// Update Rental 
	@Operation (summary = "Update Rental", description = "Update existing rental and return success message")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Rental updated successfully",
	    			content = @Content(schema = @Schema(implementation = SuccessMessageDto.class))),
	    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token",
	               	content = @Content(schema = @Schema(implementation = ApiErrorDto.class))),
	    @ApiResponse(responseCode = "404", description = "Rental not found",
        content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
			})
		@PutMapping("/{id}")
		public SuccessMessageDto updateRental (@PathVariable Integer id, @ModelAttribute RentalUpdateDto rentalUpdateDto) {
		return rentalService.updateRental(id, rentalUpdateDto);
		}
	
	
	
	

}
