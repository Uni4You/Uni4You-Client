package de.uni_passau.facultyinfo.client.model.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_passau.facultyinfo.client.model.connection.RestConnection;
import de.uni_passau.facultyinfo.client.model.dto.SportsCourse;
import de.uni_passau.facultyinfo.client.model.dto.SportsCourseCategory;

/**
 * Enables access to remote Sports course ressources.
 * 
 * @author Timo Staudinger
 */
public class SportsCourseAccess {
	private static final String RESSOURCE = "/sportscourse";

	private RestConnection<SportsCourse> restConnectionSportsCourse = null;
	private RestConnection<SportsCourseCategory> restConnectionSportsCourseCategory = null;

	private RestConnection<SportsCourse> getRestConnectionSportsCourse() {
		if (restConnectionSportsCourse == null) {
			restConnectionSportsCourse = new RestConnection<SportsCourse>(
					SportsCourse.class);
		}
		return restConnectionSportsCourse;
	}

	private RestConnection<SportsCourseCategory> getRestConnectionSportsCourseCategory() {
		if (restConnectionSportsCourseCategory == null) {
			restConnectionSportsCourseCategory = new RestConnection<SportsCourseCategory>(
					SportsCourseCategory.class);
		}
		return restConnectionSportsCourseCategory;
	}

	protected SportsCourseAccess() {
		super();
	}

	/**
	 * Gives a list of available Sports Courses in alphabetical order.
	 * 
	 * @return List<SportsCourseCategory>
	 */
	public List<SportsCourseCategory> getSportsCourses() {
		List<SportsCourseCategory> sportsCourseCategories = null;

		sportsCourseCategories = getRestConnectionSportsCourseCategory()
				.getRessourceAsList(RESSOURCE);

		if (sportsCourseCategories == null) {
			return null;
		}

		for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
			for (SportsCourse sportsCourse : sportsCourseCategory
					.getSportsCourses()) {
				sportsCourse.setCategory(sportsCourseCategory);
			}
		}

		// TODO: Database operations

		return Collections.unmodifiableList(sportsCourseCategories);
	}

	/**
	 * Gives a list of Sports Courses that are currently cached locally in
	 * alphabetical order.
	 * 
	 * @return List<SportsCourseCategory>
	 */
	public List<SportsCourseCategory> getSportsCoursesFromCache() {
		return Collections
				.unmodifiableList(new ArrayList<SportsCourseCategory>());
	}

	/**
	 * Gives detailed information about a single Sports Course.
	 * 
	 * @param id
	 * @return The sports course matching the id.
	 */
	public SportsCourseCategory getSportsCourseCategory(String id) {
		SportsCourseCategory sportsCourseCategory = null;

		sportsCourseCategory = getRestConnectionSportsCourseCategory()
				.getRessource(RESSOURCE + "/category/" + id);

		if (sportsCourseCategory == null) {
			return null;
		}

		// TODO: Database operations

		return sportsCourseCategory;
	}

	/**
	 * Gives detailed information about a single Sports Course that is cached
	 * locally.
	 * 
	 * @param id
	 * @return The sports course matching the id.
	 */
	public SportsCourseCategory getSportsCourseCategoryFromCache(String id) {
		return null;
	}

	/**
	 * Gives detailed information about a single Sports Course.
	 * 
	 * @param id
	 * @return The sports course matching the id.
	 */
	public SportsCourse getSportsCourse(String id) {
		SportsCourse sportsCourse = null;

		sportsCourse = getRestConnectionSportsCourse().getRessource(
				RESSOURCE + "/course/" + id);

		if (sportsCourse == null) {
			return null;
		}

		// TODO: Database operations

		return sportsCourse;
	}

	/**
	 * Gives detailed information about a single Sports Course that is cached
	 * locally.
	 * 
	 * @param id
	 * @return The sports course matching the id.
	 */
	public SportsCourse getSportsCourseFromCache(String id) {
		return null;
	}

	/**
	 * Gives a list of today's Sports Courses in alphabetical order.
	 * 
	 * @return List<SportsCourseCategory>
	 */
	public List<SportsCourseCategory> getTodaysSportsCourses() {
		List<SportsCourseCategory> sportsCourseCategories = null;

		sportsCourseCategories = getRestConnectionSportsCourseCategory()
				.getRessourceAsList(RESSOURCE + "/today");

		if (sportsCourseCategories == null) {
			return null;
		}

		for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
			for (SportsCourse sportsCourse : sportsCourseCategory
					.getSportsCourses()) {
				sportsCourse.setCategory(sportsCourseCategory);
			}
		}

		return Collections.unmodifiableList(sportsCourseCategories);
	}

	/**
	 * Gives a list of Sports Courses that meet one or more of the following
	 * criteria:
	 * <ul>
	 * <li>The title of the course's category contains the search string.</li>
	 * <li>The description of the course contains the search string.</li>
	 * <li>The host of the course contains the search string.</li>
	 * </ul>
	 * 
	 * The search is case insensitive.
	 * 
	 * @param input
	 *            The search parameter
	 * @return List of matching Sports Course Categories that contain a list of
	 *         matching Sports Courses each.
	 */
	public List<SportsCourseCategory> findSportsCourses(String input) {
		if (input != null && !input.isEmpty()) {
			List<SportsCourseCategory> sportsCourseCategories = null;

			sportsCourseCategories = getRestConnectionSportsCourseCategory()
					.getRessourceAsList(RESSOURCE + "/find/" + input);

			if (sportsCourseCategories == null) {
				return null;
			}

			for (SportsCourseCategory sportsCourseCategory : sportsCourseCategories) {
				for (SportsCourse sportsCourse : sportsCourseCategory
						.getSportsCourses()) {
					sportsCourse.setCategory(sportsCourseCategory);
				}
			}

			return sportsCourseCategories;
		}
		return Collections
				.unmodifiableList(new ArrayList<SportsCourseCategory>());
	}
}
