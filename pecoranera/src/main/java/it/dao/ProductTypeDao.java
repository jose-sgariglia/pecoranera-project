package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.model.OrderBean;
import it.model.ProductTypeBean;

public class ProductTypeDao extends BeanDaoAbstract<ProductTypeBean> {

	private static final String TABLE_NAME = "product_type";
	
	public ProductTypeDao(DataSource ds) {
		super(ds);
	}
	
	@Override
	public synchronized void doSave(ProductTypeBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String insertSQL = "INSERT INTO " + ProductTypeDao.TABLE_NAME
				+ "(name) VALUES ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(insertSQL);
			
			preStm.setString(1, item.getName());
			
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
	public synchronized ProductTypeBean doRetrieveByKey(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		ProductTypeBean productType = new ProductTypeBean();
		String selectSQL = "SELECT * FROM " + ProductTypeDao.TABLE_NAME + " WHERE id_type = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			preStm.setInt(1, id);
			
			ResultSet rs = preStm.executeQuery();
			
			while (rs.next()) {
				productType.setTypeId(rs.getInt("id_type"));
				productType.setName(rs.getString("name"));
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
		return productType;
	}

	@Override
	public synchronized Collection<ProductTypeBean> doRetrieveAll() throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		Collection<ProductTypeBean> productTypes = new LinkedList<ProductTypeBean>();
		String selectSQL = "SELECT * FROM " + ProductTypeDao.TABLE_NAME;
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
			
			ResultSet rs = preStm.executeQuery();
			
			while (rs.next()) {
				ProductTypeBean productType = new ProductTypeBean();
				
				productType.setTypeId(rs.getInt("id_type"));
				productType.setName(rs.getString("name"));
			
				productTypes.add(productType);
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
		return productTypes;
	}

	@Override
	public void doUpdate(ProductTypeBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		String updateSQL = "UPDATE " + ProductTypeDao.TABLE_NAME 
				+ "name = ? WHERE id_type = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);
			
			preStm.setString(1, item.getName());
			preStm.setInt(2, item.getTypeId());
			
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
