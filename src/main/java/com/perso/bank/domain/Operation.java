package com.perso.bank.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;

@Entity
@Table( name = "OPERATIONS" )
@Getter
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column( name =  "ID" )
	private long id;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@Enumerated
	@Column(name = "OPERATION_WAY" )
	private OperationWayEnum operationWay;
	
}
