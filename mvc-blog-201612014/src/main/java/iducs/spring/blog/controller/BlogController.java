package iducs.spring.blog.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import iducs.spring.blog.domain.Blog;
import iducs.spring.blog.service.BlogService;
//import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class BlogController {
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	private BlogService blogService;
	private String uploadPath;
	public BlogController(BlogService blogService)
	{
		this.blogService = blogService;
	}
	
	
	@RequestMapping("/main")
	public String index() {
		return "redirect:/main/index";
	}
	@GetMapping("/blogs/get-blogs")
	public String getBlogs(Locale local, Model model) {
		logger.info("GetBlogs");
		List<Blog> blogList = blogService.getBlogs();
		model.addAttribute("blogList", blogList);
		return "blogs/get-blogs";
	}
	@GetMapping("/blogs/{id}")
	public String getBlog(@PathVariable("id") Long id, Locale local, Model model) {
		logger.info("GetBlog");
		model.addAttribute("blog",blogService.getBlog(id));
		return "blogs/get-blog";
	}
	
	@PostMapping("/blogs")
	@Transactional
	public String postBlog(MultipartHttpServletRequest request, Locale local, Model model) throws IllegalStateException, IOException {
		logger.info("PostBlog");
		Blog blog = new Blog();
		blog.setTitle(request.getParameter("title"));
		blog.setContent(request.getParameter("content"));
		blog.setBlogger(request.getParameter("blogger"));
		blog.setRegDateTime(java.sql.Timestamp.valueOf(request.getParameter("regDateTime")));
		
		uploadPath = this.getClass().getResource("/").getPath() + "..\\..\\resources\\uploads";
		MultipartFile file = request.getFile("filepath");
		if(!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(uploadPath, file.getOriginalFilename()));
			blog.setFilepath(file.getOriginalFilename());
		}
		else {
			
		}
		blogService.postBlog(blog);
		
		return "redirect:/blogs/get-blogs";
	}
	//n Log
	@GetMapping("/blogs/new-blog")
	public String newBlog(Model model) {
		return "blogs/new-form";
	}
	
	@PostMapping("/blogs/{id}")
	@Transactional
	public String updateBlog(
			@PathVariable long id,
			@RequestParam final String title,
			@RequestParam final String content,
			@RequestParam final String blogger,
			@RequestParam Timestamp regDateTime,
			@RequestParam("filepath") MultipartFile file,
			Locale local, Model model) throws IllegalStateException, IOException
	{
		logger.info("UpdateBlog");
		
		Blog blog = new Blog();
		blog.setId(id);
		blog.setTitle(title);
		blog.setContent(content);
		blog.setBlogger(blogger);
		blog.setRegDateTime(regDateTime);
		uploadPath = this.getClass().getResource("/").getPath() + "..\\..\\resources\\uploads";
		if(!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(uploadPath, file.getOriginalFilename()));
			blog.setFilepath(file.getOriginalFilename());
		} else {
			
		}
		int count = blogService.updateBlog(blog);
		System.out.println("count : " + count);
		if(count >0)
			return "redirect:/blogs/" + id;
		else
			return "redirect:/blogs/error";
	}
	@GetMapping("/blogs/edit")
	public String editBlog(@RequestParam(name="id") long id, Model model) {
		Blog blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		return "blogs/edit-form";
	}
	
	@DeleteMapping("/blogs/{id}")
	public String deleteBlog(@PathVariable long id, Model model) {
		logger.info("DeleteBlog");
		int count = blogService.deleteBlog(id);
		
		if(count > 0)
			return "redirect:/blogs/get-blogs";
		else
			return "redirect:/blogs/error";
				
	}

}
