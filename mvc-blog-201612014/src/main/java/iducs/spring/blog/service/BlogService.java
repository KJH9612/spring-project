package iducs.spring.blog.service;

import java.util.List;
import iducs.spring.blog.domain.Blog;

public interface BlogService {
	Blog getBlog(long id); // primary key�뿉 �빐�떦�븯�뒗 id濡� 議고쉶
	List<Blog> getBlogs(); // 紐⑤뱺 �궗�슜�옄 議고쉶
	List<Blog> getBlogsByTitle(String title); // name�쑝濡� 議고쉶
	List<Blog> getBlogByBlogger(String blogger); // company�쑝濡� 議고쉶
	List<Blog> getBlogByPage(int index, int size); // �럹�씠吏�濡� 議고쉶
	int postBlog(Blog blog); // �깮�꽦
	int putBlog(Blog blog); // �닔�젙
	int deleteBlog(Blog blog); // �궘�젣
}
