package com.perso.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.bank.domain.dto.OperationDTO;
import com.perso.bank.domain.dto.convert.OperationConverter;
import com.perso.bank.repository.OperationRepository;

@Service
public class OperationService {

	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private OperationConverter operationConverter;
	
	public List<OperationDTO> getAll(){
		return operationConverter.createDto( operationRepository.findAll() );
	}
	
	public OperationDTO createOperation(OperationDTO dto) {
		return operationConverter.createDto( 
					operationRepository.save( operationConverter.createFromDto(dto) )
			   );
	}
	
	public boolean deleteOperation(OperationDTO dto) {
		operationRepository.delete( operationConverter.createFromDto(dto) );
		return true;
	}
	
}
