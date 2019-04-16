package com.perso.bank.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;

@Entity
@Table( name = "CATEGORY" )
@Getter
public class Category {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "COLOR")
	private String color;
	
	@OneToMany
	private List<Operation> operations;
	
}
