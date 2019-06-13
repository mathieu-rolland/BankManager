package com.perso.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoAffectDTO {

	private Long id;
	
	private String regex;
	
	private CategoryDTO category;
	
}
