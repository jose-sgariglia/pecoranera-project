package it.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import it.model.ArtistBean;
import it.model.EventBean;
import it.model.TagBean;

public interface EventDaoInterface {
	
	/**
	 * Crea o salva lo status di un evento
	 * @param EventBean event
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doSave(EventBean event) throws SQLException;
	
	/**
	 * Cancella un evento dal db con tutti gli artisti e i tag assocciati
	 * @param EventBean event
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean doDelete(EventBean event) throws SQLException;
	
	/**
	 * Restituisce un evento tramite l'id
	 * @param Integer id
	 * @return EventBean
	 * @return null
	 * @throws SQLException
	 */
	public EventBean doRetrieveByKey(int id) throws SQLException;
	
	/**
	 * Restituisce tutti gli eventi
	 * @return Collection<EventBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<EventBean> doRetrieveAll() throws SQLException;
	
	/**
	 * Assoccia i tag al evento scelto
	 * @param EventBean event
	 * @param Collection<TagBean> tags
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean insertAllTags(EventBean event, Collection<TagBean> tags) throws SQLException;
	
	/**
	 * Restituisce tutti i tag assocciati ad un evento
	 * @param EventBean event
	 * @return Collection<TagBean>
	 * @return null
	 * @throws SQLException
	 */
	public Collection<TagBean> doRetrieveAllTags(EventBean event) throws SQLException;
	
	/**
	 * Assoccia tutti gli artisti e il propri ruolo ad un evento scelto
	 * @param EventBean event
	 * @param Map<ArtistBean, String> artists
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean insertAllArtists(EventBean event, Map<ArtistBean, String> artists) throws SQLException;
	
	/**
	 * Restituisce tutti gli artisti assocciati ad un evento
	 * @param EventBean event
	 * @return Map<ArtistBean, String>
	 * @return null
	 * @throws SQLException
	 */
	public Map<ArtistBean, String> doRetrieveAllArtists(EventBean event) throws SQLException;
}
