package com.perso.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.bank.domain.Category;
import com.perso.bank.domain.dto.CategoryDTO;
import com.perso.bank.domain.dto.convert.CategoryConverter;
import com.perso.bank.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryConverter categoryConverter;
	
	public List<CategoryDTO> getAllCategory() {
		return categoryConverter.createDto(categoryRepository.findAll());
	}
	
	public CategoryDTO createCategory( CategoryDTO dto ) {
		Category category = categoryConverter.createFromDto(dto);
		return categoryConverter.createDto(categoryRepository.save(category));
	}

	public boolean deleteCategory(CategoryDTO category) {
		 categoryRepository.delete( categoryConverter.createFromDto(category) );
		 return true;
	}
	
}
