package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.model.TagBean;

public class TagDao implements BeanDaoInterface<TagBean> {
	
	private static final String TABLE_NAME = "tag";
	
	private DataSource ds = null;
	
	
	public TagDao(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Tag Model creation....");
	}

	@Override
	public synchronized void doSave(TagBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		String insertSQL = "INSERT INTO " + TagDao.TABLE_NAME
				+ "(name) VALUES (?)";
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
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		int result = 0;
		String deleteSQL = "DELETE FROM " + TagDao.TABLE_NAME + " WHERE id_tag = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(deleteSQL);

			preStm.setInt(1, id);
			
			result = preStm.executeUpdate();
		} finally {
			try {
				if (preStm != null)
					preStm.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized TagBean doRetrieveByKey(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		TagBean tag = new TagBean();
		String selectSQL = "SELECT * FROM " + TagDao.TABLE_NAME + "WHERE id_tag = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
		
			preStm.setInt(1, id);
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				tag.setTagId(rs.getInt("id_tag"));
				tag.setName(rs.getString("name"));
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
				
		return tag;
	}

	@Override
	public synchronized Collection<TagBean> doRetrieveAll() throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;
		
		Collection<TagBean> tags = new LinkedList<TagBean>();
		String selectSQL = "SELECT * FROM " + TagDao.TABLE_NAME;
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(selectSQL);
		
			
			ResultSet rs = preStm.executeQuery();
			
			while(rs.next()) {
				TagBean tag = new TagBean();
				
				tag.setTagId(rs.getInt("id_tag"));
				tag.setName(rs.getString("name"));
				
				tags.add(tag);
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
				
		return tags;
	}

	@Override
	public void doUpdate(TagBean item) throws SQLException {
		Connection conn = null;
		PreparedStatement preStm = null;

		String updateSQL = "UPDATE " + TagDao.TABLE_NAME
				+ " name = ? WHERE id_tag = ?";
		
		try {
			conn = ds.getConnection();
			preStm = conn.prepareStatement(updateSQL);

			preStm.setString(1, item.getName());
			preStm.setInt(2, item.getTagId());
			
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
