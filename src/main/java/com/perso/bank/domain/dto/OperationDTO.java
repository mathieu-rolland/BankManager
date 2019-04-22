package com.perso.bank.domain.dto;

import java.math.BigDecimal;

import com.perso.bank.domain.OperationWayEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationDTO {

	private long id;
	
	private BigDecimal amount;
	
	private long category;
	
	private OperationWayEnum operationWay;
	
	private String label;
	
}
