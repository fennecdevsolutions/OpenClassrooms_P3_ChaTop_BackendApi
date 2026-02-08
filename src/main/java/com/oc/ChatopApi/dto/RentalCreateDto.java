package com.oc.chatopapi.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RentalCreateDto {
		private String name;
		private BigDecimal surface;
		private BigDecimal price;
		private MultipartFile picture;
		private String description;
}

