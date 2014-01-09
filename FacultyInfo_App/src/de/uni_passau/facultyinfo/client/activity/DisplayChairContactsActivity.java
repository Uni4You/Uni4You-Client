package de.uni_passau.facultyinfo.client.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.uni_passau.facultyinfo.client.R;
import de.uni_passau.facultyinfo.client.R.id;
import de.uni_passau.facultyinfo.client.R.layout;
import de.uni_passau.facultyinfo.client.R.menu;
import de.uni_passau.facultyinfo.client.model.access.AccessFacade;
import de.uni_passau.facultyinfo.client.model.dto.ContactGroup;
import de.uni_passau.facultyinfo.client.model.dto.ContactPerson;
import de.uni_passau.facultyinfo.client.util.AsyncDataLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayChairContactsActivity extends Activity {
	private String chairId; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_chair_contacts);
		
		Intent intent = getIntent(); 
		chairId = intent.getStringExtra("chairId"); 
		
//		ListView listView = (ListView)findViewById(R.id.chairContacts);
//		
//		List valueList = new ArrayList<String>();
//
//		for (int i = 0; i < 5; i++) {
//			valueList.add("Lehrstuhl: " + chairId + " Kontakt " + i); 
//		}
//		
//		ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
//		
//		listView.setAdapter(adapter); 
		
		ContactLoader contactLoader = new ContactLoader(getCurrentFocus()); 
		
		setTitle(contactLoader.getGroupTitle()); 
		
		contactLoader.execute(); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_chair_contacts, menu);
		return true;
	}
	
	protected class ContactLoader extends AsyncDataLoader<ContactGroup> {
		private AccessFacade accessFacade; 
		private ContactLoader(View rootView) {
			super(rootView);
		}

		@Override
		protected ContactGroup doInBackground(Void... unused) {
			accessFacade = new AccessFacade();

			ContactGroup group = accessFacade.getContactPersonAccess()
					.getContactGroup(chairId);

//			if (group == null) {
//				publishProgress(NewsLoader.NO_CONNECTION_PROGRESS);
//				group = accessFacade.getContactPersonAccess()
//						.getContactGroupsFromCache();
//			}

//			if (group == null) {
//				group = Collections
//						.unmodifiableList(new ArrayList<ContactGroup>());
//			}

			return group;
		}

		@Override
		protected void onPostExecute(ContactGroup group) {
			ListView listView = (ListView) rootView.findViewById(R.id.chairContacts);

			
			
			final ArrayList<HashMap<String, String>> personList = new ArrayList<HashMap<String, String>>();

			for (ContactPerson person : group.getContactPersons()) {
				HashMap<String, String> temp1 = new HashMap<String, String>();
				temp1.put("name", person.getName());
				temp1.put("office", person.getOffice());
				temp1.put("telefon", person.getPhone()); 
				temp1.put("email", person.getEmail()); 
				temp1.put("description", person.getDescription()); 
				personList.add(temp1);
			}

			SimpleAdapter adapter = new SimpleAdapter(rootView.getContext(),
					personList, R.layout.contact_view,
					new String[] { "title", "office", "telefon", "email", "description"}, new int[] { R.id.title, R.id.description_contact, R.id.description_contact, R.id.description_contact, R.id.description_contact }

			);

			listView.setAdapter(adapter); 
		}
		
		public String getGroupTitle(){
			return accessFacade.getContactPersonAccess().getContactGroup(chairId).getTitle(); 
					
		}
	}

}
