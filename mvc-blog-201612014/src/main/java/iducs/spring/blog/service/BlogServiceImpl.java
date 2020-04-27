package iducs.spring.blog.service;

import java.util.List;
import iducs.spring.blog.domain.Blog;
import iducs.spring.blog.repository.BlogRepository;

public class BlogServiceImpl implements BlogService{
	private BlogRepository blogRepository;
	
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	//read
	@Override
	public Blog getBlog(long id) {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
		blog.setId(id);
		return blogRepository.read(blog);
	}
	
	//read list
	@Override
	public List<Blog> getBlogs() {
		// TODO Auto-generated method stub
		return blogRepository.readList();
	}

	@Override
	public List<Blog> getBlogsByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Blog> getBlogByBlogger(String blogger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> getBlogByPage(int index, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	//create
	@Override
	public int postBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogRepository.create(blog);
	}

	//update
	@Override
	public int putBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogRepository.update(blog);
	}
	
	//delete
	@Override
	public int deleteBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogRepository.delete(blog);
	}
	
}
