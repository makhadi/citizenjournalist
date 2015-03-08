package com.cj.bean;
import com.cj.dao.*;
import com.cj.dto.ContentDto;
import com.cj.dto.UserDto;


public class UserBean {
	UserDao ud=new UserDao();
	public boolean register(UserDto userDto, String[] catArray)
	{
		boolean b=ud.register(userDto,catArray);
		return b;
	}
	
	public boolean beanOper(String userId,String password){
	boolean b=ud.Operation(userId,password);
	return b;
	}
	
	

}
