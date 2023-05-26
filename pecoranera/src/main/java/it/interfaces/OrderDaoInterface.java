package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.model.OrderBean;

public interface OrderDaoInterface {
	
	/**
	 * Salva o aggiorna lo status dell' ordine
	 * @param OrderBean order
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(OrderBean order) throws SQLException;
	
	/**
	 * Cancella l'ordine selezionato
	 * @param OrderBean order
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(OrderBean order) throws SQLException;
	
	/**
	 * Restiuisce l'ordine tramite l'id
	 * @param Interger id
	 * @return OrderBean
	 * @return null
	 * @throws SQLException
	 */
	public OrderBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 * Restituisce tutti gli ordini
	 * @return Collection<OrderBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<OrderBean> doRetrieveAll() throws SQLException;
}
