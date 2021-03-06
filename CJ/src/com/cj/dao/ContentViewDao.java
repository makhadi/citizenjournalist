package com.cj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.cj.dto.ContentDto;


public class ContentViewDao {

	public ArrayList<ContentDto> ContentView(String categoryName,
			String contentType) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@newideas:1521:oracle", "journalist",
					"citizen");

			String query = "select * from cj_content where category_name=? and content_type_name=?";

			ps = con.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.setString(2, contentType);
			
			rs = ps.executeQuery();

			ArrayList<ContentDto> al = new ArrayList<ContentDto>();
			while (rs.next()) {
				ContentDto contentDto = new ContentDto();
				contentDto.setContentId(rs.getInt(1));
				contentDto.setCategoryName(rs.getString(2));
				contentDto.setUserName(rs.getString(3));
				contentDto.setContentTypeName(rs.getString(4));
				contentDto.setContentName(rs.getString(5));
				contentDto.setContentPath(rs.getString(6));
				contentDto.setContentStatus(rs.getString(7));
				contentDto.setContentUploadDate(rs.getDate(8));
				contentDto.setContentApprovedDate(rs.getDate(9));				
				contentDto.setContentDescription(rs.getString(10));
				
				al.add(contentDto);
			}
			
			System.out.println("al.size:::"+al.size());
			return al;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
