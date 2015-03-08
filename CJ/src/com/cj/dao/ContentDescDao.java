package com.cj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.cj.dto.textDto;

public class ContentDescDao {
	public boolean contentDesc(String fileName,String fileDesc) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@newideas:1521:oracle", "journalist",
					"citizen");
			Statement statement = con.createStatement();
			PreparedStatement ps = con
			.prepareStatement("insert into cj_content(content_id,category_name,user_name,content_type_name,content_name,content_path,content_status,date_upload,content_approve_date,content_description) values(?,?,?,?,?,?,?,?,?,?)");
	
				ps.setString(5, fileName);
				ps.setString(10, fileDesc);
				int i = ps.executeUpdate();
				if (i == 1) {
					return true;
				} else {
					return false;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
