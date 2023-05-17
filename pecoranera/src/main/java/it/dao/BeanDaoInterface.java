package it.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface BeanDaoInterface<T> {
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(T item) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException;
	
	public T doRetrieveByKey(int id) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
}
