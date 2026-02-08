package com.oc.chatopapi.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RentalDto {
	
	private Integer id;
	private String name;
	private BigDecimal surface;
	private BigDecimal price;
	private String picture;
	private String description;
	private Integer owner_id;
	private Timestamp created_at;
	private Timestamp updated_at;

}
