package com.ioptime.calculatorapp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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

public class UnitConverterLength extends SherlockActivity implements
		OnClickListener, ISideNavigationCallback {
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;
	LinearLayout ll_meters;
	LinearLayout ll_feet;
	LinearLayout ll_yards;
	LinearLayout ll_inches;
	LinearLayout ll_kilometers;
	LinearLayout ll_miles;
	LinearLayout ll_centimeters;
	LinearLayout ll_milimeters;
	LinearLayout ll_microns;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_meters;
	ImageView ca_feet;
	ImageView ca_yards;
	ImageView ca_inches;
	ImageView ca_kilometers;
	ImageView ca_miles;
	ImageView ca_centimeters;
	ImageView ca_milimeters;
	ImageView ca_microns;
	ImageView ca_nanometers;
	ImageView ca_lightyears;

	TextView conversions_meters;
	TextView conversions_feet;
	TextView conversions_yards;
	TextView conversions_inches;
	TextView conversions_kilometers;
	TextView conversions_miles;
	TextView conversions_centimeters;
	TextView conversions_milimeters;
	TextView conversions_microns;
	TextView conversions_nanometers;
	TextView conversions_lightyears;

	TextView symbols_meters;
	TextView symbols_feet;
	TextView symbols_yards;
	TextView symbols_inches;
	TextView symbols_kilometers;
	TextView symbols_miles;
	TextView symbols_centimeters;
	TextView symbols_milimeters;
	TextView symbols_microns;
	TextView symbols_nanometers;
	TextView symbols_lightyears;

	String value_meters;
	String value_feet;
	String value_yards;
	String value_inches;
	String value_kilometers;
	String value_miles;
	String value_centimeters;
	String value_milimeters;
	String value_microns;
	String value_nanometers;
	String value_lightyears;

	TextView unit_name_meters;
	TextView unit_name_feet;
	TextView unit_name_yards;
	TextView unit_name_inches;
	TextView unit_name_kilometers;
	TextView unit_name_miles;
	TextView unit_name_centimeters;
	TextView unit_name_milimeters;
	TextView unit_name_microns;
	TextView unit_name_nanometers;
	TextView unit_name_lightyears;

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
	
	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp=0;
	Animation anim;
	Animation anim_back;
	int slider = 1;
	RelativeLayout rl_slider;
	RelativeLayout rl_slider_parent;
	RelativeLayout.LayoutParams params;
	ImageView uc_slider;
	ImageView uc_slider2;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.unit_converter_length);
		gestureDetector = new GestureDetector(this, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};
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
				upgradePopUp=0;
			}
		});
		upgrade_bg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp=0;
			}
		});
		upgrade_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp=0;
		funtionPad = (LinearLayout) findViewById(R.id.functionPad);
		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		menuIcon = (ImageView) findViewById(R.id.menuicon);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		uc_edittext = (EditText) findViewById(R.id.uc_edittext);
		ll_centimeters = (LinearLayout) findViewById(R.id.centimeters_ll);
		ll_feet = (LinearLayout) findViewById(R.id.feet_ll);
		ll_inches = (LinearLayout) findViewById(R.id.inches_ll);
		ll_kilometers = (LinearLayout) findViewById(R.id.kilometers_ll);
		ll_lightyears = (LinearLayout) findViewById(R.id.lightyears_ll);
		ll_meters = (LinearLayout) findViewById(R.id.meters_ll);
		ll_microns = (LinearLayout) findViewById(R.id.microns_ll);
		ll_miles = (LinearLayout) findViewById(R.id.miles_ll);
		ll_milimeters = (LinearLayout) findViewById(R.id.milimeters_ll);
		ll_nanometers = (LinearLayout) findViewById(R.id.nanometers_ll);
		ll_yards = (LinearLayout) findViewById(R.id.yards_ll);

		ca_centimeters = (ImageView) findViewById(R.id.centimeters_conversion_arrow);
		ca_feet = (ImageView) findViewById(R.id.feet_conversion_arrow);
		ca_inches = (ImageView) findViewById(R.id.inches_conversion_arrow);
		ca_kilometers = (ImageView) findViewById(R.id.kilometers_conversion_arrow);
		ca_lightyears = (ImageView) findViewById(R.id.lightyears_conversion_arrow);
		ca_meters = (ImageView) findViewById(R.id.meters_conversion_arrow);
		ca_microns = (ImageView) findViewById(R.id.microns_conversion_arrow);
		ca_miles = (ImageView) findViewById(R.id.miles_conversion_arrow);
		ca_milimeters = (ImageView) findViewById(R.id.milimeters_conversion_arrow);
		ca_nanometers = (ImageView) findViewById(R.id.nanometers_conversion_arrow);
		ca_yards = (ImageView) findViewById(R.id.yards_conversion_arrow);

		conversions_centimeters = (TextView) findViewById(R.id.centimeters_conversions);
		conversions_feet = (TextView) findViewById(R.id.feet_conversions);
		conversions_inches = (TextView) findViewById(R.id.inches_conversions);
		conversions_kilometers = (TextView) findViewById(R.id.kilometers_conversions);
		conversions_lightyears = (TextView) findViewById(R.id.lightyears_conversions);
		conversions_meters = (TextView) findViewById(R.id.meters_conversions);
		conversions_microns = (TextView) findViewById(R.id.microns_conversions);
		conversions_miles = (TextView) findViewById(R.id.miles_conversions);
		conversions_milimeters = (TextView) findViewById(R.id.milimeters_conversions);
		conversions_nanometers = (TextView) findViewById(R.id.nanometers_conversions);
		conversions_yards = (TextView) findViewById(R.id.yards_conversions);

		symbols_centimeters = (TextView) findViewById(R.id.centimeters_symbols);
		symbols_feet = (TextView) findViewById(R.id.feet_symbols);
		symbols_inches = (TextView) findViewById(R.id.inches_symbols);
		symbols_kilometers = (TextView) findViewById(R.id.kilometers_symbols);
		symbols_lightyears = (TextView) findViewById(R.id.lightyears_symbols);
		symbols_meters = (TextView) findViewById(R.id.meters_symbols);
		symbols_microns = (TextView) findViewById(R.id.microns_symbols);
		symbols_miles = (TextView) findViewById(R.id.miles_symbols);
		symbols_milimeters = (TextView) findViewById(R.id.milimeters_symbols);
		symbols_nanometers = (TextView) findViewById(R.id.nanometers_symbols);
		symbols_yards = (TextView) findViewById(R.id.yards_symbols);

		unit_name_centimeters = (TextView) findViewById(R.id.centimeters_unit);
		unit_name_feet = (TextView) findViewById(R.id.feet_unit);
		unit_name_inches = (TextView) findViewById(R.id.inches_unit);
		unit_name_kilometers = (TextView) findViewById(R.id.kilometers_unit);
		unit_name_lightyears = (TextView) findViewById(R.id.lightyears_unit);
		unit_name_meters = (TextView) findViewById(R.id.meters_unit);
		unit_name_microns = (TextView) findViewById(R.id.microns_unit);
		unit_name_miles = (TextView) findViewById(R.id.miles_unit);
		unit_name_milimeters = (TextView) findViewById(R.id.milimeters_unit);
		unit_name_nanometers = (TextView) findViewById(R.id.nanometers_unit);
		unit_name_yards = (TextView) findViewById(R.id.yards_unit);
		ImageView ucBullets = (ImageView) findViewById(R.id.uc_length_bullets);

		ImageView nextScreen = (ImageView) findViewById(R.id.uc_right_arrow_dark);
		ImageView nextScreenValue = (ImageView) findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) findViewById(R.id.uc_length_prev_heading);
		ucBullets.setImageResource(R.drawable.uc_length_bullets);
		prevScreenValue.setVisibility(View.GONE);

		ImageView next_empty_view = (ImageView) findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view = (ImageView) findViewById(R.id.uc_empty_view_prev);

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
		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
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

		ll_centimeters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("cm");
				CentimetersToOther();
				// storeValueToString();

			}
		});
		ll_feet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				FeetToOther();
				// storeValueToString();

			}
		});
		ll_inches.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				InchesToOther();
				// storeValueToString();

			}
		});
		ll_kilometers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				kmToOther();
				// storeValueToString();

			}
		});
		ll_lightyears.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ly");
				lyToOther();
				// storeValueToString();

			}
		});
		ll_meters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				metersToOther();
				// storeValueToString();

			}
		});
		ll_microns.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("um");
				micronsToOther();
				// storeValueToString();

			}
		});
		ll_miles.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				milesToOther();
				// storeValueToString();

			}
		});
		ll_milimeters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mm");
				milimetersToOther();
				// storeValueToString();
			}
		});
		ll_nanometers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("nm");
				nanometersToOther();
				// storeValueToString();

			}
		});
		ll_yards.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				yardsToOther();
				// storeValueToString();

			}
		});

		next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.startActivity(UnitConverterLength.this, new Intent(
						getApplicationContext(), UnitConverterTemp.class));
				finish();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.startActivity(UnitConverterLength.this, new Intent(
						getApplicationContext(), UnitConverterTemp.class));
				finish();
			}
		});

		final ImageView uc_slider_area = (ImageView) findViewById(R.id.uc_slider_area);
		final ImageView uc_slider_datasize = (ImageView) findViewById(R.id.uc_slider_datasize);
		final ImageView uc_slider_energy = (ImageView) findViewById(R.id.uc_slider_energy);
		final ImageView uc_slider_force = (ImageView) findViewById(R.id.uc_slider_force);
		final ImageView uc_slider_length = (ImageView) findViewById(R.id.uc_slider_length);
		uc_slider_length.setImageResource(R.drawable.uc_slider_length_active);
		final ImageView uc_slider_power = (ImageView) findViewById(R.id.uc_slider_power);
		final ImageView uc_slider_pressure = (ImageView) findViewById(R.id.uc_slider_pressure);
		final ImageView uc_slider_speed = (ImageView) findViewById(R.id.uc_slider_speed);
		final ImageView uc_slider_temp = (ImageView) findViewById(R.id.uc_slider_temp);
		final ImageView uc_slider_time = (ImageView) findViewById(R.id.uc_slider_time);
		final ImageView uc_slider_volume = (ImageView) findViewById(R.id.uc_slider_volume);
		final ImageView uc_slider_weight = (ImageView) findViewById(R.id.uc_slider_weight);

		uc_slider_area.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterArea.class));
					finish();

				}
			}
		});

		uc_slider_datasize.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterDataSize.class));
					finish();
				}
			}
		});

		uc_slider_energy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterEnergy.class));
					finish();
				}
			}
		});

		uc_slider_force.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterForce.class));
					finish();
				}
			}
		});

		uc_slider_length.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterLength.class));
					finish();
				}
			}
		});

		uc_slider_power.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterPower.class));
					finish();
				}
			}
		});

		uc_slider_pressure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterPressure.class));
					finish();
				}
			}
		});

		uc_slider_speed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterSpeed.class));
					finish();
				}
			}
		});

		uc_slider_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterTemp.class));
					finish();
				}
			}
		});

		uc_slider_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterTime.class));
					finish();
				}
			}
		});

		uc_slider_volume.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterVolume.class));
					finish();
				}
			}
		});

		uc_slider_weight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					startActivity(new Intent(getApplicationContext(),
							UnitConverterWeight.class));
					finish();
				}
			}
		});

		uc_slider = (ImageView) findViewById(R.id.uc_slider_btn);
		uc_slider2 = (ImageView) findViewById(R.id.uc_slider_btn2);
		rl_slider = (RelativeLayout) findViewById(R.id.rl_slider);
		rl_slider_parent = (RelativeLayout) findViewById(R.id.rl_slider_parent);
		rl_slider.setVisibility(View.GONE);

		uc_slider2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rl_slider_parent.startAnimation(inFromRightAnimation());

			}
		});

		uc_slider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				rl_slider_parent.startAnimation(outToRightAnimation());
				slider = 1;

			}
		});
	}

	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setFillAfter(true);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		inFromRight.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				rl_slider.setVisibility(View.VISIBLE);

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				slider = 0;
			}
		});
		return inFromRight;
	}

	private Animation outToRightAnimation() {

		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setFillAfter(true);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		outtoRight.setAnimationListener(new AnimationListener() {

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

				rl_slider.setVisibility(View.GONE);
				Log.d("animation", "end" + rl_slider.getVisibility());
			}
		});

		return outtoRight;
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
			finish();
			break;

		case R.id.side_navigation_menu_item5:
			invokeActivity(getString(R.string.title5));
			finish();
			break;

		case R.id.side_navigation_menu_item6:
			invokeActivity(getString(R.string.title6));
		//	finish();
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
		else if(upgradePopUp==1)
		{
			rl_upgrade_parent.startAnimation(anim_back);
			upgradePopUp=0;
		}
		
		else if (slider == 0) {
			rl_slider_parent.startAnimation(outToRightAnimation());
			slider = 1;
		}
		else {
			startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
			intent = new Intent(this, UnitConverterLength.class);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
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
			intent = new Intent(this, HealthCalculator.class);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MODE,
					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else if (title.equals("SETTINGS")) {
			intent = new Intent(this, MainActivity.class); Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
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
			sideNavigationView.hideMenu();
			rl_upgrade_parent.startAnimation(anim);
			upgradePopUp=1;
			overridePendingTransition(0, 0);
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
					if (slider == 1) {
						rl_slider_parent.startAnimation(inFromRightAnimation());
						slider = 0;
					}
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					if (slider == 0) {
						rl_slider_parent.startAnimation(outToRightAnimation());
						slider = 1;
					} else {
						sideNavigationView.toggleMenu();
					}

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
		// InputMethodManager imm = (InputMethodManager)
		// getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
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

	public void changeBackGround(String val) {
		ll_centimeters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_feet.setBackgroundColor(Color.parseColor("#00000000"));
		ll_inches.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilometers.setBackgroundColor(Color.parseColor("#00000000"));
		ll_lightyears.setBackgroundColor(Color.parseColor("#00000000"));
		ll_meters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_microns.setBackgroundColor(Color.parseColor("#00000000"));
		ll_miles.setBackgroundColor(Color.parseColor("#00000000"));
		ll_milimeters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_nanometers.setBackgroundColor(Color.parseColor("#00000000"));
		ll_yards.setBackgroundColor(Color.parseColor("#00000000"));
		ca_centimeters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_feet.setImageResource(R.drawable.uc_conversion_arrow);
		ca_inches.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilometers.setImageResource(R.drawable.uc_conversion_arrow);
		ca_lightyears.setImageResource(R.drawable.uc_conversion_arrow);
		ca_meters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_microns.setImageResource(R.drawable.uc_conversion_arrow);
		ca_miles.setImageResource(R.drawable.uc_conversion_arrow);
		ca_milimeters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_nanometers.setImageResource(R.drawable.uc_conversion_arrow);
		ca_yards.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_centimeters.setTextColor(Color.parseColor("#ffffff"));
		conversions_feet.setTextColor(Color.parseColor("#ffffff"));
		conversions_inches.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilometers.setTextColor(Color.parseColor("#ffffff"));
		conversions_lightyears.setTextColor(Color.parseColor("#ffffff"));
		conversions_meters.setTextColor(Color.parseColor("#ffffff"));
		conversions_microns.setTextColor(Color.parseColor("#ffffff"));
		conversions_miles.setTextColor(Color.parseColor("#ffffff"));
		conversions_milimeters.setTextColor(Color.parseColor("#ffffff"));
		conversions_nanometers.setTextColor(Color.parseColor("#ffffff"));
		conversions_yards.setTextColor(Color.parseColor("#ffffff"));

		symbols_centimeters.setTextColor(Color.parseColor("#ffffff"));
		symbols_feet.setTextColor(Color.parseColor("#ffffff"));
		symbols_inches.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilometers.setTextColor(Color.parseColor("#ffffff"));
		symbols_lightyears.setTextColor(Color.parseColor("#ffffff"));
		symbols_meters.setTextColor(Color.parseColor("#ffffff"));
		symbols_microns.setTextColor(Color.parseColor("#ffffff"));
		symbols_miles.setTextColor(Color.parseColor("#ffffff"));
		symbols_milimeters.setTextColor(Color.parseColor("#ffffff"));
		symbols_nanometers.setTextColor(Color.parseColor("#ffffff"));
		symbols_yards.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_meters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_meters.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_meters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_meters.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_feet.setBackgroundColor(Color.parseColor("#30000000"));
			ca_feet.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_feet.setTextColor(Color.parseColor("#cc58ca"));
			symbols_feet.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_yards.setBackgroundColor(Color.parseColor("#30000000"));
			ca_yards.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_yards.setTextColor(Color.parseColor("#cc58ca"));
			symbols_yards.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_inches.setBackgroundColor(Color.parseColor("#30000000"));
			ca_inches.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_inches.setTextColor(Color.parseColor("#cc58ca"));
			symbols_inches.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_kilometers.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilometers
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilometers.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilometers.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_miles.setBackgroundColor(Color.parseColor("#30000000"));
			ca_miles.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_miles.setTextColor(Color.parseColor("#cc58ca"));
			symbols_miles.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("cm")) {
			ll_centimeters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_centimeters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_centimeters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_centimeters.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mm")) {

			ll_milimeters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_milimeters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_milimeters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_milimeters.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("um")) {
			ll_microns.setBackgroundColor(Color.parseColor("#30000000"));
			ca_microns
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_microns.setTextColor(Color.parseColor("#cc58ca"));
			symbols_microns.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("nm")) {

			ll_nanometers.setBackgroundColor(Color.parseColor("#30000000"));
			ca_nanometers
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_nanometers.setTextColor(Color.parseColor("#cc58ca"));
			symbols_nanometers.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("ly")) {
			ll_lightyears.setBackgroundColor(Color.parseColor("#30000000"));
			ca_lightyears
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_lightyears.setTextColor(Color.parseColor("#cc58ca"));
			symbols_lightyears.setTextColor(Color.parseColor("#cc58ca"));
		}
	}

	public void metersToOther() {
		conversions_centimeters.setText("100");
		conversions_feet.setText("3.2808");
		conversions_inches.setText("39.3701");
		conversions_kilometers.setText("0.001");
		conversions_lightyears.setText("1.05702341E-16");
		conversions_meters.setText("1");
		conversions_microns.setText("1000000");
		conversions_miles.setText("0.000621371");
		conversions_milimeters.setText("1000");
		conversions_nanometers.setText("1000000000");
		conversions_yards.setText("1.09361");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void CentimetersToOther() {
		conversions_centimeters.setText("1");
		conversions_feet.setText("0.0328084");
		conversions_inches.setText("0.393701");
		conversions_kilometers.setText("1.0E-5");
		conversions_lightyears.setText("1.05702341E-18 ");
		conversions_meters.setText("0.01");
		conversions_microns.setText("10000");
		conversions_miles.setText("6.21371E-6");
		conversions_milimeters.setText("10");
		conversions_nanometers.setText("10000000");
		conversions_yards.setText("0.0109361");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void FeetToOther() {
		conversions_centimeters.setText("30.48");
		conversions_feet.setText("1");
		conversions_inches.setText("12");
		conversions_kilometers.setText("0.0003048");
		conversions_lightyears.setText("3.22180736E-17");
		conversions_meters.setText("0.3048");
		conversions_microns.setText("304800");
		conversions_miles.setText("0.000189394");
		conversions_milimeters.setText("304.8");
		conversions_nanometers.setText("304800000");
		conversions_yards.setText("0.333333");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void InchesToOther() {
		conversions_centimeters.setText("2.54");
		conversions_feet.setText("0.0833333");
		conversions_inches.setText("1");
		conversions_kilometers.setText("2.54e-5");
		conversions_lightyears.setText("2.68483946E-18");
		conversions_meters.setText("0.0254");
		conversions_microns.setText("25400");
		conversions_miles.setText("1.57828E-5");
		conversions_milimeters.setText("25.4");
		conversions_nanometers.setText("25400000");
		conversions_yards.setText("0.0277778");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void kmToOther() {
		conversions_centimeters.setText("100000");
		conversions_feet.setText("3280.84");
		conversions_inches.setText("39370.1");
		conversions_kilometers.setText("1");
		conversions_lightyears.setText("1.05702341E-13");
		conversions_meters.setText("1000");
		conversions_microns.setText("1000000000");
		conversions_miles.setText("0.621371");
		conversions_milimeters.setText("1000000");
		conversions_nanometers.setText("1000000000000");
		conversions_yards.setText("1093.61");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void lyToOther() {
		conversions_centimeters.setText("9.4605284E17");
		conversions_feet.setText("3.1038479E16");
		conversions_inches.setText("3.72461748E17");
		conversions_kilometers.setText("9.4605284E12");
		conversions_lightyears.setText("1");
		conversions_meters.setText("9.4605284E15");
		conversions_microns.setText("9.4605284E21");
		conversions_miles.setText("5.87849981E12");
		conversions_milimeters.setText("9.4605284E18");
		conversions_nanometers.setText("9.4605284E24");
		conversions_yards.setText("1.03461597E16");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void micronsToOther() {
		conversions_centimeters.setText("0.0001");
		conversions_feet.setText("3.2808399E-6");
		conversions_inches.setText("3.93700787E-5");
		conversions_kilometers.setText("1.0e-9");
		conversions_lightyears.setText("1.05702341E-22");
		conversions_meters.setText("1.0E-6");
		conversions_microns.setText("1");
		conversions_miles.setText("6.21371192E-10");
		conversions_milimeters.setText("0.001");
		conversions_nanometers.setText("1000");
		conversions_yards.setText("1.0936133E-6");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void milesToOther() {
		conversions_centimeters.setText("160934");
		conversions_feet.setText("5280");
		conversions_inches.setText("63360");
		conversions_kilometers.setText("1.60934");
		conversions_lightyears.setText("1.70111428E-13");
		conversions_meters.setText("1609.34");
		conversions_microns.setText("1609344000");
		conversions_miles.setText("1");
		conversions_milimeters.setText("1609344");
		conversions_nanometers.setText("1609344000000");
		conversions_yards.setText("1760");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void milimetersToOther() {
		conversions_centimeters.setText("0.1");
		conversions_feet.setText("0.00328084");
		conversions_inches.setText("0.0393701");
		conversions_kilometers.setText("1.0E-6");
		conversions_lightyears.setText("1.05702341E-19");
		conversions_meters.setText("0.001");
		conversions_microns.setText("1000");
		conversions_miles.setText("6.21371E-7");
		conversions_milimeters.setText("1");
		conversions_nanometers.setText("1000000");
		conversions_yards.setText("0.00109361");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void nanometersToOther() {
		conversions_centimeters.setText("1.0e-7");
		conversions_feet.setText("3.2808399e-9");
		conversions_inches.setText("3.93700787E-8");
		conversions_kilometers.setText("1.0e-12");
		conversions_lightyears.setText("1.05702341E-25");
		conversions_meters.setText("1.0e-9");
		conversions_microns.setText("0.001");
		conversions_miles.setText("6.21371192E-13");
		conversions_milimeters.setText("1.0e-6");
		conversions_nanometers.setText("1");
		conversions_yards.setText("1.0936133E-9");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void yardsToOther() {
		conversions_centimeters.setText("91.44");
		conversions_feet.setText("3");
		conversions_inches.setText("36");
		conversions_kilometers.setText("0.0009144");
		conversions_lightyears.setText("9.66542207E-17");
		conversions_meters.setText("0.9144");
		conversions_microns.setText("914400");
		conversions_miles.setText("0.000568182");
		conversions_milimeters.setText("914.4");
		conversions_nanometers.setText("914400000");
		conversions_yards.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_centimeters = conversions_centimeters.getText().toString();
		value_feet = conversions_feet.getText().toString();
		value_inches = conversions_inches.getText().toString();
		value_kilometers = conversions_kilometers.getText().toString();
		value_lightyears = conversions_lightyears.getText().toString();
		value_meters = conversions_meters.getText().toString();
		value_microns = conversions_microns.getText().toString();
		value_miles = conversions_miles.getText().toString();
		value_milimeters = conversions_milimeters.getText().toString();
		value_nanometers = conversions_nanometers.getText().toString();
		value_yards = conversions_yards.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		metersToOther();
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"HelveticaNeue-Medium.otf");

		Typeface ttf = Typeface.createFromAsset(getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_centimeters.setTypeface(ttf);
		conversions_feet.setTypeface(ttf);
		conversions_inches.setTypeface(ttf);
		conversions_kilometers.setTypeface(ttf);
		conversions_lightyears.setTypeface(ttf);
		conversions_meters.setTypeface(ttf);
		conversions_microns.setTypeface(ttf);
		conversions_miles.setTypeface(ttf);
		conversions_milimeters.setTypeface(ttf);
		conversions_nanometers.setTypeface(ttf);
		conversions_yards.setTypeface(ttf);
		uc_edittext.setTypeface(ttf);
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

				editTextValueMultiplier(s.toString());
			}
		}
	};

	public void setValuesAccoridingToEdittext() {
		if (uc_edittext.getText().toString().equals("")) {
			uc_edittext.setText("1");
		}

		editTextValueMultiplier(uc_edittext.getText().toString());
	}

	public void editTextValueMultiplier(String value) {
		BigDecimal value1 = BigDecimal.valueOf((Float.valueOf(value) * Float
				.valueOf(value_centimeters)));

		String number1 = value1.toString();
		double amount1 = Double.parseDouble(number1);
		DecimalFormat formatter1 = new DecimalFormat("#,###.00");
		String val1 = String.valueOf(amount1);
		if (val1.contains("E")) {
			String arr1[] = val1.split("E");
			if (arr1[0].length() > 10) {
				arr1[0] = arr1[0].substring(0, 10);
			}
			conversions_centimeters.setText(arr1[0] + "E" + arr1[1]);
		} else {
			if (val1.length() > 16)
				val1 = val1.substring(0, 16);
			conversions_centimeters.setText(val1 + "");
		}

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_feet)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_feet.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_feet.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_inches)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_inches.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_inches.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilometers)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_kilometers.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_kilometers.setText(val4 + "");
		}

		BigDecimal value5 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_lightyears)));
		String number5 = value5.toString();
		double amount5 = Double.parseDouble(number5);
		DecimalFormat formatter5 = new DecimalFormat("#,###.00");
		String val5 = String.valueOf(amount5);
		if (val5.contains("E")) {
			String arr5[] = val5.split("E");
			if (arr5[0].length() > 10) {
				arr5[0] = arr5[0].substring(0, 10);
			}
			conversions_lightyears.setText(arr5[0] + "E" + arr5[1]);
		} else {
			if (val5.length() > 16)
				val5 = val5.substring(0, 16);
			conversions_lightyears.setText(val5 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_meters)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_meters.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_meters.setText(val6 + "");
		}

		BigDecimal value7 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_microns)));
		String number7 = value7.toString();
		double amount7 = Double.parseDouble(number7);
		DecimalFormat formatter7 = new DecimalFormat("#,###.00");
		String val7 = String.valueOf(amount7);
		if (val7.contains("E")) {
			String arr7[] = val7.split("E");
			if (arr7[0].length() > 10) {
				arr7[0] = arr7[0].substring(0, 10);
			}
			conversions_microns.setText(arr7[0] + "E" + arr7[1]);
		} else {
			if (val7.length() > 16)
				val7 = val7.substring(0, 16);
			conversions_microns.setText(val7 + "");
		}

		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_miles)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_miles.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_miles.setText(val8 + "");
		}

		BigDecimal value9 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_milimeters)));
		String number9 = value9.toString();
		double amount9 = Double.parseDouble(number9);
		DecimalFormat formatter9 = new DecimalFormat("#,###.00");
		String val9 = String.valueOf(amount9);
		if (val9.contains("E")) {
			String arr9[] = val9.split("E");
			if (arr9[0].length() > 10) {
				arr9[0] = arr9[0].substring(0, 10);
			}
			conversions_milimeters.setText(arr9[0] + "E" + arr9[1]);
		} else {
			if (val9.length() > 16)
				val9 = val9.substring(0, 16);
			conversions_milimeters.setText(val9 + "");
		}

		BigDecimal value10 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_nanometers)));
		String number10 = value10.toString();
		double amount10 = Double.parseDouble(number10);
		DecimalFormat formatter10 = new DecimalFormat("#,###.00");
		String val10 = String.valueOf(amount10);
		if (val10.contains("E")) {
			String arr10[] = val10.split("E");
			if (arr10[0].length() > 10) {
				arr10[0] = arr10[0].substring(0, 10);
			}
			conversions_nanometers.setText(arr10[0] + "E" + arr10[1]);
		} else {
			if (val10.length() > 16)
				val10 = val10.substring(0, 16);
			conversions_nanometers.setText(val10 + "");
		}

		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_yards)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_yards.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_yards.setText(val11 + "");
		}
	}

	public static void startActivity(Context ctx, Intent intent) {
		ctx.startActivity(intent);
		((Activity) ctx).overridePendingTransition(R.drawable.right_slide_in,
				R.drawable.right_slide_out);
	}

}
