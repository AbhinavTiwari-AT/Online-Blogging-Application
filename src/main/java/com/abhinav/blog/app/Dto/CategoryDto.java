package com.abhinav.blog.app.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	
	private int categoryId;
	
	@NotBlank
	@Size(min=4,message="mimimmum size of title is 4")
    private String categoryTitle;
	@NotBlank
	private String categoryDescription;
	


}
