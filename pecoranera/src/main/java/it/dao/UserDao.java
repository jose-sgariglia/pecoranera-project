package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.model.OrderBean;
import it.model.UserBean;
import it.model.UserBean.Role;

public class UserDao extends BeanDaoAbstract<UserBean> {

	private static final String TABLE_NAME = "user";
	
	public UserDao(DataSource ds) {
		super(ds);
	}
	
	@Override
	public synchronized void doSave(UserBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String insertSQL = "INSERT INTO " + UserDao.TABLE_NAME 
				+ "(email, password, username, role) VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(insertSQL);
			
			preStm.setString(1, item.getEmail());
			preStm.setString(2, item.getPassword());
			preStm.setString(3, item.getUsername());
			preStm.setString(4, item.getRole().toString());
			
			preStm.executeUpdate();
			conn.commit();
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		
	}

	@Override
	public synchronized UserBean doRetrieveByKey(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		UserBean user = new UserBean();
		String selectSQL = "SELECT * FROM " + UserDao.TABLE_NAME + "WHERE id_user = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			preStm.setInt(1, id);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				user.setUserId(rs.getInt("id_user"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getString("role") == "admin" ? Role.ADMIN : Role.USER);
			}
			
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null) 
					conn.close();
			}
		}
		
		return user;
	}

	@Override
	public synchronized Collection<UserBean> doRetrieveAll() throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		Collection<UserBean> users = new LinkedList<UserBean>();
		
		String selectSQL = "SELECT * FROM " + UserDao.TABLE_NAME;
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				UserBean user = new UserBean();
				
				user.setUserId(rs.getInt("id_user"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getString("role") == "admin" ? Role.ADMIN : Role.USER);
			
				users.add(user);
			}
			
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null) 
					conn.close();
			}
		}
		
		return users;
	}

	@Override
	public void doUpdate(UserBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String updateSQL = "UPDATE " + UserDao.TABLE_NAME 
				+ " email = ?, password = ?, username = ?, role = ?"
				+ " WHERE id_user = ?"; 		
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);
			
			preStm.setString(1, item.getEmail());
			preStm.setString(2, item.getPassword());
			preStm.setString(3, item.getUsername());
			preStm.setString(4, item.getRole().toString());
			preStm.setInt(5, item.getUserId());
			
			preStm.executeUpdate();
			conn.commit();
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}		
	}

}
