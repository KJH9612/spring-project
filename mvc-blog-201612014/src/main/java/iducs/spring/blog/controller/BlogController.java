package iducs.spring.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
	@RequestMapping(value = "/main")
	public String main() {
		return "main/index";
	}
	@RequestMapping(value = "/blogs-post")
	public String postBlog() {
		return "blogs/new-form";
	}
	@RequestMapping(value = "/blogs-get")
	public String getBlog(){
		return "blogs/get-blog";
	}
	@RequestMapping(value = "/blogs-all")
	public String getBlogs() {
		return "blogs/get-blogs";
	}
}
