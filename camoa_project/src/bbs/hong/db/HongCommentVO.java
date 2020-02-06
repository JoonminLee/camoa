package bbs.hong.db;

import java.sql.Timestamp;

public class HongCommentVO {
	private int c_num;
	private String c_hong_name;
	private String c_hong_content;
	private int c_hong_num;
	private Timestamp c_hong_date;

	public HongCommentVO(int c_num, String c_hong_name, String c_hong_content, int c_hong_num, Timestamp c_hong_date) {
		this.c_num = c_num;
		this.c_hong_name = c_hong_name;
		this.c_hong_content = c_hong_content;
		this.c_hong_num = c_hong_num;
		this.c_hong_date = c_hong_date;

	}

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public String getC_hong_name() {
		return c_hong_name;
	}

	public void setC_hong_name(String c_hong_name) {
		this.c_hong_name = c_hong_name;
	}

	public String getC_hong_content() {
		return c_hong_content;
	}

	public void setC_hong_content(String c_hong_content) {
		this.c_hong_content = c_hong_content;
	}

	public int getC_hong_num() {
		return c_hong_num;
	}

	public void setC_hong_num(int c_hong_num) {
		this.c_hong_num = c_hong_num;
	}

	public Timestamp getC_hong_date() {
		return c_hong_date;
	}

	public void setC_hong_date(Timestamp c_hong_date) {
		this.c_hong_date = c_hong_date;
	}

}
