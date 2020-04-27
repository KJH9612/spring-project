package iducs.spring.blog.domain;

import java.sql.Timestamp;

public class Blog {
	private long id; // 釉붾줈洹� �븘�씠�뵒 (�떇蹂꾩옄)
	private String title; // 釉붾줈洹� �젣紐�
	private String content; // 釉붾줈洹� �궡�슜
	private String filepath; // 釉붾줈洹� 泥⑤� �씠誘몄�
	private String blogger; // 釉붾줈洹� �옉�꽦�옄
	private Timestamp regDateTime; // 釉붾줈洹� �옉�꽦�씪 : �궇吏쒖� �떆媛�
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getBlogger() {
		return blogger;
	}
	public void setBlogger(String blogger) {
		this.blogger = blogger;
	}
	public Timestamp getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(Timestamp regDateTime) {
		this.regDateTime = regDateTime;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", filepath=" + filepath + ", blogger="
				+ blogger + ", regDateTime=" + regDateTime + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
