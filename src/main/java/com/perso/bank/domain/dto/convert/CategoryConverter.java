package com.perso.bank.domain.dto.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.bank.domain.Category;
import com.perso.bank.domain.dto.CategoryDTO;
import com.perso.bank.repository.OperationRepository;

@Service
public class CategoryConverter extends AbstractDtoConverter<Category, CategoryDTO>{

	@Autowired 
	private OperationRepository operationRepository;
	
	@Override
	public Category createFromDto(CategoryDTO dto) {
		Category category = new Category();
		category.setColor(dto.getColor());
		category.setId( dto.getId() );
		category.setName( dto.getName() );
		if( dto.getOperations() != null && dto.getOperations().size() > 0 ) {
			category.setOperations(operationRepository.findAllById(dto.getOperations()));
		}
		
		return category;
	}

	@Override
	public CategoryDTO createDto(Category source) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId( source.getId() );
		dto.setColor( source.getColor() );
		dto.setName( source.getName() );
		List<Long> operationsId = new ArrayList<>();
		Optional.ofNullable( source.getOperations() )
				.orElse( new ArrayList<>() )
				.forEach( e -> operationsId.add(e.getId()));
		
		return dto;
	}

}
