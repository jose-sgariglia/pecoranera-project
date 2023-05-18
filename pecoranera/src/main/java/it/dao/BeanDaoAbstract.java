package it.dao;

import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

public abstract class BeanDaoAbstract<T> implements BeanDaoInterface<T>{
	protected DataSource ds = null;

	public BeanDaoAbstract(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public abstract void doSave(T item) throws SQLException;

	@Override
	public abstract void doUpdate(T item) throws SQLException, UnsupportedOperationException;

	@Override
	public abstract boolean doDelete(int id) throws SQLException;

	@Override
	public abstract T doRetrieveByKey(int id) throws SQLException;

	@Override
	public abstract Collection<T> doRetrieveAll() throws SQLException;
}
