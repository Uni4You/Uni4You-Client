package de.uni_passau.facultyinfo.client.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import de.uni_passau.facultyinfo.client.R;

public class CafeteriaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cafeteria);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cafeteria, menu);
		return true;
	}

}