package com.abhinav.blog.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinav.blog.app.Dto.CategoryDto;
import com.abhinav.blog.app.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public  class CategoryController {
	
	@Autowired
	private CategoryService categoryService; 
	
      //Create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		   CategoryDto create =this.categoryService.createCategory(categoryDto);
		   return new ResponseEntity<CategoryDto>(create,HttpStatus.CREATED);

		}
	
	   //Update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId)
	{
		
	 CategoryDto updated	=this.categoryService.updateCategory(categoryDto,categoryId);
	 
	 return new ResponseEntity<CategoryDto>(updated,HttpStatus.OK);
	}
	
	
	  //delete
	@DeleteMapping("/{categoryId}")
	public String deleteCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId)
	{
		       this.categoryService.deleteCategory(categoryId);
	           return "deleted";
	}
	
	
	  // get 
	@GetMapping("/{categoryId}")
	public  ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
	{
		  CategoryDto get = this.categoryService.getCategory(categoryId);
	      return new ResponseEntity<CategoryDto>(get,HttpStatus.OK);
		            
	}

	  // get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> getAll = this.categoryService.getCategories();
           return ResponseEntity.ok(getAll);
	}
}