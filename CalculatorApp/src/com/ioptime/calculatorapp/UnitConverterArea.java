package com.ioptime.calculatorapp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Html;
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

public class UnitConverterArea extends SherlockActivity implements
		OnClickListener, ISideNavigationCallback {
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_sqmilimeters;
	LinearLayout ll_sqcentimeters;
	LinearLayout ll_sqmeters;
	LinearLayout ll_sqkilometers;
	LinearLayout ll_sqinches;
	LinearLayout ll_sqfeet;
	LinearLayout ll_sqyards;
	LinearLayout ll_acres;
	LinearLayout ll_sqmiles;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_sqmilimeters;
	ImageView ca_sqcentimeters;
	ImageView ca_sqmeters;
	ImageView ca_sqkilometers;
	ImageView ca_sqinches;
	ImageView ca_sqfeet;
	ImageView ca_sqyards;
	ImageView ca_acres;
	ImageView ca_sqmiles;

	TextView conversions_sqmilimeters;
	TextView conversions_sqcentimeters;
	TextView conversions_sqmeters;
	TextView conversions_sqkilometers;
	TextView conversions_sqinches;
	TextView conversions_sqfeet;
	TextView conversions_sqyards;
	TextView conversions_acres;
	TextView conversions_sqmiles;

	String value_sqmilimeters;
	String value_sqcentimeters;
	String value_sqmeters;
	String value_sqkilometers;
	String value_sqinches;
	String value_sqfeet;
	String value_sqyards;
	String value_acres;
	String value_sqmiles;

	TextView symbols_sqmilimeters;
	TextView symbols_sqcentimeters;
	TextView symbols_sqmeters;
	TextView symbols_sqkilometers;
	TextView symbols_sqinches;
	TextView symbols_sqfeet;
	TextView symbols_sqyards;
	TextView symbols_acres;
	TextView symbols_sqmiles;

	TextView unit_name_sqmilimeters;
	TextView unit_name_sqcentimeters;
	TextView unit_name_sqmeters;
	TextView unit_name_sqkilometers;
	TextView unit_name_sqinches;
	TextView unit_name_sqfeet;
	TextView unit_name_sqyards;
	TextView unit_name_acres;
	TextView unit_name_sqmiles;

	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private SideNavigationView sideNavigationView;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
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
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		uc_edittext = (EditText) findViewById(R.id.uc_edittext);
		ll_sqyards = (LinearLayout) findViewById(R.id.centimeters_ll);
		ll_sqcentimeters = (LinearLayout) findViewById(R.id.feet_ll);
		ll_sqkilometers = (LinearLayout) findViewById(R.id.inches_ll);
		ll_sqinches = (LinearLayout) findViewById(R.id.kilometers_ll);
		ll_sqmilimeters = (LinearLayout) findViewById(R.id.meters_ll);
		ll_sqmiles = (LinearLayout) findViewById(R.id.microns_ll);
		ll_sqfeet = (LinearLayout) findViewById(R.id.miles_ll);
		ll_acres = (LinearLayout) findViewById(R.id.milimeters_ll);
		ll_sqmeters = (LinearLayout) findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) findViewById(R.id.lightyears_ll);
		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);

		ca_sqyards = (ImageView) findViewById(R.id.centimeters_conversion_arrow);
		ca_sqcentimeters = (ImageView) findViewById(R.id.feet_conversion_arrow);
		ca_sqkilometers = (ImageView) findViewById(R.id.inches_conversion_arrow);
		ca_sqinches = (ImageView) findViewById(R.id.kilometers_conversion_arrow);
		ca_sqmilimeters = (ImageView) findViewById(R.id.meters_conversion_arrow);
		ca_sqmiles = (ImageView) findViewById(R.id.microns_conversion_arrow);
		ca_sqfeet = (ImageView) findViewById(R.id.miles_conversion_arrow);
		ca_acres = (ImageView) findViewById(R.id.milimeters_conversion_arrow);
		ca_sqmeters = (ImageView) findViewById(R.id.yards_conversion_arrow);

		conversions_sqyards = (TextView) findViewById(R.id.centimeters_conversions);
		conversions_sqcentimeters = (TextView) findViewById(R.id.feet_conversions);
		conversions_sqkilometers = (TextView) findViewById(R.id.inches_conversions);
		conversions_sqinches = (TextView) findViewById(R.id.kilometers_conversions);
		conversions_sqmilimeters = (TextView) findViewById(R.id.meters_conversions);
		conversions_sqmiles = (TextView) findViewById(R.id.microns_conversions);
		conversions_sqfeet = (TextView) findViewById(R.id.miles_conversions);
		conversions_acres = (TextView) findViewById(R.id.milimeters_conversions);
		conversions_sqmeters = (TextView) findViewById(R.id.yards_conversions);

		symbols_sqyards = (TextView) findViewById(R.id.centimeters_symbols);
		symbols_sqcentimeters = (TextView) findViewById(R.id.feet_symbols);
		symbols_sqkilometers = (TextView) findViewById(R.id.inches_symbols);
		symbols_sqinches = (TextView) findViewById(R.id.kilometers_symbols);
		symbols_sqmilimeters = (TextView) findViewById(R.id.meters_symbols);
		symbols_sqmiles = (TextView) findViewById(R.id.microns_symbols);
		symbols_sqfeet = (TextView) findViewById(R.id.miles_symbols);
		symbols_acres = (TextView) findViewById(R.id.milimeters_symbols);
		symbols_sqmeters = (TextView) findViewById(R.id.yards_symbols);

		unit_name_sqyards = (TextView) findViewById(R.id.centimeters_unit);
		unit_name_sqcentimeters = (TextView) findViewById(R.id.feet_unit);
		unit_name_sqkilometers = (TextView) findViewById(R.id.inches_unit);
		unit_name_sqinches = (TextView) findViewById(R.id.kilometers_unit);
		unit_name_sqmilimeters = (TextView) findViewById(R.id.meters_unit);
		unit_name_sqmiles = (TextView) findViewById(R.id.microns_unit);
		unit_name_sqfeet = (TextView) findViewById(R.id.miles_unit);
		unit_name_acres = (TextView) findViewById(R.id.milimeters_unit);
		unit_name_sqmeters = (TextView) findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_volume_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_lentgh_temp);
		currentHeading.setImageResource(R.drawable.uc_area_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_area_bullets);

		symbols_sqmilimeters.setText(Html.fromHtml("mm<sup>2</sup>"));
		symbols_sqcentimeters.setText(Html.fromHtml("cm<sup>2</sup>"));
		symbols_sqmeters.setText(Html.fromHtml("m<sup>2</sup>"));
		symbols_sqkilometers.setText(Html.fromHtml("km<sup>2</sup>"));
		symbols_sqinches.setText("sq in");
		symbols_sqfeet.setText("sq ft");
		symbols_sqyards.setText("sq yd");
		symbols_acres.setText("ac");
		symbols_sqmiles.setText("sq mi");

		unit_name_sqmilimeters.setText("Sq. Millimeters");
		unit_name_sqcentimeters.setText("Sq. Centimeters");
		unit_name_sqmeters.setText("Sq. Meters");
		unit_name_sqkilometers.setText("Sq. Kilometers");
		unit_name_sqinches.setText("Sq. Inches");
		unit_name_sqfeet.setText("Sq. Feet");
		unit_name_sqyards.setText("Sq. Yards");
		unit_name_acres.setText("Acres");
		unit_name_sqmiles.setText("Sq. Miles");
		ImageView next_empty_view = (ImageView) findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view = (ImageView) findViewById(R.id.uc_empty_view_prev);

		next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.startActivity(UnitConverterArea.this, new Intent(
						getApplicationContext(), UnitConverterVolume.class));
				finish();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.startActivity(UnitConverterArea.this, new Intent(
						getApplicationContext(), UnitConverterVolume.class));
				finish();
			}
		});

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.startActivityPrev(UnitConverterArea.this, new Intent(
						getApplicationContext(), UnitConverterTemp.class));
				finish();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.startActivityPrev(UnitConverterArea.this, new Intent(
						getApplicationContext(), UnitConverterTemp.class));
				finish();

			}
		});

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

		ll_sqyards.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("cm");
				sqyardsToOther();
				// storeValueToString();

			}
		});
		ll_sqcentimeters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				sqcentimetersToOther();
				// storeValueToString();

			}
		});
		ll_sqkilometers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				sqkilometersToOther();
				// storeValueToString();

			}
		});
		ll_sqinches.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				sqinchesToOther();
				// storeValueToString();

			}
		});
		ll_sqmilimeters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				sqmilimeterToOther();
				// storeValueToString();

			}
		});
		ll_sqmiles.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("um");
				sqmilesToOther();
				// storeValueToString();

			}
		});
		ll_sqfeet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				sqfeetToOther();
				// storeValueToString();

			}
		});
		ll_acres.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mm");
				acresToOther();
				// storeValueToString();
			}
		});

		ll_sqmeters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				sqmetersToOther();
				// storeValueToString();

			}
		});

		final ImageView uc_slider_area = (ImageView) findViewById(R.id.uc_slider_area);
		uc_slider_area.setImageResource(R.drawable.uc_slider_area_active);
		final ImageView uc_slider_datasize = (ImageView) findViewById(R.id.uc_slider_datasize);
		final ImageView uc_slider_energy = (ImageView) findViewById(R.id.uc_slider_energy);
		final ImageView uc_slider_force = (ImageView) findViewById(R.id.uc_slider_force);
		final ImageView uc_slider_length = (ImageView) findViewById(R.id.uc_slider_length);
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
		ll_sqyards.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqcentimeters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqkilometers.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqinches.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqmilimeters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqmiles.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqfeet.setBackgroundColor(Color.parseColor("#00000000"));
		ll_acres.setBackgroundColor(Color.parseColor("#00000000"));
		ll_sqmeters.setBackgroundColor(Color.parseColor("#00000000"));
		ca_sqyards.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqcentimeters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqkilometers.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqinches.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqmilimeters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqmiles.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqfeet.setImageResource(R.drawable.uc_conversion_arrow);
		ca_acres.setImageResource(R.drawable.uc_conversion_arrow);
		ca_sqmeters.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_sqyards.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqcentimeters.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqkilometers.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqinches.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqmilimeters.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqmiles.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqfeet.setTextColor(Color.parseColor("#ffffff"));
		conversions_acres.setTextColor(Color.parseColor("#ffffff"));
		conversions_sqmeters.setTextColor(Color.parseColor("#ffffff"));

		symbols_sqyards.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqcentimeters.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqkilometers.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqinches.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqmilimeters.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqmiles.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqfeet.setTextColor(Color.parseColor("#ffffff"));
		symbols_acres.setTextColor(Color.parseColor("#ffffff"));
		symbols_sqmeters.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_sqmilimeters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqmilimeters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqmilimeters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqmilimeters.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_sqcentimeters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqcentimeters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqcentimeters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqcentimeters.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_sqmeters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqmeters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqmeters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqmeters.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_sqkilometers.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqkilometers
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqkilometers.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqkilometers.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_sqinches.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqinches
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqinches.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqinches.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_sqfeet.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqfeet.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqfeet.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqfeet.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("cm")) {
			ll_sqyards.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqyards
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqyards.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqyards.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mm")) {

			ll_acres.setBackgroundColor(Color.parseColor("#30000000"));
			ca_acres.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_acres.setTextColor(Color.parseColor("#cc58ca"));
			symbols_acres.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("um")) {
			ll_sqmiles.setBackgroundColor(Color.parseColor("#30000000"));
			ca_sqmiles
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_sqmiles.setTextColor(Color.parseColor("#cc58ca"));
			symbols_sqmiles.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void sqmilimeterToOther() {
		conversions_sqyards.setText("1.19599005E-6");
		conversions_sqcentimeters.setText("0.01");
		conversions_sqkilometers.setText("1.0E-12");
		conversions_sqinches.setText("0.0015500031");
		conversions_sqmilimeters.setText("1");
		conversions_sqmiles.setText("3.86102159E-13");
		conversions_sqfeet.setText("1.07639104E-5");
		conversions_acres.setText("2.47105381E-10");
		conversions_sqmeters.setText("1.0E-6");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void sqyardsToOther() {
		conversions_sqyards.setText("1");
		conversions_sqcentimeters.setText("8361.2736");
		conversions_sqkilometers.setText("8.36127E-7");
		conversions_sqinches.setText("1296");
		conversions_sqmilimeters.setText("836127.36");
		conversions_sqmiles.setText("3.22831E-7");
		conversions_sqfeet.setText("9");
		conversions_acres.setText("0.000206612");
		conversions_sqmeters.setText("0.836127");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void sqcentimetersToOther() {
		conversions_sqyards.setText("0.000119599005");
		conversions_sqcentimeters.setText("1");
		conversions_sqkilometers.setText("1.0E-10");
		conversions_sqinches.setText("0.15500031");
		conversions_sqmilimeters.setText("100");
		conversions_sqmiles.setText("3.86102159E-11");
		conversions_sqfeet.setText("0.00107639104");
		conversions_acres.setText("2.47105381E-8");
		conversions_sqmeters.setText("0.0001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void sqkilometersToOther() {
		conversions_sqyards.setText("1.19599E6");
		conversions_sqcentimeters.setText("10000000000");
		conversions_sqkilometers.setText("1");
		conversions_sqinches.setText("1.55E9");
		conversions_sqmilimeters.setText("1000000000000");
		conversions_sqmiles.setText("0.386102");
		conversions_sqfeet.setText("1.07639E7");
		conversions_acres.setText("247.105");
		conversions_sqmeters.setText("1000000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void sqinchesToOther() {
		conversions_sqyards.setText("0.000771605");
		conversions_sqcentimeters.setText("6.4516");
		conversions_sqkilometers.setText("6.4516E-10");
		conversions_sqinches.setText("1");
		conversions_sqmilimeters.setText("645.16");
		conversions_sqmiles.setText("2.49098E-10");
		conversions_sqfeet.setText("0.00694444");
		conversions_acres.setText("1.59423E-7");
		conversions_sqmeters.setText("0.00064516");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void sqmilesToOther() {
		conversions_sqyards.setText("3097600");
		conversions_sqcentimeters.setText("2.58998811E10");
		conversions_sqkilometers.setText("2.58999");
		conversions_sqinches.setText("4014489600");
		conversions_sqmilimeters.setText("2589988110336");
		conversions_sqmiles.setText("1");
		conversions_sqfeet.setText("27878400");
		conversions_acres.setText("640");
		conversions_sqmeters.setText("2.58999E6");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void sqfeetToOther() {
		conversions_sqyards.setText("0.111111");
		conversions_sqcentimeters.setText("929.0304");
		conversions_sqkilometers.setText("9.2903E-8");
		conversions_sqinches.setText("144");
		conversions_sqmilimeters.setText("92903.04");
		conversions_sqmiles.setText("3.58701E-8");
		conversions_sqfeet.setText("1");
		conversions_acres.setText("2.29568E-5");
		conversions_sqmeters.setText("0.092903");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void acresToOther() {
		conversions_sqyards.setText("4840");
		conversions_sqcentimeters.setText("40468564.2");
		conversions_sqkilometers.setText("0.00404686");
		conversions_sqinches.setText("6272640");
		conversions_sqmilimeters.setText("4.04685642E9");
		conversions_sqmiles.setText("0.0015625");
		conversions_sqfeet.setText("43560");
		conversions_acres.setText("1");
		conversions_sqmeters.setText("4046.86");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void sqmetersToOther() {
		conversions_sqyards.setText("1.19599");
		conversions_sqcentimeters.setText("10000");
		conversions_sqkilometers.setText("1.0E-6");
		conversions_sqinches.setText("1550");
		conversions_sqmilimeters.setText("1000000");
		conversions_sqmiles.setText("3.86102E-7");
		conversions_sqfeet.setText("10.7639");
		conversions_acres.setText("0.000247105");
		conversions_sqmeters.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_sqyards = conversions_sqyards.getText().toString();
		value_sqcentimeters = conversions_sqcentimeters.getText().toString();
		value_sqkilometers = conversions_sqkilometers.getText().toString();
		value_sqinches = conversions_sqinches.getText().toString();
		value_sqmilimeters = conversions_sqmilimeters.getText().toString();
		value_sqmiles = conversions_sqmiles.getText().toString();
		value_sqfeet = conversions_sqfeet.getText().toString();
		value_acres = conversions_acres.getText().toString();
		value_sqmeters = conversions_sqmeters.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		sqmilimeterToOther();
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"HelveticaNeue-Medium.otf");

		Typeface ttf = Typeface.createFromAsset(getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_sqyards.setTypeface(ttf);
		conversions_sqcentimeters.setTypeface(ttf);
		conversions_sqkilometers.setTypeface(ttf);
		conversions_sqinches.setTypeface(ttf);
		conversions_sqmilimeters.setTypeface(ttf);
		conversions_sqmiles.setTypeface(ttf);
		conversions_sqfeet.setTypeface(ttf);
		conversions_acres.setTypeface(ttf);
		conversions_sqmeters.setTypeface(ttf);
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
				.valueOf(value_sqyards)));

		String number1 = value1.toString();
		double amount1 = Double.parseDouble(number1);
		DecimalFormat formatter1 = new DecimalFormat("#,###.00");
		String val1 = String.valueOf(amount1);
		if (val1.contains("E")) {
			String arr1[] = val1.split("E");
			if (arr1[0].length() > 10) {
				arr1[0] = arr1[0].substring(0, 10);
			}
			conversions_sqyards.setText(arr1[0] + "E" + arr1[1]);
		} else {
			if (val1.length() > 16)
				val1 = val1.substring(0, 16);
			conversions_sqyards.setText(val1 + "");
		}

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqcentimeters)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_sqcentimeters.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_sqcentimeters.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqkilometers)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_sqkilometers.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_sqkilometers.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqinches)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_sqinches.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_sqinches.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqmilimeters)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_sqmilimeters.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_sqmilimeters.setText(val6 + "");
		}

		BigDecimal value7 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqmiles)));
		String number7 = value7.toString();
		double amount7 = Double.parseDouble(number7);
		DecimalFormat formatter7 = new DecimalFormat("#,###.00");
		String val7 = String.valueOf(amount7);
		if (val7.contains("E")) {
			String arr7[] = val7.split("E");
			if (arr7[0].length() > 10) {
				arr7[0] = arr7[0].substring(0, 10);
			}
			conversions_sqmiles.setText(arr7[0] + "E" + arr7[1]);
		} else {
			if (val7.length() > 16)
				val7 = val7.substring(0, 16);
			conversions_sqmiles.setText(val7 + "");
		}

		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqfeet)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_sqfeet.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_sqfeet.setText(val8 + "");
		}

		BigDecimal value9 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_acres)));
		String number9 = value9.toString();
		double amount9 = Double.parseDouble(number9);
		DecimalFormat formatter9 = new DecimalFormat("#,###.00");
		String val9 = String.valueOf(amount9);
		if (val9.contains("E")) {
			String arr9[] = val9.split("E");
			if (arr9[0].length() > 10) {
				arr9[0] = arr9[0].substring(0, 10);
			}
			conversions_acres.setText(arr9[0] + "E" + arr9[1]);
		} else {
			if (val9.length() > 16)
				val9 = val9.substring(0, 16);
			conversions_acres.setText(val9 + "");
		}

		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_sqmeters)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_sqmeters.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_sqmeters.setText(val11 + "");
		}
	}

}
