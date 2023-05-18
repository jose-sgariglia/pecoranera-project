package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

public abstract class BeanDaoAbstract<T>{
	protected DataSource ds = null;
	protected String TABLE_NAME;

	public BeanDaoAbstract(DataSource ds) {
		this.ds = ds;
	}

	public abstract void doSave(T item) throws SQLException;

	public abstract void doUpdate(T item) throws SQLException, UnsupportedOperationException;

	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + this.TABLE_NAME + " WHERE id_" + this.TABLE_NAME + " = ?";

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

	public abstract T doRetrieveByKey(int id) throws SQLException;

	public abstract Collection<T> doRetrieveAll() throws SQLException;
}
