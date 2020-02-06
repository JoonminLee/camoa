package com.javalaec.ex.dto;

import java.sql.Timestamp;

public class CDto {
	int cNum;
	String cContent; 
	String cName;
	String cDateFmt;
	int cStep; 
	int cLevel;
	int cMom;
	
	public CDto (int cNum, String cContent, String cName, String cDateFmt, int cStep, int cLevel,int cMom){
		this.cNum = cNum;
		this.cContent=cContent; 
		this.cName=cName;
		this.cDateFmt=cDateFmt;
		this.cStep=cStep; 
		this.cLevel=cLevel;
		this.cMom= cMom;
	}
	public String getcDateFmt() {
		return cDateFmt;
	}
	public void setcDateFmt(String cDateFmt) {
		this.cDateFmt = cDateFmt;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getcStep() {
		return cStep;
	}
	public void setcStep(int cStep) {
		this.cStep = cStep;
	}
	public int getcLevel() {
		return cLevel;
	}
	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}
	public int getcMom() {
		return cMom;
	}
	public void setcMom(int cMom) {
		this.cMom = cMom;
	}
}
