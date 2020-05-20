package iducs.spring.blog.service;

import java.util.List;

import iducs.spring.blog.domain.Blog;

public interface BlogService {
	Blog getBlog(long id); // primary key 議고쉶
	List<Blog> getBlogs(); // all user 議고쉶
	List<Blog> getBlogsByTitle(String title); // name 議고쉶
	List<Blog> getBlogByBlogger(String blogger); // company 議고쉶
	List<Blog> getBlogByPage(int index, int size); // page濡� 議고쉶
	int postBlog(Blog blog); // �깮�꽦 create
	int updateBlog(Blog blog); // �닔�젙 edit
	int deleteBlog(long id); // �궘�젣 delete
}
