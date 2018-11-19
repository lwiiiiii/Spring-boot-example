package com.example.demo.dto;

import java.util.Date;

public class Uploadedfile {
	int id;
	String fileName;
	int fileSize;
	Date fileTime;
	byte[] data;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public Date getFileTime() {
		return fileTime;
	}
	
	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) { // 업로드된 파일의 목록을 웹페이지에 표시할 때는, 파일 내용을 DB에서 조회할 필요가 없음
		this.data = data;
	}
	
}
