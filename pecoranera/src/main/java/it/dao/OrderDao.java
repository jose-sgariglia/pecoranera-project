package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.model.OrderBean;

public class OrderDao extends BeanDaoAbstract<OrderBean> {
	
	public OrderDao(DataSource ds) {
		super(ds, "order");
	}
	
	@Override
	public synchronized void doSave(OrderBean item) throws SQLException 
	{
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String insertSQL = "INSERT INTO " + this.TABLE_NAME 
				+ " (tickets, price, date, user, id_event) VALUES (?, ?, ?, ?, ?)";;
	
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(insertSQL);
			
			preStm.setInt(1, item.getTickets());
			preStm.setDouble(2, item.getPrice());
			preStm.setDate(3, new java.sql.Date(item.getDate().getTime()));
			preStm.setInt(4, item.getUser().getUserId());
			preStm.setInt(5, item.getEvent().getEventId());
			
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
	public synchronized OrderBean doRetrieveByKey(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		OrderBean order = new OrderBean();
		String selectSQL = "SELECT * FROM " + this.TABLE_NAME + "WHERE id_order = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			preStm.setInt(1, id);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				order.setOrderId(rs.getInt("id_order"));
				order.setTickets(rs.getInt("tickets"));
				order.setPrice(rs.getDouble("price"));
				order.setDate(new java.util.Date(rs.getDate("date").getTime()));
				order.setUser(new UserDao(ds).doRetrieveByKey(rs.getInt("id_user")));
				order.setEvent(new EventDao(ds).doRetrieveByKey(rs.getInt("id_event")));
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
	public synchronized Collection<OrderBean> doRetrieveAll() throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		
		String selectSQL = "SELECT * FROM " + this.TABLE_NAME;
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				OrderBean order = new OrderBean();
				
				order.setOrderId(rs.getInt("id_order"));
				order.setTickets(rs.getInt("tickets"));
				order.setPrice(rs.getDouble("price"));
				order.setDate(new java.util.Date(rs.getDate("date").getTime()));
				order.setUser(new UserDao(ds).doRetrieveByKey(rs.getInt("id_user")));
				order.setEvent(new EventDao(ds).doRetrieveByKey(rs.getInt("id_event")));
			
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

	@Override
	public void doUpdate(OrderBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String updateSQL = "UPDATE " + this.TABLE_NAME
				+ "SET tickets = ?, price = ?, date = ?, user = ?, id_event = ? "
				+ "WHERE id_order = ?";
	
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);
			
			preStm.setInt(1, item.getTickets());
			preStm.setDouble(2, item.getPrice());
			preStm.setDate(3, new java.sql.Date(item.getDate().getTime()));
			preStm.setInt(4, item.getUser().getUserId());
			preStm.setInt(5, item.getEvent().getEventId());
			preStm.setInt(6, item.getOrderId());
			
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
