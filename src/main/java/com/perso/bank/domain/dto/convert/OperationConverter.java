package com.perso.bank.domain.dto.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.bank.domain.Operation;
import com.perso.bank.domain.dto.OperationDTO;
import com.perso.bank.repository.CategoryRepository;

@Service
public class OperationConverter extends AbstractDtoConverter<Operation, OperationDTO>{

	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public Operation createFromDto(OperationDTO dto) {
		Operation op = new Operation();
		op.setId( dto.getId() );
		op.setAmount(dto.getAmount());
		op.setOperationWay(dto.getOperationWay());
		op.setLabel( dto.getLabel() );
		if( dto.getCategory() != null ) {
			op.setCategory( categoryConverter.createFromDto( dto.getCategory() ) );
		}
		op.setDate( dto.getDate() );
		return op;
	}

	@Override
	public OperationDTO createDto(Operation source) {
		OperationDTO dto = new OperationDTO();
		dto.setId( source.getId() );
		dto.setAmount(source.getAmount());
		dto.setOperationWay( source.getOperationWay() );
		dto.setLabel( source.getLabel() );
		dto.setDate( source.getDate() );
		if( source.getCategory() != null ) {
			dto.setCategory( categoryConverter.createDto( source.getCategory() ) );
		}
		return dto;
	}

}
