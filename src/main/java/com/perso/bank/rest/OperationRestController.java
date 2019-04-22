package com.perso.bank.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.perso.bank.domain.dto.OperationDTO;
import com.perso.bank.services.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationRestController {

	@Autowired
	private OperationService operationService;
	
	@RequestMapping( method = RequestMethod.GET , path = "/list" )
	public List<OperationDTO> getAll(){
		return operationService.getAll();
	}
	
	@RequestMapping( method = RequestMethod.DELETE , path = "/delete" )
	public boolean delete(@RequestBody OperationDTO dto){
		return operationService.deleteOperation(dto);
	}
	
	@RequestMapping( method = RequestMethod.POST, path = "/create" )
	public OperationDTO create(@RequestBody OperationDTO dto) {
		return operationService.createOperation(dto);
	}
	
}
