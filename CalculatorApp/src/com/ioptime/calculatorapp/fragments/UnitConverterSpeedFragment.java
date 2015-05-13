package com.ioptime.calculatorapp.fragments;

import java.math.BigDecimal;

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

public class UnitConverterSpeedFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_meterPerSecond;
	LinearLayout ll_kilometerPerHour;
	LinearLayout ll_FeetPerSecond;
	LinearLayout ll_milesPerHour;
	LinearLayout ll_knots;
	LinearLayout ll_ounces;
	LinearLayout ll_micrograms;
	LinearLayout ll_gallon;
	LinearLayout ll_pint;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_meterPerSecond;
	ImageView ca_kilometerPerHour;
	ImageView ca_FeetPerSecond;
	ImageView ca_milesPerHour;
	ImageView ca_knots;

	TextView conversions_meterPerSecond;
	TextView conversions_kilometerPerHour;
	TextView conversions_FeetPerSecond;
	TextView conversions_milesPerHour;
	TextView conversions_knots;

	String value_meterPerSecond;
	String value_kilometerPerHour;
	String value_FeetPerSecond;
	String value_milesPerHour;
	String value_knots;

	TextView symbols_meterPerSecond;
	TextView symbols_kilometerPerHour;
	TextView symbols_FeetPerSecond;
	TextView symbols_milesPerHour;
	TextView symbols_knots;

	TextView unit_name_meterPerSecond;
	TextView unit_name_kilometerPerHour;
	TextView unit_name_FeetPerSecond;
	TextView unit_name_milesPerHour;
	TextView unit_name_knots;

	int slider = 1;
	RelativeLayout rl_slider;
	RelativeLayout rl_slider_parent;
	RelativeLayout.LayoutParams params;
	ImageView uc_slider;
	ImageView uc_slider2;
	
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
		ll_kilometerPerHour = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_milesPerHour = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_knots = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_meterPerSecond = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_pint = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_ounces = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_gallon = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_FeetPerSecond = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);

		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);
		ll_pint.setVisibility(View.GONE);
		ll_gallon.setVisibility(View.GONE);
		ll_ounces.setVisibility(View.GONE);
		ll_micrograms.setVisibility(View.GONE);

		ca_kilometerPerHour = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_milesPerHour = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_knots = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_meterPerSecond = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_FeetPerSecond = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_kilometerPerHour = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_milesPerHour = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_knots = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_meterPerSecond = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_FeetPerSecond = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_kilometerPerHour = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_milesPerHour = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_knots = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_meterPerSecond = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_FeetPerSecond = (TextView) view.findViewById(R.id.yards_symbols);

		unit_name_kilometerPerHour = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_milesPerHour = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_knots = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_meterPerSecond = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_FeetPerSecond = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_datasize_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_time_heading_light);
		currentHeading.setImageResource(R.drawable.uc_speed_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_speed_bullets);

		symbols_meterPerSecond.setText("m/s");
		symbols_kilometerPerHour.setText("km/h");
		symbols_FeetPerSecond.setText("ft/s");
		symbols_milesPerHour.setText("mph");
		symbols_knots.setText("kt");

		unit_name_meterPerSecond.setText("Meters per second");
		unit_name_kilometerPerHour.setText("Kilometers per hour");
		unit_name_FeetPerSecond.setText("Feet per second");
		unit_name_milesPerHour.setText("Miles per hour");
		unit_name_knots.setText("Knots");

		 ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
			ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);
		
			next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterDataSizeFragment();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterDataSizeFragment();
			}
		});

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterTimeFragment();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterTimeFragment();

			}
		});
		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);


		ll_kilometerPerHour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				kilometerPerHourToOther();
				// storeValueToString();

			}
		});
		ll_milesPerHour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				milesPerHourToOther();
				// storeValueToString();

			}
		});
		ll_knots.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				knotsToOther();
				// storeValueToString();

			}
		});
		ll_meterPerSecond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				meterPerSecondToOther();
				// storeValueToString();

			}
		});

		ll_FeetPerSecond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				FeetPerSecondToOther();
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
		uc_slider_speed.setImageResource(R.drawable.uc_slider_speed_active);
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


	public boolean onTouchEvent(MotionEvent event) {
		// InputMethodManager imm = (InputMethodManager)
		// getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return true;
	}

	public void changeBackGround(String val) {
		ll_micrograms.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilometerPerHour.setBackgroundColor(Color.parseColor("#00000000"));
		ll_milesPerHour.setBackgroundColor(Color.parseColor("#00000000"));
		ll_knots.setBackgroundColor(Color.parseColor("#00000000"));
		ll_meterPerSecond.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pint.setBackgroundColor(Color.parseColor("#00000000"));
		ll_ounces.setBackgroundColor(Color.parseColor("#00000000"));
		ll_gallon.setBackgroundColor(Color.parseColor("#00000000"));
		ll_FeetPerSecond.setBackgroundColor(Color.parseColor("#00000000"));
		ca_kilometerPerHour.setImageResource(R.drawable.uc_conversion_arrow);
		ca_milesPerHour.setImageResource(R.drawable.uc_conversion_arrow);
		ca_knots.setImageResource(R.drawable.uc_conversion_arrow);
		ca_meterPerSecond.setImageResource(R.drawable.uc_conversion_arrow);
		ca_FeetPerSecond.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_kilometerPerHour.setTextColor(Color.parseColor("#ffffff"));
		conversions_milesPerHour.setTextColor(Color.parseColor("#ffffff"));
		conversions_knots.setTextColor(Color.parseColor("#ffffff"));
		conversions_meterPerSecond.setTextColor(Color.parseColor("#ffffff"));
		conversions_FeetPerSecond.setTextColor(Color.parseColor("#ffffff"));

		symbols_kilometerPerHour.setTextColor(Color.parseColor("#ffffff"));
		symbols_milesPerHour.setTextColor(Color.parseColor("#ffffff"));
		symbols_knots.setTextColor(Color.parseColor("#ffffff"));
		symbols_meterPerSecond.setTextColor(Color.parseColor("#ffffff"));
		symbols_FeetPerSecond.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_meterPerSecond.setBackgroundColor(Color.parseColor("#30000000"));
			ca_meterPerSecond
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_meterPerSecond
					.setTextColor(Color.parseColor("#cc58ca"));
			symbols_meterPerSecond.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_kilometerPerHour.setBackgroundColor(Color
					.parseColor("#30000000"));
			ca_kilometerPerHour
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilometerPerHour.setTextColor(Color
					.parseColor("#cc58ca"));
			symbols_kilometerPerHour.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_FeetPerSecond.setBackgroundColor(Color.parseColor("#30000000"));
			ca_FeetPerSecond
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_FeetPerSecond.setTextColor(Color.parseColor("#cc58ca"));
			symbols_FeetPerSecond.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_milesPerHour.setBackgroundColor(Color.parseColor("#30000000"));
			ca_milesPerHour
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_milesPerHour.setTextColor(Color.parseColor("#cc58ca"));
			symbols_milesPerHour.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_knots.setBackgroundColor(Color.parseColor("#30000000"));
			ca_knots.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_knots.setTextColor(Color.parseColor("#cc58ca"));
			symbols_knots.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void meterPerSecondToOther() {
		conversions_kilometerPerHour.setText("3.6");
		conversions_milesPerHour.setText("2.23694");
		conversions_knots.setText("1.94384");
		conversions_meterPerSecond.setText("1");
		conversions_FeetPerSecond.setText("3.28084");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void kilometerPerHourToOther() {
		conversions_kilometerPerHour.setText("1");
		conversions_milesPerHour.setText("0.621371");
		conversions_knots.setText("0.539956803");
		conversions_meterPerSecond.setText("0.277778");
		conversions_FeetPerSecond.setText("0.911344");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void milesPerHourToOther() {
		conversions_kilometerPerHour.setText("1.60934");
		conversions_milesPerHour.setText("1");
		conversions_knots.setText("0.868976");
		conversions_meterPerSecond.setText("0.44704");
		conversions_FeetPerSecond.setText("1.46667");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void knotsToOther() {
		conversions_kilometerPerHour.setText("1.852");
		conversions_milesPerHour.setText("1.15078");
		conversions_knots.setText("1");
		conversions_meterPerSecond.setText("0.514444");
		conversions_FeetPerSecond.setText("1.68781");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void FeetPerSecondToOther() {
		conversions_kilometerPerHour.setText("1.09728");
		conversions_milesPerHour.setText("0.681818");
		conversions_knots.setText("0.592484");
		conversions_meterPerSecond.setText("0.3048");
		conversions_FeetPerSecond.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_kilometerPerHour = conversions_kilometerPerHour.getText()
				.toString();
		value_milesPerHour = conversions_milesPerHour.getText().toString();
		value_knots = conversions_knots.getText().toString();
		value_meterPerSecond = conversions_meterPerSecond.getText().toString();
		value_FeetPerSecond = conversions_FeetPerSecond.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		meterPerSecondToOther();

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_kilometerPerHour.setTypeface(ttf);
		conversions_milesPerHour.setTypeface(ttf);
		conversions_knots.setTypeface(ttf);
		conversions_meterPerSecond.setTypeface(ttf);
		conversions_FeetPerSecond.setTypeface(ttf);
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

		BigDecimal value2 = BigDecimal
				.valueOf((Float.valueOf(uc_edittext.getText().toString()) * Float
						.valueOf(value_kilometerPerHour)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_kilometerPerHour.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_kilometerPerHour.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_milesPerHour)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_milesPerHour.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_milesPerHour.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_knots)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_knots.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_knots.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_meterPerSecond)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_meterPerSecond.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_meterPerSecond.setText(val6 + "");
		}

		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_FeetPerSecond)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_FeetPerSecond.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_FeetPerSecond.setText(val11 + "");
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
