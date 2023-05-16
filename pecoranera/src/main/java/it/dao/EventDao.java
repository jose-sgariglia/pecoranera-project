package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.LinkedList;

import it.model.EventBean;

public class EventDao implements BeanDaoInterface<EventBean> {
	
	private static final String TABLE_NAME = "event";
	private DataSource ds = null;

	public EventDao(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public synchronized void doSave(EventBean event) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + EventDao.TABLE_NAME
				+ " (date, name, description, price, available_tickets, max_tickets) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, new Date(event.getDate().getTime()));
			preparedStatement.setString(2, event.getName());
			preparedStatement.setString(3, event.getDescription());
			preparedStatement.setDouble(4, event.getPrice());
			preparedStatement.setInt(5, event.getAvailable_tickets());
			preparedStatement.setInt(5, event.getMax_tickets());
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + EventDao.TABLE_NAME + " WHERE id_event = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	@Override
	public synchronized EventBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		EventBean bean = new EventBean();

		String selectSQL = "SELECT * FROM " + EventDao.TABLE_NAME + " WHERE id_event = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setDate(rs.getDate("date"));
				bean.setName(rs.getString("name"));
				bean.setName(rs.getString("description"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailable_tickets(rs.getInt("available_tickets"));
				bean.setMax_tickets(rs.getInt("max_tickets"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	@Override
	public synchronized Collection<EventBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<EventBean> events = new LinkedList<EventBean>();

		String selectSQL = "SELECT * FROM " + EventDao.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				EventBean bean = new EventBean();

				bean.setDate(rs.getDate("date"));
				bean.setName(rs.getString("name"));
				bean.setName(rs.getString("description"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailable_tickets(rs.getInt("available_tickets"));
				bean.setMax_tickets(rs.getInt("max_tickets"));
				events.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return events;
	}
}
