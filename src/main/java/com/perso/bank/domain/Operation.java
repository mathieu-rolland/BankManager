package com.perso.bank.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "OPERATIONS" )
@Getter
@Setter
@ToString
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column( name =  "ID" )
	private long id;
	
	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "OPERATION_WAY" )
	private OperationWayEnum operationWay;
	
	@Column(name = "DATE")
	private LocalDateTime date;
	
}
