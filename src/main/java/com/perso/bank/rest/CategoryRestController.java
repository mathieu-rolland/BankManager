package com.perso.bank.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.perso.bank.domain.dto.CategoryDTO;
import com.perso.bank.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	public CategoryService categoryService;
	
	@RequestMapping( method = RequestMethod.GET , path = "/list")
	public List<CategoryDTO> getCategories(){
		return categoryService.getAllCategory();
	}
	
	@RequestMapping( method = RequestMethod.POST , path = "/create")
	public CategoryDTO createCategorie(@RequestBody CategoryDTO category){
		return categoryService.createCategory(category);
	}
	
	@RequestMapping( method = RequestMethod.DELETE , path = "/delete")
	public boolean deleteCategorie(@RequestBody CategoryDTO category){
		return categoryService.deleteCategory(category);
	}
	
}
