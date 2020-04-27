package iducs.spring.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iducs.spring.blog.domain.Blog;

public class BlogRepositoryImpl implements BlogRepository{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private DataTemplateImpl dataSource = new DataTemplateImpl();
	
	public BlogRepositoryImpl(DataTemplateImpl dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int create(Blog blog) {
		int rows = 0;
		String sql = "insert into blog values(seq_blog.nextval, ?, ?, ?, ?, ?)";
		conn = dataSource.getConnection();
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blog.getTitle());
			pstmt.setString(2, blog.getContent());
			pstmt.setString(3, blog.getFilepath());
			pstmt.setString(4, blog.getBlogger());
			pstmt.setTimestamp(5, blog.getRegDateTime());
			rows = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally { dataSource.closeResources(conn, stmt, pstmt, rs); }
		return rows;
	}
	@Override
	public Blog read(Blog blog) {
		Blog retBlog = null;
		conn = dataSource.getConnection();
		
		String sql = "select * from blog where id='" + blog.getId()  + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) { // �떎�쓬 record媛믪쓣 �젒洹�	
				// rs : record 吏묓빀, rs.getString(1) : �쁽�옱 �젅肄붾뱶�쓽 泥ル쾲�옱 �븘�뱶 媛�
				retBlog = new Blog();
				retBlog.setId(rs.getInt(1)); // rs.getString("<field_name>"); �븘�뱶�씠由꾨줈�룄 媛��뒫
				retBlog.setTitle(rs.getString(2)); // �깮�꽦 �떆 �븘�뱶 �닚�꽌
				retBlog.setContent(rs.getString(3));
				retBlog.setFilepath(rs.getString(4));
				retBlog.setBlogger(rs.getString(5));;
				retBlog.setRegDateTime(rs.getTimestamp(6));
			}
		} catch (SQLException e) {			e.printStackTrace();		} 
		finally {			dataSource.closeResources(conn, stmt, pstmt, rs);		}
		return retBlog;
	}

	@Override
	public List<Blog> readList() {
		List<Blog> blogList = null;
		Blog retBlog = null;
		conn = dataSource.getConnection();
		
		String sql = "select * from blog order by id desc";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			blogList = new ArrayList<Blog>();
			while(rs.next()) { // �떎�쓬 record媛믪쓣 �젒洹�	
				// rs : record 吏묓빀, rs.getString(1) : �쁽�옱 �젅肄붾뱶�쓽 泥ル쾲�옱 �븘�뱶 媛�
				retBlog = new Blog();
				retBlog.setId(rs.getInt(1)); // rs.getString("<field_name>"); �븘�뱶�씠由꾨줈�룄 媛��뒫
				retBlog.setTitle(rs.getString(2)); // �깮�꽦 �떆 �븘�뱶 �닚�꽌
				retBlog.setContent(rs.getString(3));
				retBlog.setFilepath(rs.getString(4));
				retBlog.setBlogger(rs.getString(5));;
				retBlog.setRegDateTime(rs.getTimestamp(6));
				blogList.add(retBlog);
			}
		} catch (SQLException e) {			e.printStackTrace();		} 
		finally {			dataSource.closeResources(conn, stmt, pstmt, rs);		}
		return blogList;
	}

	@Override
	public int update(Blog blog) {
		int rows = 0;
		String sql = "update blog set title=?, content=?, filepath=?, reg_date_time=? where id=?";
		//TO_TIMESTAMP('2019-12-15 12:00:00.0', 'YYYY-MM-DD HH24:MI:SS.FF3')
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blog.getTitle());
			pstmt.setString(2, blog.getContent());
			pstmt.setString(3, blog.getFilepath());
			pstmt.setTimestamp(4, blog.getRegDateTime());
			pstmt.setLong(5, blog.getId());
			rows = pstmt.executeUpdate();
			if(rows == 0)
				System.out.println(blog.getTitle() + "error : " + blog.getRegDateTime() + ":::" + blog.getId());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally { dataSource.closeResources(conn, stmt, pstmt, rs); }
		return rows;
	}

	@Override
	public int delete(Blog blog) {
		int rows = 0;
		String sql = "delete from blog where id=?";
		// 肄붾뱶 異붽�
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, blog.getId());
			rows = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally { dataSource.closeResources(conn, stmt, pstmt, rs); }
		return rows;
	}
}
