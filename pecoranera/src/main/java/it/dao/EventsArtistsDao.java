package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.LinkedList;

import it.model.EventsArtistsBean;

public class EventsArtistsDao implements BeanDaoInterface<EventsArtistsBean> {
	
	private static final String TABLE_NAME = "events_artists";
	private DataSource ds = null;

	public EventsArtistsDao(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public synchronized void doSave(EventsArtistsBean events_artists) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + EventsArtistsDao.TABLE_NAME
				+ " (id_event, id_artist, role) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, events_artists.getEvent().getId_event());
			preparedStatement.setInt(2, events_artists.getArtist().getId_artist());
			preparedStatement.setString(3, events_artists.getRole());
			
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

		String deleteSQL = "DELETE FROM " + EventsArtistsDao.TABLE_NAME + " WHERE id_artist = ?";

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
	public synchronized EventsArtistsBean doRetrieveByKey(int id, int id2) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		EventsArtistsBean bean = new EventsArtistsBean();

		String selectSQL = "SELECT * FROM " + EventsArtistsDao.TABLE_NAME + " WHERE id_event = ?";

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
