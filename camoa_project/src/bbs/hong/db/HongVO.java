package bbs.hong.db;

import java.sql.Timestamp;

public class HongVO {
	private int hong_num, hong_count, hong_hit;
	private String hong_name, hong_subject, hong_content, hong_realpath, hong_filename;
	private Timestamp hong_date;

	public HongVO(int hong_num, String hong_name, String hong_subject, String hong_content, String hong_realpath,
			String hong_filename, int hong_count, int hong_hit, Timestamp hong_date) {
		this.hong_num = hong_num;
		this.hong_count = hong_count;
		this.hong_hit = hong_hit;
		this.hong_name = hong_name;
		this.hong_subject = hong_subject;
		this.hong_content = hong_content;
		this.hong_realpath = hong_realpath;
		this.hong_filename = hong_filename;
		this.hong_date = hong_date;
	}

	public int getHong_hit() {
		return hong_hit;
	}

	public void setHong_hit(int hong_hit) {
		this.hong_hit = hong_hit;
	}

	public int getHong_num() {
		return hong_num;
	}

	public void setHong_num(int hong_num) {
		this.hong_num = hong_num;
	}

	public int getHong_count() {
		return hong_count;
	}

	public void setHong_count(int hong_count) {
		this.hong_count = hong_count;
	}

	public String getHong_name() {
		return hong_name;
	}

	public void setHong_name(String hong_name) {
		this.hong_name = hong_name;
	}

	public String getHong_subject() {
		return hong_subject;
	}

	public void setHong_subject(String hong_subject) {
		this.hong_subject = hong_subject;
	}

	public String getHong_content() {
		return hong_content;
	}

	public void setHong_content(String hong_content) {
		this.hong_content = hong_content;
	}

	public String getHong_realpath() {
		return hong_realpath;
	}

	public void setHong_realpath(String hong_realpath) {
		this.hong_realpath = hong_realpath;
	}

	public String getHong_filename() {
		return hong_filename;
	}

	public void setHong_filename(String hong_filename) {
		this.hong_filename = hong_filename;
	}

	public Timestamp getHong_date() {
		return hong_date;
	}

	public void setHong_date(Timestamp hong_date) {
		this.hong_date = hong_date;
	}

}
