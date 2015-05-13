package com.ioptime.calculatorapp.fragments;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.content.Context;
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

public class UnitConverterVolumeFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	LinearLayout funtionPad;
	EditText uc_edittext;

	LinearLayout ll_mililiters;
	LinearLayout ll_liters;
	LinearLayout ll_cumeters;
	LinearLayout ll_cuinches;
	LinearLayout ll_cufeet;
	LinearLayout ll_cuyards;
	LinearLayout ll_fluidounces;
	LinearLayout ll_gallon;
	LinearLayout ll_pint;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_mililiters;
	ImageView ca_liters;
	ImageView ca_cumeters;
	ImageView ca_cuinches;
	ImageView ca_cufeet;
	ImageView ca_cuyards;
	ImageView ca_fluidounces;
	ImageView ca_gallon;
	ImageView ca_pint;

	TextView conversions_mililiters;
	TextView conversions_liters;
	TextView conversions_cumeters;
	TextView conversions_cuinches;
	TextView conversions_cufeet;
	TextView conversions_cuyards;
	TextView conversions_fluidounces;
	TextView conversions_gallon;
	TextView conversions_pint;

	String value_mililiters;
	String value_liters;
	String value_cumeters;
	String value_cuinches;
	String value_cufeet;
	String value_cuyards;
	String value_fluidounces;
	String value_gallon;
	String value_pint;

	TextView symbols_mililiters;
	TextView symbols_liters;
	TextView symbols_cumeters;
	TextView symbols_cuinches;
	TextView symbols_cufeet;
	TextView symbols_cuyards;
	TextView symbols_fluidounces;
	TextView symbols_gallon;
	TextView symbols_pint;

	TextView unit_name_mililiters;
	TextView unit_name_liters;
	TextView unit_name_cumeters;
	TextView unit_name_cuinches;
	TextView unit_name_cufeet;
	TextView unit_name_cuyards;
	TextView unit_name_fluidounces;
	TextView unit_name_gallon;
	TextView unit_name_pint;

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
		ll_fluidounces = (LinearLayout) view.findViewById(R.id.centimeters_ll);
		ll_liters = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_cuinches = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_cufeet = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_mililiters = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_pint = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_cuyards = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_gallon = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_cumeters = (LinearLayout) view.findViewById(R.id.yards_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);
		ll_nanometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);

		ca_fluidounces = (ImageView) view.findViewById(R.id.centimeters_conversion_arrow);
		ca_liters = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_cuinches = (ImageView) view.findViewById(R.id.inches_conversion_arrow);
		ca_cufeet = (ImageView) view.findViewById(R.id.kilometers_conversion_arrow);
		ca_mililiters = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_pint = (ImageView) view.findViewById(R.id.microns_conversion_arrow);
		ca_cuyards = (ImageView) view.findViewById(R.id.miles_conversion_arrow);
		ca_gallon = (ImageView) view.findViewById(R.id.milimeters_conversion_arrow);
		ca_cumeters = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_fluidounces = (TextView) view.findViewById(R.id.centimeters_conversions);
		conversions_liters = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_cuinches = (TextView) view.findViewById(R.id.inches_conversions);
		conversions_cufeet = (TextView) view.findViewById(R.id.kilometers_conversions);
		conversions_mililiters = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_pint = (TextView) view.findViewById(R.id.microns_conversions);
		conversions_cuyards = (TextView) view.findViewById(R.id.miles_conversions);
		conversions_gallon = (TextView) view.findViewById(R.id.milimeters_conversions);
		conversions_cumeters = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_fluidounces = (TextView) view.findViewById(R.id.centimeters_symbols);
		symbols_liters = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_cuinches = (TextView) view.findViewById(R.id.inches_symbols);
		symbols_cufeet = (TextView) view.findViewById(R.id.kilometers_symbols);
		symbols_mililiters = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_pint = (TextView) view.findViewById(R.id.microns_symbols);
		symbols_cuyards = (TextView) view.findViewById(R.id.miles_symbols);
		symbols_gallon = (TextView) view.findViewById(R.id.milimeters_symbols);
		symbols_cumeters = (TextView) view.findViewById(R.id.yards_symbols);

		unit_name_fluidounces = (TextView) view.findViewById(R.id.centimeters_unit);
		unit_name_liters = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_cuinches = (TextView) view.findViewById(R.id.inches_unit);
		unit_name_cufeet = (TextView) view.findViewById(R.id.kilometers_unit);
		unit_name_mililiters = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_pint = (TextView) view.findViewById(R.id.microns_unit);
		unit_name_cuyards = (TextView) view.findViewById(R.id.miles_unit);
		unit_name_gallon = (TextView) view.findViewById(R.id.milimeters_unit);
		unit_name_cumeters = (TextView) view.findViewById(R.id.yards_unit);

		ImageView nextScreenArrow = (ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow = (ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue = (ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue = (ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading = (ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets = (ImageView) view.findViewById(R.id.uc_length_bullets);

		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_weight_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_area_heading_light);
		currentHeading.setImageResource(R.drawable.uc_volume_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_volume_bullets);

		symbols_mililiters.setText("ml");
		symbols_liters.setText("l");
		symbols_cumeters.setText(Html.fromHtml("m<sup>3</sup>"));
		symbols_cuinches.setText("cu in");
		symbols_cufeet.setText("cu ft");
		symbols_cuyards.setText("cu yd");
		symbols_fluidounces.setText("fl oz");
		symbols_gallon.setText("gal");
		symbols_pint.setText("pt");

		unit_name_mililiters.setText("Millileters (cc)");
		unit_name_liters.setText("Liters");
		unit_name_cumeters.setText("Cu.Meters");
		unit_name_cuinches.setText("Cu.Inches");
		unit_name_cufeet.setText("Cu.Feet");
		unit_name_cuyards.setText("Cu.Yards");
		unit_name_fluidounces.setText("Fluid Ounce (US)");
		unit_name_fluidounces.setTextSize(17);
		unit_name_gallon.setText("Gallon (US)");
		unit_name_pint.setText("Pint (US)");

		ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);
		
		next_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterWeightFragment();
			}
		});

		nextScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterWeightFragment();
			}
		});

		prev_empty_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterAreaFragment();

			}
		});

		prevScreenValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterAreaFragment();

			}
		});
		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);

		ll_fluidounces.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("cm");
				fluidouncesToOther();
				// storeValueToString();

			}
		});
		ll_liters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				litersToOther();
				// storeValueToString();

			}
		});
		ll_cuinches.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("in");
				cuinchesToOther();
				// storeValueToString();

			}
		});
		ll_cufeet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("km");
				cufeetToOther();
				// storeValueToString();

			}
		});
		ll_mililiters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				mililitersToOther();
				// storeValueToString();

			}
		});
		ll_pint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("um");
				pintToOther();
				// storeValueToString();

			}
		});
		ll_cuyards.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mi");
				cuyardsToOther();
				// storeValueToString();

			}
		});
		ll_gallon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("mm");
				gallonToOther();
				// storeValueToString();
			}
		});

		ll_cumeters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				cumetersToOther();
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
		uc_slider_volume.setImageResource(R.drawable.uc_slider_volume_active);
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
		ll_fluidounces.setBackgroundColor(Color.parseColor("#00000000"));
		ll_liters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_cuinches.setBackgroundColor(Color.parseColor("#00000000"));
		ll_cufeet.setBackgroundColor(Color.parseColor("#00000000"));
		ll_mililiters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_pint.setBackgroundColor(Color.parseColor("#00000000"));
		ll_cuyards.setBackgroundColor(Color.parseColor("#00000000"));
		ll_gallon.setBackgroundColor(Color.parseColor("#00000000"));
		ll_cumeters.setBackgroundColor(Color.parseColor("#00000000"));
		ca_fluidounces.setImageResource(R.drawable.uc_conversion_arrow);
		ca_liters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_cuinches.setImageResource(R.drawable.uc_conversion_arrow);
		ca_cufeet.setImageResource(R.drawable.uc_conversion_arrow);
		ca_mililiters.setImageResource(R.drawable.uc_conversion_arrow);
		ca_pint.setImageResource(R.drawable.uc_conversion_arrow);
		ca_cuyards.setImageResource(R.drawable.uc_conversion_arrow);
		ca_gallon.setImageResource(R.drawable.uc_conversion_arrow);
		ca_cumeters.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_fluidounces.setTextColor(Color.parseColor("#ffffff"));
		conversions_liters.setTextColor(Color.parseColor("#ffffff"));
		conversions_cuinches.setTextColor(Color.parseColor("#ffffff"));
		conversions_cufeet.setTextColor(Color.parseColor("#ffffff"));
		conversions_mililiters.setTextColor(Color.parseColor("#ffffff"));
		conversions_pint.setTextColor(Color.parseColor("#ffffff"));
		conversions_cuyards.setTextColor(Color.parseColor("#ffffff"));
		conversions_gallon.setTextColor(Color.parseColor("#ffffff"));
		conversions_cumeters.setTextColor(Color.parseColor("#ffffff"));

		symbols_fluidounces.setTextColor(Color.parseColor("#ffffff"));
		symbols_liters.setTextColor(Color.parseColor("#ffffff"));
		symbols_cuinches.setTextColor(Color.parseColor("#ffffff"));
		symbols_cufeet.setTextColor(Color.parseColor("#ffffff"));
		symbols_mililiters.setTextColor(Color.parseColor("#ffffff"));
		symbols_pint.setTextColor(Color.parseColor("#ffffff"));
		symbols_cuyards.setTextColor(Color.parseColor("#ffffff"));
		symbols_gallon.setTextColor(Color.parseColor("#ffffff"));
		symbols_cumeters.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_mililiters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_mililiters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_mililiters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_mililiters.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_liters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_liters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_liters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_liters.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_cumeters.setBackgroundColor(Color.parseColor("#30000000"));
			ca_cumeters
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_cumeters.setTextColor(Color.parseColor("#cc58ca"));
			symbols_cumeters.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("in")) {
			ll_cuinches.setBackgroundColor(Color.parseColor("#30000000"));
			ca_cuinches
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_cuinches.setTextColor(Color.parseColor("#cc58ca"));
			symbols_cuinches.setTextColor(Color.parseColor("#cc58ca"));
		}

		if (val.equals("km")) {
			ll_cufeet.setBackgroundColor(Color.parseColor("#30000000"));
			ca_cufeet
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_cufeet.setTextColor(Color.parseColor("#cc58ca"));
			symbols_cufeet.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mi")) {
			ll_cuyards.setBackgroundColor(Color.parseColor("#30000000"));
			ca_cuyards.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_cuyards.setTextColor(Color.parseColor("#cc58ca"));
			symbols_cuyards.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("cm")) {
			ll_fluidounces.setBackgroundColor(Color.parseColor("#30000000"));
			ca_fluidounces
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_fluidounces.setTextColor(Color.parseColor("#cc58ca"));
			symbols_fluidounces.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("mm")) {

			ll_gallon.setBackgroundColor(Color.parseColor("#30000000"));
			ca_gallon.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_gallon.setTextColor(Color.parseColor("#cc58ca"));
			symbols_gallon.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("um")) {
			ll_pint.setBackgroundColor(Color.parseColor("#30000000"));
			ca_pint
					.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_pint.setTextColor(Color.parseColor("#cc58ca"));
			symbols_pint.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void mililitersToOther() {
		conversions_fluidounces.setText("0.033814");
		conversions_liters.setText("0.001");
		conversions_cuinches.setText("0.0610237");
		conversions_cufeet.setText("3.53147e-5");
		conversions_mililiters.setText("1");
		conversions_pint.setText("0.00211338");
		conversions_cuyards.setText("1.30795062E-6");
		conversions_gallon.setText("0.000264172");
		conversions_cumeters.setText("1.0e-6");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void fluidouncesToOther() {
		conversions_fluidounces.setText("1");
		conversions_liters.setText("0.0295735");
		conversions_cuinches.setText("1.80469");
		conversions_cufeet.setText("0.00104438");
		conversions_mililiters.setText("29.5735296");
		conversions_pint.setText("0.0625");
		conversions_cuyards.setText("3.86807163E-5");
		conversions_gallon.setText("0.0078125");
		conversions_cumeters.setText("2.95735e-5");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void litersToOther() {
		conversions_fluidounces.setText("33.814");
		conversions_liters.setText("1");
		conversions_cuinches.setText("61.0237");
		conversions_cufeet.setText("0.0353147");
		conversions_mililiters.setText("1000");
		conversions_pint.setText("2.11338");
		conversions_cuyards.setText("0.00130795062");
		conversions_gallon.setText("0.264172");
		conversions_cumeters.setText("0.001");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void cuinchesToOther() {
		conversions_fluidounces.setText("0.554113");
		conversions_liters.setText("0.0163871");
		conversions_cuinches.setText("1");
		conversions_cufeet.setText("0.000578704");
		conversions_mililiters.setText("16.3871");
		conversions_pint.setText("0.034632");
		conversions_cuyards.setText("2.14334705E-5");
		conversions_gallon.setText("0.004329");
		conversions_cumeters.setText("1.63871e-5");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void cufeetToOther() {
		conversions_fluidounces.setText("957.506");
		conversions_liters.setText("28.3168");
		conversions_cuinches.setText("1728");
		conversions_cufeet.setText("1");
		conversions_mililiters.setText("28316.8");
		conversions_pint.setText("59.8442");
		conversions_cuyards.setText("0.037037037");
		conversions_gallon.setText("7.48052");
		conversions_cumeters.setText("0.0283168");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void pintToOther() {
		conversions_fluidounces.setText("16");
		conversions_liters.setText("0.473176");
		conversions_cuinches.setText("28.875");
		conversions_cufeet.setText("0.0167101");
		conversions_mililiters.setText("473.176473");
		conversions_pint.setText("1");
		conversions_cuyards.setText("0.000618891461");
		conversions_gallon.setText("0.125");
		conversions_cumeters.setText("0.000473176");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void cuyardsToOther() {
		conversions_fluidounces.setText("25852.6753");
		conversions_liters.setText("764.554858");
		conversions_cuinches.setText("46656");
		conversions_cufeet.setText("27");
		conversions_mililiters.setText("764554.858");
		conversions_pint.setText("1615.79221");
		conversions_cuyards.setText("1");
		conversions_gallon.setText("201.974026");
		conversions_cumeters.setText("0.764554858");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void gallonToOther() {
		conversions_fluidounces.setText("128");
		conversions_liters.setText("3.78541");
		conversions_cuinches.setText("231");
		conversions_cufeet.setText("0.133681");
		conversions_mililiters.setText("3785.41");
		conversions_pint.setText("8");
		conversions_cuyards.setText("0.00495113169");
		conversions_gallon.setText("1");
		conversions_cumeters.setText("0.00378541");
		storeValueToString();
		setValuesAccoridingToEdittext();
	}

	public void cumetersToOther() {
		conversions_fluidounces.setText("33814");
		conversions_liters.setText("1000");
		conversions_cuinches.setText("61023.7");
		conversions_cufeet.setText("35.3147");
		conversions_mililiters.setText("1000000");
		conversions_pint.setText("2113.38");
		conversions_cuyards.setText("1.30795062");
		conversions_gallon.setText("264.172");
		conversions_cumeters.setText("1");
		storeValueToString();
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_fluidounces = conversions_fluidounces.getText().toString();
		value_liters = conversions_liters.getText().toString();
		value_cuinches = conversions_cuinches.getText().toString();
		value_cufeet = conversions_cufeet.getText().toString();
		value_mililiters = conversions_mililiters.getText().toString();
		value_pint = conversions_pint.getText().toString();
		value_cuyards = conversions_cuyards.getText().toString();
		value_gallon = conversions_gallon.getText().toString();
		value_cumeters = conversions_cumeters.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		mililitersToOther();
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),
				"HelveticaNeue-Medium.otf");

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_fluidounces.setTypeface(ttf);
		conversions_liters.setTypeface(ttf);
		conversions_cuinches.setTypeface(ttf);
		conversions_cufeet.setTypeface(ttf);
		conversions_mililiters.setTypeface(ttf);
		conversions_pint.setTypeface(ttf);
		conversions_cuyards.setTypeface(ttf);
		conversions_gallon.setTypeface(ttf);
		conversions_cumeters.setTypeface(ttf);
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
				.valueOf(value_fluidounces)));

		String number1 = value1.toString();
		double amount1 = Double.parseDouble(number1);
		DecimalFormat formatter1 = new DecimalFormat("#,###.00");
		String val1 = String.valueOf(amount1);
		if (val1.contains("E")) {
			String arr1[] = val1.split("E");
			if (arr1[0].length() > 10) {
				arr1[0] = arr1[0].substring(0, 10);
			}
			conversions_fluidounces.setText(arr1[0] + "E" + arr1[1]);
		} else {
			if (val1.length() > 16)
				val1 = val1.substring(0, 16);
			conversions_fluidounces.setText(val1 + "");
		}

		BigDecimal value2 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_liters)));
		String number2 = value2.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_liters.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_liters.setText(val2 + "");
		}

		BigDecimal value3 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_cuinches)));
		String number3 = value3.toString();
		double amount3 = Double.parseDouble(number3);
		DecimalFormat formatter3 = new DecimalFormat("#,###.00");
		String val3 = String.valueOf(amount3);
		if (val3.contains("E")) {
			String arr3[] = val3.split("E");
			if (arr3[0].length() > 10) {
				arr3[0] = arr3[0].substring(0, 10);
			}
			conversions_cuinches.setText(arr3[0] + "E" + arr3[1]);
		} else {
			if (val3.length() > 16)
				val3 = val3.substring(0, 16);
			conversions_cuinches.setText(val3 + "");
		}

		BigDecimal value4 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_cufeet)));
		String number4 = value4.toString();
		double amount4 = Double.parseDouble(number4);
		DecimalFormat formatter4 = new DecimalFormat("#,###.00");
		String val4 = String.valueOf(amount4);
		if (val4.contains("E")) {
			String arr4[] = val4.split("E");
			if (arr4[0].length() > 10) {
				arr4[0] = arr4[0].substring(0, 10);
			}
			conversions_cufeet.setText(arr4[0] + "E" + arr4[1]);
		} else {
			if (val4.length() > 16)
				val4 = val4.substring(0, 16);
			conversions_cufeet.setText(val4 + "");
		}

		BigDecimal value6 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_mililiters)));
		String number6 = value6.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_mililiters.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_mililiters.setText(val6 + "");
		}

		BigDecimal value7 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_pint)));
		String number7 = value7.toString();
		double amount7 = Double.parseDouble(number7);
		DecimalFormat formatter7 = new DecimalFormat("#,###.00");
		String val7 = String.valueOf(amount7);
		if (val7.contains("E")) {
			String arr7[] = val7.split("E");
			if (arr7[0].length() > 10) {
				arr7[0] = arr7[0].substring(0, 10);
			}
			conversions_pint.setText(arr7[0] + "E" + arr7[1]);
		} else {
			if (val7.length() > 16)
				val7 = val7.substring(0, 16);
			conversions_pint.setText(val7 + "");
		}

		BigDecimal value8 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_cuyards)));
		String number8 = value8.toString();
		double amount8 = Double.parseDouble(number8);
		DecimalFormat formatter8 = new DecimalFormat("#,###.00");
		String val8 = String.valueOf(amount8);
		if (val8.contains("E")) {
			String arr8[] = val8.split("E");
			if (arr8[0].length() > 10) {
				arr8[0] = arr8[0].substring(0, 10);
			}
			conversions_cuyards.setText(arr8[0] + "E" + arr8[1]);
		} else {
			if (val8.length() > 16)
				val8 = val8.substring(0, 16);
			conversions_cuyards.setText(val8 + "");
		}

		BigDecimal value9 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_gallon)));
		String number9 = value9.toString();
		double amount9 = Double.parseDouble(number9);
		DecimalFormat formatter9 = new DecimalFormat("#,###.00");
		String val9 = String.valueOf(amount9);
		if (val9.contains("E")) {
			String arr9[] = val9.split("E");
			if (arr9[0].length() > 10) {
				arr9[0] = arr9[0].substring(0, 10);
			}
			conversions_gallon.setText(arr9[0] + "E" + arr9[1]);
		} else {
			if (val9.length() > 16)
				val9 = val9.substring(0, 16);
			conversions_gallon.setText(val9 + "");
		}

		BigDecimal value11 = BigDecimal.valueOf((Float.valueOf(uc_edittext
				.getText().toString()) * Float.valueOf(value_cumeters)));
		String number11 = value11.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_cumeters.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_cumeters.setText(val11 + "");
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
