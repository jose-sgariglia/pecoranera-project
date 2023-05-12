package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import javax.naming.*;
import javax.sql.*;

import it.model.OrderBean;

public class OrderDao implements BeanDaoInterface<OrderBean> {

	private static final String TABLE_NAME = "order";
	
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
		
			ds = (DataSource) envCtx.lookup("jdbc/pecoranera_db");
			
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	@Override
	public synchronized void doSave(OrderBean item) throws SQLException 
	{
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String insertSQL = "INSERT INTO " + OrderDao.TABLE_NAME 
				+ " (id_order, tickets, price, date, user, event) VALUES (?, ?, ?, ?, ?)";;
	
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(insertSQL);
			preStm.setInt(1, item.getId_order());
			preStm.setInt(2, item.getTickets());
			preStm.setDouble(3, item.getPrice());
			preStm.setDate(4, new java.sql.Date(item.getDate().getTime()));
			preStm.setInt(5, item.getUser().getUser_id());
			preStm.setInt(6, item.getEvent().getId_event());
			
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
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " + OrderDao.TABLE_NAME + " WHERE id_order = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(deleteSQL);
			preStm.setInt(1, id);
			
			result = preStm.executeUpdate();
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		
		return (result != 0);
	}

	@Override
	public OrderBean doRetrieveByKey(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OrderBean> doRetrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
