package com.perso.bank.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountRestController {

	@RequestMapping("/list")
	public String getAccount() {
		return "Account1";
	}
	
}
