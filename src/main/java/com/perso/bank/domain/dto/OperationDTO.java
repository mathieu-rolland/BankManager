package com.perso.bank.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.perso.bank.domain.OperationWayEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationDTO {

	private long id;
	
	private BigDecimal amount;
	
	private CategoryDTO category;
	
	private OperationWayEnum operationWay;
	
	private String label;
	
	private LocalDateTime date;
	
}
