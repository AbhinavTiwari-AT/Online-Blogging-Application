package com.abhinav.blog.app.Services;

import java.util.List;

import com.abhinav.blog.app.Dto.CategoryDto;


	

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	void deleteCategory( Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
   List<CategoryDto> getCategories();


}
