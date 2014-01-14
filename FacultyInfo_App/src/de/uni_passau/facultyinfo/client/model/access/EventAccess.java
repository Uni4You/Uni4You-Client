package de.uni_passau.facultyinfo.client.model.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_passau.facultyinfo.client.model.connection.RestConnection;
import de.uni_passau.facultyinfo.client.model.dto.ContactGroup;
import de.uni_passau.facultyinfo.client.model.dto.ContactPerson;
import de.uni_passau.facultyinfo.client.model.dto.Event;
import de.uni_passau.facultyinfo.client.model.dto.News;

/**
 * @author Timo Staudinger
 * 
 */
public class EventAccess {
	private static final String RESSOURCE = "/event";

	private RestConnection<Event> restConnection = null;

	protected EventAccess() {
		restConnection = new RestConnection<Event>(Event.class);
	}

	/**
	 * Gives a list of available News.
	 * 
	 * @return List<Event>
	 */
	public List<Event> getEvents() {
		List<Event> event = null;

		// news = createNewsSampleData();
		event = restConnection.getRessourceAsList(RESSOURCE);

		// TODO: Database operations

		if (event == null) {
			return null;
		}

		return Collections.unmodifiableList(event);
	}

	/**
	 * Gives a list of available News that are cached locally.
	 * 
	 * @return List<Event>
	 */
	public List<Event> getEventsFromCache() {
		// TODO: Load cached data
		return Collections.unmodifiableList(new ArrayList<Event>());
	}

	/**
	 * Gives detailed information about a specific News.
	 * 
	 * @param eventId
	 * @return Event
	 */
	public Event getEvent(String eventId) {
		Event event = null;

		event = restConnection.getRessource(RESSOURCE + '/' + eventId);

		// TODO: Database operations

		return event;
	}

	/**
	 * Gives detailed information about a specific News that is cached locally.
	 * 
	 * @param eventId
	 * @return Event
	 */
	public Event getEventFromCache(String eventId) {
		// TODO: load cached data
		return null;
	}

	/**
	 * Gives a list of Events that contain the search string in one or more of
	 * the following attributes:
	 * <ul>
	 * <li>Title</li>
	 * <li>Subtitle</li>
	 * <li>Description</li>
	 * <li>Host</li>
	 * <li>Location</li>
	 * </ul>
	 * 
	 * The search is case insensitive.
	 * 
	 * @param input
	 *            The search parameter
	 */
	public List<Event> find(String input) {
		if (input != null && !input.isEmpty()) {
			List<Event> events = null;

			events = restConnection.getRessourceAsList(RESSOURCE + "/find/"
					+ input);
			return events != null ? Collections.unmodifiableList(events) : null;
		}
		return Collections.unmodifiableList(new ArrayList<Event>());
	}

}
