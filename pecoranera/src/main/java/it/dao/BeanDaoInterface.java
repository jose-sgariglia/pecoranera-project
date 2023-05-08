package it.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface BeanDaoInterface<T> {
	
	public void doSave(T item) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException;
	
	public T doRetriveByKey(int id) throws SQLException;
	
	public Collection<T> doRetriveAll() throws SQLException;
}
