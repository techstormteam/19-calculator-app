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

public class UnitConverterEnergyFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_calories;
	LinearLayout ll_kilocalories;
	LinearLayout ll_joules;
	LinearLayout ll_kilojoules;
	LinearLayout ll_megajoules;
	LinearLayout ll_kilowatts;
	LinearLayout ll_micrograms;
	LinearLayout ll_gallon;
	LinearLayout ll_pint;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_calories;
	ImageView ca_kilocalories;
	ImageView ca_joules;
	ImageView ca_kilojoules;
	ImageView ca_megajoules;
	ImageView ca_kilowatts;

	TextView conversions_calories;
	TextView conversions_kilocalories;
	TextView conversions_joules;
	TextView conversions_kilojoules;
	TextView conversions_megajoules;
	TextView conversions_kilowatts;

	String value_calories;
	String value_kilocalories;
	String value_joules;
	String value_kilojoules;
	String value_megajoules;
	String value_kilowatts;

	TextView symbols_calories;
	TextView symbols_kilocalories;
	TextView symbols_joules;
	TextView symbols_kilojoules;
	TextView symbols_megajoules;
	TextView symbols_kilowatts;

	TextView unit_name_calories;
	TextView unit_name_kilocalories;
	TextView unit_name_joules;
	TextView unit_name_kilojoules;
	TextView unit_name_megajoules;
	TextView unit_name_kilowatts;

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
		ll_micrograms = (LinearLayout) view.findViewById(R.id.centimeters_ll);
		ll_kilocalories = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_kilojoules = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_megajoules = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_calories = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_pint = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_kilowatts = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_gallon = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_joules = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);
		
		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);
		ll_pint.setVisibility(View.GONE);
		ll_gallon.setVisibility(View.GONE);
		ll_micrograms.setVisibility(View.GONE);

		ca_kilocalories = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_kilojoules = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_megajoules = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_calories = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_kilowatts = (ImageView) view.findViewById(R.id.miles_conversion_arrow);
		ca_joules = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_kilocalories = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_kilojoules = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_megajoules = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_calories = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_kilowatts = (TextView) view.findViewById(R.id.miles_conversions);
		conversions_joules = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_kilocalories = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_kilojoules = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_megajoules = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_calories = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_kilowatts = (TextView) view.findViewById(R.id.miles_symbols);
		symbols_joules = (TextView) view.findViewById(R.id.yards_symbols);

		unit_name_kilocalories = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_kilojoules = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_megajoules = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_calories = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_kilowatts = (TextView) view.findViewById(R.id.miles_unit);
		unit_name_joules = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_pressure_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_power_heading_light);
		currentHeading.setImageResource(R.drawable.uc_energy_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_energy_bullets);

		symbols_calories.setText("cal");
		symbols_kilocalories.setText("kcal");
		symbols_joules.setText("J");
		symbols_kilojoules.setText("kJ");
		symbols_megajoules.setText("MJ");
		symbols_kilowatts.setText("hp");

		unit_name_calories.setText("Calories");
		unit_name_kilocalories.setText("Kilocalories");
		unit_name_joules.setText("Joules");
		unit_name_kilojoules.setText("Kilojoules");
		unit_name_megajoules.setText("Megajoules");
		unit_name_kilowatts.setText("Kilowatt-Hours");
		
		ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);

		next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterPressureFragment();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterPressureFragment();
			}
		});

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterPowerFragment();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterPowerFragment();

			}
		});

		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);

		
		ll_kilocalories.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				kilocaloriesToOther();
				// storeValueToString();

			}
		});
		ll_kilojoules.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				kilojoulesToOther();
				// storeValueToString();

			}
		});
		ll_megajoules.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				megajoulesToOther();
				// storeValueToString();

			}
		});
		ll_calories.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				caloriesToOther();
				// storeValueToString();

			}
		});
		
		ll_kilowatts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				kilowattsToOther();
				// storeValueToString();

			}
		});

		ll_joules.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				joulesToOther();
				// storeValueToString();

			}
		});

		final ImageView uc_slider_area = (ImageView) view.findViewById(R.id.uc_slider_area);
		final ImageView uc_slider_datasize = (ImageView) view.findViewById(R.id.uc_slider_datasize);
		final ImageView uc_slider_energy = (ImageView) view.findViewById(R.id.uc_slider_energy);
		uc_slider_energy.setImageResource(R.drawable.uc_slider_energy_active);
		final ImageView uc_slider_force = (ImageView) view.findViewById(R.id.uc_slider_force);
		final ImageView uc_slider_length = (ImageView) view.findViewById(R.id.uc_slider_length);
		final ImageView uc_slider_power = (ImageView) view.findViewById(R.id.uc_slider_power);
		final ImageView uc_slider_pressure = (ImageView) view.findViewById(R.id.uc_slider_pressure);
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
		ll_micrograms.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilocalories.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilojoules.setBackgroundColor(Color.parseColor("#00000000"));
		ll_megajoules.setBackgroundColor(Color.parseColor("#00000000"));
		ll_calories.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pint.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilowatts.setBackgroundColor(Color.parseColor("#00000000"));
		ll_gallon.setBackgroundColor(Color.parseColor("#00000000"));
		ll_joules.setBackgroundColor(Color.parseColor("#00000000"));
		ca_kilocalories.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilojoules.setImageResource(R.drawable.uc_conversion_arrow);
		ca_megajoules.setImageResource(R.drawable.uc_conversion_arrow);
		ca_calories.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilowatts.setImageResource(R.drawable.uc_conversion_arrow);
		ca_joules.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_kilocalories.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilojoules.setTextColor(Color.parseColor("#ffffff"));
		conversions_megajoules.setTextColor(Color.parseColor("#ffffff"));
		conversions_calories.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilowatts.setTextColor(Color.parseColor("#ffffff"));
		conversions_joules.setTextColor(Color.parseColor("#ffffff"));

		symbols_kilocalories.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilojoules.setTextColor(Color.parseColor("#ffffff"));
		symbols_megajoules.setTextColor(Color.parseColor("#ffffff"));
		symbols_calories.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilowatts.setTextColor(Color.parseColor("#ffffff"));
		symbols_joules.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_calories.setBackgroundColor(Color.parseColor("#30000000"));
			ca_calories
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_calories.setTextColor(Color.parseColor("#cc58ca"));
			symbols_calories.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_kilocalories.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilocalories
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilocalories.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilocalories.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_joules.setBackgroundColor(Color.parseColor("#30000000"));
			ca_joules
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_joules.setTextColor(Color.parseColor("#cc58ca"));
			symbols_joules.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_kilojoules.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilojoules
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilojoules.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilojoules.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_megajoules.setBackgroundColor(Color.parseColor("#30000000"));
			ca_megajoules
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_megajoules.setTextColor(Color.parseColor("#cc58ca"));
			symbols_megajoules.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_kilowatts.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilowatts.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilowatts.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilowatts.setTextColor(Color.parseColor("#cc58ca"));

		}


	}

	public void caloriesToOther() {
		conversions_kilocalories.setText("0.001");
		conversions_kilojoules.setText("0.004184");
		conversions_megajoules.setText("4.18400E-6");
		conversions_calories.setText("1");
		conversions_kilowatts.setText("1.16222222E-6");
		conversions_joules.setText("4.18400");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void kilocaloriesToOther() {
		conversions_kilocalories.setText("1");
		conversions_kilojoules.setText("4.18400");
		conversions_megajoules.setText("0.004184");
		conversions_calories.setText("1000");
		conversions_kilowatts.setText("0.00116222222");
		conversions_joules.setText("4184");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void kilojoulesToOther() {
		conversions_kilocalories.setText("0.239005736");
		conversions_kilojoules.setText("1");
		conversions_megajoules.setText("0.001");
		conversions_calories.setText("239.005736");
		conversions_kilowatts.setText("0.000277778");
		conversions_joules.setText("1000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void megajoulesToOther() {
		conversions_kilocalories.setText("239.005736");
		conversions_kilojoules.setText("1000");
		conversions_megajoules.setText("1");
		conversions_calories.setText("239005.736");
		conversions_kilowatts.setText("0.277777778");
		conversions_joules.setText("1000000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void kilowattsToOther() {
		conversions_kilocalories.setText("860.42065");
		conversions_kilojoules.setText("3600");
		conversions_megajoules.setText("3.6");
		conversions_calories.setText("860420.65");
		conversions_kilowatts.setText("1");
		conversions_joules.setText("3600000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void joulesToOther() {
		conversions_kilocalories.setText("0.000239005736");
		conversions_kilojoules.setText("0.001");
		conversions_megajoules.setText("1.0E-6");
		conversions_calories.setText("0.239005736");
		conversions_kilowatts.setText("2.77777778E-7");
		conversions_joules.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_kilocalories = conversions_kilocalories.getText().toString();
		value_kilojoules = conversions_kilojoules.getText().toString();
		value_megajoules = conversions_megajoules.getText().toString();
		value_calories = conversions_calories.getText().toString();
		value_kilowatts = conversions_kilowatts.getText().toString();
		value_joules = conversions_joules.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		caloriesToOther();

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_kilocalories.setTypeface(ttf);
		conversions_kilojoules.setTypeface(ttf);
		conversions_megajoules.setTypeface(ttf);
		conversions_calories.setTypeface(ttf);
		conversions_kilowatts.setTypeface(ttf);
		conversions_joules.setTypeface(ttf);
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
		

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilocalories)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_kilocalories.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_kilocalories.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilojoules)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_kilojoules.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_kilojoules.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_megajoules)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_megajoules.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_megajoules.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_calories)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_calories.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_calories.setText(val6 + "");
		}


		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilowatts)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_kilowatts.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_kilowatts.setText(val8 + "");
		}

		
		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_joules)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_joules.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_joules.setText(val11 + "");
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
