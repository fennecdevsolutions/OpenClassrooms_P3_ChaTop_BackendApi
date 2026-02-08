package com.oc.chatopapi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RentalUpdateDto {
		private String name;
		private BigDecimal surface;
		private BigDecimal price;
		private String description;
}