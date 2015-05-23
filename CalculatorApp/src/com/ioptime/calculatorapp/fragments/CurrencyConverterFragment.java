package com.ioptime.calculatorapp.fragments;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
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

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.ioptime.calculatorapp.ConstantAds;
import com.ioptime.calculatorapp.CurrencyFlags;
import com.ioptime.calculatorapp.CurrencyListArray;
import com.ioptime.calculatorapp.Purchases;
import com.ioptime.calculatorapp.WebserviceUtil;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class CurrencyConverterFragment extends SherlockFragment implements Upgradeable {

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

	RelativeLayout contentMain;
	LinearLayout rl_sub_list;
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
	
	List<CurrencyFlags> worldpopulationlist = null;
	
	CounterClass timer;

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.currency_converters, container, false);
		super.onCreate(savedInstanceState);
		ctx = container.getContext();
		Purchases.initiatePurchase(MainActivityA.getInstance());
		
		bundle = savedInstanceState;
		
		mainRelativeLayout = (RelativeLayout) view.findViewById(R.id.main_relative_layout);
		mainRelativeLayout.setOnDragListener(new MyDragListener());
		
		prefs = container.getContext().getSharedPreferences(MainActivityA.MY_PREFS_NAME, container.getContext().MODE_PRIVATE);
		if (!prefs.getString("isPaymentMade", "").equals("true")) {
			ConstantAds.loadInterstitialAd(container.getContext(),
					"top");
		}
		currencyNames = prefs.getString("currencyNames", "");
		currencyFlags = prefs.getString("flags", "");
		currencyFullNames = prefs.getString("currencyFullNames", "");
		fromCurrencyNames = prefs.getString("fromCurrencyNames", "USD");
		fromCurrencyFlags = prefs.getString("fromCurrencyFlags", String.valueOf(R.drawable.usd));
		fromCurrencyNamesFull = prefs.getString("fromCurrencyNamesFull",
				"USD - USA");
		timer = new CounterClass(60000, 1000);
		// timer.start();
		gestureDetector = new GestureDetector(container.getContext(), new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};
		currencyListArray = new CurrencyListArray();
		anim = AnimationUtils.loadAnimation(container.getContext(), R.drawable.scale);
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

		anim_back = AnimationUtils.loadAnimation(container.getContext(), R.drawable.scale_back);
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

		rl_upgrade = (RelativeLayout) view.findViewById(R.id.rl_upgrade);
		rl_upgrade_parent = (RelativeLayout) view.findViewById(R.id.rl_upgrade_parent);
		upgrade_close = (ImageView) view.findViewById(R.id.upgrade_close);
		upgrade_bg = (ImageView) view.findViewById(R.id.upgrade_bg);
		upgrade_text = (ImageView) view.findViewById(R.id.upgrade_text);
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
						.makePurchase(MainActivityA.getInstance());

//				if (checkvar == true) {
//					editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//							.edit();
//					editor.putString("isPaymentMade", "true");
//					editor.commit();
//					editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//							.edit();
//					editor.putString("isPaymentMade", "false");
//					editor.commit();
//				}
//				rl_upgrade_parent.startAnimation(anim_back);
//				upgradePopUp = 0;
			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;
		refreshButton = (ImageView) view.findViewById(R.id.refresh_button);
		funtionPad = (LinearLayout) view.findViewById(R.id.functionPad);
		sideNavigationView = (SideNavigationView) view.findViewById(R.id.side_navigation_view);
		refreshTime = (TextView) view.findViewById(R.id.next_refresh_time);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		circleBtn = (ImageView) view.findViewById(R.id.circle_button_check);
		etContent = (EditText) view.findViewById(R.id.tvContent);
		etContent.setOnDragListener(new MyDragListener());
		currenyContentFull = (TextView) view.findViewById(R.id.content_currency_full);
		fromImage = (ImageView) view.findViewById(R.id.imgContent);
		sub_1_tv = (TextView) view.findViewById(R.id.sub_1_tv);
		sub_2_tv = (TextView) view.findViewById(R.id.sub_2_tv);
		sub_3_tv = (TextView) view.findViewById(R.id.sub_3_tv);
		sub_4_tv = (TextView) view.findViewById(R.id.sub_4_tv);
		sub_5_tv = (TextView) view.findViewById(R.id.sub_5_tv);
		sub_1_currency = (TextView) view.findViewById(R.id.sub_1_currency);
		sub_2_currency = (TextView) view.findViewById(R.id.sub_2_currency);
		sub_3_currency = (TextView) view.findViewById(R.id.sub_3_currency);
		sub_4_currency = (TextView) view.findViewById(R.id.sub_4_currency);
		sub_5_currency = (TextView) view.findViewById(R.id.sub_5_currency);
		sub_1_currency_full = (TextView) view.findViewById(R.id.sub_1_currency_full);
		sub_2_currency_full = (TextView) view.findViewById(R.id.sub_2_currency_full);
		sub_3_currency_full = (TextView) view.findViewById(R.id.sub_3_currency_full);
		sub_4_currency_full = (TextView) view.findViewById(R.id.sub_4_currency_full);
		sub_5_currency_full = (TextView) view.findViewById(R.id.sub_5_currency_full);
		sub_1_delete = (ImageView) view.findViewById(R.id.sub_1_delete_button);
		sub_2_delete = (ImageView) view.findViewById(R.id.sub_2_delete_button);
		sub_3_delete = (ImageView) view.findViewById(R.id.sub_3_delete_button);
		sub_4_delete = (ImageView) view.findViewById(R.id.sub_4_delete_button);
		sub_5_delete = (ImageView) view.findViewById(R.id.sub_5_delete_button);
		sub_1_image = (ImageView) view.findViewById(R.id.sub_1_img);
		sub_2_image = (ImageView) view.findViewById(R.id.sub_2_img);
		sub_3_image = (ImageView) view.findViewById(R.id.sub_3_img);
		sub_4_image = (ImageView) view.findViewById(R.id.sub_4_img);
		sub_5_image = (ImageView) view.findViewById(R.id.sub_5_img);
		sub_1 = (ImageView) view.findViewById(R.id.sub_1);
		sub_2 = (ImageView) view.findViewById(R.id.sub_2);
		sub_3 = (ImageView) view.findViewById(R.id.sub_3);
		sub_4 = (ImageView) view.findViewById(R.id.sub_4);
		sub_5 = (ImageView) view.findViewById(R.id.sub_5);
		contentMain = (RelativeLayout) view.findViewById(R.id.rl_content_main);
		contentMain.setOnDragListener(new MyDragListener());
		rl_sub_list = (LinearLayout) view.findViewById(R.id.rl_sub_list);
		sub_1_rl = (RelativeLayout) view.findViewById(R.id.rl_sub_1);
		sub_1_rl.setOnLongClickListener(new MyLongClickListener());
		sub_1_rl.setOnDragListener(new MyDragListener());
		sub_1_rl.setOnClickListener(new MyClickListener());
		sub_1_rl.setTag("0");
		sub_2_rl = (RelativeLayout) view.findViewById(R.id.rl_sub_2);
		sub_2_rl.setOnLongClickListener(new MyLongClickListener());
		sub_2_rl.setOnDragListener(new MyDragListener());
		sub_2_rl.setOnClickListener(new MyClickListener());
		sub_2_rl.setTag("1");
		sub_3_rl = (RelativeLayout) view.findViewById(R.id.rl_sub_3);
		sub_3_rl.setOnLongClickListener(new MyLongClickListener());
		sub_3_rl.setOnDragListener(new MyDragListener());
		sub_3_rl.setOnClickListener(new MyClickListener());
		sub_3_rl.setTag("2");
		sub_4_rl = (RelativeLayout) view.findViewById(R.id.rl_sub_4);
		sub_4_rl.setOnLongClickListener(new MyLongClickListener());
		sub_4_rl.setOnDragListener(new MyDragListener());
		sub_4_rl.setOnClickListener(new MyClickListener());
		sub_4_rl.setTag("3");
		sub_5_rl = (RelativeLayout) view.findViewById(R.id.rl_sub_5);
		sub_5_rl.setOnLongClickListener(new MyLongClickListener());
		sub_5_rl.setOnDragListener(new MyDragListener());
		sub_5_rl.setOnClickListener(new MyClickListener());
		sub_5_rl.setTag("4");
		addCurrencyImage = (ImageView) view.findViewById(R.id.add_currency_image);
		currencySlotFullImage = (ImageView) view.findViewById(R.id.slot_full_image);
		addNewGuide = (ImageView) view.findViewById(R.id.add_new_guide);
		if (!fromCurrencyFlags.equals("")) {
			fromImage.setImageResource(Integer.valueOf(fromCurrencyFlags));
			fromImage.setTag(Integer.valueOf(fromCurrencyFlags));
		} else {
			fromImage.setImageResource(R.drawable.usd_united_states_dollar);
			fromImage.setTag(R.drawable.usd_united_states_dollar);
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
		updateGuiValue();
		circleBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showSelectFormCountryFragment();
			}
		});

		addCurrencyImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				currencyNamesArray = currencyNames.split(",");
				if (currencyFlagsArray.length == 5) {
					Toast.makeText(container.getContext(),
							"Currency Slot full", Toast.LENGTH_LONG).show();
				} else {
					MainActivityA.getInstance().showSelectCountriesListFragment();
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
				editor = container.getContext().getSharedPreferences(MainActivityA.MY_PREFS_NAME, container.getContext().MODE_PRIVATE)
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
				editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME, container.getContext().MODE_PRIVATE)
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
				editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME, container.getContext().MODE_PRIVATE)
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
				editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME, container.getContext().MODE_PRIVATE)
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
				editor = container.getContext().getSharedPreferences(MainActivityA.MY_PREFS_NAME, container.getContext().MODE_PRIVATE)
						.edit();
				editor.putString("sub5_currencyRate", "");
				editor.commit();
			}
		});
		etContent.setText("1.00");
		lastUpdate = (TextView) view.findViewById(R.id.your_last_update_date);
		lastUpdate.setText(getTimeUpdateValue());

		refreshButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				refresh();
			}
		});

		
		if (bundle != null && bundle.getString("refresh") != null) {
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

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) container.getContext().getSystemService(
				Context.VIBRATOR_SERVICE);

		etContent.addTextChangedListener(watch);
		
		
		return view;
	}

	private void refresh() {
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
						Toast.makeText(ctx, "Service Down",
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
					Toast.makeText(ctx, "Wrong Input",
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

	private void updateGuiValue() {
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
	}
	
	class RefreshWithCurrency extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Log.d("currencyNamesarray", currencyNamesArray.length + " | "
					+ sub_1_abrv);

			if (!currencyNames.equals("") && currencyNamesArray.length == 1) {
				sub1 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_1_abrv);

			}
			if (currencyNamesArray.length == 2) {
				sub1 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_1_abrv);
				sub2 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_2_abrv);
			}
			if (currencyNamesArray.length == 3) {
				sub1 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_1_abrv);
				sub2 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_2_abrv);
				sub3 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_3_abrv);
			}
			if (currencyNamesArray.length == 4) {
				sub1 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_1_abrv);
				sub2 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_2_abrv);
				sub3 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_3_abrv);
				sub4 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_4_abrv);
			}
			if (currencyNamesArray.length == 5) {
				sub1 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_1_abrv);
				sub2 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_2_abrv);
				sub3 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_3_abrv);
				sub4 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_4_abrv);
				sub5 = WebserviceUtil.apiCurrencyConverter(fromCurrencyNames, sub_5_abrv);
			}

			MainActivityA.getInstance().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					a.cancel();

					syncGui();

				}
			});
			return null;
		}
	}

	private void syncGui() {
		syncGuiWithoutName();
		if (!currencyNames.equals("")
				&& currencyNamesArray.length == 1) {
			sub_1_tv.setText(sub1);

			editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
					ctx.MODE_PRIVATE).edit();
			editor.putString("sub1_currencyRate", sub1 + "," + "1 "
					+ fromCurrencyNames + " = " + sub1 + " "
					+ sub_1_abrv + "," + fullName1);
			editor.commit();

		}
		if (currencyNamesArray.length == 2) {
			sub_1_tv.setText(sub1);
			sub_2_tv.setText(sub2);

			editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
					ctx.MODE_PRIVATE).edit();
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

			editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
					ctx.MODE_PRIVATE).edit();
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
			
			editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
					ctx.MODE_PRIVATE).edit();
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

			editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
					ctx.MODE_PRIVATE).edit();
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
	
	private void syncGuiWithoutName() {
		if (!currencyNames.equals("")
				&& currencyNamesArray.length == 1) {
			sub_1_image.setImageResource(Integer.parseInt(sub_1_flagRes));
			sub_1_currency.setText("1 " + fromCurrencyNames + " = "
					+ sub1 + " " + sub_1_abrv);
			sub_1_currency_full.setText(fullName1);

		}
		if (currencyNamesArray.length == 2) {
			sub_1_image.setImageResource(Integer.parseInt(sub_1_flagRes));
			sub_2_image.setImageResource(Integer.parseInt(sub_2_flagRes));
			sub_1_currency.setText("1 " + fromCurrencyNames + " = "
					+ sub1 + " " + sub_1_abrv);
			sub_2_currency.setText("1 " + fromCurrencyNames + " = "
					+ sub2 + " " + sub_2_abrv);
			sub_1_currency_full.setText(fullName1);
			sub_2_currency_full.setText(fullName2);

		}
		if (currencyNamesArray.length == 3) {
			sub_1_image.setImageResource(Integer.parseInt(sub_1_flagRes));
			sub_2_image.setImageResource(Integer.parseInt(sub_2_flagRes));
			sub_3_image.setImageResource(Integer.parseInt(sub_3_flagRes));
			sub_1_currency.setText("1 " + fromCurrencyNames + " = "
					+ sub1 + " " + sub_1_abrv);
			sub_2_currency.setText("1 " + fromCurrencyNames + " = "
					+ sub2 + " " + sub_2_abrv);
			sub_3_currency.setText("1 " + fromCurrencyNames + " = "
					+ sub3 + " " + sub_3_abrv);
			sub_1_currency_full.setText(fullName1);
			sub_2_currency_full.setText(fullName2);
			sub_3_currency_full.setText(fullName3);

		}
		if (currencyNamesArray.length == 4) {
			sub_1_image.setImageResource(Integer.parseInt(sub_1_flagRes));
			sub_2_image.setImageResource(Integer.parseInt(sub_2_flagRes));
			sub_3_image.setImageResource(Integer.parseInt(sub_3_flagRes));
			sub_4_image.setImageResource(Integer.parseInt(sub_4_flagRes));
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

		}
		if (currencyNamesArray.length == 5) {
			sub_1_image.setImageResource(Integer.parseInt(sub_1_flagRes));
			sub_2_image.setImageResource(Integer.parseInt(sub_2_flagRes));
			sub_3_image.setImageResource(Integer.parseInt(sub_3_flagRes));
			sub_4_image.setImageResource(Integer.parseInt(sub_4_flagRes));
			sub_5_image.setImageResource(Integer.parseInt(sub_5_flagRes));
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

		}
		
	}
	
	private class MyClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switchMainContent(v);
		}
		
	}
	
	private final class MyLongClickListener implements OnLongClickListener {

	    // called when the item is long-clicked
		@Override
		public boolean onLongClick(View view) {
		
			// create it from the object's tag
			ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());

	        String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
	        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
	        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	   
	        view.startDrag( data, //data to be dragged
	        				shadowBuilder, //drag shadow
	        				view, //local data about the drag and drop operation
	        				0   //no needed flags
	        			  );
	        
	        
	        view.setVisibility(View.INVISIBLE);
	        return true;
		}	
	}

	private String getContentCurrencyFullString(View view) {
		  return currencyFullNamesArray[Integer.parseInt((String)view.getTag())];
	}
	
	private String getCurrencyFlagsString(View view) {
		  return currencyFlagsArray[Integer.parseInt((String)view.getTag())];
	}
	
	private String getCurrencyNamesString(View view) {
		  return currencyNamesArray[Integer.parseInt((String)view.getTag())];
	}
	
	private void switchMainContent(View view) {
		String tempFromCurrencyNames = fromCurrencyNames;
		  String tempFromCurrencyFlags = fromCurrencyFlags;
		  String tempFromCurrencyNamesFull = fromCurrencyNamesFull;
		  
		  fromCurrencyNames = getCurrencyNamesString(view);
		  fromCurrencyFlags = getCurrencyFlagsString(view);
		  fromCurrencyNamesFull	= getContentCurrencyFullString(view);
		  
		  int index = Integer.parseInt((String)view.getTag());
		  currencyNamesArray[index] = tempFromCurrencyNames;
		  currencyFlagsArray[index] = tempFromCurrencyFlags;
		  currencyFullNamesArray[index] = tempFromCurrencyNamesFull;
		  syncConverterList();
		  
		  updateGuiValue();
		  
		  TextView nameSub = (TextView) view.findViewWithTag("subName");
		  nameSub.setText(tempFromCurrencyNames);
		  ImageView imgSub = (ImageView) view.findViewWithTag("subImg");
		  imgSub.setImageResource(Integer.parseInt(tempFromCurrencyFlags));
		  TextView fullSub = (TextView) view.findViewWithTag("subFull");
		  fullSub.setText(tempFromCurrencyNamesFull);
		  
		  ImageView imgContent = (ImageView) contentMain.findViewById(R.id.imgContent);
		  imgContent.setImageResource(Integer.parseInt(fromCurrencyFlags));
		  TextView fullContent = (TextView) contentMain.findViewById(R.id.content_currency_full);
		  fullContent.setText(fromCurrencyNamesFull);
		  
		  currencyFullNamesArray.getClass();
		  editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
					ctx.MODE_PRIVATE).edit();
		  editor.putString("fromCurrencyNames",
				  fromCurrencyNames);
		  editor.putString("fromCurrencyFlags",
				  fromCurrencyFlags);
		  editor.putString("fromCurrencyNamesFull",
				  fromCurrencyNamesFull);
		  editor.commit();
		  
		  refresh();
	}
	
	public class MyDragListener implements OnDragListener {
		Drawable normalShape = getResources().getDrawable(R.drawable.cc_content_bg_copyy);
		Drawable targetShape = getResources().getDrawable(R.drawable.target_shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
	  
			// Handles each of the expected events
		    switch (event.getAction()) {
		    
		    //signal for the start of a drag and drop operation.
		    case DragEvent.ACTION_DRAG_STARTED:
		        // do nothing
		        break;
		        
		    //the drag point has entered the bounding box of the View
		    case DragEvent.ACTION_DRAG_ENTERED:
		    	if (v != etContent) {
			    	if (v == sub_1_rl || v == sub_2_rl || v == sub_3_rl
			    			|| v == sub_4_rl || v == sub_5_rl || v == contentMain) {
			    		v.setBackground(targetShape);	//change the shape of the view
			    	}
		    	}
		        break;
		        
		    //the user has moved the drag shadow outside the bounding box of the View
		    case DragEvent.ACTION_DRAG_EXITED:
		    	if (v != etContent) {
			    	if (v == sub_1_rl || v == sub_2_rl || v == sub_3_rl
	    					|| v == sub_4_rl || v == sub_5_rl || v == contentMain) {
			    		v.setBackground(normalShape);	//change the shape of the view back to normal
			    	}
		    	}
		        break;
		        
		    //drag shadow has been released,the drag point is within the bounding box of the View
		    case DragEvent.ACTION_DROP:
		        // if the view is the bottomlinear, we accept the drag item
		    	  View view = (View) event.getLocalState();
		    	  int viewDraggingindex = Integer.parseInt((String)view.getTag());
		    	  if(v == contentMain || v == etContent) {
		    		  
		    		  switchMainContent(view);
		    		  
		    		  contentMain.setBackground(normalShape);
		    		  view.setVisibility(View.VISIBLE);
		    	  } else if (v == rl_sub_list.getChildAt(0)) {
		    		  switchPlace(viewDraggingindex, 0);
		    		  switchCurrencyTv(view, v);
		    		  syncGuiWithoutName();
		    		  view.setVisibility(View.VISIBLE);
		    	  } else if (v == rl_sub_list.getChildAt(1)) {
		    		  switchPlace(viewDraggingindex, 1);
		    		  switchCurrencyTv(view, v);
		    		  syncGuiWithoutName();
		    		  view.setVisibility(View.VISIBLE);
		    	  } else if (v == rl_sub_list.getChildAt(2)) {
		    		  switchPlace(viewDraggingindex, 2);
		    		  switchCurrencyTv(view, v);
		    		  syncGuiWithoutName();
		    		  view.setVisibility(View.VISIBLE);
		    	  } else if (v == rl_sub_list.getChildAt(3)) {
		    		  switchPlace(viewDraggingindex, 3);
		    		  switchCurrencyTv(view, v);
		    		  syncGuiWithoutName();
		    		  view.setVisibility(View.VISIBLE);
		    	  } else if (v == rl_sub_list.getChildAt(4)) {
		    		  switchPlace(viewDraggingindex, 4);
		    		  switchCurrencyTv(view, v);
		    		  syncGuiWithoutName();
		    		  view.setVisibility(View.VISIBLE);
		    	  } else {
		    		  view.setVisibility(View.VISIBLE);
		    		  break;
		    	   }
		    	  break;
		    	  
		    //the drag and drop operation has concluded.
		    case DragEvent.ACTION_DRAG_ENDED:
		    	if (v == sub_1_rl || v == sub_2_rl || v == sub_3_rl
    					|| v == sub_4_rl || v == sub_5_rl || v == contentMain) {
		    		v.setBackground(normalShape);	//go back to normal shape
		    	}
		    default:
		        break;
		    }
		    
		    
		    return true;
		}
	}
	
	private void switchCurrencyTv(View view1, View view2) {
		TextView textView1 = (TextView) view1.findViewWithTag("subName");
		TextView textView2 = (TextView) view2.findViewWithTag("subName");
		String temp = textView1.getText().toString();
		textView1.setText(textView2.getText());
		textView2.setText(temp);
	}
	
	private void switchPlace(int pos1, int pos2) {
		String temp = currencyNamesArray[pos1];
		currencyNamesArray[pos1] = currencyNamesArray[pos2];
		currencyNamesArray[pos2] = temp;
		
		temp = currencyFlagsArray[pos1];
		currencyFlagsArray[pos1] = currencyFlagsArray[pos2];
		currencyFlagsArray[pos2] = temp;
		
		temp = currencyFullNamesArray[pos1];
		currencyFullNamesArray[pos1] = currencyFullNamesArray[pos2];
		currencyFullNamesArray[pos2] = temp;
		
		syncConverterList();
		
		updateGuiValue();

	}
	
	public void syncConverterList() {
		StringBuilder currencyNamesBuilder = new StringBuilder();
		StringBuilder currencyFlagsBuilder = new StringBuilder();
		StringBuilder currencyFullNamesBuilder = new StringBuilder();
		for (int index = 0; index < currencyNamesArray.length; index++) {
			currencyNamesBuilder.append(currencyNamesArray[index]);
			currencyNamesBuilder.append(",");
			
			currencyFlagsBuilder.append(currencyFlagsArray[index]);
			currencyFlagsBuilder.append(",");
			
			currencyFullNamesBuilder.append(currencyFullNamesArray[index]);
			currencyFullNamesBuilder.append(",");
		}
		if (currencyNamesArray.length > 0) {
			currencyNamesBuilder.deleteCharAt(currencyNamesBuilder.length() - 1);
			currencyFlagsBuilder.deleteCharAt(currencyFlagsBuilder.length() - 1);
			currencyFullNamesBuilder.deleteCharAt(currencyFullNamesBuilder.length() - 1);
		}
		
		editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME,
				ctx.MODE_PRIVATE).edit();
		editor.putString("currencyNames", currencyNamesBuilder.toString());
		editor.putString("flags", currencyFlagsBuilder.toString());
		editor.putString("currencyFullNames",
				currencyFullNamesBuilder.toString());
		editor.commit();
		
		currencyNames = currencyNamesBuilder.toString();
		currencyFlags = currencyFlagsBuilder.toString();
		currencyFullNames = currencyFullNamesBuilder.toString();
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
		ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
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
		editor = ctx.getSharedPreferences(MainActivityA.MY_PREFS_NAME, ctx.MODE_PRIVATE).edit();
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
	public void showUpgrade() {
		rl_upgrade_parent.startAnimation(anim);
		upgradePopUp=1;
	}

	@Override
	public int getUpgradePopUp() {
		return upgradePopUp;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Purchases.destroy();
		ConstantAds.displayInterstitial();
	}
}
