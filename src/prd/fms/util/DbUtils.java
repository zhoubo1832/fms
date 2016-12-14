package prd.fms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {

	static{
		Connection conn = null; 
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:tag.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("create table if not exists t_file_tags(file_path, tag_name);");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//
				}
			}
		}
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:tag.db");
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<String> selectFileTags(String filePath) {
		List<String> list = new ArrayList<String>();
		Connection conn = getConnection();
		if(conn != null) {
			Statement stat = null;
			ResultSet rs = null;
			try {
				stat = conn.createStatement();
				rs = stat.executeQuery("select tag_name from t_file_tags where file_path = '" + filePath + "';");
				while (rs.next()) {
					list.add(rs.getString("tag_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	
		return list;
	}
	
	public List<String> selectAllTags() {
		List<String> list = new ArrayList<String>();
		Connection conn = getConnection();
		if(conn != null) {
			Statement stat = null;
			ResultSet rs = null;
			try {
				stat = conn.createStatement();
				rs = stat.executeQuery("select distinct tag_name from t_file_tags;");
				while (rs.next()) {
					list.add(rs.getString("tag_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	
		return list;
	}
	
	public void updateFileTags(String filePath, List<String> list) {
		Connection conn = getConnection();
		if(conn != null) {
			PreparedStatement stat = null;

			try {
				stat = conn.prepareStatement("delete from t_file_tags where file_path = ? ;");
				stat.setString(1, filePath);
				stat.executeUpdate();

				if(list.size() > 0) {
					stat = conn.prepareStatement("insert into t_file_tags(file_path,tag_name) values(?,?);");				
					for(String s : list) {
						stat.setString(1, filePath);
						stat.setString(2, s);
						stat.addBatch();
					}
					stat.executeBatch();
				}
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return;
	}
	
	public static void main(String[] args) {
		DbUtils db = new DbUtils();
		List<String> list = new ArrayList<String>();
		list.add("Meal");
		list.add("Travel");
		list.add("ppt");
		
		String filePath = "C:\\test\\b2";
		db.updateFileTags(filePath, list);
		List<String> tagList = db.selectFileTags(filePath);
		for(String s : tagList) {
			System.out.println(s);
		}
	}
}
