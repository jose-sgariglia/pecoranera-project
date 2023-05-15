package it.dao;

import java.sql.*;
import java.util.*;
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
	public synchronized OrderBean doRetrieveByKey(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		OrderBean order = null;
		String selectSQL = "SELECT * FROM " + OrderDao.TABLE_NAME + "WHERE id_order = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			preStm.setInt(1, id);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				order.setId_order(rs.getInt("id_order"));
				order.setTickets(rs.getInt("tickets"));
				order.setPrice(rs.getDouble("price"));
				order.setDate(new java.util.Date(rs.getDate("date").getTime()));
				order.setUser(new UserDao().doRetrieveByKey(rs.getInt("id_user")));
				order.setEvent(new EventDao().doRetrieveByKey(rs.getInt("id_event")));
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
		
		return order;
	}

	@Override
	public Collection<OrderBean> doRetrieveAll() throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		
		String selectSQL = "SELECT * FROM " + OrderDao.TABLE_NAME;
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				OrderBean order = new OrderBean();
				
				order.setId_order(rs.getInt("id_order"));
				order.setTickets(rs.getInt("tickets"));
				order.setPrice(rs.getDouble("price"));
				order.setDate(new java.util.Date(rs.getDate("date").getTime()));
				order.setUser(new UserDao().doRetrieveByKey(rs.getInt("id_user")));
				order.setEvent(new EventDao().doRetrieveByKey(rs.getInt("id_event")));
			
				orders.add(order);
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
		
		return orders;
	}

}
