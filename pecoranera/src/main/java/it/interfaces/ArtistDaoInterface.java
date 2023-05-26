package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.model.ArtistBean;

public interface ArtistDaoInterface {
	
	/**
	 * Metodo che o crea l'oggetto o, se esiste, lo aggiorna
	 * @param ArtistBean item
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(ArtistBean item) throws SQLException;
	
	/**
	 * Metodo che cancella l'oggetto
	 * @param ArtistBean item
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(ArtistBean item) throws SQLException; 
	
	/**
	 * Metodo che trova l'oggetto tramite l'id
	 * @param Integer id
	 * @return ArtistBean
	 * @return null
	 * @throws SQLException
	 */
	public ArtistBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 *  Metodo restituisce tutti gli oggetti
	 * @return Collection<ArtistBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<ArtistBean> doRetrieveAll() throws SQLException;
}
