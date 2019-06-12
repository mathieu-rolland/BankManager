package com.perso.bank.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import liquibase.util.file.FilenameUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StorageService {

	@Value("${bank-manager.storage}")
	private String storageDirectory;
	
	@Value("${bank-manager.max-iteration-same-file}")
	private String maxIterationSameFile;
	
	private int maxIterationConverted;
	
	@PostConstruct
	public void checkStorage() throws Exception {
		
		File f = new File( storageDirectory );
		if( !f.isDirectory() ) {
			if( !f.mkdirs() ) {
				log.error("Failed to create directory {}" , storageDirectory );
				throw new Exception("Failed to create directory");
			}
		}
		if( !f.canWrite() ) {
			throw new Exception("Directory " + storageDirectory + " is not writable!");
		}
		
		try {
			maxIterationConverted = Integer.parseInt(maxIterationSameFile);
		}catch(NumberFormatException e) {
			throw new Exception("The parameter \"max-iteration-same-file\": "+ maxIterationSameFile
						+" is not an Integer");
		}
	}
	
	public String writeFileToLocalStorage( String originalFilename, MultipartFile file ) {
		
		if( file == null  ) {
			return "Unexpected missing file!";
		}
		
		FileOutputStream fos;
		try {
			File f = generateNonExistingFile( originalFilename );
			fos = new FileOutputStream( f );
			fos.write( file.getBytes() );
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return "No file found exception";
		}catch (IOException e) {
			e.printStackTrace();
			return "Unexpected error when read/write file";
		}catch (Exception e) {
			return e.getMessage();
		}
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
	
	private File generateNonExistingFile( String originalFilename ) throws Exception {
	
		File f = new File( storageDirectory + File.separator + originalFilename );
		
		String extension = "";
		String filename = originalFilename;
		
		int i = originalFilename.lastIndexOf('.');
		if (i > 0) {
		    extension = originalFilename.substring(i+1);
		    filename = originalFilename.substring(0,i);
		}
	
		int cpt = 0;

		while( f.exists() && cpt < maxIterationConverted ) {
			f = new File( storageDirectory + File.separator + filename + "_" + cpt + "." + extension );
			cpt++;
		}
		
		if( cpt == maxIterationConverted && f.exists() ) {
			log.error( "To many file exists with the original name " + originalFilename );
			throw new Exception("Too many file with the same name");
		}
		
		return f;
		
	}
	
	
}
