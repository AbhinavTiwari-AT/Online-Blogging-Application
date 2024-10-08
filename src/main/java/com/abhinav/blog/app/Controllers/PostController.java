package com.abhinav.blog.app.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abhinav.blog.app.Dto.PostDto;
import com.abhinav.blog.app.Dto.PostResponse;
import com.abhinav.blog.app.Services.FileService;
import com.abhinav.blog.app.Services.PostService;



@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//get By user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}	
		
	//get By category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			                                       @RequestParam(value="pageSize",defaultValue = "10",required = false)Integer pageSize,
	                                               @RequestParam(value ="sortBy",defaultValue = "postId",required = false ) String sortBy,
	                                               @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
			                                       )
	                                               
	{
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	// get  post details by id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId)
	{
        PostDto postDto		  =this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public String deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return "deleted";
	}
      //update post
	 
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId)
	{
	PostDto updatePost	=this.postService.updatePost(postDto, postId);
		
 		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//Search  not working
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keyword )
	{
		      List<PostDto> result  =  this.postService.searchPosts(keyword);
		      
		      return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	// post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException
	{
		      String fileName = this.fileService.uploadImage(path, image);
		      PostDto postDto = this.postService.getPostById(postId);
		      postDto.setImageName(fileName);
		      PostDto updatePost = this.postService.updatePost(postDto, postId);
		      return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}

}
