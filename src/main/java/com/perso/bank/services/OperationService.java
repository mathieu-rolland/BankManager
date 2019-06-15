package com.perso.bank.services;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perso.bank.domain.dto.OperationDTO;
import com.perso.bank.domain.dto.convert.OperationConverter;
import com.perso.bank.repository.OperationRepository;
import com.perso.bank.services.parser.BankParser;
import com.perso.bank.services.parser.CEParser;

@Service
public class OperationService {

	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private OperationConverter operationConverter;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private AutoAffectService autoAffectService;
	
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
	
	public void importOperations( String originalFilename , MultipartFile file ) {
		File f = storageService.writeFileToLocalStorage(originalFilename, file);
		BankParser parser = new CEParser(f , operationRepository, autoAffectService);
		parser.importFile();
	}
	
}
