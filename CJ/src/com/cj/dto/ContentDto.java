package com.cj.dto;

import java.util.Date;

public class ContentDto {
	private int contentId;
	private String categoryName;
	private String userName;
	private String contentTypeName;
	private String contentName;
	private String contentPath;
	private Date contentUploadDate;
	private Date contentApprovedDate;
	private String contentStatus;
	private String contentDescription;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getContentDescription() {
		return contentDescription;
	}
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getContentStatus() {
		return contentStatus;
	}
	public void setContentStatus(String contentStatus) {
		this.contentStatus = contentStatus;
	}
	public String getContentTypeName() {
		return contentTypeName;
	}
	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getContentApprovedDate() {
		return contentApprovedDate;
	}
	public void setContentApprovedDate(Date contentApproved) {
		this.contentApprovedDate = contentApproved;
	}
	public Date getContentUploadDate() {
		return contentUploadDate;
	}
	public void setContentUploadDate(Date contentUpload) {
		this.contentUploadDate = contentUpload;
	}
	
	
	
	
}
