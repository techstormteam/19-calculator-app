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

public class UnitConverterTempFragment extends SherlockFragment implements
Upgradeable {

	Context ctx;
	ListView lv;

	private final static String FLAG_CELSIUS = "C";
	private final static String FLAG_KELVIN = "K";
	private final static String FLAG_FARENHEIT = "F";
	
	String flagUnit = FLAG_CELSIUS; // C, K, F
	
	LinearLayout funtionPad;
	EditText uc_edittext;
	LinearLayout ll_celsius;
	LinearLayout ll_kelvin;
	LinearLayout ll_farenheit;
	LinearLayout ll_inches;
	LinearLayout ll_kilometers;
	LinearLayout ll_miles;
	LinearLayout ll_centimeters;
	LinearLayout ll_milimeters;
	LinearLayout ll_microns;
	LinearLayout ll_nanometers;
	LinearLayout ll_lightyears;

	ImageView ca_celsius;
	ImageView ca_kelvin;
	ImageView ca_farenheit;

	TextView conversions_celsius;
	TextView conversions_kelvin;
	TextView conversions_farenheit;

	TextView symbols_celsius;
	TextView symbols_kelvin;
	TextView symbols_farenheit;

	String value_celsius;
	String value_kelvin;
	String value_farenheit;
	
	TextView unit_name_celsius;
	TextView unit_name_kelvin;
	TextView unit_name_farenheit;

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
		ll_centimeters = (LinearLayout) view.findViewById(R.id.centimeters_ll);
		ll_kelvin = (LinearLayout) view.findViewById(R.id.feet_ll);
		ll_inches = (LinearLayout) view.findViewById(R.id.inches_ll);
		ll_kilometers = (LinearLayout) view.findViewById(R.id.kilometers_ll);
		ll_lightyears = (LinearLayout) view.findViewById(R.id.lightyears_ll);
		ll_celsius = (LinearLayout) view.findViewById(R.id.meters_ll);
		ll_microns = (LinearLayout) view.findViewById(R.id.microns_ll);
		ll_miles = (LinearLayout) view.findViewById(R.id.miles_ll);
		ll_milimeters = (LinearLayout) view.findViewById(R.id.milimeters_ll);
		ll_nanometers = (LinearLayout) view.findViewById(R.id.nanometers_ll);
		ll_farenheit = (LinearLayout) view.findViewById(R.id.yards_ll);
		
		
		ll_centimeters.setVisibility(View.GONE);
		ll_inches.setVisibility(View.GONE);
		ll_kilometers.setVisibility(View.GONE);
		ll_lightyears.setVisibility(View.GONE);
		ll_microns.setVisibility(View.GONE);
		ll_miles.setVisibility(View.GONE);
		ll_milimeters.setVisibility(View.GONE);
		ll_nanometers.setVisibility(View.GONE);

		ca_kelvin = (ImageView) view.findViewById(R.id.feet_conversion_arrow);
		ca_celsius = (ImageView) view.findViewById(R.id.meters_conversion_arrow);
		ca_farenheit = (ImageView) view.findViewById(R.id.yards_conversion_arrow);

		conversions_kelvin = (TextView) view.findViewById(R.id.feet_conversions);
		conversions_celsius = (TextView) view.findViewById(R.id.meters_conversions);
		conversions_farenheit = (TextView) view.findViewById(R.id.yards_conversions);

		symbols_kelvin = (TextView) view.findViewById(R.id.feet_symbols);
		symbols_celsius = (TextView) view.findViewById(R.id.meters_symbols);
		symbols_farenheit = (TextView) view.findViewById(R.id.yards_symbols);
		
		unit_name_kelvin = (TextView) view.findViewById(R.id.feet_unit);
		unit_name_celsius = (TextView) view.findViewById(R.id.meters_unit);
		unit_name_farenheit = (TextView) view.findViewById(R.id.yards_unit);
		
		ImageView nextScreenArrow=(ImageView) view.findViewById(R.id.uc_right_arrow_dark);
		ImageView prevScreenArrow=(ImageView) view.findViewById(R.id.uc_left_arrow_light);
		ImageView nextScreenValue=(ImageView) view.findViewById(R.id.uc_length_temprature_heading);
		ImageView prevScreenValue=(ImageView) view.findViewById(R.id.uc_length_prev_heading);
		ImageView currentHeading=(ImageView) view.findViewById(R.id.uc_length_heading);
		ImageView ucBullets=(ImageView) view.findViewById(R.id.uc_length_bullets);
		
		nextScreenArrow.setImageResource(R.drawable.uc_right_arrow_dark);
		prevScreenArrow.setImageResource(R.drawable.uc_left_arrow_dark);
		nextScreenValue.setImageResource(R.drawable.uc_area_heading_light);
		prevScreenValue.setImageResource(R.drawable.uc_length_heading_light);
		currentHeading.setImageResource(R.drawable.uc_temperature_heading_dark);
		ucBullets.setImageResource(R.drawable.uc_temperature_bullets);
		
		
		ImageView next_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_next);
		ImageView prev_empty_view=(ImageView) view.findViewById(R.id.uc_empty_view_prev);
		
		next_empty_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterAreaFragment();
			}
		});
		
		nextScreenValue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterAreaFragment();
			}
		});
		
		prev_empty_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterLengthFragment();
				
			}
		});
		
		prevScreenValue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainActivityA.getInstance().showUnitConverterLengthFragment();
				
			}
		});
		
		unit_name_celsius.setText("Celsius");
		unit_name_kelvin.setText("Kelvin");
		unit_name_farenheit.setText("Farenheit");
		
		symbols_kelvin.setText("K");
		symbols_celsius.setText("°C");
		symbols_farenheit.setText("°F");

		defaultConfig();
		uc_edittext.addTextChangedListener(watch);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);

		
		ll_kelvin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("ft");
				kelvinToOther();
				// storeValueToString();

			}
		});
		
		ll_celsius.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("m");
				celciusToOther();
				// storeValueToString();

			}
		});
		
		ll_farenheit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				changeBackGround("yd");
				farenheitToOther();
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
		uc_slider_temp.setImageResource(R.drawable.uc_slider_temp_active);
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
		ll_centimeters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kelvin.setBackgroundColor(Color.parseColor("#00000000"));
		ll_inches.setBackgroundColor(Color.parseColor("#00000000"));
		ll_kilometers.setBackgroundColor(Color.parseColor("#00000000"));
		ll_lightyears.setBackgroundColor(Color.parseColor("#00000000"));
		ll_celsius.setBackgroundColor(Color.parseColor("#00000000"));
		ll_microns.setBackgroundColor(Color.parseColor("#00000000"));
		ll_miles.setBackgroundColor(Color.parseColor("#00000000"));
		ll_milimeters.setBackgroundColor(Color.parseColor("#00000000"));
		ll_nanometers.setBackgroundColor(Color.parseColor("#00000000"));
		ll_farenheit.setBackgroundColor(Color.parseColor("#00000000"));
		ca_kelvin.setImageResource(R.drawable.uc_conversion_arrow);
		ca_celsius.setImageResource(R.drawable.uc_conversion_arrow);
		ca_farenheit.setImageResource(R.drawable.uc_conversion_arrow);

		conversions_kelvin.setTextColor(Color.parseColor("#ffffff"));
		conversions_celsius.setTextColor(Color.parseColor("#ffffff"));
		conversions_farenheit.setTextColor(Color.parseColor("#ffffff"));

		symbols_kelvin.setTextColor(Color.parseColor("#ffffff"));
		symbols_celsius.setTextColor(Color.parseColor("#ffffff"));
		symbols_farenheit.setTextColor(Color.parseColor("#ffffff"));

		if (val.equals("m")) {
			ll_celsius.setBackgroundColor(Color.parseColor("#30000000"));
			ca_celsius.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_celsius.setTextColor(Color.parseColor("#cc58ca"));
			symbols_celsius.setTextColor(Color.parseColor("#cc58ca"));

		}
		if (val.equals("ft")) {
			ll_kelvin.setBackgroundColor(Color.parseColor("#30000000"));
			ca_kelvin.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_kelvin.setTextColor(Color.parseColor("#cc58ca"));
			symbols_kelvin.setTextColor(Color.parseColor("#cc58ca"));

		}

		if (val.equals("yd")) {
			ll_farenheit.setBackgroundColor(Color.parseColor("#30000000"));
			ca_farenheit.setImageResource(R.drawable.uc_conversion_arrow_selected);
			conversions_farenheit.setTextColor(Color.parseColor("#cc58ca"));
			symbols_farenheit.setTextColor(Color.parseColor("#cc58ca"));

		}

	}

	public void celciusToOther() {
		flagUnit = FLAG_CELSIUS;
		setValuesAccoridingToEdittext();

	}

	

	public void kelvinToOther() {
		flagUnit = FLAG_KELVIN;
		setValuesAccoridingToEdittext();
	}

	

	public void farenheitToOther() {
		flagUnit = FLAG_FARENHEIT;
		setValuesAccoridingToEdittext();

	}

	public void storeValueToString() {
		value_kelvin = conversions_kelvin.getText().toString();
		value_celsius = conversions_celsius.getText().toString();
		value_farenheit = conversions_farenheit.getText().toString();
	}

	public void defaultConfig() {
		changeBackGround("m");
		celciusToOther();
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),
				"HelveticaNeue-Medium.otf");

		Typeface ttf = Typeface.createFromAsset(ctx.getAssets(),
				"Helvetica Narrow CE Regular.ttf");
		conversions_kelvin.setTypeface(ttf);
		conversions_celsius.setTypeface(ttf);
		conversions_farenheit.setTypeface(ttf);
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
		BigDecimal kelvinValue = BigDecimal.ZERO;
		BigDecimal celsiusValue = BigDecimal.ZERO;
		BigDecimal farenheitValue = BigDecimal.ZERO;
		
		if (flagUnit.equals(FLAG_CELSIUS)) {
			celsiusValue = BigDecimal.valueOf(Float.valueOf(value));
			kelvinValue = BigDecimal.valueOf((Float.valueOf(value) + 273.15f));
			farenheitValue = BigDecimal.valueOf((Float.valueOf(value) * 1.8 + 32));
			
		} else if (flagUnit.equals(FLAG_KELVIN)) {
			kelvinValue = BigDecimal.valueOf(Float.valueOf(value));
			celsiusValue = BigDecimal.valueOf(Float.valueOf(value) - 273.15f);
			farenheitValue = BigDecimal.valueOf(((Float.valueOf(value) - 273.15) * 1.8) + 32);
			
		} else if (flagUnit.equals(FLAG_FARENHEIT)) {
			farenheitValue = BigDecimal.valueOf(Float.valueOf(value));
			kelvinValue = BigDecimal.valueOf((Float.valueOf(value) + 459.67) * 5 / 9);
			celsiusValue = BigDecimal.valueOf(Float.valueOf(value) - 32 / 1.8);
		}
		
		
		String number2 = kelvinValue.toString();
		double amount2 = Double.parseDouble(number2);
		DecimalFormat formatter2 = new DecimalFormat("#,###.00");
		String val2 = String.valueOf(amount2);
		if (val2.contains("E")) {
			String arr2[] = val2.split("E");
			if (arr2[0].length() > 10) {
				arr2[0] = arr2[0].substring(0, 10);
			}
			conversions_kelvin.setText(arr2[0] + "E" + arr2[1]);
		} else {
			if (val2.length() > 16)
				val2 = val2.substring(0, 16);
			conversions_kelvin.setText(val2 + "");
		}

		
		
		
		String number6 = celsiusValue.toString();
		double amount6 = Double.parseDouble(number6);
		DecimalFormat formatter6 = new DecimalFormat("#,###.00");
		String val6 = String.valueOf(amount6);
		if (val6.contains("E")) {
			String arr6[] = val6.split("E");
			if (arr6[0].length() > 10) {
				arr6[0] = arr6[0].substring(0, 10);
			}
			conversions_celsius.setText(arr6[0] + "E" + arr6[1]);
		} else {
			if (val6.length() > 16)
				val6 = val6.substring(0, 16);
			conversions_celsius.setText(val6 + "");
		}


		
		String number11 = farenheitValue.toString();
		double amount11 = Double.parseDouble(number11);
		String val11 = String.valueOf(amount11);
		if (val11.contains("E")) {
			String arr11[] = val11.split("E");
			if (arr11[0].length() > 10) {
				arr11[0] = arr11[0].substring(0, 10);
			}
			conversions_farenheit.setText(arr11[0] + "E" + arr11[1]);
		} else {
			if (val11.length() > 16)
				val11 = val11.substring(0, 16);
			conversions_farenheit.setText(val11 + "");
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
