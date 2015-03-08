package com.cj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cj.dto.ContentDto;
import com.cj.dto.UserDto;

public class UserDao {
	
	
	public boolean register(UserDto userDto, String[] catArray){
		String userName=userDto.getUserName();
		String mobileNumber=userDto.getMobileNumber();
		String gender=userDto.getGender();
		String emailId=userDto.getEmailId();
		String address=userDto.getAddress();
		
		System.out.println("username:::"+userName);
		System.out.println("mobileNumber:::"+mobileNumber);
		System.out.println("gender:::"+gender);
		System.out.println("emailId:::"+emailId);
		System.out.println("address:::"+address);
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@newideas:1521:oracle","journalist","citizen");
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement("insert into cj_user(user_name,ph_no,gender,email_id,address) values(?,?,?,?,?)");
			ps.setString(1,userName);
			ps.setString(2,mobileNumber);
			ps.setString(3,gender);
			ps.setString(4,emailId);
			ps.setString(5,address);
			int i=ps.executeUpdate();
			if(i==1){
				
				PreparedStatement ps1 = con.prepareStatement("insert into cj_category_has_cj_user values(?,?)");
				
				int count = 0;
				for(String cat : catArray ){
					ps1.setString(1, userName);
					ps1.setString(2, cat.toLowerCase());
					int j = ps1.executeUpdate();
					if (j>0) {
						count++;
					}
				}
				
				if(count == catArray.length){
					con.commit();
					return true;
				}else{
					con.rollback();
					return false;
				}
			}
			else
				return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return false;
	}
	
	public boolean Operation(String userId, String password)
	{
		if(userId.equals("idea") && password.equals("labs"))
		{
			return true;
		}else
			return false;
	}

}
