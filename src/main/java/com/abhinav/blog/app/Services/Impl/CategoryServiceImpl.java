package com.abhinav.blog.app.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinav.blog.app.Dto.CategoryDto;
import com.abhinav.blog.app.Entities.Category;
import com.abhinav.blog.app.Exceptions.ResourceNotFoundException;
import com.abhinav.blog.app.Repositories.CategoryRepo;
import com.abhinav.blog.app.Services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
    private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto,Category.class);
		Category added = this.categoryRepo.save(category);
		
		return this.modelMapper.map(added, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
     
		Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category Id",categoryId));
		
		cate.setCategoryTitle(categoryDto.getCategoryTitle());
		cate.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCate = this.categoryRepo.save(cate);
		return this.modelMapper.map(updateCate,CategoryDto.class);
	}
    
	// delete
	@Override
	public void deleteCategory(Integer categoryId) {

		Category cate=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id",categoryId));
		
		this.categoryRepo.delete(cate);
		
		
	}

	// get category
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category Id",categoryId));
		
		return this.modelMapper.map(cat,CategoryDto.class);
		
		
	}

	@Override
	public List<CategoryDto> getCategories() {

       List<Category> categories = this.categoryRepo.findAll();
       List<CategoryDto> categoriesDto = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return categoriesDto;
	}


}
