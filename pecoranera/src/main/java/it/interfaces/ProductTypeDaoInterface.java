package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.model.ProductBean;
import it.model.ProductTypeBean;

public interface ProductTypeDaoInterface {
	
	/**
	 * Crea o salva lo status della categoria di prodotto
	 * @param ProductTypeBean product_type
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(ProductTypeBean product_type) throws SQLException;
	
	/**
	 * Cancella la categoria di prodotto
	 * @param ProductTypeBean product_type
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(ProductTypeBean product_type) throws SQLException;
	
	/**
	 * Restituisce una categoria di prodotto tramite l'id
	 * @param id
	 * @return ProductTypeBean
	 * @return null
	 * @throws SQLException
	 */
	public ProductTypeBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 * Restituisce tutte le categorie di prodotto
	 * @return Collection<ProductTypeBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<ProductTypeBean> doRetrieveAll() throws SQLException;
	
	/**
	 * Assoccia tutti i prodotti alla categoria di prodotto selezionata
	 * @param ProductTypeBean product_type
	 * @param product
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean insertAllProducts(ProductTypeBean product_type, Collection<ProductBean> product) throws SQLException;
	
	/**
	 * Restituisce i prodotti assocciati alla categoria di prodotto
	 * @param ProductTypeBean product_type
	 * @return Collection<ProductBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<ProductBean> doRetrieveAllProducts(ProductTypeBean product_type) throws SQLException;
}
