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
import it.model.TagBean;

public class EventDao extends BeanDaoAbstract<EventBean> {
	
	private static final String TABLE_RELATIONSHIP_TAG = "event_tag";

	public EventDao(DataSource ds) {
		super(ds, "event");
	}
	
	@Override
	public synchronized void doSave(EventBean event) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + this.TABLE_NAME
				+ " (date, name, description, price, available_tickets, max_tickets, cancellation) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, new Date(event.getDate().getTime()));
			preparedStatement.setString(2, event.getName());
			preparedStatement.setString(3, event.getDescription());
			preparedStatement.setDouble(4, event.getPrice());
			preparedStatement.setInt(5, event.getAvailable_tickets());
			preparedStatement.setInt(6, event.getMax_tickets());
			preparedStatement.setDate(7, new Date(event.getCancellation().getTime()));
			
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
	public synchronized EventBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		EventBean bean = new EventBean();

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME + " WHERE id_event = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEventId(rs.getInt("id_event"));
				bean.setDate(rs.getDate("date"));
				bean.setName(rs.getString("name"));
				bean.setName(rs.getString("description"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailable_tickets(rs.getInt("available_tickets"));
				bean.setMax_tickets(rs.getInt("max_tickets"));
				bean.setCancellation(rs.getDate("cancellation"));
				bean.setTags(this.getTags(bean));
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

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				EventBean bean = new EventBean();

				bean.setEventId(rs.getInt("id_event"));
				bean.setDate(rs.getDate("date"));
				bean.setName(rs.getString("name"));
				bean.setName(rs.getString("description"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailable_tickets(rs.getInt("available_tickets"));
				bean.setMax_tickets(rs.getInt("max_tickets"));
				bean.setCancellation(rs.getDate("cancellation"));
				bean.setTags(this.getTags(bean));
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

	@Override
	public synchronized void doUpdate(EventBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		String updateSQL = "UPDATE " + this.TABLE_NAME 
				+ "SET date = ?, name = ?, description = ?, price = ?, available_tickets = ?, max_tickets = ?"
				+ "WHERE id_event = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);
			
			preStm.setDate(1, new Date(item.getDate().getTime()));
			preStm.setString(2, item.getName());
			preStm.setString(3, item.getDescription());
			preStm.setDouble(4, item.getPrice());
			preStm.setInt(5, item.getAvailable_tickets());
			preStm.setInt(6,  item.getMax_tickets());
			preStm.setInt(7, item.getEventId());

			preStm.executeUpdate();
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
	
	public synchronized void insertTag(EventBean item, TagBean node) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		String insertSQL = "INSERT INTO " + EventDao.TABLE_RELATIONSHIP_TAG 
				+ "(id_event, id_tag) VALUES (? ?)";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(insertSQL);
			
			preStm.setInt(item.getEventId(), node.getTagId());

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
	
	public synchronized boolean deleteTag(EventBean item, TagBean node) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + EventDao.TABLE_RELATIONSHIP_TAG + " WHERE id_event = ? AND id_tag  = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setInt(1, item.getEventId());
			preparedStatement.setInt(2, node.getTagId());

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
	
	public synchronized Collection<TagBean> getTags(EventBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		Collection<TagBean> tags = new LinkedList<>();
		String selectSQL = "SELECT tag.* FROM " + EventDao.TABLE_RELATIONSHIP_TAG + " INNER JOIN tag ON tag.id_tag = " + EventDao.TABLE_RELATIONSHIP_TAG + ".id_tag "
				+ "WHERE " + EventDao.TABLE_RELATIONSHIP_TAG + ".id_event = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			preStm.setInt(1, item.getEventId());

			ResultSet rs = preStm.executeQuery();
			
			while (rs.next()) {
				TagBean tag = new TagBean();
				
				tag.setTagId(rs.getInt("id_tag"));
				tag.setName(rs.getString("name"));
				
				tags.add(tag);
			}
			
			preStm.executeUpdate();
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		
		return tags;
	}
}
