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

public class UnitConverterForceFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_micronewtons;
	LinearLayout ll_millinewtons;
	LinearLayout ll_newtons;
	LinearLayout ll_kilonewtons;
	LinearLayout ll_kilogramforce;
	LinearLayout ll_poundforce;
	LinearLayout ll_micrograms;
	LinearLayout ll_gallon;
	LinearLayout ll_pint;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_micronewtons;
	ImageView ca_millinewtons;
	ImageView ca_newtons;
	ImageView ca_kilonewtons;
	ImageView ca_kilogramforce;
	ImageView ca_poundforce;

	TextView conversions_micronewtons;
	TextView conversions_millinewtons;
	TextView conversions_newtons;
	TextView conversions_kilonewtons;
	TextView conversions_kilogramforce;
	TextView conversions_poundforce;

	String value_micronewtons;
	String value_millinewtons;
	String value_newtons;
	String value_kilonewtons;
	String value_kilogramforce;
	String value_poundforce;

	TextView symbols_micronewtons;
	TextView symbols_millinewtons;
	TextView symbols_newtons;
	TextView symbols_kilonewtons;
	TextView symbols_kilogramforce;
	TextView symbols_poundforce;

	TextView unit_name_micronewtons;
	TextView unit_name_millinewtons;
	TextView unit_name_newtons;
	TextView unit_name_kilonewtons;
	TextView unit_name_kilogramforce;
	TextView unit_name_poundforce;

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
		ll_millinewtons = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_kilonewtons = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_kilogramforce = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_micronewtons = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_pint = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_poundforce = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_gallon = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_newtons = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);
		
		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);
		ll_pint.setVisibility(View.GONE);
		ll_gallon.setVisibility(View.GONE);
		ll_micrograms.setVisibility(View.GONE);

		ca_millinewtons = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_kilonewtons = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_kilogramforce = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_micronewtons = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_poundforce = (ImageView) view.findViewById(R.id.miles_conversion_arrow);
		ca_newtons = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_millinewtons = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_kilonewtons = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_kilogramforce = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_micronewtons = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_poundforce = (TextView) view.findViewById(R.id.miles_conversions);
		conversions_newtons = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_millinewtons = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_kilonewtons = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_kilogramforce = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_micronewtons = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_poundforce = (TextView) view.findViewById(R.id.miles_symbols);
		symbols_newtons = (TextView) view.findViewById(R.id.yards_symbols);

		unit_name_millinewtons = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_kilonewtons = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_kilogramforce = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_micronewtons = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_poundforce = (TextView) view.findViewById(R.id.miles_unit);
		unit_name_newtons = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);
		
		nextScreenValue.setVisibility(View.GONE);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_light);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		prevScreenValue.setImageResource(R.drawable.uc_pressure_heading_light);
		currentHeading.setImageResource(R.drawable.uc_force_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_force_bullets);

		symbols_micronewtons.setText("µN");
		symbols_millinewtons.setText("mN");
		symbols_newtons.setText("N");
		symbols_kilonewtons.setText("kN");
		symbols_kilogramforce.setText("kgF");
		symbols_poundforce.setText("lbF");

		unit_name_micronewtons.setText("Micronewtons");
		unit_name_millinewtons.setText("Millinewtons");
		unit_name_newtons.setText("Newtons");
		unit_name_kilonewtons.setText("Kilonewtons");
		unit_name_kilogramforce.setText("Kilogram-Force");
		unit_name_poundforce.setText("Pound-Force");
		

		ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterPressureFragment();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterPressureFragment();

			}
		});

		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);
		
		ll_millinewtons.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				millinewtonsToOther();
				// storeValueToString();

			}
		});
		ll_kilonewtons.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				kilonewtonsToOther();
				// storeValueToString();

			}
		});
		ll_kilogramforce.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				kilogramforceToOther();
				// storeValueToString();

			}
		});
		ll_micronewtons.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				micronewtonsToOther();
				// storeValueToString();

			}
		});
		
		ll_poundforce.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				poundforceToOther();
				// storeValueToString();

			}
		});

		ll_newtons.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				newtonsToOther();
				// storeValueToString();

			}
		});

		final ImageView uc_slider_area = (ImageView) view.findViewById(R.id.uc_slider_area);
		final ImageView uc_slider_datasize = (ImageView) view.findViewById(R.id.uc_slider_datasize);
		final ImageView uc_slider_energy = (ImageView) view.findViewById(R.id.uc_slider_energy);
		final ImageView uc_slider_force = (ImageView) view.findViewById(R.id.uc_slider_force);
		uc_slider_force.setImageResource(R.drawable.uc_slider_force_active);
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
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterEnergyFragment();
				}
			}
		});

		uc_slider_force.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
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
		ll_millinewtons.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilonewtons.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilogramforce.setBackgroundColor(Color.parseColor("#00000000"));
		ll_micronewtons.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pint.setBackgroundColor(Color.parseColor("#00000000"));
		ll_poundforce.setBackgroundColor(Color.parseColor("#00000000"));
		ll_gallon.setBackgroundColor(Color.parseColor("#00000000"));
		ll_newtons.setBackgroundColor(Color.parseColor("#00000000"));
		ca_millinewtons.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilonewtons.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilogramforce.setImageResource(R.drawable.uc_conversion_arrow);
		ca_micronewtons.setImageResource(R.drawable.uc_conversion_arrow);
		ca_poundforce.setImageResource(R.drawable.uc_conversion_arrow);
		ca_newtons.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_millinewtons.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilonewtons.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilogramforce.setTextColor(Color.parseColor("#ffffff"));
		conversions_micronewtons.setTextColor(Color.parseColor("#ffffff"));
		conversions_poundforce.setTextColor(Color.parseColor("#ffffff"));
		conversions_newtons.setTextColor(Color.parseColor("#ffffff"));

		symbols_millinewtons.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilonewtons.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilogramforce.setTextColor(Color.parseColor("#ffffff"));
		symbols_micronewtons.setTextColor(Color.parseColor("#ffffff"));
		symbols_poundforce.setTextColor(Color.parseColor("#ffffff"));
		symbols_newtons.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_micronewtons.setBackgroundColor(Color.parseColor("#30000000"));
			ca_micronewtons
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_micronewtons.setTextColor(Color.parseColor("#cc58ca"));
			symbols_micronewtons.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_millinewtons.setBackgroundColor(Color.parseColor("#30000000"));
			ca_millinewtons
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_millinewtons.setTextColor(Color.parseColor("#cc58ca"));
			symbols_millinewtons.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_newtons.setBackgroundColor(Color.parseColor("#30000000"));
			ca_newtons
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_newtons.setTextColor(Color.parseColor("#cc58ca"));
			symbols_newtons.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_kilonewtons.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilonewtons
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilonewtons.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilonewtons.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_kilogramforce.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilogramforce
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilogramforce.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilogramforce.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_poundforce.setBackgroundColor(Color.parseColor("#30000000"));
			ca_poundforce.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_poundforce.setTextColor(Color.parseColor("#cc58ca"));
			symbols_poundforce.setTextColor(Color.parseColor("#cc58ca"));

		}


	}

	public void micronewtonsToOther() {
		conversions_millinewtons.setText("0.001");
		conversions_kilonewtons.setText("1.0E-9");
		conversions_kilogramforce.setText("1.01971621E-7");
		conversions_micronewtons.setText("1");
		conversions_poundforce.setText("2.24808943E-7");
		conversions_newtons.setText("1.0E-6");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void millinewtonsToOther() {
		conversions_millinewtons.setText("1");
		conversions_kilonewtons.setText("1.0E-6 ");
		conversions_kilogramforce.setText("0.000101971621");
		conversions_micronewtons.setText("1000");
		conversions_poundforce.setText("0.000224808943");
		conversions_newtons.setText("0.001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void kilonewtonsToOther() {
		conversions_millinewtons.setText("1000000");
		conversions_kilonewtons.setText("1");
		conversions_kilogramforce.setText("101.971621");
		conversions_micronewtons.setText("1000000000");
		conversions_poundforce.setText("224.808943");
		conversions_newtons.setText("1000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void kilogramforceToOther() {
		conversions_millinewtons.setText("9806.65");
		conversions_kilonewtons.setText("0.00980665");
		conversions_kilogramforce.setText("1");
		conversions_micronewtons.setText("9806650");
		conversions_poundforce.setText("2.20462262");
		conversions_newtons.setText("9.80665");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void poundforceToOther() {
		conversions_millinewtons.setText("4448.22162");
		conversions_kilonewtons.setText("0.00444822162");
		conversions_kilogramforce.setText("0.45359237");
		conversions_micronewtons.setText("4448221.62");
		conversions_poundforce.setText("1");
		conversions_newtons.setText("4.44822162");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void newtonsToOther() {
		conversions_millinewtons.setText("1000");
		conversions_kilonewtons.setText("0.001");
		conversions_kilogramforce.setText("0.101971621");
		conversions_micronewtons.setText("1000000");
		conversions_poundforce.setText("0.224808943");
		conversions_newtons.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_millinewtons = conversions_millinewtons.getText().toString();
		value_kilonewtons = conversions_kilonewtons.getText().toString();
		value_kilogramforce = conversions_kilogramforce.getText().toString();
		value_micronewtons = conversions_micronewtons.getText().toString();
		value_poundforce = conversions_poundforce.getText().toString();
		value_newtons = conversions_newtons.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		micronewtonsToOther();

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_millinewtons.setTypeface(ttf);
		conversions_kilonewtons.setTypeface(ttf);
		conversions_kilogramforce.setTypeface(ttf);
		conversions_micronewtons.setTypeface(ttf);
		conversions_poundforce.setTypeface(ttf);
		conversions_newtons.setTypeface(ttf);
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
				.getText().toString()) * Float.valueOf(value_millinewtons)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_millinewtons.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_millinewtons.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilonewtons)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_kilonewtons.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_kilonewtons.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilogramforce)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_kilogramforce.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_kilogramforce.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_micronewtons)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_micronewtons.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_micronewtons.setText(val6 + "");
		}


		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_poundforce)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_poundforce.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_poundforce.setText(val8 + "");
		}

		
		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_newtons)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_newtons.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_newtons.setText(val11 + "");
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
