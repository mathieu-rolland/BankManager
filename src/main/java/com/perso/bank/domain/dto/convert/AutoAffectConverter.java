package com.perso.bank.domain.dto.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.bank.domain.AutoAffect;
import com.perso.bank.domain.dto.AutoAffectDTO;

@Service
public class AutoAffectConverter extends AbstractDtoConverter<AutoAffect, AutoAffectDTO>{

	@Autowired
	private CategoryConverter categoryConverter;
	
	
	@Override
	public AutoAffect createFromDto(AutoAffectDTO dto) {
		AutoAffect aa = new AutoAffect();
		if( dto.getCategory() != null ) {
			aa.setCategory( categoryConverter.createFromDto(dto.getCategory()) );
		}
		aa.setRegex( dto.getRegex() );
		aa.setId( dto.getId() );
		return aa;
	}

	@Override
	public AutoAffectDTO createDto(AutoAffect source) {
		AutoAffectDTO dto = new AutoAffectDTO();
		dto.setCategory( categoryConverter.createDto( source.getCategory() ) );
		dto.setRegex( source.getRegex() );
		dto.setId(source.getId());
		return dto;
	}

}

