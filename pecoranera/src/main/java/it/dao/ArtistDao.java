package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
import java.util.LinkedList;

import it.model.ArtistBean;

public class ArtistDao implements BeanDaoInterface<ArtistBean> {
	
	private static final String TABLE_NAME = "artist";
	private DataSource ds = null;

	public ArtistDao(DataSource ds) {
		this.ds = ds;
		System.out.println("DataSource Artist model creation...");
	}
	
	@Override
	public synchronized void doSave(ArtistBean artist) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ArtistDao.TABLE_NAME
				+ " (name, description) VALUES (?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, artist.getName());
			preparedStatement.setString(2, artist.getDescription());

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

		String deleteSQL = "DELETE FROM " + ArtistDao.TABLE_NAME + " WHERE id_artist = ?";

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
	public synchronized ArtistBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArtistBean bean = new ArtistBean();

		String selectSQL = "SELECT * FROM " + ArtistDao.TABLE_NAME + " WHERE id_artist = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_artist(rs.getInt("id_artist"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
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
	public synchronized Collection<ArtistBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ArtistBean> artists = new LinkedList<ArtistBean>();

		String selectSQL = "SELECT * FROM " + ArtistDao.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ArtistBean bean = new ArtistBean();

				bean.setId_artist(rs.getInt("id_artist"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				artists.add(bean);
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
		return artists;
	}
}
