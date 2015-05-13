package com.ioptime.calculatorapp.fragments;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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

import com.actionbarsherlock.app.SherlockFragment;
import com.devspark.sidenavigation.SideNavigationView;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class UnitConverterPressureFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_pascal;
	LinearLayout ll_kilopascal;
	LinearLayout ll_megapascal;
	LinearLayout ll_millibar;
	LinearLayout ll_bar;
	LinearLayout ll_atmosphere;
	LinearLayout ll_poundpersqinch;
	LinearLayout ll_gallon;
	LinearLayout ll_pint;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_pascal;
	ImageView ca_kilopascal;
	ImageView ca_megapascal;
	ImageView ca_millibar;
	ImageView ca_bar;
	ImageView ca_atmosphere;
	ImageView ca_poundpersqinch;

	TextView conversions_pascal;
	TextView conversions_kilopascal;
	TextView conversions_megapascal;
	TextView conversions_millibar;
	TextView conversions_bar;
	TextView conversions_atmosphere;
	TextView conversions_poundpersqinch;

	String value_pascal;
	String value_kilopascal;
	String value_megapascal;
	String value_millibar;
	String value_bar;
	String value_atmosphere;
	String value_poundpersqinch;

	TextView symbols_pascal;
	TextView symbols_kilopascal;
	TextView symbols_megapascal;
	TextView symbols_millibar;
	TextView symbols_bar;
	TextView symbols_atmosphere;
	TextView symbols_poundpersqinch;

	TextView unit_name_pascal;
	TextView unit_name_kilopascal;
	TextView unit_name_megapascal;
	TextView unit_name_millibar;
	TextView unit_name_bar;
	TextView unit_name_atmosphere;
	TextView unit_name_poundpersqinch;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.unit_converter_length, container, false);
		
		super.onCreate(savedInstanceState);
		ctx = container.getContext();
		gestureDetector = new GestureDetector(ctx, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};
		anim = AnimationUtils.loadAnimation(ctx, R.drawable.scale);
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
		
		anim_back = AnimationUtils.loadAnimation(ctx, R.drawable.scale_back);
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
		funtionPad = (LinearLayout) view.findViewById(R.id.functionPad);
		sideNavigationView = (SideNavigationView) view.findViewById(R.id.side_navigation_view);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		uc_edittext = (EditText) view.findViewById(R.id.uc_edittext);
		ll_poundpersqinch = (LinearLayout) view.findViewById(R.id.centimeters_ll);
		ll_kilopascal = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_millibar = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_bar = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_pascal = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_pint = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_atmosphere = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_gallon = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_megapascal = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);
		
		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);
		ll_pint.setVisibility(View.GONE);
		ll_gallon.setVisibility(View.GONE);

		ca_poundpersqinch = (ImageView) view.findViewById(R.id.centimeters_conversion_arrow);
		ca_kilopascal = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_millibar = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_bar = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_pascal = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_atmosphere = (ImageView) view.findViewById(R.id.miles_conversion_arrow);
		ca_megapascal = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_poundpersqinch = (TextView) view.findViewById(R.id.centimeters_conversions);
		conversions_kilopascal = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_millibar = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_bar = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_pascal = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_atmosphere = (TextView) view.findViewById(R.id.miles_conversions);
		conversions_megapascal = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_poundpersqinch = (TextView) view.findViewById(R.id.centimeters_symbols);
		symbols_kilopascal = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_millibar = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_bar = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_pascal = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_atmosphere = (TextView) view.findViewById(R.id.miles_symbols);
		symbols_megapascal = (TextView) view.findViewById(R.id.yards_symbols);

		unit_name_poundpersqinch = (TextView) view.findViewById(R.id.centimeters_unit);
		unit_name_kilopascal = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_millibar = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_bar = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_pascal = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_atmosphere = (TextView) view.findViewById(R.id.miles_unit);
		unit_name_megapascal = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_force_headng_light);
		prevScreenValue.setImageResource(R.drawable.uc_energy_heading_light);
		currentHeading.setImageResource(R.drawable.uc_pressure_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_pressure_bullets);

		symbols_pascal.setText("Pa");
		symbols_kilopascal.setText("kPa");
		symbols_megapascal.setText("MPa");
		symbols_millibar.setText("mbar");
		symbols_bar.setText("bar");
		symbols_atmosphere.setText("atm");
		symbols_poundpersqinch.setText("psi");

		unit_name_pascal.setText("Pascal");
		unit_name_kilopascal.setText("Kilopascal");
		unit_name_megapascal.setText("Megapascal");
		unit_name_millibar.setText("Millibar");
		unit_name_bar.setText("Bar");
		unit_name_atmosphere.setText("Atmosphere(std)");
		unit_name_atmosphere.setTextSize(16);
		unit_name_poundpersqinch.setText("Pounds per sq.inch");
		
		ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);
		
		next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterForceFragment();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterForceFragment();
			}
		});

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterEnergyFragment();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterEnergyFragment();

			}
		});

		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);


		ll_poundpersqinch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("cm");
				poundpersqinchToOther();
				// storeValueToString();

			}
		});
		ll_kilopascal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				kilopascalToOther();
				// storeValueToString();

			}
		});
		ll_millibar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				millibarToOther();
				// storeValueToString();

			}
		});
		ll_bar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				barToOther();
				// storeValueToString();

			}
		});
		ll_pascal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				pascalToOther();
				// storeValueToString();

			}
		});
		
		ll_atmosphere.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				atmosphereToOther();
				// storeValueToString();

			}
		});

		ll_megapascal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				megapascalToOther();
				// storeValueToString();

			}
		});

		final ImageView uc_slider_area = (ImageView) view.findViewById(R.id.uc_slider_area);
		final ImageView uc_slider_datasize = (ImageView) view.findViewById(R.id.uc_slider_datasize);
		final ImageView uc_slider_energy = (ImageView) view.findViewById(R.id.uc_slider_energy);
		final ImageView uc_slider_force = (ImageView) view.findViewById(R.id.uc_slider_force);
		final ImageView uc_slider_length = (ImageView) view.findViewById(R.id.uc_slider_length);
		final ImageView uc_slider_power = (ImageView) view.findViewById(R.id.uc_slider_power);
		final ImageView uc_slider_pressure = (ImageView) view.findViewById(R.id.uc_slider_pressure);
		uc_slider_pressure.setImageResource(R.drawable.uc_slider_pressure_active);
		final ImageView uc_slider_speed = (ImageView) view.findViewById(R.id.uc_slider_speed);
		final ImageView uc_slider_temp = (ImageView) view.findViewById(R.id.uc_slider_temp);
		final ImageView uc_slider_time = (ImageView) view.findViewById(R.id.uc_slider_time);
		final ImageView uc_slider_volume = (ImageView) view.findViewById(R.id.uc_slider_volume);
		final ImageView uc_slider_weight = (ImageView) view.findViewById(R.id.uc_slider_weight);

		uc_slider_area.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterAreaFragment();

				}
			}
		});

		uc_slider_datasize.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterDataSizeFragment();
				}
			}
		});

		uc_slider_energy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterEnergyFragment();
				}
			}
		});

		uc_slider_force.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterForceFragment();
				}
			}
		});

		uc_slider_length.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterLengthFragment();
				}
			}
		});

		uc_slider_power.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterPowerFragment();
				}
			}
		});

		uc_slider_pressure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterPressureFragment();
				}
			}
		});

		uc_slider_speed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterSpeedFragment();
				}
			}
		});

		uc_slider_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterTempFragment();
				}
			}
		});

		uc_slider_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterTimeFragment();
				}
			}
		});

		uc_slider_volume.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterVolumeFragment();
				}
			}
		});

		uc_slider_weight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterWeightFragment();
				}
			}
		});

		uc_slider = (ImageView) view.findViewById(R.id.uc_slider_btn);
		uc_slider2 = (ImageView) view.findViewById(R.id.uc_slider_btn2);
		rl_slider = (RelativeLayout) view.findViewById(R.id.rl_slider);
		rl_slider_parent = (RelativeLayout) view.findViewById(R.id.rl_slider_parent);
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
		
		return view;
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
//						sideNavigationView.toggleMenu();
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


	public boolean onTouchEvent(MotionEvent event) {
		// InputMethodManager imm = (InputMethodManager)
		// getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return true;
	}


	public void changeBackGround(String val) {
		ll_poundpersqinch.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilopascal.setBackgroundColor(Color.parseColor("#00000000"));
		ll_millibar.setBackgroundColor(Color.parseColor("#00000000"));
		ll_bar.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pascal.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pint.setBackgroundColor(Color.parseColor("#00000000"));
		ll_atmosphere.setBackgroundColor(Color.parseColor("#00000000"));
		ll_gallon.setBackgroundColor(Color.parseColor("#00000000"));
		ll_megapascal.setBackgroundColor(Color.parseColor("#00000000"));
		ca_poundpersqinch.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilopascal.setImageResource(R.drawable.uc_conversion_arrow);
		ca_millibar.setImageResource(R.drawable.uc_conversion_arrow);
		ca_bar.setImageResource(R.drawable.uc_conversion_arrow);
		ca_pascal.setImageResource(R.drawable.uc_conversion_arrow);
		ca_atmosphere.setImageResource(R.drawable.uc_conversion_arrow);
		ca_megapascal.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_poundpersqinch.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilopascal.setTextColor(Color.parseColor("#ffffff"));
		conversions_millibar.setTextColor(Color.parseColor("#ffffff"));
		conversions_bar.setTextColor(Color.parseColor("#ffffff"));
		conversions_pascal.setTextColor(Color.parseColor("#ffffff"));
		conversions_atmosphere.setTextColor(Color.parseColor("#ffffff"));
		conversions_megapascal.setTextColor(Color.parseColor("#ffffff"));

		symbols_poundpersqinch.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilopascal.setTextColor(Color.parseColor("#ffffff"));
		symbols_millibar.setTextColor(Color.parseColor("#ffffff"));
		symbols_bar.setTextColor(Color.parseColor("#ffffff"));
		symbols_pascal.setTextColor(Color.parseColor("#ffffff"));
		symbols_atmosphere.setTextColor(Color.parseColor("#ffffff"));
		symbols_megapascal.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_pascal.setBackgroundColor(Color.parseColor("#30000000"));
			ca_pascal
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_pascal.setTextColor(Color.parseColor("#cc58ca"));
			symbols_pascal.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_kilopascal.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilopascal
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilopascal.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilopascal.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_megapascal.setBackgroundColor(Color.parseColor("#30000000"));
			ca_megapascal
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_megapascal.setTextColor(Color.parseColor("#cc58ca"));
			symbols_megapascal.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_millibar.setBackgroundColor(Color.parseColor("#30000000"));
			ca_millibar
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_millibar.setTextColor(Color.parseColor("#cc58ca"));
			symbols_millibar.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_bar.setBackgroundColor(Color.parseColor("#30000000"));
			ca_bar
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_bar.setTextColor(Color.parseColor("#cc58ca"));
			symbols_bar.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_atmosphere.setBackgroundColor(Color.parseColor("#30000000"));
			ca_atmosphere.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_atmosphere.setTextColor(Color.parseColor("#cc58ca"));
			symbols_atmosphere.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("cm")) {
			ll_poundpersqinch.setBackgroundColor(Color.parseColor("#30000000"));
			ca_poundpersqinch
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_poundpersqinch.setTextColor(Color.parseColor("#cc58ca"));
			symbols_poundpersqinch.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void pascalToOther() {
		conversions_poundpersqinch.setText("0.000145037738");
		conversions_kilopascal.setText("0.001");
		conversions_millibar.setText("0.01");
		conversions_bar.setText("1.0E-5");
		conversions_pascal.setText("1");
		conversions_atmosphere.setText("9.86923267E-6");
		conversions_megapascal.setText("1.0E-6");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void poundpersqinchToOther() {
		conversions_poundpersqinch.setText("1");
		conversions_kilopascal.setText("6.89475729");
		conversions_millibar.setText("68.9475729");
		conversions_bar.setText("0.0689475729");
		conversions_pascal.setText("6894.75729");
		conversions_atmosphere.setText("0.0680459639");
		conversions_megapascal.setText("0.00689475729");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void kilopascalToOther() {
		conversions_poundpersqinch.setText("0.145037738");
		conversions_kilopascal.setText("1");
		conversions_millibar.setText("10");
		conversions_bar.setText("0.01");
		conversions_pascal.setText("1000");
		conversions_atmosphere.setText("0.00986923267");
		conversions_megapascal.setText("0.001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void millibarToOther() {
		conversions_poundpersqinch.setText("0.0145037738");
		conversions_kilopascal.setText("0.1");
		conversions_millibar.setText("1");
		conversions_bar.setText("0.001");
		conversions_pascal.setText("100");
		conversions_atmosphere.setText("0.000986923267");
		conversions_megapascal.setText("0.0001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void barToOther() {
		conversions_poundpersqinch.setText("14.5037738");
		conversions_kilopascal.setText("100");
		conversions_millibar.setText("1000");
		conversions_bar.setText("1");
		conversions_pascal.setText("100000");
		conversions_atmosphere.setText("0.986923267");
		conversions_megapascal.setText("0.1");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void atmosphereToOther() {
		conversions_poundpersqinch.setText("14.6959488");
		conversions_kilopascal.setText("101.32500");
		conversions_millibar.setText("1013.25");
		conversions_bar.setText("1.01325");
		conversions_pascal.setText("101325");
		conversions_atmosphere.setText("1");
		conversions_megapascal.setText("0.101325");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void megapascalToOther() {
		conversions_poundpersqinch.setText("145.037738");
		conversions_kilopascal.setText("1000");
		conversions_millibar.setText("10000");
		conversions_bar.setText("10");
		conversions_pascal.setText("1000000");
		conversions_atmosphere.setText("9.86923267");
		conversions_megapascal.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_poundpersqinch = conversions_poundpersqinch.getText().toString();
		value_kilopascal = conversions_kilopascal.getText().toString();
		value_millibar = conversions_millibar.getText().toString();
		value_bar = conversions_bar.getText().toString();
		value_pascal = conversions_pascal.getText().toString();
		value_atmosphere = conversions_atmosphere.getText().toString();
		value_megapascal = conversions_megapascal.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		pascalToOther();

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_poundpersqinch.setTypeface(ttf);
		conversions_kilopascal.setTypeface(ttf);
		conversions_millibar.setTypeface(ttf);
		conversions_bar.setTypeface(ttf);
		conversions_pascal.setTypeface(ttf);
		conversions_atmosphere.setTypeface(ttf);
		conversions_megapascal.setTypeface(ttf);
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
				.valueOf(value_poundpersqinch)));

		String number1 = value1.toString();
		double amount1 = Double.parseDouble(number1);
		DecimalFormat formatter1 = new DecimalFormat("#,###.00");
		String val1 = String.valueOf(amount1);
		if (val1.contains("E")) {
			String arr1[] = val1.split("E");
			if (arr1[0].length() > 10) {
				arr1[0] = arr1[0].substring(0, 10);
			}
			conversions_poundpersqinch.setText(arr1[0] + "E" + arr1[1]);
		} else {
			if (val1.length() > 16)
				val1 = val1.substring(0, 16);
			conversions_poundpersqinch.setText(val1 + "");
		}

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilopascal)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_kilopascal.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_kilopascal.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_millibar)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_millibar.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_millibar.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_bar)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_bar.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_bar.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_pascal)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_pascal.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_pascal.setText(val6 + "");
		}


		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_atmosphere)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_atmosphere.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_atmosphere.setText(val8 + "");
		}

		
		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_megapascal)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_megapascal.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_megapascal.setText(val11 + "");
		}
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
	
}
