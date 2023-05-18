package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.model.ProductBean;

public class ProductDao extends BeanDaoAbstract<ProductBean> {
	
	private static final String TABLE_NAME = "product";
		
	public ProductDao(DataSource ds) {
		super(ds);
	}

	@Override
	public synchronized void doSave(ProductBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String insertSQL = "INSERT INTO " + ProductDao.TABLE_NAME
				+ "(name, description, price, id_type)"
				+ "VALUES ? ? ? ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(insertSQL);

			preStm.setString(1, item.getName());
			preStm.setString(2, item.getDescription());
			preStm.setDouble(3, item.getPrice());
			preStm.setInt(4, item.getType().getTypeId());
			
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
	public synchronized ProductBean doRetrieveByKey(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		ProductBean product = new ProductBean();
		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + "WHERE id_product = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			preStm.setInt(1, id);
			
			ResultSet rs = preStm.executeQuery();
			
			while (rs.next()) {
				product.setProductId(rs.getInt("id_product"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setType(new ProductTypeDao(ds).doRetrieveByKey(rs.getInt("id_type")));
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
		
		return product;
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll() throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		Collection<ProductBean> products = new LinkedList<ProductBean>();
		
		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME;
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			ResultSet rs = preStm.executeQuery();
			
			while (rs.next()) {
				ProductBean product = new ProductBean();
				
				product.setProductId(rs.getInt("id_product"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setType(new ProductTypeDao(ds).doRetrieveByKey(rs.getInt("id_type")));
			
				products.add(product);
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
		
		return products;
	}

	@Override
	public void doUpdate(ProductBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String updateSQL = "UPDATE " + ProductDao.TABLE_NAME 
				+ "name = ?, description = ?, price = ?, id_type = ?"
				+ " WHERE id_type = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);

			preStm.setString(1, item.getName());
			preStm.setString(2, item.getDescription());
			preStm.setDouble(3, item.getPrice());
			preStm.setInt(4, item.getType().getTypeId());
			preStm.setInt(5, item.getProductId());
			
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
