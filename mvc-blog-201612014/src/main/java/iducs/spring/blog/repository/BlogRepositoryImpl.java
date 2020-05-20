package iducs.spring.blog.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import iducs.spring.blog.domain.Blog;
import iducs.spring.blog.repository.BlogRepository;

@Repository
public class BlogRepositoryImpl implements BlogRepository{

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public BlogRepositoryImpl(SimpleDriverDataSource dataSource)
	{
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public int create(Blog blog) {
		int count = 0;
		String sql = "insert into blog values(seq_blog.nextval, :title, :content, :filepath, :blogger, :regDateTime)";
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(blog);
		count = jdbcTemplate.update(sql,  params);
		System.out.println(count);
		return count;
	}

	@Override
	public Blog read(Blog blog) {
		String sql = "select * from blog where id= :id";
		//Object[] id = new Object[] {blog.getId()};
		Map<String, Integer> params = Collections.singletonMap("id", (int) blog.getId());
		Blog b = jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<Blog>(Blog.class));
		return b;
	}

	@Override
	public List<Blog> readList() {
		String sql = "select  * from blog order by id desc";
		List<Blog> blogList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class));
		return blogList;
	}

	@Override
	public int update(Blog blog) {
		int count = 0;
		String sql = "update blog set title=:title, content=:content, filepath=:filepath, blogger= :blogger, "
				+ "reg_date_time=:regDateTime where id= :id";
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(blog);
		count = jdbcTemplate.update(sql, params);
		return count;
	}

	@Override
	public int delete(Blog blog) {
		int count = 0;
		//Object[] params = new Object[] {blog.getId()};
		String sql = "delete from blog where id=:id";
		Map<String, Integer> params = Collections.singletonMap("id", (int) blog.getId());
		count = jdbcTemplate.update(sql, params);
				
		return count;
	}

}
