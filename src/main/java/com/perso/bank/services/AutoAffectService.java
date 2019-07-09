package com.perso.bank.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.bank.domain.AutoAffect;
import com.perso.bank.domain.Category;
import com.perso.bank.domain.Operation;
import com.perso.bank.domain.dto.AutoAffectDTO;
import com.perso.bank.domain.dto.convert.AutoAffectConverter;
import com.perso.bank.repository.AutoAffectRepository;

@Service
public class AutoAffectService {

	@Autowired
	private AutoAffectRepository autoAffectRepository;
	
	@Autowired
	private AutoAffectConverter autoAffectConverter;
	
	@Transactional
	public AutoAffectDTO create(AutoAffectDTO dto) {
		AutoAffect aa = autoAffectRepository.save( autoAffectConverter.createFromDto(dto) );
		return autoAffectConverter.createDto(aa);
	}
	
	public boolean delete(AutoAffectDTO dto) {
		autoAffectRepository.delete( autoAffectConverter.createFromDto(dto) );
		return true;
	}
	
	@Transactional
	public List<AutoAffectDTO> findAll() {
		return autoAffectConverter.createDto( autoAffectRepository.findAll() );
	}
	
	public Category affectOperation( Operation op ) {
		
		List<AutoAffect> autoAffects = autoAffectRepository.findAll();
		
		for( AutoAffect autoAffect : autoAffects ) {
			Pattern pattern = Pattern.compile( autoAffect.getRegex() );
			Matcher m = pattern.matcher( op.getLabel() );
			if( m.find() ) return autoAffect.getCategory();
					
		}
		return null;
		
	}
	
}
