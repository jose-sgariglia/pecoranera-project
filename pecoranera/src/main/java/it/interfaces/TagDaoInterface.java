package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.model.TagBean;

public interface TagDaoInterface {
	
	/**
	 * Crea o Salva lo status di un tag
	 * @param TagBean tag
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(TagBean tag) throws SQLException;
	
	/**
	 * Cancella un tag
	 * @param TagBean tag
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(TagBean tag) throws SQLException;
	
	/**
	 * Restituisce un tag trami l'id
	 * @param id
	 * @return TagBean
	 * @return null
	 * @throws SQLException
	 */
	public TagBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 * Restituisce tutti i tag
	 * @return Collection<TagBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<TagBean> doRetrieveAll() throws SQLException;
}
