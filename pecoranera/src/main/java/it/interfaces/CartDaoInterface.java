package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import it.model.CartBean;
import it.model.EventBean;

public interface CartDaoInterface {
	/**
	 * Metodo che o crea l'oggetto o, se esiste, lo aggiorna
	 * @param CartBean item
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(CartBean item) throws SQLException;
	
	/**
	 *  Metodo che cancella l'oggetto
	 * @param CartBean item
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(CartBean item) throws SQLException; 
	
	/**
	 * Metodo che trova l'oggetto tramite l'id
	 * @param Integer id
	 * @return CartBean 
	 * @return null
	 * @throws SQLException
	 */
	public CartBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 *  Metodo restituisce tutti gli oggetti
	 * @return Collection<CartBean> 
	 * @return null
	 * @throws SQLException
	 */
	public Collection<CartBean> doRetrieveAll() throws SQLException;
	
	/**
	 * Metodo che assoccia tutti gli oggetti al carrello selezionato
	 * @param CartBean item
	 * @param Map<EventBean, Integer> events
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean insertAllEvents(CartBean item, Map<EventBean, Integer> events) throws SQLException;
	
	/**
	 * Metodo che restituisce tutti gli oggetti assocciati al oggetto passatto nel argomento
	 * @param CartBean item
	 * @return Map<EventBean, Integer>
	 * @return null
	 * @throws SQLException
	 */
	public Map<EventBean, Integer> doRetrieveAllEvents(CartBean item) throws SQLException;
}
