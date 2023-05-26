package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.model.ProductBean;

public interface ProductDaoInterface {
	
	/**
	 * Salva o aggiornalo status del prodotto
	 * @param ProductBean product
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(ProductBean product) throws SQLException;
	
	/**
	 * Cancella il prodotto
	 * @param ProductBean product
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(ProductBean product) throws SQLException;
	
	/**
	 * Restituisce il prodotto tramite l'id
	 * @param id
	 * @return ProductBean
	 * @return null
	 * @throws SQLException
	 */
	public ProductBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 * Restituisce tutti i prodotti
	 * @return Collection<ProductBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<ProductBean> doRetrieveAll() throws SQLException;
}
