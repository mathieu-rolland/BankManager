package com.perso.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationWayEnum {

	CREDIT("+"),
	DEBIT("-");
	
	private String display;
	
}
