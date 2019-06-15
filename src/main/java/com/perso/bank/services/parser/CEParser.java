package com.perso.bank.services.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.perso.bank.domain.Category;
import com.perso.bank.domain.Operation;
import com.perso.bank.domain.OperationWayEnum;
import com.perso.bank.repository.OperationRepository;
import com.perso.bank.services.AutoAffectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CEParser extends BankParser{

	private static final int HEADER_CODE_BANQUE    = 0;
	private static final int HEADER_NUM_ACCOUNT    = 1;
	private static final int HEADER_SOLDE          = 3;
	private static final int HEADER_HEADER_CONTENT = 4;
	
	private static final int LINE_INDEX_DATE = 0;
	private static final String DATE_PATTERN = "dd/MM/uu";
	private static final int LINE_INDEX_NUM_OPERATION = 1;
	private static final int LINE_INDEX_LIBELLE = 2;
	private static final int LINE_INDEX_DEBIT = 3;
	private static final int LINE_INDEX_CREDIT = 4;
	private static final int LINE_INDEX_DETAILS = 5;
	
	private static final String LINE_INDEX_SEPARATOR = ";";
	
	private OperationRepository operationRepository;
	
	private AutoAffectService autoAffectService;
	
	public CEParser(File file, OperationRepository operationRepository , AutoAffectService autoAffectService) {
		super(file);
		this.operationRepository = operationRepository;
		this.autoAffectService = autoAffectService;
	}

	@Override
	public int parseHeader(BufferedReader buffer) throws IOException {
		
		int cpt = 0;
		
		while( buffer.ready() && cpt < HEADER_HEADER_CONTENT + 1 ) {
			
			switch( cpt ) {
			case HEADER_CODE_BANQUE:
			case HEADER_HEADER_CONTENT:
			case HEADER_NUM_ACCOUNT:
			case HEADER_SOLDE:
			default:
				log.debug( "The header line '{}' is ignored" );
				buffer.readLine();
			}
			
			cpt++;
			
		}
		
		return cpt;
	}

	@Override
	@Transactional
	public int parseOperations(BufferedReader buffer) throws IOException {
		int cpt = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		while( buffer.ready() ) {
			Operation op = new Operation(); 
			String[] line = buffer.readLine().split( LINE_INDEX_SEPARATOR );
			
			//TODO : to improve !
			if ( line[LINE_INDEX_DATE].startsWith("Solde en") ) {
				return cpt;
			}
			
			op.setDate( LocalDate.parse( line[LINE_INDEX_DATE], formatter ).atStartOfDay() );
			
			op.setLabel( line[LINE_INDEX_LIBELLE] );
			
			String debit = line[LINE_INDEX_DEBIT].replace(",", ".");
			String credit = line[LINE_INDEX_CREDIT].replace(",", ".");
			
			if( debit.trim().isEmpty() ) {
				op.setAmount( new BigDecimal( credit ) );
				op.setOperationWay( OperationWayEnum.CREDIT );
			}else {
				op.setAmount( new BigDecimal( debit ) );
				op.setOperationWay( OperationWayEnum.DEBIT );
			}
			
			Category c = autoAffectService.affectOperation(op);
			op.setCategory( c );
			
			operationRepository.save(op);
			log.debug( "Generate operation {}" , op );
			cpt++;
		}
		return cpt;
	}

	

	
}
