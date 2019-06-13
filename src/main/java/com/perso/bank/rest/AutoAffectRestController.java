package com.perso.bank.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.bank.domain.dto.AutoAffectDTO;
import com.perso.bank.services.AutoAffectService;

@RestController
@RequestMapping("/auto-affect")
public class AutoAffectRestController {

	@Autowired
	private AutoAffectService autoAffectService;
	
	@RequestMapping("/create")
	public AutoAffectDTO create( @RequestBody AutoAffectDTO autoAffect ) {
		return autoAffectService.create(autoAffect);
	}
	
	@RequestMapping("/delete")
	public boolean delete( @RequestBody AutoAffectDTO autoAffect ) {
		return autoAffectService.delete(autoAffect);
	}
	
	@RequestMapping("/update")
	public AutoAffectDTO update( @RequestBody AutoAffectDTO autoAffect ) {
		return autoAffectService.create(autoAffect);
	}
	
	@RequestMapping("/list")
	public List<AutoAffectDTO> findAll(){
		return autoAffectService.findAll();
	}

	
}
