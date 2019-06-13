package com.perso.bank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "AUTO_AFFECT" )
@Getter
@Setter
public class AutoAffect {

	@Id
	@Column( name = "ID" )
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column( name =  "REGEX" )
	private String regex;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	
}
