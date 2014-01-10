package de.uni_passau.facultyinfo.client.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.uni_passau.facultyinfo.client.R;
import de.uni_passau.facultyinfo.client.fragment.BuslinesFragment;
import de.uni_passau.facultyinfo.client.fragment.CafeteriaFragment;
import de.uni_passau.facultyinfo.client.fragment.ContactFragment;
import de.uni_passau.facultyinfo.client.fragment.FaqsFragment;
import de.uni_passau.facultyinfo.client.fragment.HomeFragment;
import de.uni_passau.facultyinfo.client.fragment.MapFragment;
import de.uni_passau.facultyinfo.client.fragment.NewsFragment;
import de.uni_passau.facultyinfo.client.fragment.SportsFragment;
import de.uni_passau.facultyinfo.client.fragment.TimetableFragment;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] drawerValues;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		drawerValues = getResources().getStringArray(R.array.drawer_values);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, drawerValues));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// if (savedInstanceState == null) {
		// selectItem(0);
		// }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	// getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	// getActionBar().setDisplayHomeAsUpEnabled(false);
	// getActionBar().setDisplayShowHomeEnabled(true);
	// getActionBar().setDisplayShowTitleEnabled(false);
	//
	// ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	// this, R.array.spinner_menu,
	// android.R.layout.simple_spinner_item);
	// getActionBar().setListNavigationCallbacks(adapter, this);
	// }

	@Override
	protected void onResume() {

		isGooglePlayServicesAvailable();
		super.onResume();
	}

	private void isGooglePlayServicesAvailable() {

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		Fragment fragment = null;

		// update the main content by replacing fragments
		switch (position) {
		case 0:
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new HomeFragment();
			System.out.println("home");
			break;
		case 1:
			System.out.println("news");
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new NewsFragment();
			break;
		case 2:
			System.out.println("timetable");
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new TimetableFragment();
			break;
		case 3:
			System.out.println("busfahrplan");
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new BuslinesFragment();
			break;
		case 4:
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			// getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			// ViewPager mViewPager = new ViewPager(getApplicationContext());
			// ((FrameLayout)findViewById(R.id.content_frame)).addView(mViewPager);
			// mViewPager.setAdapter(new AppSectionsPagerAdapter(
			// getSupportFragmentManager()));
			// mViewPager
			// .setOnPageChangeListener(new
			// ViewPager.SimpleOnPageChangeListener() {
			// @Override
			// public void onPageSelected(int position) {
			// System.out.println("position: " + position);
			// getActionBar().setSelectedNavigationItem(position);
			// }
			// });
			fragment = new SportsFragment();
//			fragment.setHasOptionsMenu(true); 
			break;
		case 5:
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new CafeteriaFragment();
			break;
		case 6:
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new ContactFragment();
			break;
		case 7:
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new FaqsFragment();
			break;
		case 8:
			getActionBar()
					.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			fragment = new MapFragment();
			break;
		}

		android.app.FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(drawerValues[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	public void showDay(View view) {
		System.out.println(view.getId());
		int viewId = view.getId();
		int dayId = 0;

		if (viewId == (findViewById(R.id.mo).getId())
				|| viewId == (findViewById(R.id.mo810).getId())
				|| viewId == (findViewById(R.id.mo1012).getId())
				|| viewId == (findViewById(R.id.mo1214).getId())
				|| viewId == (findViewById(R.id.mo1416).getId())
				|| viewId == (findViewById(R.id.mo1618).getId())
				|| viewId == (findViewById(R.id.mo1820).getId())) {
			dayId = 0;
		} else if (viewId == (findViewById(R.id.di).getId())
				|| viewId == (findViewById(R.id.di810).getId())
				|| viewId == (findViewById(R.id.di1012).getId())
				|| viewId == (findViewById(R.id.di1214).getId())
				|| viewId == (findViewById(R.id.di1416).getId())
				|| viewId == (findViewById(R.id.di1618).getId())
				|| viewId == (findViewById(R.id.di1820).getId())) {
			dayId = 1;
		} else if (viewId == (findViewById(R.id.mi).getId())
				|| viewId == (findViewById(R.id.mi810).getId())
				|| viewId == (findViewById(R.id.mi1012).getId())
				|| viewId == (findViewById(R.id.mi1214).getId())
				|| viewId == (findViewById(R.id.mi1416).getId())
				|| viewId == (findViewById(R.id.mi1618).getId())
				|| viewId == (findViewById(R.id.mi1820).getId())) {
			dayId = 2;
		} else if (viewId == (findViewById(R.id.don).getId())
				|| viewId == (findViewById(R.id.don810).getId())
				|| viewId == (findViewById(R.id.don1012).getId())
				|| viewId == (findViewById(R.id.don1214).getId())
				|| viewId == (findViewById(R.id.don1416).getId())
				|| viewId == (findViewById(R.id.don1618).getId())
				|| viewId == (findViewById(R.id.don1820).getId())) {
			dayId = 3;
		} else if (viewId == (findViewById(R.id.fr).getId())
				|| viewId == (findViewById(R.id.fr810).getId())
				|| viewId == (findViewById(R.id.fr1012).getId())
				|| viewId == (findViewById(R.id.fr1214).getId())
				|| viewId == (findViewById(R.id.fr1416).getId())
				|| viewId == (findViewById(R.id.fr1618).getId())
				|| viewId == (findViewById(R.id.fr1820).getId())) {
			dayId = 4;
		}

		Intent intent = new Intent(this, DisplayDayActivity.class);

		intent.putExtra("dayId", dayId);
		startActivity(intent);
		System.out.println("startActivity");
	}

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */

	// @Override
	// public boolean onNavigationItemSelected(int itemPosition, long itemId) {
	//
	// System.out.println("MainActivity->onNavigationItemSelected->itemId");
	// System.out.println(itemId);
	//
	// if (itemId == 1) {
	// System.out.println("itemId==1");
	// Intent intent = new Intent(this, NewsActivity.class);
	// startActivity(intent);
	// return true;
	// } else if (itemId == 2) {
	// Intent intent = new Intent(this, TimetableActivity.class);
	// startActivity(intent);
	// return true;
	// } else if (itemId == 3) {
	// Intent intent = new Intent(this, BuslinesActivity.class);
	// startActivity(intent);
	// return true;
	// } else if (itemId == 4) {
	// Intent intent = new Intent(this, SportsActivity.class);
	// startActivity(intent);
	// return true;
	// } else if (itemId == 5) {
	// Intent intent = new Intent(this, CafeteriaActivity.class);
	// startActivity(intent);
	// return true;
	// } else if (itemId == 6) {
	// Intent intent = new Intent(this, ContactsActivity.class);
	// startActivity(intent);
	// return true;
	// } else if (itemId == 7) {
	// Intent intent = new Intent(this, FAQsActivity.class);
	// startActivity(intent);
	// return true;
	// }
	// return false;
	// }

	// /**
	// * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	// * one of the primary sections of the app.
	// */
	// public static class AppSectionsPagerAdapter extends FragmentPagerAdapter
	// {
	//
	// public AppSectionsPagerAdapter(
	// android.support.v4.app.FragmentManager fragmentManager) {
	// super(fragmentManager);
	// }
	//
	// @Override
	// public android.support.v4.app.Fragment getItem(int i) {
	// System.out.println("getItem: " + i);
	// android.support.v4.app.Fragment fragment = new SportsCategoryFragment();
	// Bundle args = new Bundle();
	//
	// args.putInt(SportsCategoryFragment.ARG_PERIOD, i + 1);
	// fragment.setArguments(args);
	// return fragment;
	//
	// }
	//
	// @Override
	// public int getCount() {
	// return 2;
	// }
	//
	// @Override
	// public CharSequence getPageTitle(int position) {
	// String tab = "";
	// switch (position) {
	// case 0:
	// tab = "A-Z";
	// break;
	// case 1:
	// tab = "heute";
	// break;
	// }
	// return tab;
	// }
	// }

}