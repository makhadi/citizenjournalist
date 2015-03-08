package com.cj.bean;

import java.util.ArrayList;

import com.cj.dao.ContentViewDao;
import com.cj.dto.ContentDto;

public class ContentViewBean {
	ContentViewDao contentViewDao=new ContentViewDao();  
	ArrayList<ContentDto> al=new ArrayList<ContentDto>();
	public ArrayList<ContentDto> ContentView(String categoryName,String contentType)
	{
		al=contentViewDao.ContentView(categoryName, contentType);
		return al;
	}
	
}
