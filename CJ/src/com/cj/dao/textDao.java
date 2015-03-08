package com.cj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.cj.dto.textDto;

public class textDao {

	public boolean textDescript(textDto textdto) {
		String textDescript = textdto.getTextDescript();
		String contentName = textdto.getContentName();
		String category = textdto.getCategory();
		Date uploadDate = textdto.getUploadDate();
		String userName = textdto.getUserName();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@newideas:1521:oracle", "journalist",
					"citizen");
			Statement statement = con.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select content_seq.nextval from dual");
			PreparedStatement ps = con
			.prepareStatement("insert into cj_content(content_id,category_name,user_name,content_type_name,content_name,content_path,content_status,date_upload,content_approve_date,content_description) values(?,?,?,?,?,?,?,?,?,?)");
	
			if (resultSet.next()) {
				ps.setInt(1, resultSet.getInt(1));
				ps.setString(2, category.toLowerCase());
				ps.setString(3, userName);
				ps.setString(4, "text");
				// ps.setDate(8,uploadDate);
				// ps.setString(8,uploadDate);
				ps.setString(5, contentName);
				ps.setString(6, null);
				ps.setString(7, null);
				java.util.Date date = new java.util.Date();
				ps.setDate(8, new java.sql.Date(date.getTime()));
				ps.setString(9, null);
				ps.setString(10, textDescript);
				int i = ps.executeUpdate();
				if (i == 1) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
