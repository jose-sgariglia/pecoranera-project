package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
import java.util.LinkedList;

import it.model.ArtistBean;

public class ArtistDao extends BeanDaoAbstract<ArtistBean> {

	public ArtistDao(DataSource ds) {
		super(ds, "artist");
	}
	
	@Override
	public synchronized void doSave(ArtistBean artist) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + this.TABLE_NAME
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
	public synchronized ArtistBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArtistBean bean = new ArtistBean();

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME + " WHERE id_artist = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setArtistId(rs.getInt("id_artist"));
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

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ArtistBean bean = new ArtistBean();

				bean.setArtistId(rs.getInt("id_artist"));
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

	@Override
	public void doUpdate(ArtistBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		String updateSQL = "UPDATE " + this.TABLE_NAME 
				+ "SET name = ?, description = ?"
				+ "WHERE id_artist = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);
			
			preStm.setString(1, item.getName());
			preStm.setString(2, item.getDescription());
			preStm.setInt(3, item.getArtistId());

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
}
