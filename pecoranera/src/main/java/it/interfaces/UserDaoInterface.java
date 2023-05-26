package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.model.UserBean;
import it.model.OrderBean;
import it.model.TagBean;

public interface UserDaoInterface {
	/**
	 * Crea o Salva lo status di un utente
	 * @param UserBean user
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(UserBean user) throws SQLException;
	
	/**
	 * Cancella un utente
	 * @param UserBean user
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(UserBean user) throws SQLException;
	
	/**
	 * Restituisce un utente tramite l'id
	 * @param id
	 * @return UserBean
	 * @return null
	 * @throws SQLException
	 */
	public UserBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 * Restituisce tutti gli utenti
	 * @return Collection<UserBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<UserBean> doRetrieveAll() throws SQLException;

	/**
	 * Assoccia tutti gli ordini ad un utente selezionato
	 * @param UserBean user
	 * @param orders
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean insertAllOrders(UserBean user, Collection<OrderBean> orders) throws SQLException;
	
	/**
	 * Restituisce tutti gli ordini di un utente
	 * @param UserBean user
	 * @return Collection<OrderBean> 
	 * @return null
	 * @throws SQLException
	 */
	public Collection<OrderBean> doRetrieveAllOrders(UserBean user) throws SQLException;
	
	/**
	 * Assoccia tutti i tag ad un utente selezionato
	 * @param UserBean user
	 * @param tags
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean insertAllTags(UserBean user, Collection<TagBean> tags) throws SQLException;
	
	/**
	 * Restituisce tutti i tag di un utente
	 * @param UserBean user
	 * @return Collection<TagBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<TagBean> doRetrieveAllTags(UserBean user) throws SQLException;
}
