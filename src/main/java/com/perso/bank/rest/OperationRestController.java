package com.perso.bank.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perso.bank.domain.dto.OperationDTO;
import com.perso.bank.services.OperationService;
import com.perso.bank.services.StorageService;

@RestController
@RequestMapping("/operations")
public class OperationRestController {

	@Autowired
	private OperationService operationService;

	@Autowired
	private StorageService storageService;
	
	
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

	@RequestMapping( method = RequestMethod.POST , path = "/upload" )
	public String upload( @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes ){
		
		String message = "";
		if( file != null ) {
			message = storageService.writeFileToLocalStorage( file.getOriginalFilename() , file );
		}
				
		redirectAttributes.addFlashAttribute("message", message );

        return "redirect:/";

  	}
	
}
