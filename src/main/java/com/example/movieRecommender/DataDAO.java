package com.example.movieRecommender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class DataDAO {

	private Connection connection;

	public DataDAO() throws Exception {
		connection = DBUtility.getConnection();
	}

	public ArrayList<String> getMovieNames(String searchStr) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement ps = null;
		String data;
		try {
			ps = connection.prepareStatement("select name from ML_TAB1 where lower(name) like ? and rownum <=10");
			ps.setString(1, searchStr + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				data = rs.getString("NAME");
				list.add(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public String getMovieId(String movieName) {
		String movieId = "";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("select id from ML_TAB1 where lower(name) = ?");
			ps.setString(1, movieName.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs !=null){
				rs.next();	
				movieId = rs.getString("ID");			
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return movieId;
	}

}
