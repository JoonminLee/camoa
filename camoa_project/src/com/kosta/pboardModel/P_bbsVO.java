package com.kosta.pboardModel;
import java.io.Serializable;
import java.sql.Timestamp;

public class P_bbsVO implements Serializable{
	int cf_num;
	int cf_ame;
	int cf_latte;
	int cf_caramel;
	int cf_mocha;
	int cf_vanila;
	int cf_readcount;
	int cf_recom;
	String cf_id;
	String cf_name;
	String cf_address;
	String cf_workhour;
	String cf_phone;
	String cf_park;
	String cf_intro; 
	Timestamp  cf_reg_date;
	
	
	public Timestamp getCf_reg_date() {
		return cf_reg_date;
	}
	public void setCf_reg_date(Timestamp cf_reg_date) {
		this.cf_reg_date = cf_reg_date;
	}
	public String getCf_phone() {
		return cf_phone;
	}
	public void setCf_phone(String cf_phone) {
		this.cf_phone = cf_phone;
	}
	public String getCf_address() {
		return cf_address;
	}
	public void setCf_address(String cf_address) {
		this.cf_address = cf_address;
	}
	public String getCf_name() {
		return cf_name;
	}
	public void setCf_name(String cf_name) {
		this.cf_name = cf_name;
	}
	public int getCf_num() {
		return cf_num;
	}

	public void setCf_num(int cf_num) {
		this.cf_num = cf_num;
	}
	public int getCf_ame() {
		return cf_ame;
	}
	public void setCf_ame(int cf_ame) {
		this.cf_ame = cf_ame;
	}
	public int getCf_latte() {
		return cf_latte;
	}
	public void setCf_latte(int cf_latte) {
		this.cf_latte = cf_latte;
	}
	public int getCf_caramel() {
		return cf_caramel;
	}
	public void setCf_caramel(int cf_caramel) {
		this.cf_caramel = cf_caramel;
	}
	public int getCf_mocha() {
		return cf_mocha;
	}
	public void setCf_mocha(int cf_mocha) {
		this.cf_mocha = cf_mocha;
	}
	public int getCf_vanila() {
		return cf_vanila;
	}
	public void setCf_vanila(int cf_vanila) {
		this.cf_vanila = cf_vanila;
	}
	public int getCf_readcount() {
		return cf_readcount;
	}
	public void setCf_readcount(int cf_readcount) {
		this.cf_readcount = cf_readcount;
	}
	public int getCf_recom() {
		return cf_recom;
	}
	public void setCf_recom(int cf_recom) {
		this.cf_recom = cf_recom;
	}
	public String getCf_id() {
		return cf_id;
	}
	public void setCf_id(String cf_id) {
		this.cf_id = cf_id;
	}
	public String getCf_workhour() {
		return cf_workhour;
	}
	public void setCf_workhour(String cf_workhour) {
		this.cf_workhour = cf_workhour;
	}
	public String getCf_park() {
		return cf_park;
	}
	public void setCf_park(String cf_park) {
		this.cf_park = cf_park;
	}
	public String getCf_intro() {
		return cf_intro;
	}
	public void setCf_intro(String cf_intro) {
		this.cf_intro = cf_intro;
	}
}
