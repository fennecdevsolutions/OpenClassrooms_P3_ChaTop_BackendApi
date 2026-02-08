package com.oc.chatopapi.mapper;

import java.util.List;
import java.util.stream.StreamSupport;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oc.chatopapi.dto.RentalCreateDto;
import com.oc.chatopapi.dto.RentalDto;
import com.oc.chatopapi.dto.RentalUpdateDto;
import com.oc.chatopapi.dto.RentalsResponseDto;
import com.oc.chatopapi.model.Rental;

@Mapper(componentModel = "spring")
public interface RentalMapper {
	    RentalDto toRentalDto(Rental rental);
	    
	    @Mapping(target = "picture", ignore = true)
	    Rental toRentalEntity(RentalCreateDto rentalCreateDto);
	    
	    default RentalsResponseDto toRentalsResponseDto (Iterable<Rental> rentals)  {
	    	List<RentalDto> dtos = StreamSupport.stream(rentals.spliterator(),false)
	    			.map(this::toRentalDto)
	    			.toList();
	    	return new RentalsResponseDto(dtos);
	    	
	    }
	    
		Rental updateRental(RentalUpdateDto rentalUpdateDto, @MappingTarget Rental rental);
	    
	    
	}


