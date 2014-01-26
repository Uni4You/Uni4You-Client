package de.uni_passau.facultyinfo.client.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import de.uni_passau.facultyinfo.client.R;
import de.uni_passau.facultyinfo.client.activity.DisplayEventActivity;
import de.uni_passau.facultyinfo.client.activity.SearchEventsActivity;
import de.uni_passau.facultyinfo.client.model.access.AccessFacade;
import de.uni_passau.facultyinfo.client.model.dto.Event;
import de.uni_passau.facultyinfo.client.util.AsyncDataLoader;

public class EventFragment extends Fragment {
	private SearchView searchView;
	private View rootView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_event, container, false);

		setHasOptionsMenu(true);

		Activity activity = getActivity();
		ActionBar actionBar = activity.getActionBar();
		actionBar.setTitle(activity.getApplicationContext().getString(
				R.string.title_events));
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		(new EventLoader(rootView)).execute();

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.events, menu);
		searchView = (SearchView) menu.findItem(R.id.events_search)
				.getActionView();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.events_search:

			final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextSubmit(String query) {
					Intent intent = new Intent(rootView.getContext(),
							SearchEventsActivity.class);
					intent.putExtra("query", query);
					startActivity(intent);
					return false;
				}

				@Override
				public boolean onQueryTextChange(String newText) {
					return false;
				}
			};
			searchView.setOnQueryTextListener(queryTextListener);

			return true;
		default:
			return false;
		}
	}

	protected class EventLoader extends AsyncDataLoader<List<Event>> {

		public EventLoader(View rootView) {
			super(rootView);
		}

		@Override
		protected List<Event> doInBackground(Void... unused) {
			AccessFacade accessFacade = new AccessFacade();
			List<Event> events = null;

			events = accessFacade.getEventAccess().getEvents();

			if (events == null) {
				publishProgress(AsyncDataLoader.NO_CONNECTION_PROGRESS);
				events = accessFacade.getEventAccess().getEventsFromCache();
			}

			if (events == null) {
				events = new ArrayList<Event>();
			}

			return events;
		}

		@Override
		protected void onPostExecute(List<Event> events) {
			ListView listView = (ListView) rootView
					.findViewById(R.id.event_list);

			final ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();

			for (Event event : events) {
				HashMap<String, String> listEntry = new HashMap<String, String>();
				listEntry.put("title", event.getTitle());
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy",
						Locale.GERMAN);
				listEntry.put("date", sdf.format(event.getStartDate()));
				listEntry.put("eventId", event.getId());
				eventList.add(listEntry);
			}

			SimpleAdapter adapter = new SimpleAdapter(rootView.getContext(),
					eventList, R.layout.custom_row_view, new String[] { "date",
							"title" },
					new int[] { R.id.title, R.id.description });

			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					displayEvent(eventList.get(position).get("eventId"));
				}
			});

		}

		private void displayEvent(String id) {
			Intent intent = new Intent(rootView.getContext(),
					DisplayEventActivity.class);
			intent.putExtra("eventId", id);
			startActivity(intent);
		}
	}

}
