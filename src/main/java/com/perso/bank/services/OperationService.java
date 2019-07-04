package com.perso.bank.services;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perso.bank.domain.dto.OperationDTO;
import com.perso.bank.domain.dto.convert.OperationConverter;
import com.perso.bank.repository.OperationRepository;
import com.perso.bank.services.parser.BankParser;
import com.perso.bank.services.parser.CEParser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperationService {

	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private OperationConverter operationConverter;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private AutoAffectService autoAffectService;
	
	private DateTimeFormatter dateTimeFormatter;
	
	@PostConstruct
	public void setup() {
//		dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu'THH:mm:ss");
		dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
	}
	
	public List<OperationDTO> getWithDate(String date){
		
		LocalDateTime startDate = LocalDateTime.parse(date , dateTimeFormatter).withDayOfMonth(1);
		LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);
		
		log.debug("Recherche des operations entre {} et {}." , startDate , endDate);
		
		return operationConverter.createDto( operationRepository.dateBetween(startDate, endDate) );
	}
	
	
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
