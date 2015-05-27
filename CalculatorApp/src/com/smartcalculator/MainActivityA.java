package com.smartcalculator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.ioptime.calculatorapp.MenuListAdapter;
import com.ioptime.calculatorapp.fragments.AboutFragment;
import com.ioptime.calculatorapp.fragments.BasicCalculatorFragment;
import com.ioptime.calculatorapp.fragments.CurrencyConverterFragment;
import com.ioptime.calculatorapp.fragments.CurrencyConverterFragment.MyDragListener;
import com.ioptime.calculatorapp.fragments.FitnessCalculatorFragment;
import com.ioptime.calculatorapp.fragments.HealthResultsFragment;
import com.ioptime.calculatorapp.fragments.SelectCountriesListFragment;
import com.ioptime.calculatorapp.fragments.SelectFormCountryFragment;
import com.ioptime.calculatorapp.fragments.SettingsFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterAreaFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterDataSizeFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterEnergyFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterForceFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterLengthFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterPowerFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterPressureFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterSpeedFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterTempFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterTimeFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterVolumeFragment;
import com.ioptime.calculatorapp.fragments.UnitConverterWeightFragment;
import com.ioptime.calculatorapp.fragments.Upgradeable;

public class MainActivityA extends SherlockFragmentActivity {

	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	// Declare Variables
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] titleStr;
	int[] title;
	
	SharedPreferences prefs;
	
	BasicCalculatorFragment fragBasicCalculator = new BasicCalculatorFragment();
	CurrencyConverterFragment fragCurrencyConverter = new CurrencyConverterFragment();
	UnitConverterLengthFragment fragUnitConverterLength = new UnitConverterLengthFragment();
	FitnessCalculatorFragment fragFitnessCalculator = new FitnessCalculatorFragment();
	SettingsFragment fragSettings = new SettingsFragment();
	
	
	UnitConverterAreaFragment fragUnitConverterArea = new UnitConverterAreaFragment();
	UnitConverterDataSizeFragment fragUnitConverterDataSize = new UnitConverterDataSizeFragment();
	UnitConverterEnergyFragment fragUnitConverterEnergy = new UnitConverterEnergyFragment();
	UnitConverterForceFragment fragUnitConverterForce = new UnitConverterForceFragment();
	UnitConverterPowerFragment fragUnitConverterPower = new UnitConverterPowerFragment();
	UnitConverterPressureFragment fragUnitConverterPressure = new UnitConverterPressureFragment();
	UnitConverterSpeedFragment fragUnitConverterSpeed = new UnitConverterSpeedFragment();
	UnitConverterTempFragment fragUnitConverterTemp = new UnitConverterTempFragment();
	UnitConverterTimeFragment fragUnitConverterTime = new UnitConverterTimeFragment();
	UnitConverterVolumeFragment fragUnitConverterVolume = new UnitConverterVolumeFragment();
	UnitConverterWeightFragment fragUnitConverterWeight = new UnitConverterWeightFragment();
	
	HealthResultsFragment fragHealthResults = new HealthResultsFragment();
	SelectCountriesListFragment fragSelectCountriesList = new SelectCountriesListFragment();
	SelectFormCountryFragment fragSelectFormCountry = new SelectFormCountryFragment();
	
	public void showCurrencyConverterFragment(String refresh) {
		selectItem(1, false);
	}
	
	public void showHealthResultsFragment(String bmi, String bmr, String bodyFat, String Gender) {
		getIntent().putExtra("BMI", bmi);
		getIntent().putExtra("BMR", bmr);
		getIntent().putExtra("BodyFat", bodyFat);
		getIntent().putExtra("Gender", Gender);
		selectItem(80, true);
	}
	
	public void showSelectCountriesListFragment() {
		selectItem(81, true);
	}
	
	public void showSelectFormCountryFragment() {
		selectItem(82, true);
	}
	
	
	
	
	public void showUnitConverterAreaFragment() {
		selectItem(90, true);
	}
	
	public void showUnitConverterDataSizeFragment() {
		selectItem(91, true);
	}
	
	public void showUnitConverterEnergyFragment() {
		selectItem(92, true);
	}
	
	public void showUnitConverterForceFragment() {
		selectItem(93, true);
	}
	
	public void showUnitConverterPowerFragment() {
		selectItem(94, true);
	}
	
	public void showUnitConverterPressureFragment() {
		selectItem(95, true);
	}	
	
	public void showUnitConverterSpeedFragment() {
		selectItem(96, true);
	}
	
	public void showUnitConverterTempFragment() {
		selectItem(97, true);
	}
	
	public void showUnitConverterTimeFragment() {
		selectItem(98, true);
	}	
	
	public void showUnitConverterVolumeFragment() {
		selectItem(99, true);
	}
	
	public void showUnitConverterWeightFragment() {
		selectItem(100, true);
	}
	
	public void showUnitConverterLengthFragment() {
		selectItem(101, true);
	}
	
	Upgradeable currentFragmentCanUpgrade;
	AboutFragment fragAbout = new AboutFragment();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	private static MainActivityA instance;
	public static MainActivityA getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);

//		DrawerLayout mainView = (DrawerLayout)findViewById(R.id.drawer_layout);
//		mainView.setOnDragListener(new MyDragListener());
		
		currentFragmentCanUpgrade = fragBasicCalculator;

		prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
		
		// Get the Title
		mTitle = mDrawerTitle = getTitle();

		// Generate title
		titleStr = new String[] { "BASIC CALCULATOR", "CURRENCY CONVERTER", "UNIT CONVERTER", 
				"FITNESS CALCULATOR", "SETTINGS", "UPGRADE", "ABOUT" };
		
		
		
		title = new int[] { R.drawable.menuleft1, R.drawable.menuleft2, R.drawable.menuleft3, 
				R.drawable.menuleft4, R.drawable.menuleft5, R.drawable.menuleft6, R.drawable.menuleft7 };
		
		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass string arrays to MenuListAdapter
		mMenuAdapter = new MenuListAdapter(MainActivityA.this, titleStr, title);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture listview menu item click
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// Set the title on the action when drawer open
				getSupportActionBar().setTitle(mDrawerTitle);
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			selectItem(0, false);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	// ListView click listener in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position, false);
		}
	}

	private void selectItem(int position, boolean changeViewOnly) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, fragBasicCalculator);
			currentFragmentCanUpgrade = fragBasicCalculator;
			break;
		case 1:
			ft.replace(R.id.content_frame, fragCurrencyConverter);
			currentFragmentCanUpgrade = fragCurrencyConverter;
			break;
		case 2:
			if (prefs.getString("isPaymentMade", "").equals("true")) {
				ft.replace(R.id.content_frame, fragUnitConverterLength);
				currentFragmentCanUpgrade = fragUnitConverterLength;
			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
				currentFragmentCanUpgrade.showUpgrade();
			}
			break;
		case 3:
			if (prefs.getString("isPaymentMade", "").equals("true")) {
				ft.replace(R.id.content_frame, fragFitnessCalculator);
				currentFragmentCanUpgrade = fragFitnessCalculator;
			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
				currentFragmentCanUpgrade.showUpgrade();
			}
			break;
		case 4:
			//ft.replace(R.id.content_frame, fragSettings);
			break;
		case 5:
			if (!prefs.getString("isPaymentMade", "").equals("true")) {
				currentFragmentCanUpgrade.showUpgrade();
			}
			break;
		case 6:
			ft.replace(R.id.content_frame, fragAbout);
			currentFragmentCanUpgrade = fragAbout;
			break;
		
		case 80:
			ft.replace(R.id.content_frame, fragHealthResults);
			currentFragmentCanUpgrade = fragHealthResults;
			break;
		case 81:
			ft.replace(R.id.content_frame, fragSelectCountriesList);
			currentFragmentCanUpgrade = fragSelectCountriesList;
			break;
		case 82:
			ft.replace(R.id.content_frame, fragSelectFormCountry);
			currentFragmentCanUpgrade = fragSelectFormCountry;
			break;
			
		case 90:
			ft.replace(R.id.content_frame, fragUnitConverterArea);
			currentFragmentCanUpgrade = fragUnitConverterArea;
			break;
		case 91:
			ft.replace(R.id.content_frame, fragUnitConverterDataSize);
			currentFragmentCanUpgrade = fragUnitConverterDataSize;
			break;
		case 92:
			ft.replace(R.id.content_frame, fragUnitConverterEnergy);
			currentFragmentCanUpgrade = fragUnitConverterEnergy;
			break;
		case 93:
			ft.replace(R.id.content_frame, fragUnitConverterForce);
			currentFragmentCanUpgrade = fragUnitConverterForce;
			break;
		case 94:
			ft.replace(R.id.content_frame, fragUnitConverterPower);
			currentFragmentCanUpgrade = fragUnitConverterPower;
			break;
		case 95:
			ft.replace(R.id.content_frame, fragUnitConverterPressure);
			currentFragmentCanUpgrade = fragUnitConverterPressure;
			break;
		case 96:
			ft.replace(R.id.content_frame, fragUnitConverterSpeed);
			currentFragmentCanUpgrade = fragUnitConverterSpeed;
			break;
		case 97:
			ft.replace(R.id.content_frame, fragUnitConverterTemp);
			currentFragmentCanUpgrade = fragUnitConverterTemp;
			break;
		case 98:
			ft.replace(R.id.content_frame, fragUnitConverterTime);
			currentFragmentCanUpgrade = fragUnitConverterTime;
			break;
		case 99:
			ft.replace(R.id.content_frame, fragUnitConverterVolume);
			currentFragmentCanUpgrade = fragUnitConverterVolume;
			break;
		case 100:
			ft.replace(R.id.content_frame, fragUnitConverterWeight);
			currentFragmentCanUpgrade = fragUnitConverterWeight;
			break;
		case 101:
			ft.replace(R.id.content_frame, fragUnitConverterLength);
			currentFragmentCanUpgrade = fragUnitConverterLength;
			break;
		}
		ft.commit();
		
		if (!changeViewOnly) {
			mDrawerList.setItemChecked(position, true);
			// Get the title followed by the position
			setTitle(titleStr[position]);
			// Close drawer
			mDrawerLayout.closeDrawer(mDrawerList);
		}
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
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	public void onBackPressed() {

		if(currentFragmentCanUpgrade.getUpgradePopUp() == 1)
		{
			currentFragmentCanUpgrade.showUpgrade();
		}
		
		FragmentManager manager = getSupportFragmentManager();
		if (manager.getBackStackEntryCount() > 0) {
			// If there are back-stack entries, leave the FragmentActivity
			// implementation take care of them.
			manager.popBackStack();

		} else {
			// Otherwise, ask user if he wants to leave :)
			super.onBackPressed();
		}
	}
	
}
