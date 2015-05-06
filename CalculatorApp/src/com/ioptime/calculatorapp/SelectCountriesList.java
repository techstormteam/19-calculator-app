package com.ioptime.calculatorapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.smartcalculator.R;

public class SelectCountriesList extends SherlockActivity implements
		ISideNavigationCallback, OnClickListener {
	LinearLayout functionPad;

	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private SideNavigationView sideNavigationView;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	ImageView menuIcon;
	Vibrator vibe;
	RelativeLayout mainRelativeLayout;
	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp = 0;
	Animation anim;
	Animation anim_back;
	CurrencyListArray currencyListArray;
	int[] CurrencyArray;
	List<String> currency;
	int[] flag;
	EditText etSearch;
	SearchableAdapter adapter;
	ListView lv;
	ArrayList<CurrencyFlags> arraylist = new ArrayList<CurrencyFlags>();
	ListView list;
	SharedPreferences.Editor editor;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	boolean checkvar = false;
	SharedPreferences prefs;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_countries);
		Purchases.initiatePurchase(SelectCountriesList.this);
		mainRelativeLayout = (RelativeLayout) findViewById(R.id.main_relative_layout);
		prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
		if (!prefs.getString("isPaymentMade", "").equals("true")) {
			ConstantAds.loadInterstitialAd(getApplicationContext(),
					"top");
		}
		currencyListArray = new CurrencyListArray();
		CurrencyArray = currencyListArray.drawables();
		currency = currencyListArray.currencyList();
		flag = currencyListArray.drawables();
		etSearch = (EditText) findViewById(R.id.etSearch);
		ImageView addCurrency = (ImageView) findViewById(R.id.addCurrency);
		SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME,
				MODE_PRIVATE);
		String currencyNames = prefs.getString("currencyNames", "");
		Log.d("test", currencyNames);
		for (int i = 0; i < currencyNames.length(); i++) {
			if (currencyNames.startsWith(",")) {
				currencyNames = currencyNames.substring(1);
			}
		}
		String currencyNamesArray[] = currencyNames.split(",");
		if (!currencyNames.equals("") && currencyNamesArray.length == 1) {
			addCurrency.setImageResource(R.drawable.slot_4_5);
		}
		if (currencyNamesArray.length == 2) {
			addCurrency.setImageResource(R.drawable.slot_3_5);
		}
		if (currencyNamesArray.length == 3) {
			addCurrency.setImageResource(R.drawable.slot_2_5);
		}
		if (currencyNamesArray.length == 4) {
			addCurrency.setImageResource(R.drawable.slot_1_5);
		}
		if (currencyNamesArray.length == 5) {
			addCurrency.setImageResource(R.drawable.slot_0_5);
		}

		anim = AnimationUtils.loadAnimation(this, R.drawable.scale);
		anim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				rl_upgrade.setVisibility(View.VISIBLE);
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub

			}
		});

		anim_back = AnimationUtils.loadAnimation(this, R.drawable.scale_back);
		anim_back.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				rl_upgrade.setVisibility(View.GONE);

			}
		});

		rl_upgrade = (RelativeLayout) findViewById(R.id.rl_upgrade);
		rl_upgrade_parent = (RelativeLayout) findViewById(R.id.rl_upgrade_parent);
		upgrade_close = (ImageView) findViewById(R.id.upgrade_close);
		upgrade_bg = (ImageView) findViewById(R.id.upgrade_bg);
		upgrade_text = (ImageView) findViewById(R.id.upgrade_text);
		upgrade_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp = 0;
			}
		});
		upgrade_bg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp = 0;
			}
		});
		upgrade_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Purchases.makePurchase(SelectCountriesList.this);
//				if (checkvar == true) {
//					editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//							.edit();
//					editor.putString("isPaymentMade", "true");
//					editor.commit();
//				} else {
//					editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//							.edit();
//					editor.putString("isPaymentMade", "false");
//					editor.commit();
//				}
				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp = 0;
			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;
		functionPad = (LinearLayout) findViewById(R.id.functionPad);
		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		menuIcon = (ImageView) findViewById(R.id.menuicon);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		functionPad.setOnTouchListener(gestureListener);
		sideNavigationView.setMenuClickCallback(this);
		gestureDetector = new GestureDetector(this, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};

		if (getIntent().hasExtra(EXTRA_TITLE)) {
			String title = getIntent().getStringExtra(EXTRA_TITLE);
			// int resId = getIntent().getIntExtra(EXTRA_RESOURCE_ID, 0);
			setTitle(title);
			// icon.setImageResource(resId);
			sideNavigationView
					.setMode(getIntent().getIntExtra(EXTRA_MODE, 0) == 0 ? Mode.LEFT
							: Mode.RIGHT);
		}
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) getApplicationContext().getSystemService(
				Context.VIBRATOR_SERVICE);
		menuIcon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sideNavigationView.toggleMenu();
				vibe.vibrate(50);
			}
		});

		list = (ListView) findViewById(R.id.lv);
		for (int i = 0; i < currency.size(); i++) {
			CurrencyFlags wp = new CurrencyFlags(currency.get(i), flag[i],
					false);
			arraylist.add(wp);
		}
		adapter = new SearchableAdapter(this, arraylist);
		list.setAdapter(adapter);
		etSearch = (EditText) findViewById(R.id.etSearch);

		etSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				String text = etSearch.getText().toString().trim()
						.toLowerCase(Locale.getDefault());
				adapter.filter(text);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

		etSearch.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
				}
				return false;
			}
		});

	}

	public class SearchableAdapter extends BaseAdapter {

		Context mContext;
		LayoutInflater inflater;
		private List<CurrencyFlags> worldpopulationlist = null;
		private ArrayList<CurrencyFlags> arraylist;
		SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME,
				MODE_PRIVATE);
		String currencyFullNames = prefs.getString("currencyFullNames", "");

		public boolean checkPositionArr(long pos, int array[]) {
			boolean toReturn = false;
			if (array != null) {
				for (int i = 0; i < array.length; i++) {
					if (array[i] == pos) {
						toReturn = true;
					}
				}
			}
			return toReturn;
		}

		public SearchableAdapter(Context context,
				List<CurrencyFlags> worldpopulationlist) {
			mContext = context;
			this.worldpopulationlist = worldpopulationlist;
			inflater = LayoutInflater.from(mContext);
			this.arraylist = new ArrayList<CurrencyFlags>();
			this.arraylist.addAll(worldpopulationlist);
			for (int i = 0; i < currencyFullNames.length(); i++) {
				if (currencyFullNames.startsWith(",")) {
					currencyFullNames = currencyFullNames.substring(1);
				}
			}

		}

		public class ViewHolder {
			TextView currency;
			ImageView flag;
			ImageView tickmark;
		}

		public int getCount() {
			return worldpopulationlist.size();
		}

		public Object getItem(int position) {
			return worldpopulationlist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getViewTypeCount() {
			// Count=Size of ArrayList.
			return worldpopulationlist.size();
		}

		// added functions
		@Override
		public int getItemViewType(int position) {
			return position;
		}

		public View getView(final int position, View view, ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				holder = new ViewHolder();
				view = inflater.inflate(R.layout.list_item, null);
				// Locate the TextViews in listview_item.xml
				holder.currency = (TextView) view.findViewById(R.id.tvItem);
				// Locate the ImageView in listview_item.xml
				holder.flag = (ImageView) view.findViewById(R.id.imgView);
				holder.tickmark = (ImageView) view
						.findViewById(R.id.ivTickMark);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			String spc[] = currencyFullNames.split(",");

			// Set the results into TextViews

			String getCurrency = worldpopulationlist.get(position)
					.getCurrency();
			String stringItem = getCurrency;
			for (int i = 0; i < spc.length; i++) {
				if (stringItem.equals(spc[i])) {
					holder.tickmark
							.setImageResource(R.drawable.add_currency_tick);
					worldpopulationlist.get(position).setTick(true);
					break;
				} else if (!stringItem.equals(spc[i])) {
					worldpopulationlist.get(position).setTick(false);

				}
			}
			String split[] = stringItem.split("-");
			String CurrencyAbbreviation = split[0];
			CurrencyAbbreviation = CurrencyAbbreviation.toUpperCase();
			holder.currency.setText(getCurrency);
			Typeface tf = Typeface.createFromAsset(getAssets(),
					"HelveticaNeue-Medium.otf");
			holder.currency.setTypeface(tf);
			// Set the results into ImageView
			if (worldpopulationlist.get(position).getTick() == true) {
				holder.tickmark.setImageResource(R.drawable.add_currency_tick);
			} else {
				holder.tickmark.setImageResource(0);
			}
			holder.flag.setImageResource(worldpopulationlist.get(position)
					.getFlag());

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Send single item click data to SingleItemView Class
					String currencyNameToPass;
					int currencyFlagToPass;
					SharedPreferences prefs = getSharedPreferences(
							MY_PREFS_NAME, MODE_PRIVATE);
					String currencyNames = prefs.getString("currencyNames", "");
					for (int i = 0; i < currencyNames.length(); i++) {
						if (currencyNames.startsWith(",")) {
							currencyNames = currencyNames.substring(1);
						}
					}
					String currencyFullNames = prefs.getString(
							"currencyFullNames", "");
					String currencyFlags = prefs.getString("flags", "");
					String fromCurrencyNamesFull = prefs.getString(
							"fromCurrencyNamesFull", "USD - USA");

					String currencyNamesArray[] = currencyNames.split(",");
					if (currencyNamesArray.length < 5) {

						Intent intent = new Intent(mContext,
								CurrencyConverter.class);
						String getCurrency = worldpopulationlist.get(position)
								.getCurrency();
						if (!fromCurrencyNamesFull.equals(getCurrency)) {
							Log.d("getCurrency", getCurrency);
							Log.d("fromCurrencyNamesFull",
									fromCurrencyNamesFull);
							String stringItem = getCurrency;
							String split[] = stringItem.split("-");
							String CurrencyAbbreviation = split[0];
							CurrencyAbbreviation = CurrencyAbbreviation.trim();
							currencyNameToPass = CurrencyAbbreviation;
							currencyFlagToPass = worldpopulationlist.get(
									position).getFlag();

							if (!currencyFlags
									.contains(currencyFlagToPass + "")) {
								if (currencyNames.equals("")) {
									currencyNames = currencyNameToPass;
									currencyFullNames = getCurrency;
									currencyFlags = currencyFlagToPass + "";
								} else {
									currencyNames = currencyNames + ","
											+ currencyNameToPass;
									currencyFullNames = currencyFullNames + ","
											+ getCurrency;
									currencyFlags = currencyFlags + ","
											+ currencyFlagToPass;
								}
								Log.d("passingcurrencyandflags", currencyNames
										+ "------" + currencyFlags);
								editor = getSharedPreferences(MY_PREFS_NAME,
										MODE_PRIVATE).edit();
								editor.putString("currencyNames", currencyNames);
								editor.putString("flags", currencyFlags);
								editor.putString("currencyFullNames",
										currencyFullNames);
								editor.commit();
								intent.putExtra("refresh", "refresh");

								mContext.startActivity(intent);
								finish();

							}

							else {
								Toast.makeText(getApplicationContext(),
										"Currency already added",
										Toast.LENGTH_LONG).show();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Currency already set as main currency",
									Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Currency slot full", Toast.LENGTH_LONG).show();
					}
				}
			});

			return view;
		}

		// Filter Class
		public void filter(String charText) {
			charText = charText.toLowerCase(Locale.getDefault());
			worldpopulationlist.clear();
			if (charText.length() == 0) {
				worldpopulationlist.addAll(arraylist);
			} else {
				for (CurrencyFlags wp : arraylist) {
					if (wp.getCurrency().toLowerCase(Locale.getDefault())
							.contains(charText)) {
						worldpopulationlist.add(wp);
					}
				}
			}
			notifyDataSetChanged();
		}
	}

	// /////

	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main_menu, menu);
		if (sideNavigationView.getMode() == Mode.RIGHT) {
			menu.findItem(R.id.mode_right).setChecked(true);
		} else {
			menu.findItem(R.id.mode_left).setChecked(true);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			sideNavigationView.toggleMenu();
			break;
		case R.id.mode_left:
			item.setChecked(true);
			sideNavigationView.setMode(Mode.LEFT);
			break;
		case R.id.mode_right:
			item.setChecked(true);
			sideNavigationView.setMode(Mode.RIGHT);
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void onSideNavigationItemClick(int itemId) {
		// TODO Auto-generated method stub
		switch (itemId) {
		case R.id.side_navigation_menu_item1:
			invokeActivity(getString(R.string.title1));
			finish();
			break;

		case R.id.side_navigation_menu_item2:
			invokeActivity(getString(R.string.title2));
			finish();
			break;

		case R.id.side_navigation_menu_item3:
			invokeActivity(getString(R.string.title3));
			finish();
			finish();
			break;

		case R.id.side_navigation_menu_item4:
			invokeActivity(getString(R.string.title4));
			if (prefs.getString("isPaymentMade", "").equals("true")) {
				finish();
			}
			break;

		case R.id.side_navigation_menu_item5:
			invokeActivity(getString(R.string.title5));
			if (prefs.getString("isPaymentMade", "").equals("true")) {
				finish();
			}
			break;

		case R.id.side_navigation_menu_item6:
			invokeActivity(getString(R.string.title6));
			// finish();
			break;

		case R.id.side_navigation_menu_item7:
			invokeActivity(getString(R.string.title7));
			break;

		case R.id.side_navigation_menu_item8:
			invokeActivity(getString(R.string.title8));
			finish();
			break;

		default:
			return;
		}

	}

	@Override
	public void onBackPressed() {
		// hide menu if it shown
		if (sideNavigationView.isShown()) {
			sideNavigationView.hideMenu();
			finish();
		}
		if (upgradePopUp == 1) {
			rl_upgrade_parent.startAnimation(anim_back);
			upgradePopUp = 0;
		} else {
			startActivity(new Intent(getApplicationContext(),
					MainActivity.class));
			finish();
		}
	}

	private void invokeActivity(String title) {
		Intent intent = new Intent();
		if (title.equals("SIMPLE CALCULATOR")) {
			intent = new Intent(this, MainActivity.class);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else if (title.equals("BASIC CALCULATOR")) {
			intent = new Intent(this, MainActivity.class);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else if (title.equals("UNIT CONVERTER")) {
			if (prefs.getString("isPaymentMade", "").equals("true")) {
				intent = new Intent(this, UnitConverterLength.class);
				intent.putExtra(EXTRA_TITLE, title);
				intent.putExtra(EXTRA_MODE,
						sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
				sideNavigationView.hideMenu();
				rl_upgrade_parent.startAnimation(anim);
				upgradePopUp = 1;
				overridePendingTransition(0, 0);
			}
			overridePendingTransition(0, 0);
		} else if (title.equals("CURRENCY CONVERTERS")) {
			intent = new Intent(this, CurrencyConverter.class);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else if (title.equals("FITNESS CALCULATOR")) {
			if (prefs.getString("isPaymentMade", "").equals("true")) {
				intent = new Intent(this, HealthCalculator.class);
				intent.putExtra(EXTRA_TITLE, title);
				intent.putExtra(EXTRA_MODE,
						sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				overridePendingTransition(0, 0);
			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
				sideNavigationView.hideMenu();
				rl_upgrade_parent.startAnimation(anim);
				upgradePopUp = 1;
				overridePendingTransition(0, 0);
			}
		} else if (title.equals("SETTINGS")) {
			intent = new Intent(this, MainActivity.class);
			Toast.makeText(getApplicationContext(), "Coming soon",
					Toast.LENGTH_LONG).show();
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else if (title.equals("ABOUT")) {
			intent = new Intent(this, About.class);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else if (title.equals("UPGRADE")) {
			if (!prefs.getString("isPaymentMade", "").equals("true")) {
				sideNavigationView.hideMenu();
				rl_upgrade_parent.startAnimation(anim);
				upgradePopUp = 1;
				overridePendingTransition(0, 0);
			}

		}
	}

	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					sideNavigationView.toggleMenu();
				}
			} catch (Exception e) {
				// nothing
			}
			return false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public boolean onTouchEvent(MotionEvent event) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {

			sideNavigationView.toggleMenu();
			return true;
		}

		// let the system handle all other key events
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Purchases.destroy();
		ConstantAds.displayInterstitial();
	}

}
