package com.perso.bank.services.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BankParser {

	private File file;
	
	public BankParser(File file) {
		this.file = file;
	}
	
	public void importFile(  ) {
		
		BufferedReader buf;
		try {
			buf = new BufferedReader( new FileReader( file ) );
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}

		try {
			
			int nbLineHeader  = parseHeader( buf );
			int nbLineContent = parseOperations( buf );
		
			log.info("{} lines has been parse for the header"  , nbLineHeader);
			log.info("{} lines has been parse for the content" , nbLineContent);
			

			buf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public abstract int parseHeader(BufferedReader buffer) throws IOException;
	
	public abstract int parseOperations(BufferedReader buffer) throws IOException;
	
}
