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

public class UnitConverterWeightFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_kilograms;
	LinearLayout ll_grams;
	LinearLayout ll_miligrams;
	LinearLayout ll_tonnes;
	LinearLayout ll_pounds;
	LinearLayout ll_ounces;
	LinearLayout ll_micrograms;
	LinearLayout ll_gallon;
	LinearLayout ll_pint;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_kilograms;
	ImageView ca_grams;
	ImageView ca_miligrams;
	ImageView ca_tonnes;
	ImageView ca_pounds;
	ImageView ca_ounces;
	ImageView ca_micrograms;

	TextView conversions_kilograms;
	TextView conversions_grams;
	TextView conversions_miligrams;
	TextView conversions_tonnes;
	TextView conversions_pounds;
	TextView conversions_ounces;
	TextView conversions_micrograms;

	String value_kilograms;
	String value_grams;
	String value_miligrams;
	String value_tonnes;
	String value_pounds;
	String value_ounces;
	String value_micrograms;

	TextView symbols_kilograms;
	TextView symbols_grams;
	TextView symbols_miligrams;
	TextView symbols_tonnes;
	TextView symbols_pounds;
	TextView symbols_ounces;
	TextView symbols_micrograms;

	TextView unit_name_kilograms;
	TextView unit_name_grams;
	TextView unit_name_miligrams;
	TextView unit_name_tonnes;
	TextView unit_name_pounds;
	TextView unit_name_ounces;
	TextView unit_name_micrograms;

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
	int upgradePopUp = 0;
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
			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;
		funtionPad = (LinearLayout) view.findViewById(R.id.functionPad);
		sideNavigationView = (SideNavigationView) view.findViewById(R.id.side_navigation_view);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		uc_edittext = (EditText) view.findViewById(R.id.uc_edittext);
		ll_micrograms = (LinearLayout) view.findViewById(R.id.centimeters_ll);
		ll_grams = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_tonnes = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_pounds = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_kilograms = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_pint = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_ounces = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_gallon = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_miligrams = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);

		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);
		ll_pint.setVisibility(View.GONE);
		ll_gallon.setVisibility(View.GONE);

		ca_micrograms = (ImageView) view.findViewById(R.id.centimeters_conversion_arrow);
		ca_grams = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_tonnes = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_pounds = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_kilograms = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_ounces = (ImageView) view.findViewById(R.id.miles_conversion_arrow);
		ca_miligrams = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_micrograms = (TextView) view.findViewById(R.id.centimeters_conversions);
		conversions_grams = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_tonnes = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_pounds = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_kilograms = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_ounces = (TextView) view.findViewById(R.id.miles_conversions);
		conversions_miligrams = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_micrograms = (TextView) view.findViewById(R.id.centimeters_symbols);
		symbols_grams = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_tonnes = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_pounds = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_kilograms = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_ounces = (TextView) view.findViewById(R.id.miles_symbols);
		symbols_miligrams = (TextView) view.findViewById(R.id.yards_symbols);

		unit_name_micrograms = (TextView) view.findViewById(R.id.centimeters_unit);
		unit_name_grams = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_tonnes = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_pounds = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_kilograms = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_ounces = (TextView) view.findViewById(R.id.miles_unit);
		unit_name_miligrams = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_time_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_volume_heading_light);
		currentHeading.setImageResource(R.drawable.uc_weight_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_weight_bullets);

		symbols_kilograms.setText("kg");
		symbols_grams.setText("g");
		symbols_miligrams.setText("mg");
		symbols_tonnes.setText("t");
		symbols_pounds.setText("lb");
		symbols_ounces.setText("oz");
		symbols_micrograms.setText("µg");

		unit_name_kilograms.setText("Kilograms");
		unit_name_grams.setText("Grams");
		unit_name_miligrams.setText("Milligrams");
		unit_name_tonnes.setText("Tonnes");
		unit_name_pounds.setText("Pounds");
		unit_name_ounces.setText("Ounces");
		unit_name_micrograms.setText("Micrograms");
		ImageView next_empty_view = (ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view = (ImageView) view.findViewById(R.id.uc_empty_view_prev);

		next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterTimeFragment();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterTimeFragment();
			}
		});

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterVolumeFragment();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterVolumeFragment();

			}
		});

		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);

		ll_micrograms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("cm");
				microgramsToOther();
				// storeValueToString();

			}
		});
		ll_grams.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				gramsToOther();
				// storeValueToString();

			}
		});
		ll_tonnes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				tonnesToOther();
				// storeValueToString();

			}
		});
		ll_pounds.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				poundsToOther();
				// storeValueToString();

			}
		});
		ll_kilograms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				kilogramsToOther();
				// storeValueToString();

			}
		});

		ll_ounces.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				ouncesToOther();
				// storeValueToString();

			}
		});

		ll_miligrams.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				miligramsToOther();
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
		final ImageView uc_slider_volume = (ImageView) view.findViewById(R.id.uc_slider_volume);
		final ImageView uc_slider_weight = (ImageView) view.findViewById(R.id.uc_slider_weight);
		uc_slider_weight.setImageResource(R.drawable.uc_slider_weight_active);

		uc_slider_area.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (slider == 0) {
					MainActivityA.getInstance().showUnitConverterEnergyFragment();

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
		ll_grams.setBackgroundColor(Color.parseColor("#00000000"));
		ll_tonnes.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pounds.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilograms.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pint.setBackgroundColor(Color.parseColor("#00000000"));
		ll_ounces.setBackgroundColor(Color.parseColor("#00000000"));
		ll_gallon.setBackgroundColor(Color.parseColor("#00000000"));
		ll_miligrams.setBackgroundColor(Color.parseColor("#00000000"));
		ca_micrograms.setImageResource(R.drawable.uc_conversion_arrow);
		ca_grams.setImageResource(R.drawable.uc_conversion_arrow);
		ca_tonnes.setImageResource(R.drawable.uc_conversion_arrow);
		ca_pounds.setImageResource(R.drawable.uc_conversion_arrow);
		ca_kilograms.setImageResource(R.drawable.uc_conversion_arrow);
		ca_ounces.setImageResource(R.drawable.uc_conversion_arrow);
		ca_miligrams.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_micrograms.setTextColor(Color.parseColor("#ffffff"));
		conversions_grams.setTextColor(Color.parseColor("#ffffff"));
		conversions_tonnes.setTextColor(Color.parseColor("#ffffff"));
		conversions_pounds.setTextColor(Color.parseColor("#ffffff"));
		conversions_kilograms.setTextColor(Color.parseColor("#ffffff"));
		conversions_ounces.setTextColor(Color.parseColor("#ffffff"));
		conversions_miligrams.setTextColor(Color.parseColor("#ffffff"));

		symbols_micrograms.setTextColor(Color.parseColor("#ffffff"));
		symbols_grams.setTextColor(Color.parseColor("#ffffff"));
		symbols_tonnes.setTextColor(Color.parseColor("#ffffff"));
		symbols_pounds.setTextColor(Color.parseColor("#ffffff"));
		symbols_kilograms.setTextColor(Color.parseColor("#ffffff"));
		symbols_ounces.setTextColor(Color.parseColor("#ffffff"));
		symbols_miligrams.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_kilograms.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kilograms
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kilograms.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kilograms.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_grams.setBackgroundColor(Color.parseColor("#30000000"));
			ca_grams.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_grams.setTextColor(Color.parseColor("#cc58ca"));
			symbols_grams.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_miligrams.setBackgroundColor(Color.parseColor("#30000000"));
			ca_miligrams
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_miligrams.setTextColor(Color.parseColor("#cc58ca"));
			symbols_miligrams.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_tonnes.setBackgroundColor(Color.parseColor("#30000000"));
			ca_tonnes.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_tonnes.setTextColor(Color.parseColor("#cc58ca"));
			symbols_tonnes.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_pounds.setBackgroundColor(Color.parseColor("#30000000"));
			ca_pounds.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_pounds.setTextColor(Color.parseColor("#cc58ca"));
			symbols_pounds.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_ounces.setBackgroundColor(Color.parseColor("#30000000"));
			ca_ounces.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_ounces.setTextColor(Color.parseColor("#cc58ca"));
			symbols_ounces.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("cm")) {
			ll_micrograms.setBackgroundColor(Color.parseColor("#30000000"));
			ca_micrograms
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_micrograms.setTextColor(Color.parseColor("#cc58ca"));
			symbols_micrograms.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void kilogramsToOther() {
		conversions_micrograms.setText("1000000000");
		conversions_grams.setText("1000");
		conversions_tonnes.setText("0.001");
		conversions_pounds.setText("2.20462");
		conversions_kilograms.setText("1");
		conversions_ounces.setText("35.274");
		conversions_miligrams.setText("1000000");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void microgramsToOther() {
		conversions_micrograms.setText("1");
		conversions_grams.setText("1.0e-6");
		conversions_tonnes.setText("1.0e-12");
		conversions_pounds.setText("2.20462e-9");
		conversions_kilograms.setText("2.20462e-9");
		conversions_ounces.setText("");
		conversions_miligrams.setText("");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void gramsToOther() {
		conversions_micrograms.setText("");
		conversions_grams.setText("1");
		conversions_tonnes.setText("");
		conversions_pounds.setText("");
		conversions_kilograms.setText("");
		conversions_ounces.setText("3.5274e-8");
		conversions_miligrams.setText("0.001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void tonnesToOther() {
		conversions_micrograms.setText("1000000000000");
		conversions_grams.setText("1000000");
		conversions_tonnes.setText("1");
		conversions_pounds.setText("2204.62");
		conversions_kilograms.setText("1000");
		conversions_ounces.setText("35274");
		conversions_miligrams.setText("1000000000");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void poundsToOther() {
		conversions_micrograms.setText("453592370");
		conversions_grams.setText("453.592");
		conversions_tonnes.setText("0.000453592");
		conversions_pounds.setText("1");
		conversions_kilograms.setText("0.453592");
		conversions_ounces.setText("16");
		conversions_miligrams.setText("453592");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void ouncesToOther() {
		conversions_micrograms.setText("2.83495e7");
		conversions_grams.setText("28.3495");
		conversions_tonnes.setText("2.83495e-5");
		conversions_pounds.setText("0.0625");
		conversions_kilograms.setText("0.0283495");
		conversions_ounces.setText("1");
		conversions_miligrams.setText("28349.5");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void miligramsToOther() {
		conversions_micrograms.setText("1000");
		conversions_grams.setText("0.001");
		conversions_tonnes.setText("1.0e-9");
		conversions_pounds.setText("2.20462e-6");
		conversions_kilograms.setText("1.0e-6");
		conversions_ounces.setText("3.5274e-5");
		conversions_miligrams.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_micrograms = conversions_micrograms.getText().toString();
		value_grams = conversions_grams.getText().toString();
		value_tonnes = conversions_tonnes.getText().toString();
		value_pounds = conversions_pounds.getText().toString();
		value_kilograms = conversions_kilograms.getText().toString();
		value_ounces = conversions_ounces.getText().toString();
		value_miligrams = conversions_miligrams.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		kilogramsToOther();
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),
				"HelveticaNeue-Medium.otf");

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_micrograms.setTypeface(ttf);
		conversions_grams.setTypeface(ttf);
		conversions_tonnes.setTypeface(ttf);
		conversions_pounds.setTypeface(ttf);
		conversions_kilograms.setTypeface(ttf);
		conversions_ounces.setTypeface(ttf);
		conversions_miligrams.setTypeface(ttf);
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
				.valueOf(value_micrograms)));

		String number1 = value1.toString();
		double amount1 = Double.parseDouble(number1);
		DecimalFormat formatter1 = new DecimalFormat("#,###.00");
		String val1 = String.valueOf(amount1);
		if (val1.contains("E")) {
			String arr1[] = val1.split("E");
			if (arr1[0].length() > 10) {
				arr1[0] = arr1[0].substring(0, 10);
			}
			conversions_micrograms.setText(arr1[0] + "E" + arr1[1]);
		} else {
			if (val1.length() > 16)
				val1 = val1.substring(0, 16);
			conversions_micrograms.setText(val1 + "");
		}

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_grams)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_grams.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_grams.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_tonnes)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_tonnes.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_tonnes.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_pounds)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_pounds.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_pounds.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_kilograms)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_kilograms.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_kilograms.setText(val6 + "");
		}

		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_ounces)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_ounces.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_ounces.setText(val8 + "");
		}

		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_miligrams)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_miligrams.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_miligrams.setText(val11 + "");
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
