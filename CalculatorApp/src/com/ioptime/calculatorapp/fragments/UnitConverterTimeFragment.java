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

public class UnitConverterTimeFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;
	LinearLayout ll_nanoseconds;
	LinearLayout ll_microseconds;
	LinearLayout ll_miliseconds;
	LinearLayout ll_seconds;
	LinearLayout ll_minutes;
	LinearLayout ll_hours;
	LinearLayout ll_days;
	LinearLayout ll_weeks;
	LinearLayout ll_months;
	LinearLayout ll_years;
	LinearLayout ll_lightyears;

	ImageView ca_nanoseconds;
	ImageView ca_microseconds;
	ImageView ca_miliseconds;
	ImageView ca_seconds;
	ImageView ca_minutes;
	ImageView ca_hours;
	ImageView ca_days;
	ImageView ca_weeks;
	ImageView ca_months;
	ImageView ca_years;

	TextView conversions_nanoseconds;
	TextView conversions_microseconds;
	TextView conversions_miliseconds;
	TextView conversions_seconds;
	TextView conversions_minutes;
	TextView conversions_hours;
	TextView conversions_days;
	TextView conversions_weeks;
	TextView conversions_months;
	TextView conversions_years;

	TextView symbols_nanoseconds;
	TextView symbols_microseconds;
	TextView symbols_miliseconds;
	TextView symbols_seconds;
	TextView symbols_minutes;
	TextView symbols_hours;
	TextView symbols_days;
	TextView symbols_weeks;
	TextView symbols_months;
	TextView symbols_years;

	String value_nanoseconds;
	String value_microseconds;
	String value_miliseconds;
	String value_seconds;
	String value_minutes;
	String value_hours;
	String value_days;
	String value_weeks;
	String value_months;
	String value_years;
	
	TextView unit_name_nanoseconds;
	TextView unit_name_microseconds;
	TextView unit_name_miliseconds;
	TextView unit_name_seconds;
	TextView unit_name_minutes;
	TextView unit_name_hours;
	TextView unit_name_days;
	TextView unit_name_weeks;
	TextView unit_name_months;
	TextView unit_name_years;

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
		ll_days = (LinearLayout) view.findViewById(R.id.centimeters_ll);
		ll_microseconds = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_seconds = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_minutes = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);
		ll_nanoseconds = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_months = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_hours = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_weeks = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_years = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_miliseconds = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_lightyears.setVisibility(View.GONE);

		ca_days = (ImageView) view.findViewById(R.id.centimeters_conversion_arrow);
		ca_microseconds = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_seconds = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_minutes = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_nanoseconds = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_months = (ImageView) view.findViewById(R.id.microns_conversion_arrow);
		ca_hours = (ImageView) view.findViewById(R.id.miles_conversion_arrow);
		ca_weeks = (ImageView) view.findViewById(R.id.milimeters_conversion_arrow);
		ca_years = (ImageView) view.findViewById(R.id.nanometers_conversion_arrow);
		ca_miliseconds = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_days = (TextView) view.findViewById(R.id.centimeters_conversions);
		conversions_microseconds = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_seconds = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_minutes = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_nanoseconds = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_months = (TextView) view.findViewById(R.id.microns_conversions);
		conversions_hours = (TextView) view.findViewById(R.id.miles_conversions);
		conversions_weeks = (TextView) view.findViewById(R.id.milimeters_conversions);
		conversions_years = (TextView) view.findViewById(R.id.nanometers_conversions);
		conversions_miliseconds = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_days = (TextView) view.findViewById(R.id.centimeters_symbols);
		symbols_microseconds = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_seconds = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_minutes = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_nanoseconds = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_months = (TextView) view.findViewById(R.id.microns_symbols);
		symbols_hours = (TextView) view.findViewById(R.id.miles_symbols);
		symbols_weeks = (TextView) view.findViewById(R.id.milimeters_symbols);
		symbols_years = (TextView) view.findViewById(R.id.nanometers_symbols);
		symbols_miliseconds = (TextView) view.findViewById(R.id.yards_symbols);
		
		unit_name_days = (TextView) view.findViewById(R.id.centimeters_unit);
		unit_name_microseconds = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_seconds = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_minutes = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_nanoseconds = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_months = (TextView) view.findViewById(R.id.microns_unit);
		unit_name_hours = (TextView) view.findViewById(R.id.miles_unit);
		unit_name_weeks = (TextView) view.findViewById(R.id.milimeters_unit);
		unit_name_years = (TextView) view.findViewById(R.id.nanometers_unit);
		unit_name_miliseconds = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_speed_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_weight_heading_light);
		currentHeading.setImageResource(R.drawable.uc_time_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_time_bullets);

		 symbols_nanoseconds.setText("ns");
		 symbols_microseconds.setText("µs");
		 symbols_miliseconds.setText("ms");
		 symbols_seconds.setText("s");
		 symbols_minutes.setText("min");
		 symbols_hours.setText("h");
		 symbols_days.setText("d");
		 symbols_weeks.setText("wk");
		 symbols_months.setText("mth");
		 symbols_years.setText("yr");

		 unit_name_nanoseconds.setText("Nanoseconds");
		 unit_name_microseconds.setText("Microseconds");
		 unit_name_miliseconds.setText("Miliseconds");
		 unit_name_seconds.setText("Seconds");
		 unit_name_minutes.setText("Minutes");
		 unit_name_hours.setText("Hours");
		 unit_name_days.setText("Days");
		 unit_name_weeks.setText("Weeks");
		 unit_name_months.setText("Months");
		 unit_name_years.setText("Years");
		
		 ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);

		next_empty_view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					MainActivityA.getInstance().showUnitConverterSpeedFragment();
				}
			});

			nextScreenValue.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					MainActivityA.getInstance().showUnitConverterSpeedFragment();
				}
			});

			prev_empty_view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					MainActivityA.getInstance().showUnitConverterWeightFragment();

				}
			});

			prevScreenValue.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					MainActivityA.getInstance().showUnitConverterWeightFragment();

				}
			});


		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);

		ll_days.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("cm");
				daysToOther();
				// storeValueToString();

			}
		});
		ll_microseconds.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				microsecondsToOther();
				// storeValueToString();

			}
		});
		ll_seconds.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				secondsToOther();
				// storeValueToString();

			}
		});
		ll_minutes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				minutesToOther();
				// storeValueToString();

			}
		});
		
		ll_nanoseconds.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				nanosecondsToOther();
				// storeValueToString();

			}
		});
		ll_months.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("um");
				monthsToOther();
				// storeValueToString();

			}
		});
		ll_hours.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				hoursToOther();
				// storeValueToString();

			}
		});
		ll_weeks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mm");
				weeksToOther();
				// storeValueToString();
			}
		});
		ll_years.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("nm");
				yearsToOther();
				// storeValueToString();

			}
		});
		ll_miliseconds.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				milisecondsToOther();
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
		final ImageView uc_slider_speed = (ImageView) view.findViewById(R.id.uc_slider_speed);
		final ImageView uc_slider_temp = (ImageView) view.findViewById(R.id.uc_slider_temp);
		final ImageView uc_slider_time = (ImageView) view.findViewById(R.id.uc_slider_time);
		uc_slider_time.setImageResource(R.drawable.uc_slider_time_active);
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
		ll_days.setBackgroundColor(Color.parseColor("#00000000"));
		ll_microseconds.setBackgroundColor(Color.parseColor("#00000000"));
		ll_seconds.setBackgroundColor(Color.parseColor("#00000000"));
		ll_minutes.setBackgroundColor(Color.parseColor("#00000000"));
		ll_lightyears.setBackgroundColor(Color.parseColor("#00000000"));
		ll_nanoseconds.setBackgroundColor(Color.parseColor("#00000000"));
		ll_months.setBackgroundColor(Color.parseColor("#00000000"));
		ll_hours.setBackgroundColor(Color.parseColor("#00000000"));
		ll_weeks.setBackgroundColor(Color.parseColor("#00000000"));
		ll_years.setBackgroundColor(Color.parseColor("#00000000"));
		ll_miliseconds.setBackgroundColor(Color.parseColor("#00000000"));
		ca_days.setImageResource(R.drawable.uc_conversion_arrow);
		ca_microseconds.setImageResource(R.drawable.uc_conversion_arrow);
		ca_seconds.setImageResource(R.drawable.uc_conversion_arrow);
		ca_minutes.setImageResource(R.drawable.uc_conversion_arrow);
		ca_nanoseconds.setImageResource(R.drawable.uc_conversion_arrow);
		ca_months.setImageResource(R.drawable.uc_conversion_arrow);
		ca_hours.setImageResource(R.drawable.uc_conversion_arrow);
		ca_weeks.setImageResource(R.drawable.uc_conversion_arrow);
		ca_years.setImageResource(R.drawable.uc_conversion_arrow);
		ca_miliseconds.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_days.setTextColor(Color.parseColor("#ffffff"));
		conversions_microseconds.setTextColor(Color.parseColor("#ffffff"));
		conversions_seconds.setTextColor(Color.parseColor("#ffffff"));
		conversions_minutes.setTextColor(Color.parseColor("#ffffff"));
		conversions_nanoseconds.setTextColor(Color.parseColor("#ffffff"));
		conversions_months.setTextColor(Color.parseColor("#ffffff"));
		conversions_hours.setTextColor(Color.parseColor("#ffffff"));
		conversions_weeks.setTextColor(Color.parseColor("#ffffff"));
		conversions_years.setTextColor(Color.parseColor("#ffffff"));
		conversions_miliseconds.setTextColor(Color.parseColor("#ffffff"));

		symbols_days.setTextColor(Color.parseColor("#ffffff"));
		symbols_microseconds.setTextColor(Color.parseColor("#ffffff"));
		symbols_seconds.setTextColor(Color.parseColor("#ffffff"));
		symbols_minutes.setTextColor(Color.parseColor("#ffffff"));
		symbols_nanoseconds.setTextColor(Color.parseColor("#ffffff"));
		symbols_months.setTextColor(Color.parseColor("#ffffff"));
		symbols_hours.setTextColor(Color.parseColor("#ffffff"));
		symbols_weeks.setTextColor(Color.parseColor("#ffffff"));
		symbols_years.setTextColor(Color.parseColor("#ffffff"));
		symbols_miliseconds.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_nanoseconds.setBackgroundColor(Color.parseColor("#30000000"));
			ca_nanoseconds.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_nanoseconds.setTextColor(Color.parseColor("#cc58ca"));
			symbols_nanoseconds.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_microseconds.setBackgroundColor(Color.parseColor("#30000000"));
			ca_microseconds.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_microseconds.setTextColor(Color.parseColor("#cc58ca"));
			symbols_microseconds.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_miliseconds.setBackgroundColor(Color.parseColor("#30000000"));
			ca_miliseconds.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_miliseconds.setTextColor(Color.parseColor("#cc58ca"));
			symbols_miliseconds.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_seconds.setBackgroundColor(Color.parseColor("#30000000"));
			ca_seconds.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_seconds.setTextColor(Color.parseColor("#cc58ca"));
			symbols_seconds.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_minutes.setBackgroundColor(Color.parseColor("#30000000"));
			ca_minutes
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_minutes.setTextColor(Color.parseColor("#cc58ca"));
			symbols_minutes.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_hours.setBackgroundColor(Color.parseColor("#30000000"));
			ca_hours.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_hours.setTextColor(Color.parseColor("#cc58ca"));
			symbols_hours.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("cm")) {
			ll_days.setBackgroundColor(Color.parseColor("#30000000"));
			ca_days
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_days.setTextColor(Color.parseColor("#cc58ca"));
			symbols_days.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mm")) {

			ll_weeks.setBackgroundColor(Color.parseColor("#30000000"));
			ca_weeks
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_weeks.setTextColor(Color.parseColor("#cc58ca"));
			symbols_weeks.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("um")) {
			ll_months.setBackgroundColor(Color.parseColor("#30000000"));
			ca_months
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_months.setTextColor(Color.parseColor("#cc58ca"));
			symbols_months.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("nm")) {

			ll_years.setBackgroundColor(Color.parseColor("#30000000"));
			ca_years
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_years.setTextColor(Color.parseColor("#cc58ca"));
			symbols_years.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void nanosecondsToOther() {
		conversions_days.setText("1.15741E-14");
		conversions_microseconds.setText("0.001");
		conversions_seconds.setText("1.0E-9");
		conversions_minutes.setText("1.66667E-11");
		conversions_nanoseconds.setText("1");
		conversions_months.setText("3.80265E-16");
		conversions_hours.setText("2.77778E-13");
		conversions_weeks.setText("1.65344E-15");
		conversions_years.setText("3.16888E-17");
		conversions_miliseconds.setText("1.0E-6");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void daysToOther() {
		conversions_days.setText("1");
		conversions_microseconds.setText("86400000000");
		conversions_seconds.setText("86400");
		conversions_minutes.setText("1440");
		conversions_nanoseconds.setText("8.64E13");
		conversions_months.setText("0.0328549");
		conversions_hours.setText("24");
		conversions_weeks.setText("0.142857");
		conversions_years.setText("0.00273791");
		conversions_miliseconds.setText("86400000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void microsecondsToOther() {
		conversions_days.setText("1.15741E-11");
		conversions_microseconds.setText("1");
		conversions_seconds.setText("1.0E-6");
		conversions_minutes.setText("1.66667E-8");
		conversions_nanoseconds.setText("1000");
		conversions_months.setText("3.80265E-13");
		conversions_hours.setText("2.77778E-10");
		conversions_weeks.setText("1.65344E-12");
		conversions_years.setText("3.16888E-14");
		conversions_miliseconds.setText("0.001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void secondsToOther() {
		conversions_days.setText("1.15741E-5");
		conversions_microseconds.setText("1000000");
		conversions_seconds.setText("1");
		conversions_minutes.setText("0.0166667");
		conversions_nanoseconds.setText("1000000000");
		conversions_months.setText("3.80265E-7");
		conversions_hours.setText("0.000277778");
		conversions_weeks.setText("1.65344E-6");
		conversions_years.setText("3.16888E-8");
		conversions_miliseconds.setText("1000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	

	public void minutesToOther() {
		conversions_days.setText("0.000694444");
		conversions_microseconds.setText("60000000");
		conversions_seconds.setText("3.93700787E-5");
		conversions_minutes.setText("1");
		conversions_nanoseconds.setText("60000000000");
		conversions_months.setText("2.28159E-5");
		conversions_hours.setText("0.0166667");
		conversions_weeks.setText("9.92063E-5");
		conversions_years.setText("1.90133E-6");
		conversions_miliseconds.setText("60000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}
	
	public void monthsToOther() {
		conversions_days.setText("30.4368");
		conversions_microseconds.setText("2629743831225");
		conversions_seconds.setText("2.62974E6");
		conversions_minutes.setText("1");
		conversions_nanoseconds.setText("2.62974E15");
		conversions_months.setText("1");
		conversions_hours.setText("730.484");
		conversions_weeks.setText("4.34812");
		conversions_years.setText("0.0833333");
		conversions_miliseconds.setText("2.62974E9");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}


	public void hoursToOther() {
		conversions_days.setText("0.0416667");
		conversions_microseconds.setText("3600000000");
		conversions_seconds.setText("3600");
		conversions_minutes.setText("60");
		conversions_nanoseconds.setText("3600000000000");
		conversions_months.setText("0.00136895");
		conversions_hours.setText("1");
		conversions_weeks.setText("0.00595238");
		conversions_years.setText("0.00011408");
		conversions_miliseconds.setText("3600000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void weeksToOther() {
		conversions_days.setText("7");
		conversions_microseconds.setText("604800000000");
		conversions_seconds.setText("604800");
		conversions_minutes.setText("10080");
		conversions_nanoseconds.setText("6.048E14");
		conversions_months.setText("0.229984");
		conversions_hours.setText("168");
		conversions_weeks.setText("1");
		conversions_years.setText("0.0191654");
		conversions_miliseconds.setText("604800000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void yearsToOther() {
		conversions_days.setText("365.242");
		conversions_microseconds.setText("3.15569E13");
		conversions_seconds.setText("3.15569E7");
		conversions_minutes.setText("525949");
		conversions_nanoseconds.setText("3.15569E16");
		conversions_months.setText("12");
		conversions_hours.setText("8765.81");
		conversions_weeks.setText("52.1775");
		conversions_years.setText("1");
		conversions_miliseconds.setText("3.15569E10");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void milisecondsToOther() {
		conversions_days.setText("1.15741E-8");
		conversions_microseconds.setText("1000");
		conversions_seconds.setText("0.001");
		conversions_minutes.setText("1.66667E-5");
		conversions_nanoseconds.setText("1000000");
		conversions_months.setText("3.80265E-10");
		conversions_hours.setText("2.77778E-7");
		conversions_weeks.setText("1.65344E-9");
		conversions_years.setText("3.16888E-11");
		conversions_miliseconds.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_days = conversions_days.getText().toString();
		value_microseconds = conversions_microseconds.getText().toString();
		value_seconds = conversions_seconds.getText().toString();
		value_minutes = conversions_minutes.getText().toString();
		value_nanoseconds = conversions_nanoseconds.getText().toString();
		value_months = conversions_months.getText().toString();
		value_hours = conversions_hours.getText().toString();
		value_weeks = conversions_weeks.getText().toString();
		value_years = conversions_years.getText().toString();
		value_miliseconds = conversions_miliseconds.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		nanosecondsToOther();
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),
				"HelveticaNeue-Medium.otf");

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_days.setTypeface(ttf);
		conversions_microseconds.setTypeface(ttf);
		conversions_seconds.setTypeface(ttf);
		conversions_minutes.setTypeface(ttf);
		conversions_nanoseconds.setTypeface(ttf);
		conversions_months.setTypeface(ttf);
		conversions_hours.setTypeface(ttf);
		conversions_weeks.setTypeface(ttf);
		conversions_years.setTypeface(ttf);
		conversions_miliseconds.setTypeface(ttf);
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
				.valueOf(value_days)));

		String number1 = value1.toString();
		double amount1 = Double.parseDouble(number1);
		DecimalFormat formatter1 = new DecimalFormat("#,###.00");
		String val1 = String.valueOf(amount1);
		if (val1.contains("E")) {
			String arr1[] = val1.split("E");
			if (arr1[0].length() > 10) {
				arr1[0] = arr1[0].substring(0, 10);
			}
			conversions_days.setText(arr1[0] + "E" + arr1[1]);
		} else {
			if (val1.length() > 16)
				val1 = val1.substring(0, 16);
			conversions_days.setText(val1 + "");
		}

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_microseconds)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_microseconds.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_microseconds.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_seconds)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_seconds.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_seconds.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_minutes)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_minutes.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_minutes.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_nanoseconds)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_nanoseconds.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_nanoseconds.setText(val6 + "");
		}

		BigDecimal value7 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_months)));
		String number7 = value7.toString();
		double amount7 = Double.parseDouble(number7);
		DecimalFormat formatter7 = new DecimalFormat("#,###.00");
		String val7 = String.valueOf(amount7);
		if (val7.contains("E")) {
			String arr7[] = val7.split("E");
			if (arr7[0].length() > 10) {
				arr7[0] = arr7[0].substring(0, 10);
			}
			conversions_months.setText(arr7[0] + "E" + arr7[1]);
		} else {
			if (val7.length() > 16)
				val7 = val7.substring(0, 16);
			conversions_months.setText(val7 + "");
		}

		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_hours)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_hours.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_hours.setText(val8 + "");
		}

		BigDecimal value9 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_weeks)));
		String number9 = value9.toString();
		double amount9 = Double.parseDouble(number9);
		DecimalFormat formatter9 = new DecimalFormat("#,###.00");
		String val9 = String.valueOf(amount9);
		if (val9.contains("E")) {
			String arr9[] = val9.split("E");
			if (arr9[0].length() > 10) {
				arr9[0] = arr9[0].substring(0, 10);
			}
			conversions_weeks.setText(arr9[0] + "E" + arr9[1]);
		} else {
			if (val9.length() > 16)
				val9 = val9.substring(0, 16);
			conversions_weeks.setText(val9 + "");
		}

		BigDecimal value10 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_years)));
		String number10 = value10.toString();
		double amount10 = Double.parseDouble(number10);
		DecimalFormat formatter10 = new DecimalFormat("#,###.00");
		String val10 = String.valueOf(amount10);
		if (val10.contains("E")) {
			String arr10[] = val10.split("E");
			if (arr10[0].length() > 10) {
				arr10[0] = arr10[0].substring(0, 10);
			}
			conversions_years.setText(arr10[0] + "E" + arr10[1]);
		} else {
			if (val10.length() > 16)
				val10 = val10.substring(0, 16);
			conversions_years.setText(val10 + "");
		}

		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_miliseconds)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_miliseconds.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_miliseconds.setText(val11 + "");
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
