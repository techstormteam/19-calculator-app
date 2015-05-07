package com.ioptime.calculatorapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class CurrencyConverter extends SherlockActivity implements
		ISideNavigationCallback {

	LinearLayout funtionPad;
	Context ctx;

	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private SideNavigationView sideNavigationView;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	ImageView circleBtn;
	TextView sub_1_tv;
	TextView sub_2_tv;
	TextView sub_3_tv;
	TextView sub_4_tv;
	TextView sub_5_tv;
	TextView sub_1_currency;
	TextView sub_2_currency;
	TextView sub_3_currency;
	TextView sub_4_currency;
	TextView sub_5_currency;
	TextView sub_1_currency_full;
	TextView sub_2_currency_full;
	TextView sub_3_currency_full;
	TextView sub_4_currency_full;
	TextView sub_5_currency_full;
	Float sub_val_1;
	Float sub_val_2;
	Float sub_val_3;
	Float sub_val_4;
	Float sub_val_5;
	ImageView sub_1_delete;
	ImageView sub_2_delete;
	ImageView sub_3_delete;
	ImageView sub_4_delete;
	ImageView sub_5_delete;
	ImageView sub_1_image;
	ImageView sub_2_image;
	ImageView sub_3_image;
	ImageView sub_4_image;
	ImageView sub_5_image;
	ImageView sub_1;
	ImageView sub_2;
	ImageView sub_3;
	ImageView sub_4;
	ImageView sub_5;
	String sub_1_abrv;
	String sub_2_abrv;
	String sub_3_abrv;
	String sub_4_abrv;
	String sub_5_abrv;
	String sub_1_flagRes;
	String sub_2_flagRes;
	String sub_3_flagRes;
	String sub_4_flagRes;
	String sub_5_flagRes;
	ImageView menuIcon;
	ImageView refreshButton;
	ImageView addCurrencyImage;
	ImageView currencySlotFullImage;
	TextView lastUpdate;
	EditText etContent;
	TextView currenyContentFull;
	Vibrator vibe;

	int x;
	RotateAnimation a;
	String currencyNames;
	String currencyFlags;
	String currencyFullNames;
	String currencyNamesArray[];
	String currencyFlagsArray[];
	String currencyFullNamesArray[];
	SharedPreferences.Editor editor;
	String sub1;
	String sub2;
	String sub3;
	String sub4;
	String sub5;
	SharedPreferences prefs;
	String fromCurrencyNames;
	String fromCurrencyFlags;
	ImageView fromImage;
	TextView refreshTime;
	CurrencyListArray currencyListArray;

	String sub1_currencyRate;
	String sub2_currencyRate;
	String sub3_currencyRate;
	String sub4_currencyRate;
	String sub5_currencyRate;
	Bundle bundle;
	String refreshCheck;

	RelativeLayout sub_1_rl;
	RelativeLayout sub_2_rl;
	RelativeLayout sub_3_rl;
	RelativeLayout sub_4_rl;
	RelativeLayout sub_5_rl;
	String fromCurrencyNamesFull;

	String fullName1;
	String fullName2;
	String fullName3;
	String fullName4;
	String fullName5;

	ImageView addNewGuide;

	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp = 0;
	Animation anim;
	Animation anim_back;
	RelativeLayout mainRelativeLayout;

	public static final String MY_PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.currency_converters);
		Purchases.initiatePurchase(CurrencyConverter.this);
		mainRelativeLayout = (RelativeLayout) findViewById(R.id.main_relative_layout);

		bundle = getIntent().getExtras();
		if (bundle != null) {

		}
		prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
		if (!prefs.getString("isPaymentMade", "").equals("true")) {
			ConstantAds.loadInterstitialAd(getApplicationContext(),
					"top");
		}
		currencyNames = prefs.getString("currencyNames", "");
		currencyFlags = prefs.getString("flags", "");
		currencyFullNames = prefs.getString("currencyFullNames", "");
		fromCurrencyNames = prefs.getString("fromCurrencyNames", "USD");
		fromCurrencyFlags = prefs.getString("fromCurrencyFlags", "");
		fromCurrencyNamesFull = prefs.getString("fromCurrencyNamesFull",
				"USD - USA");
		final CounterClass timer = new CounterClass(60000, 1000);
		// timer.start();
		gestureDetector = new GestureDetector(this, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};
		currencyListArray = new CurrencyListArray();
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
				Purchases
						.makePurchase(CurrencyConverter.this);

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
		refreshButton = (ImageView) findViewById(R.id.refresh_button);
		funtionPad = (LinearLayout) findViewById(R.id.functionPad);
		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		refreshTime = (TextView) findViewById(R.id.next_refresh_time);
		menuIcon = (ImageView) findViewById(R.id.menuicon);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		circleBtn = (ImageView) findViewById(R.id.circle_button_check);
		etContent = (EditText) findViewById(R.id.tvContent);
		currenyContentFull = (TextView) findViewById(R.id.content_currency_full);
		fromImage = (ImageView) findViewById(R.id.imgContent);
		sub_1_tv = (TextView) findViewById(R.id.sub_1_tv);
		sub_2_tv = (TextView) findViewById(R.id.sub_2_tv);
		sub_3_tv = (TextView) findViewById(R.id.sub_3_tv);
		sub_4_tv = (TextView) findViewById(R.id.sub_4_tv);
		sub_5_tv = (TextView) findViewById(R.id.sub_5_tv);
		sub_1_currency = (TextView) findViewById(R.id.sub_1_currency);
		sub_2_currency = (TextView) findViewById(R.id.sub_2_currency);
		sub_3_currency = (TextView) findViewById(R.id.sub_3_currency);
		sub_4_currency = (TextView) findViewById(R.id.sub_4_currency);
		sub_5_currency = (TextView) findViewById(R.id.sub_5_currency);
		sub_1_currency_full = (TextView) findViewById(R.id.sub_1_currency_full);
		sub_2_currency_full = (TextView) findViewById(R.id.sub_2_currency_full);
		sub_3_currency_full = (TextView) findViewById(R.id.sub_3_currency_full);
		sub_4_currency_full = (TextView) findViewById(R.id.sub_4_currency_full);
		sub_5_currency_full = (TextView) findViewById(R.id.sub_5_currency_full);
		sub_1_delete = (ImageView) findViewById(R.id.sub_1_delete_button);
		sub_2_delete = (ImageView) findViewById(R.id.sub_2_delete_button);
		sub_3_delete = (ImageView) findViewById(R.id.sub_3_delete_button);
		sub_4_delete = (ImageView) findViewById(R.id.sub_4_delete_button);
		sub_5_delete = (ImageView) findViewById(R.id.sub_5_delete_button);
		sub_1_image = (ImageView) findViewById(R.id.sub_1_img);
		sub_2_image = (ImageView) findViewById(R.id.sub_2_img);
		sub_3_image = (ImageView) findViewById(R.id.sub_3_img);
		sub_4_image = (ImageView) findViewById(R.id.sub_4_img);
		sub_5_image = (ImageView) findViewById(R.id.sub_5_img);
		sub_1 = (ImageView) findViewById(R.id.sub_1);
		sub_2 = (ImageView) findViewById(R.id.sub_2);
		sub_3 = (ImageView) findViewById(R.id.sub_3);
		sub_4 = (ImageView) findViewById(R.id.sub_4);
		sub_5 = (ImageView) findViewById(R.id.sub_5);
		sub_1_rl = (RelativeLayout) findViewById(R.id.rl_sub_1);
		sub_2_rl = (RelativeLayout) findViewById(R.id.rl_sub_2);
		sub_3_rl = (RelativeLayout) findViewById(R.id.rl_sub_3);
		sub_4_rl = (RelativeLayout) findViewById(R.id.rl_sub_4);
		sub_5_rl = (RelativeLayout) findViewById(R.id.rl_sub_5);
		addCurrencyImage = (ImageView) findViewById(R.id.add_currency_image);
		currencySlotFullImage = (ImageView) findViewById(R.id.slot_full_image);
		addNewGuide = (ImageView) findViewById(R.id.add_new_guide);
		if (!fromCurrencyFlags.equals("")) {
			fromImage.setImageResource(Integer.valueOf(fromCurrencyFlags));
		} else {
			fromImage.setImageResource(R.drawable.usd_united_states_dollar);
		}
		ArrayList<String> cloneList = new ArrayList<String>();
		for (String curVal : currencyListArray.currencyList()) {
			if (curVal.contains(fromCurrencyNamesFull)) {
				cloneList.add(curVal);
			}
		}
		String arr[] = cloneList.get(0).split("-");
		String sub_curr = arr[0].toUpperCase().trim() + " - " + arr[1].trim();
		currenyContentFull.setText(sub_curr);

		subView1Gone();
		subView2Gone();
		subView3Gone();
		subView4Gone();
		subView5Gone();
		for (int i = 0; i < currencyNames.length(); i++) {
			if (currencyNames.startsWith(",")) {
				currencyNames = currencyNames.substring(1);
			}
		}

		for (int i = 0; i < currencyFlags.length(); i++) {
			if (currencyFlags.startsWith(",")) {
				currencyFlags = currencyFlags.substring(1);
			}
		}

		for (int i = 0; i < currencyFullNames.length(); i++) {
			if (currencyFullNames.startsWith(",")) {
				currencyFullNames = currencyFullNames.substring(1);
			}
		}

		Log.d("currency2", "" + currencyFlags);
		currencyNamesArray = currencyNames.split(",");
		currencyFlagsArray = currencyFlags.split(",");
		currencyFullNamesArray = currencyFullNames.split(",");

		if (currencyNames.equals("")) {
			addNewGuide.setVisibility(View.VISIBLE);
		} else {
			addNewGuide.setVisibility(View.GONE);
		}

		if (!currencyNames.equals("") && currencyNamesArray.length == 1) {
			subView1Visible();
			fullName1 = currencyFullNamesArray[0];
			sub_1_abrv = currencyNamesArray[0];
			sub_1_flagRes = currencyFlagsArray[0];
			sub_1_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[0]));
			addCurrencyImage.setImageResource(R.drawable.add_currency_4_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_1_5);

		}
		if (currencyNamesArray.length == 2) {
			subView1Visible();
			subView2Visible();
			fullName1 = currencyFullNamesArray[0];
			fullName2 = currencyFullNamesArray[1];
			sub_1_abrv = currencyNamesArray[0];
			sub_2_abrv = currencyNamesArray[1];
			sub_1_flagRes = currencyFlagsArray[0];
			sub_2_flagRes = currencyFlagsArray[1];
			Log.d("test2", currencyFlags);
			sub_1_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[0]));
			sub_2_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[1]));
			addCurrencyImage.setImageResource(R.drawable.add_currency_3_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_2_5);
		}
		if (currencyNamesArray.length == 3) {
			subView1Visible();
			subView2Visible();
			subView3Visible();
			sub_1_abrv = currencyNamesArray[0];
			sub_2_abrv = currencyNamesArray[1];
			sub_3_abrv = currencyNamesArray[2];
			fullName1 = currencyFullNamesArray[0];
			fullName2 = currencyFullNamesArray[1];
			fullName3 = currencyFullNamesArray[2];
			sub_1_flagRes = currencyFlagsArray[0];
			sub_2_flagRes = currencyFlagsArray[1];
			sub_3_flagRes = currencyFlagsArray[2];
			sub_1_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[0]));
			sub_2_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[1]));
			sub_3_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[2]));
			addCurrencyImage.setImageResource(R.drawable.add_currency_2_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_3_5);
		}
		if (currencyNamesArray.length == 4) {
			subView1Visible();
			subView2Visible();
			subView3Visible();
			subView4Visible();
			fullName1 = currencyFullNamesArray[0];
			fullName2 = currencyFullNamesArray[1];
			fullName3 = currencyFullNamesArray[2];
			fullName4 = currencyFullNamesArray[3];
			sub_1_abrv = currencyNamesArray[0];
			sub_2_abrv = currencyNamesArray[1];
			sub_3_abrv = currencyNamesArray[2];
			sub_4_abrv = currencyNamesArray[3];
			sub_1_flagRes = currencyFlagsArray[0];
			sub_2_flagRes = currencyFlagsArray[1];
			sub_3_flagRes = currencyFlagsArray[2];
			sub_4_flagRes = currencyFlagsArray[3];
			sub_1_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[0]));
			sub_2_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[1]));
			sub_3_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[2]));
			sub_4_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[3]));
			addCurrencyImage.setImageResource(R.drawable.add_currency_1_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_4_5);
		}
		if (currencyNamesArray.length == 5) {
			subView1Visible();
			subView2Visible();
			subView3Visible();
			subView4Visible();
			subView5Visible();
			fullName1 = currencyFullNamesArray[0];
			fullName2 = currencyFullNamesArray[1];
			fullName3 = currencyFullNamesArray[2];
			fullName4 = currencyFullNamesArray[3];
			fullName5 = currencyFullNamesArray[4];
			sub_1_abrv = currencyNamesArray[0];
			sub_2_abrv = currencyNamesArray[1];
			sub_3_abrv = currencyNamesArray[2];
			sub_4_abrv = currencyNamesArray[3];
			sub_5_abrv = currencyNamesArray[4];
			sub_1_flagRes = currencyFlagsArray[0];
			sub_2_flagRes = currencyFlagsArray[1];
			sub_3_flagRes = currencyFlagsArray[2];
			sub_4_flagRes = currencyFlagsArray[3];
			sub_5_flagRes = currencyFlagsArray[4];
			sub_1_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[0]));
			sub_2_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[1]));
			sub_3_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[2]));
			sub_4_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[3]));
			sub_5_image
					.setImageResource(Integer.valueOf(currencyFlagsArray[4]));
			addCurrencyImage.setImageResource(R.drawable.add_currency_0_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_5_5);

		}
		circleBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SelectFromCountry.class);

				startActivity(intent);
				finish();

			}
		});

		addCurrencyImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				currencyNamesArray = currencyNames.split(",");
				if (currencyFlagsArray.length == 5) {
					Toast.makeText(getApplicationContext(),
							"Currency Slot full", Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(getApplicationContext(),
							SelectCountriesList.class);

					startActivity(intent);
					finish();
				}
			}
		});

		sub_1_delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				subView1Gone();
				deleteAndSetImages();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				if (currencyNamesArray.length == 1) {
					currencyNames = "";
					currencyFlags = "";
					currencyFullNames = "";

				} else {
					if (currencyNames.contains("," + sub_1_abrv)) {
						currencyNames = currencyNames.replace("," + sub_1_abrv,
								"");
						currencyFlags = currencyFlags.replace(","
								+ sub_1_flagRes, "");
						currencyFullNames = currencyFullNames.replace(","
								+ fullName1, "");
					} else {
						currencyNames = currencyNames.replace(sub_1_abrv, "");
						currencyFlags = currencyFlags
								.replace(sub_1_flagRes, "");
						currencyFullNames = currencyFullNames.replace(
								fullName1, "");

					}
				}
				commitValuesAfterDelete();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
						.edit();
				editor.putString("sub1_currencyRate", "");
				editor.commit();

			}
		});

		sub_2_delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				subView2Gone();
				deleteAndSetImages();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				if (currencyNamesArray.length == 1) {
					currencyNames = "";
					currencyFlags = "";
					currencyFullNames = "";

				} else {
					if (currencyNames.contains("," + sub_2_abrv)) {
						currencyNames = currencyNames.replace("," + sub_2_abrv,
								"");
						currencyFlags = currencyFlags.replace(","
								+ sub_2_flagRes, "");
						currencyFullNames = currencyFullNames.replace(","
								+ fullName2, "");
					} else {
						currencyNames = currencyNames.replace(sub_2_abrv, "");
						currencyFlags = currencyFlags
								.replace(sub_2_flagRes, "");
						currencyFullNames = currencyFullNames.replace(
								fullName2, "");
					}
				}
				commitValuesAfterDelete();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
						.edit();
				editor.putString("sub2_currencyRate", "");
				editor.commit();

			}
		});

		sub_3_delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				subView3Gone();
				deleteAndSetImages();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				if (currencyNamesArray.length == 1) {
					currencyNames = "";
					currencyFlags = "";
					currencyFullNames = "";

				} else {
					if (currencyNames.contains("," + sub_3_abrv)) {
						currencyNames = currencyNames.replace("," + sub_3_abrv,
								"");
						currencyFlags = currencyFlags.replace(","
								+ sub_3_flagRes, "");
						currencyFullNames = currencyFullNames.replace(","
								+ fullName3, "");
					} else {
						currencyNames = currencyNames.replace(sub_3_abrv, "");
						currencyFlags = currencyFlags
								.replace(sub_3_flagRes, "");
					}
					currencyFullNames = currencyFullNames
							.replace(fullName3, "");
				}
				commitValuesAfterDelete();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
						.edit();
				editor.putString("sub3_currencyRate", "");
				editor.commit();

			}
		});

		sub_4_delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				subView4Gone();
				deleteAndSetImages();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				if (currencyNamesArray.length == 1) {
					currencyNames = "";
					currencyFlags = "";
					currencyFullNames = "";

				} else {
					if (currencyNames.contains("," + sub_4_abrv)) {
						currencyNames = currencyNames.replace("," + sub_4_abrv,
								"");
						currencyFlags = currencyFlags.replace(","
								+ sub_4_flagRes, "");
						currencyFullNames = currencyFullNames.replace(","
								+ fullName4, "");
					} else {
						currencyNames = currencyNames.replace(sub_4_abrv, "");
						currencyFlags = currencyFlags
								.replace(sub_4_flagRes, "");
						currencyFullNames = currencyFullNames.replace(
								fullName4, "");
					}
				}
				commitValuesAfterDelete();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
						.edit();
				editor.putString("sub4_currencyRate", "");
				editor.commit();

			}
		});
		sub_5_delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				subView5Gone();
				deleteAndSetImages();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				if (currencyNamesArray.length == 1) {
					currencyNames = "";
					currencyFlags = "";
					currencyFullNames = "";

				} else {
					if (currencyNames.contains("," + sub_5_abrv)) {
						currencyNames = currencyNames.replace("," + sub_5_abrv,
								"");
						currencyFlags = currencyFlags.replace(","
								+ sub_5_flagRes, "");
						currencyFullNames = currencyFullNames.replace(","
								+ fullName5, "");
					} else {
						currencyNames = currencyNames.replace(sub_5_abrv, "");
						currencyFlags = currencyFlags
								.replace(sub_5_flagRes, "");
						currencyFullNames = currencyFullNames.replace(
								fullName5, "");
					}
				}
				commitValuesAfterDelete();
				Log.d("stats", "currencyarry:" + currencyNamesArray.length
						+ "   currencynames:" + currencyNames
						+ "    currencyFullNames:" + currencyFullNames);
				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
						.edit();
				editor.putString("sub5_currencyRate", "");
				editor.commit();
			}
		});
		etContent.setText("1.00");
		lastUpdate = (TextView) findViewById(R.id.your_last_update_date);
		lastUpdate.setText(getTimeUpdateValue());

		refreshButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (isNetworkAvailable()) {
					new RefreshWithCurrency().execute();
					// sub_1_tv.setText("-------");
					// sub_2_tv.setText("-------");
					// sub_3_tv.setText("-------");
					// sub_4_tv.setText("-------");
					// sub_5_tv.setText("-------");
					// sub_1_currency.setText("-------");
					// sub_2_currency.setText("-------");
					// sub_3_currency.setText("-------");
					// sub_4_currency.setText("-------");
					// sub_5_currency.setText("-------");
					// sub_1_currency_full.setText("-------");
					// sub_2_currency_full.setText("-------");
					// sub_3_currency_full.setText("-------");
					// sub_4_currency_full.setText("-------");
					// sub_5_currency_full.setText("-------");
					lastUpdate.setText(getTimeUpdateValue());
					refreshButton.setEnabled(false);

					startAnimationRefresh();
					timer.start();
				} else {
					sub_1_tv.setText("No Connection");
					sub_2_tv.setText("No Connection");
					sub_3_tv.setText("No Connection");
					sub_4_tv.setText("No Connection");
					sub_5_tv.setText("No Connection");
				}
			}
		});

		if (bundle.getString("refresh") != null) {
			if (isNetworkAvailable()) {
				new RefreshWithCurrency().execute();
			} else {
				sub_1_tv.setText("No Connection");
				sub_2_tv.setText("No Connection");
				sub_3_tv.setText("No Connection");
				sub_4_tv.setText("No Connection");
				sub_5_tv.setText("No Connection");
			}
		} else {
			refreshWithPrefrences();
			sub1 = sub_1_tv.getText().toString();
			sub2 = sub_2_tv.getText().toString();
			sub3 = sub_3_tv.getText().toString();
			sub4 = sub_4_tv.getText().toString();
			sub5 = sub_5_tv.getText().toString();
		}
		sideNavigationView.setMenuClickCallback(this);

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

		etContent.addTextChangedListener(watch);
	}

	TextWatcher watch = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int a, int b, int c) {
			// TODO Auto-generated method stub
			if (s.toString().length() > 0) {

				try {
					if (sub_1_tv.getText().toString().equals("Service Down")) {
						Toast.makeText(getApplicationContext(), "Service Down",
								Toast.LENGTH_LONG).show();
					} else {
						currencyNamesArray = currencyNames.split(",");
						currencyFlagsArray = currencyFlags.split(",");
						if (!currencyNames.equals("")
								&& currencyNamesArray.length == 1) {
							BigDecimal value1 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub1)));

							String number1 = value1.toString();
							double amount1 = Double.parseDouble(number1);
							DecimalFormat formatter1 = new DecimalFormat(
									"#,###.00");
							sub_1_tv.setText(formatter1.format(amount1));
						}
						if (currencyNamesArray.length == 2) {
							BigDecimal value1 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub1)));
							String number1 = value1.toString();
							double amount1 = Double.parseDouble(number1);
							DecimalFormat formatter1 = new DecimalFormat(
									"#,###.00");
							sub_1_tv.setText(formatter1.format(amount1));
							BigDecimal value2 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub2)));
							String number2 = value2.toString();
							double amount2 = Double.parseDouble(number2);
							DecimalFormat formatter2 = new DecimalFormat(
									"#,###.00");
							sub_2_tv.setText(formatter2.format(amount2));
						}

						if (currencyNamesArray.length == 3) {
							BigDecimal value1 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub1)));
							String number1 = value1.toString();
							double amount1 = Double.parseDouble(number1);
							DecimalFormat formatter1 = new DecimalFormat(
									"#,###.00");
							sub_1_tv.setText(formatter1.format(amount1));
							BigDecimal value2 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub2)));
							String number2 = value2.toString();
							double amount2 = Double.parseDouble(number2);
							DecimalFormat formatter2 = new DecimalFormat(
									"#,###.00");
							sub_2_tv.setText(formatter2.format(amount2));
							BigDecimal value3 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub3)));
							String number3 = value3.toString();
							double amount3 = Double.parseDouble(number3);
							DecimalFormat formatter3 = new DecimalFormat(
									"#,###.00");
							sub_3_tv.setText(formatter3.format(amount3));
						}

						if (currencyNamesArray.length == 4) {
							BigDecimal value1 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub1)));
							String number1 = value1.toString();
							double amount1 = Double.parseDouble(number1);
							DecimalFormat formatter1 = new DecimalFormat(
									"#,###.00");
							sub_1_tv.setText(formatter1.format(amount1));
							BigDecimal value2 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub2)));
							String number2 = value2.toString();
							double amount2 = Double.parseDouble(number2);
							DecimalFormat formatter2 = new DecimalFormat(
									"#,###.00");
							sub_2_tv.setText(formatter2.format(amount2));
							BigDecimal value3 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub3)));
							String number3 = value3.toString();
							double amount3 = Double.parseDouble(number3);
							DecimalFormat formatter3 = new DecimalFormat(
									"#,###.00");
							sub_3_tv.setText(formatter3.format(amount3));
							BigDecimal value4 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub4)));
							String number4 = value4.toString();
							double amount4 = Double.parseDouble(number4);
							DecimalFormat formatter4 = new DecimalFormat(
									"#,###.00");
							sub_4_tv.setText(formatter4.format(amount4));
						}

						if (currencyNamesArray.length == 5) {
							BigDecimal value1 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub1)));
							String number1 = value1.toString();
							double amount1 = Double.parseDouble(number1);
							DecimalFormat formatter1 = new DecimalFormat(
									"#,###.00");
							sub_1_tv.setText(formatter1.format(amount1));
							BigDecimal value2 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub2)));
							String number2 = value2.toString();
							double amount2 = Double.parseDouble(number2);
							DecimalFormat formatter2 = new DecimalFormat(
									"#,###.00");
							sub_2_tv.setText(formatter2.format(amount2));
							BigDecimal value3 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub3)));
							String number3 = value3.toString();
							double amount3 = Double.parseDouble(number3);
							DecimalFormat formatter3 = new DecimalFormat(
									"#,###.00");
							sub_3_tv.setText(formatter3.format(amount3));
							BigDecimal value4 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub4)));
							String number4 = value4.toString();
							double amount4 = Double.parseDouble(number4);
							DecimalFormat formatter4 = new DecimalFormat(
									"#,###.00");
							sub_4_tv.setText(formatter4.format(amount4));
							BigDecimal value5 = BigDecimal.valueOf((Float
									.valueOf(s.toString()) * Float
									.valueOf(sub5)));
							String number5 = value5.toString();
							double amount5 = Double.parseDouble(number5);
							DecimalFormat formatter5 = new DecimalFormat(
									"#,###.00");
							sub_5_tv.setText(formatter5.format(amount5));
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), "Wrong Input",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	};

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		startAnimationRefresh();
		a.cancel();
	}

	private void startAnimationRefresh() {
		a = new RotateAnimation(360.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		a.setRepeatCount(-1);
		a.setDuration(1000);
		a.setInterpolator(new LinearInterpolator());
		refreshButton.setAnimation(a);
		// Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG)
		// .show();
	}

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
				overridePendingTransition(0, 0);
			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
				sideNavigationView.hideMenu();
				rl_upgrade_parent.startAnimation(anim);
				upgradePopUp = 1;
				overridePendingTransition(0, 0);
			}
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

	public boolean onTouchEvent(MotionEvent event) {
		// InputMethodManager imm = (InputMethodManager)
		// getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

		return true;
	}

	private String makePostRequest(String from, String to) {

		String toReturn = "";

		try {
			String url = "http://rate-exchange.appspot.com/currency?to=" + to
					+ "&from=" + from;
			Log.d("ok done url", url);
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpPost = new HttpGet(url);

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			String result = "";
			result = sb.toString();
			Log.d("ok done", result);
			toReturn = result.toString();
			try {
				toReturn = toReturn.substring(21);
				toReturn = toReturn.substring(0, toReturn.indexOf(","));
				if (toReturn.length() > 8) {
					toReturn = toReturn.substring(0, 8);
				}
			} catch (Exception e) {
				toReturn = "No Data Recieved";
			}
		} catch (ClientProtocolException e) {
			// Log exception
			toReturn = "No Data Recieved";
		} catch (IOException e) {
			// Log exception
			toReturn = "No Data Recieved";
		}
		Log.d("ok done", toReturn);
		return toReturn;

	}

	class RefreshWithCurrency extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Log.d("currencyNamesarray", currencyNamesArray.length + " | "
					+ sub_1_abrv);

			if (!currencyNames.equals("") && currencyNamesArray.length == 1) {
				sub1 = makePostRequest(fromCurrencyNames, sub_1_abrv);

			}
			if (currencyNamesArray.length == 2) {
				sub1 = makePostRequest(fromCurrencyNames, sub_1_abrv);
				sub2 = makePostRequest(fromCurrencyNames, sub_2_abrv);
			}
			if (currencyNamesArray.length == 3) {
				sub1 = makePostRequest(fromCurrencyNames, sub_1_abrv);
				sub2 = makePostRequest(fromCurrencyNames, sub_2_abrv);
				sub3 = makePostRequest(fromCurrencyNames, sub_3_abrv);

			}
			if (currencyNamesArray.length == 4) {
				sub1 = makePostRequest(fromCurrencyNames, sub_1_abrv);
				sub2 = makePostRequest(fromCurrencyNames, sub_2_abrv);
				sub3 = makePostRequest(fromCurrencyNames, sub_3_abrv);
				sub4 = makePostRequest(fromCurrencyNames, sub_4_abrv);
			}
			if (currencyNamesArray.length == 5) {
				sub1 = makePostRequest(fromCurrencyNames, sub_1_abrv);
				sub2 = makePostRequest(fromCurrencyNames, sub_2_abrv);
				sub3 = makePostRequest(fromCurrencyNames, sub_3_abrv);
				sub4 = makePostRequest(fromCurrencyNames, sub_4_abrv);
				sub5 = makePostRequest(fromCurrencyNames, sub_5_abrv);
			}

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					a.cancel();

					if (!currencyNames.equals("")
							&& currencyNamesArray.length == 1) {
						sub_1_tv.setText(sub1);
						sub_1_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub1 + " " + sub_1_abrv);
						sub_1_currency_full.setText(fullName1);

						editor = getSharedPreferences(MY_PREFS_NAME,
								MODE_PRIVATE).edit();
						editor.putString("sub1_currencyRate", sub1 + "," + "1 "
								+ fromCurrencyNames + " = " + sub1 + " "
								+ sub_1_abrv + "," + fullName1);
						editor.commit();

					}
					if (currencyNamesArray.length == 2) {
						sub_1_tv.setText(sub1);
						sub_2_tv.setText(sub2);
						sub_1_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub1 + " " + sub_1_abrv);
						sub_2_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub2 + " " + sub_2_abrv);
						sub_1_currency_full.setText(fullName1);
						sub_2_currency_full.setText(fullName2);

						editor = getSharedPreferences(MY_PREFS_NAME,
								MODE_PRIVATE).edit();
						editor.putString("sub1_currencyRate", sub1 + "," + "1 "
								+ fromCurrencyNames + " = " + sub1 + " "
								+ sub_1_abrv + "," + fullName1);
						editor.putString("sub2_currencyRate", sub2 + "," + "1 "
								+ fromCurrencyNames + " = " + sub2 + " "
								+ sub_2_abrv + "," + fullName2);
						editor.commit();

					}
					if (currencyNamesArray.length == 3) {
						sub_1_tv.setText(sub1);
						sub_2_tv.setText(sub2);
						sub_3_tv.setText(sub3);
						sub_1_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub1 + " " + sub_1_abrv);
						sub_2_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub2 + " " + sub_2_abrv);
						sub_3_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub3 + " " + sub_3_abrv);
						sub_1_currency_full.setText(fullName1);
						sub_2_currency_full.setText(fullName2);
						sub_3_currency_full.setText(fullName3);

						editor = getSharedPreferences(MY_PREFS_NAME,
								MODE_PRIVATE).edit();
						editor.putString("sub1_currencyRate", sub1 + "," + "1 "
								+ fromCurrencyNames + " = " + sub1 + " "
								+ sub_1_abrv + "," + fullName1);
						editor.putString("sub2_currencyRate", sub2 + "," + "1 "
								+ fromCurrencyNames + " = " + sub2 + " "
								+ sub_2_abrv + "," + fullName2);
						editor.putString("sub3_currencyRate", sub3 + "," + "1 "
								+ fromCurrencyNames + " = " + sub3 + " "
								+ sub_3_abrv + "," + fullName3);
						editor.commit();
					}
					if (currencyNamesArray.length == 4) {
						sub_1_tv.setText(sub1);
						sub_2_tv.setText(sub2);
						sub_3_tv.setText(sub3);
						sub_4_tv.setText(sub4);
						sub_1_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub1 + " " + sub_1_abrv);
						sub_2_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub2 + " " + sub_2_abrv);
						sub_3_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub3 + " " + sub_3_abrv);
						sub_4_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub4 + " " + sub_4_abrv);

						sub_1_currency_full.setText(fullName1);
						sub_2_currency_full.setText(fullName2);
						sub_3_currency_full.setText(fullName3);
						sub_4_currency_full.setText(fullName4);
						editor = getSharedPreferences(MY_PREFS_NAME,
								MODE_PRIVATE).edit();
						editor.putString("sub1_currencyRate", sub1 + "," + "1 "
								+ fromCurrencyNames + " = " + sub1 + " "
								+ sub_1_abrv + "," + fullName1);
						editor.putString("sub2_currencyRate", sub2 + "," + "1 "
								+ fromCurrencyNames + " = " + sub2 + " "
								+ sub_2_abrv + "," + fullName2);
						editor.putString("sub3_currencyRate", sub3 + "," + "1 "
								+ fromCurrencyNames + " = " + sub3 + " "
								+ sub_3_abrv + "," + fullName3);
						editor.putString("sub4_currencyRate", sub4 + "," + "1 "
								+ fromCurrencyNames + " = " + sub4 + " "
								+ sub_4_abrv + "," + fullName4);
						editor.commit();

					}
					if (currencyNamesArray.length == 5) {
						sub_1_tv.setText(sub1);
						sub_2_tv.setText(sub2);
						sub_3_tv.setText(sub3);
						sub_4_tv.setText(sub4);
						sub_5_tv.setText(sub5);
						sub_1_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub1 + " " + sub_1_abrv);
						sub_2_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub2 + " " + sub_2_abrv);
						sub_3_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub3 + " " + sub_3_abrv);
						sub_4_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub4 + " " + sub_4_abrv);
						sub_5_currency.setText("1 " + fromCurrencyNames + " = "
								+ sub5 + " " + sub_5_abrv);

						sub_1_currency_full.setText(fullName1);
						sub_2_currency_full.setText(fullName2);
						sub_3_currency_full.setText(fullName3);
						sub_4_currency_full.setText(fullName4);
						sub_5_currency_full.setText(fullName5);

						editor = getSharedPreferences(MY_PREFS_NAME,
								MODE_PRIVATE).edit();
						editor.putString("sub1_currencyRate", sub1 + "," + "1 "
								+ fromCurrencyNames + " = " + sub1 + " "
								+ sub_1_abrv + "," + fullName1);
						editor.putString("sub2_currencyRate", sub2 + "," + "1 "
								+ fromCurrencyNames + " = " + sub2 + " "
								+ sub_2_abrv + "," + fullName2);
						editor.putString("sub3_currencyRate", sub3 + "," + "1 "
								+ fromCurrencyNames + " = " + sub3 + " "
								+ sub_3_abrv + "," + fullName3);
						editor.putString("sub4_currencyRate", sub4 + "," + "1 "
								+ fromCurrencyNames + " = " + sub4 + " "
								+ sub_4_abrv + "," + fullName4);
						editor.putString("sub5_currencyRate", sub5 + "," + "1 "
								+ fromCurrencyNames + " = " + sub5 + " "
								+ sub_5_abrv + "," + fullName5);
						editor.commit();
					}
					try {
						etContent.setText("1.00");
						if (!currencyNames.equals("")
								&& currencyNamesArray.length == 1) {
							sub_val_1 = Float.valueOf(sub1);
						}
						if (currencyNamesArray.length == 2) {
							sub_val_1 = Float.valueOf(sub1);
							sub_val_2 = Float.valueOf(sub2);
						}
						if (currencyNamesArray.length == 3) {
							sub_val_1 = Float.valueOf(sub1);
							sub_val_2 = Float.valueOf(sub2);
							sub_val_3 = Float.valueOf(sub3);
						}
						if (currencyNamesArray.length == 4) {
							sub_val_1 = Float.valueOf(sub1);
							sub_val_2 = Float.valueOf(sub2);
							sub_val_3 = Float.valueOf(sub3);
							sub_val_4 = Float.valueOf(sub4);
						}
						if (currencyNamesArray.length == 5) {
							sub_val_1 = Float.valueOf(sub1);
							sub_val_2 = Float.valueOf(sub2);
							sub_val_3 = Float.valueOf(sub3);
							sub_val_4 = Float.valueOf(sub4);
							sub_val_5 = Float.valueOf(sub5);
						}
					} catch (Exception e) {
						if (!currencyNames.equals("")
								&& currencyNamesArray.length == 1) {
							sub_1_tv.setText("Service Down");
						}
						if (currencyNamesArray.length == 2) {
							sub_1_tv.setText("Service Down");
							sub_2_tv.setText("Service Down");
						}
						if (currencyNamesArray.length == 3) {
							sub_1_tv.setText("Service Down");
							sub_2_tv.setText("Service Down");
							sub_3_tv.setText("Service Down");
						}
						if (currencyNamesArray.length == 4) {
							sub_1_tv.setText("Service Down");
							sub_2_tv.setText("Service Down");
							sub_3_tv.setText("Service Down");
							sub_4_tv.setText("Service Down");
						}
						if (currencyNamesArray.length == 5) {
							sub_1_tv.setText("Service Down");
							sub_2_tv.setText("Service Down");
							sub_3_tv.setText("Service Down");
							sub_4_tv.setText("Service Down");
							sub_5_tv.setText("Service Down");
						}
					}

				}
			});
			return null;
		}
	}

	public String getTimeUpdateValue() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // 2014/08/06 15:59:48
		return dateFormat.format(date);
	}

	public void subView1Gone() {
		sub_1.setVisibility(View.GONE);
		sub_1_tv.setVisibility(View.GONE);
		sub_1_delete.setVisibility(View.GONE);
		sub_1_image.setVisibility(View.GONE);
		sub_1_currency.setVisibility(View.GONE);
		sub_1_currency_full.setVisibility(View.GONE);
		sub_1_rl.setVisibility(View.GONE);
	}

	public void subView2Gone() {
		sub_2.setVisibility(View.GONE);
		sub_2_tv.setVisibility(View.GONE);
		sub_2_delete.setVisibility(View.GONE);
		sub_2_image.setVisibility(View.GONE);
		sub_2_currency.setVisibility(View.GONE);
		sub_2_currency_full.setVisibility(View.GONE);
		sub_2_rl.setVisibility(View.GONE);
	}

	public void subView3Gone() {
		sub_3.setVisibility(View.GONE);
		sub_3_tv.setVisibility(View.GONE);
		sub_3_delete.setVisibility(View.GONE);
		sub_3_image.setVisibility(View.GONE);
		sub_3_currency.setVisibility(View.GONE);
		sub_3_currency_full.setVisibility(View.GONE);
		sub_3_rl.setVisibility(View.GONE);
	}

	public void subView4Gone() {
		sub_4.setVisibility(View.GONE);
		sub_4_tv.setVisibility(View.GONE);
		sub_4_delete.setVisibility(View.GONE);
		sub_4_image.setVisibility(View.GONE);
		sub_4_currency.setVisibility(View.GONE);
		sub_4_currency_full.setVisibility(View.GONE);
		sub_4_rl.setVisibility(View.GONE);
	}

	public void subView5Gone() {
		sub_5.setVisibility(View.GONE);
		sub_5_tv.setVisibility(View.GONE);
		sub_5_delete.setVisibility(View.GONE);
		sub_5_image.setVisibility(View.GONE);
		sub_5_currency.setVisibility(View.GONE);
		sub_5_currency_full.setVisibility(View.GONE);
		sub_5_rl.setVisibility(View.GONE);

	}

	public void subView1Visible() {
		// sub_1.setVisibility(View.VISIBLE);
		sub_1_tv.setVisibility(View.VISIBLE);
		sub_1_delete.setVisibility(View.VISIBLE);
		sub_1_image.setVisibility(View.VISIBLE);
		sub_1_currency.setVisibility(View.VISIBLE);
		sub_1_currency_full.setVisibility(View.VISIBLE);
		sub_1_rl.setVisibility(View.VISIBLE);
	}

	public void subView2Visible() {
		// sub_2.setVisibility(View.VISIBLE);
		sub_2_tv.setVisibility(View.VISIBLE);
		sub_2_delete.setVisibility(View.VISIBLE);
		sub_2_image.setVisibility(View.VISIBLE);
		sub_2_currency.setVisibility(View.VISIBLE);
		sub_2_currency_full.setVisibility(View.VISIBLE);
		sub_2_rl.setVisibility(View.VISIBLE);
	}

	public void subView3Visible() {
		// sub_3.setVisibility(View.VISIBLE);
		sub_3_tv.setVisibility(View.VISIBLE);
		sub_3_delete.setVisibility(View.VISIBLE);
		sub_3_image.setVisibility(View.VISIBLE);
		sub_3_currency.setVisibility(View.VISIBLE);
		sub_3_currency_full.setVisibility(View.VISIBLE);
		sub_3_rl.setVisibility(View.VISIBLE);
	}

	public void subView4Visible() {
		// sub_4.setVisibility(View.VISIBLE);
		sub_4_tv.setVisibility(View.VISIBLE);
		sub_4_delete.setVisibility(View.VISIBLE);
		sub_4_image.setVisibility(View.VISIBLE);
		sub_4_currency.setVisibility(View.VISIBLE);
		sub_4_currency_full.setVisibility(View.VISIBLE);
		sub_4_rl.setVisibility(View.VISIBLE);
	}

	public void subView5Visible() {
		// sub_5.setVisibility(View.VISIBLE);
		sub_5_tv.setVisibility(View.VISIBLE);
		sub_5_delete.setVisibility(View.VISIBLE);
		sub_5_image.setVisibility(View.VISIBLE);
		sub_5_currency.setVisibility(View.VISIBLE);
		sub_5_currency_full.setVisibility(View.VISIBLE);
		sub_5_rl.setVisibility(View.VISIBLE);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public class CounterClass extends CountDownTimer {
		public CounterClass(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);

		}

		@Override
		public void onFinish() {
			// textViewTime.setText("Completed.");
			refreshButton.setEnabled(true);
			refreshTime.setText("00:59");
		}

		@SuppressLint("NewApi")
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		@Override
		public void onTick(long millisUntilFinished) {
			long millis = millisUntilFinished;
			String hms = String.format(
					"%02d:%02d",
					// TimeUnit.MILLISECONDS.toHours(millis),
					TimeUnit.MILLISECONDS.toMinutes(millis)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
									.toHours(millis)),
					TimeUnit.MILLISECONDS.toSeconds(millis)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes(millis)));
			refreshTime.setText(hms);

		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public void deleteAndSetImages() {
		currencyNamesArray = currencyNames.split(",");
		currencyFlagsArray = currencyFlags.split(",");
		if (currencyNamesArray.length == 1 && !currencyNames.equals("")) {
			addCurrencyImage.setImageResource(R.drawable.add_currency_5_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_0_5);
			addNewGuide.setVisibility(View.VISIBLE);
		}
		if (currencyNamesArray.length == 2) {
			addCurrencyImage.setImageResource(R.drawable.add_currency_4_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_1_5);
			addNewGuide.setVisibility(View.GONE);
		}
		if (currencyNamesArray.length == 3) {
			addCurrencyImage.setImageResource(R.drawable.add_currency_3_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_2_5);
			addNewGuide.setVisibility(View.GONE);
		}
		if (currencyNamesArray.length == 4) {
			addCurrencyImage.setImageResource(R.drawable.add_currency_2_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_3_5);
			addNewGuide.setVisibility(View.GONE);
		}
		if (currencyNamesArray.length == 5) {
			addCurrencyImage.setImageResource(R.drawable.add_currency_1_5);
			currencySlotFullImage.setImageResource(R.drawable.slot_full_4_5);
			addNewGuide.setVisibility(View.GONE);
		}
	}

	public void commitValuesAfterDelete() {
		editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
		editor.putString("currencyNames", currencyNames);
		editor.putString("flags", currencyFlags);
		editor.putString("currencyFullNames", currencyFullNames);
		editor.commit();

		currencyNames = prefs.getString("currencyNames", "");
		currencyFlags = prefs.getString("flags", "");
		currencyFullNames = prefs.getString("currencyFullNames", "");
		if (currencyNames.startsWith(",")) {
			currencyNames = currencyNames.substring(1);
		}
		if (currencyFlags.startsWith(",")) {
			currencyFlags = currencyFlags.substring(1);
		}

		if (currencyFullNames.startsWith(",")) {
			currencyFullNames = currencyFullNames.substring(1);
		}
		currencyNamesArray = currencyNames.split(",");
		currencyFlagsArray = currencyFlags.split(",");
		currencyFullNamesArray = currencyFullNames.split(",");

	}

	public void refreshWithPrefrences() {
		try {
			if (!currencyNames.equals("") && currencyNamesArray.length == 1) {
				String[] sub1_texts = prefs.getString("sub1_currencyRate", "")
						.split(",");
				sub_1_tv.setText(sub1_texts[0]);
				sub_1_currency.setText(sub1_texts[1]);
				sub_1_currency_full.setText(sub1_texts[2]);
			}
			if (currencyNamesArray.length == 2) {
				String[] sub1_texts = prefs.getString("sub1_currencyRate", "")
						.split(",");
				sub_1_tv.setText(sub1_texts[0]);
				sub_1_currency.setText(sub1_texts[1]);
				sub_1_currency_full.setText(sub1_texts[2]);

				String[] sub2_texts = prefs.getString("sub2_currencyRate", "")
						.split(",");
				sub_2_tv.setText(sub2_texts[0]);
				sub_2_currency.setText(sub2_texts[1]);
				sub_2_currency_full.setText(sub2_texts[2]);
			}
			if (currencyNamesArray.length == 3) {
				String[] sub1_texts = prefs.getString("sub1_currencyRate", "")
						.split(",");
				sub_1_tv.setText(sub1_texts[0]);
				sub_1_currency.setText(sub1_texts[1]);
				sub_1_currency_full.setText(sub1_texts[2]);

				String[] sub2_texts = prefs.getString("sub2_currencyRate", "")
						.split(",");
				sub_2_tv.setText(sub2_texts[0]);
				sub_2_currency.setText(sub2_texts[1]);
				sub_2_currency_full.setText(sub2_texts[2]);

				String[] sub3_texts = prefs.getString("sub3_currencyRate", "")
						.split(",");
				sub_3_tv.setText(sub3_texts[0]);
				sub_3_currency.setText(sub3_texts[1]);
				sub_3_currency_full.setText(sub3_texts[2]);
			}
			if (currencyNamesArray.length == 4) {
				String[] sub1_texts = prefs.getString("sub1_currencyRate", "")
						.split(",");
				sub_1_tv.setText(sub1_texts[0]);
				sub_1_currency.setText(sub1_texts[1]);
				sub_1_currency_full.setText(sub1_texts[2]);

				String[] sub2_texts = prefs.getString("sub2_currencyRate", "")
						.split(",");
				sub_2_tv.setText(sub2_texts[0]);
				sub_2_currency.setText(sub2_texts[1]);
				sub_2_currency_full.setText(sub2_texts[2]);

				String[] sub3_texts = prefs.getString("sub3_currencyRate", "")
						.split(",");
				sub_3_tv.setText(sub3_texts[0]);
				sub_3_currency.setText(sub3_texts[1]);
				sub_3_currency_full.setText(sub3_texts[2]);

				String[] sub4_texts = prefs.getString("sub4_currencyRate", "")
						.split(",");
				sub_4_tv.setText(sub4_texts[0]);
				sub_4_currency.setText(sub4_texts[1]);
				sub_4_currency_full.setText(sub4_texts[2]);
			}
			if (currencyNamesArray.length == 5) {
				String[] sub1_texts = prefs.getString("sub1_currencyRate", "")
						.split(",");
				sub_1_tv.setText(sub1_texts[0]);
				sub_1_currency.setText(sub1_texts[1]);
				sub_1_currency_full.setText(sub1_texts[2]);

				String[] sub2_texts = prefs.getString("sub2_currencyRate", "")
						.split(",");
				sub_2_tv.setText(sub2_texts[0]);
				sub_2_currency.setText(sub2_texts[1]);
				sub_2_currency_full.setText(sub2_texts[2]);

				String[] sub3_texts = prefs.getString("sub3_currencyRate", "")
						.split(",");
				sub_3_tv.setText(sub3_texts[0]);
				sub_3_currency.setText(sub3_texts[1]);
				sub_3_currency_full.setText(sub3_texts[2]);

				String[] sub4_texts = prefs.getString("sub4_currencyRate", "")
						.split(",");
				sub_4_tv.setText(sub4_texts[0]);
				sub_4_currency.setText(sub4_texts[1]);
				sub_4_currency_full.setText(sub4_texts[2]);
				String[] sub5_texts = prefs.getString("sub5_currencyRate", "")
						.split(",");
				sub_5_tv.setText(sub5_texts[0]);
				sub_5_currency.setText(sub5_texts[1]);
				sub_5_currency_full.setText(sub5_texts[2]);
			}
		} catch (Exception e) {
			if (isNetworkAvailable()) {
				new RefreshWithCurrency().execute();
			} else {
				sub_1_tv.setText("No Connection");
				sub_2_tv.setText("No Connection");
				sub_3_tv.setText("No Connection");
				sub_4_tv.setText("No Connection");
				sub_5_tv.setText("No Connection");
			}
		}
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

	public void afterTextChanged(Editable view) {
		String s = null;
		try {
			// The comma in the format specifier does the trick
			s = String.format("%,d", Long.parseLong(view.toString()));
		} catch (NumberFormatException e) {
		}
		// Set s back to the view after temporarily removing the text change
		// listener
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Purchases.destroy();
		ConstantAds.displayInterstitial();
	}

}
