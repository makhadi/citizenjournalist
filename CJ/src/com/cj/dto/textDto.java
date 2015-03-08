package com.cj.dto;

import java.util.Date;

public class textDto {
	private String textDescript;
	private String contentName;
	private String category;
	private Date uploadDate;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getTextDescript() {
		return textDescript;
	}
	public void setTextDescript(String textDescript) {
		this.textDescript = textDescript;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}
