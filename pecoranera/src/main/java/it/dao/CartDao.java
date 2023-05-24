package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
import java.util.LinkedList;

import it.model.CartBean;

public class CartDao extends BeanDaoAbstract<CartBean> {

	public CartDao(DataSource ds) {
		super(ds, "cart");
	}
	
	@Override
	public synchronized void doSave(CartBean cart) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + this.TABLE_NAME
				+ " (id_user) VALUES (?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, cart.getUser().getUserId());

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
	public synchronized CartBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CartBean bean = new CartBean();

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME + " WHERE id_cart = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_cart(rs.getInt("id_cart"));
				bean.setUser(new UserDao(ds).doRetrieveByKey(rs.getInt("id_user")));
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
	public synchronized Collection<CartBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<CartBean> carts = new LinkedList<CartBean>();

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CartBean bean = new CartBean();

				bean.setId_cart(rs.getInt("id_cart"));
				bean.setUser(new UserDao(ds).doRetrieveByKey(rs.getInt("id_user")));
				carts.add(bean);
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
		return carts;
	}

	@Override
	public void doUpdate(CartBean item) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Method not supported for this model");		
	}
}
