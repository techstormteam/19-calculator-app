package com.ioptime.calculatorapp;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.smartcalculator.R;

public class HealthCalculator extends SherlockActivity implements
		ISideNavigationCallback, OnClickListener {

	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private SideNavigationView sideNavigationView;
	RelativeLayout female_layout;
	ImageView femaleUnselected;
	ImageView MaleUnselected;
	ImageView Switch;
	public Boolean isMetric = true;
	public Boolean isFemaleSelected = true;
	public Boolean isMaleSelected = false;
	EditText etAgeScreen;
	EditText etHeightScreen;
	EditText etHeightScreen2;
	EditText etWeightScreen;
	EditText etWaistScreen;
	EditText etWristScreen;
	EditText etHipsScreen;
	EditText etForeArmsScreen;
	ImageView heightScreen;
	ImageView heightScreen2;
	ImageView weightScreen;
	ImageView waistScreen;
	ImageView wristScreen;
	ImageView hipsScreen;
	ImageView forearmsScreen;
	ImageView btnCalculate;
	ImageView imgMandatoryAge;
	ImageView imgMandatoryHeight;
	ImageView imgMandatoryWeight;
	ImageView imgMandatoryWaist;
	ImageView imgMandatoryWrist;
	ImageView imgMandatoryHips;
	ImageView imgMandatoryForearms;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	LinearLayout funtionPad;
	ImageView menuIcon;
	String errorMessage;
	Vibrator vibe;
	
	ImageView btnClear;
	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp=0;
	Animation anim;
	Animation anim_back;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// hide the window title.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// hide the status bar and other OS-level chrome
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_calculator);
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
		Switch = (ImageView) findViewById(R.id.metric_switch);
		female_layout = (RelativeLayout) findViewById(R.id.female_layout);
		MaleUnselected = (ImageView) findViewById(R.id.male_unselected);
		femaleUnselected = (ImageView) findViewById(R.id.female_selected);
		heightScreen = (ImageView) findViewById(R.id.height_screen);
		heightScreen2 = (ImageView) findViewById(R.id.height_screen_2);
		weightScreen = (ImageView) findViewById(R.id.weight_screen);
		waistScreen = (ImageView) findViewById(R.id.waist_screen);
		wristScreen = (ImageView) findViewById(R.id.wrist_screen);
		hipsScreen = (ImageView) findViewById(R.id.hips_screen);
		forearmsScreen = (ImageView) findViewById(R.id.forearms_screen);
		btnCalculate = (ImageView) findViewById(R.id.bnt_calculate);
		menuIcon = (ImageView) findViewById(R.id.menuicon);
		etAgeScreen = (EditText) findViewById(R.id.et_age_screen);
		etForeArmsScreen = (EditText) findViewById(R.id.et_forearms_screen);
		etHeightScreen = (EditText) findViewById(R.id.et_height_screen);
		etHeightScreen2 = (EditText) findViewById(R.id.et_height_screen_2);
		etHipsScreen = (EditText) findViewById(R.id.et_hips_screen);
		etWaistScreen = (EditText) findViewById(R.id.et_waist_screen);
		etWeightScreen = (EditText) findViewById(R.id.et_weight_screen);
		etWristScreen = (EditText) findViewById(R.id.et_wrist_screen);
		imgMandatoryAge = (ImageView) findViewById(R.id.mandatory_age);
		imgMandatoryHeight = (ImageView) findViewById(R.id.mandatory_height);
		imgMandatoryWeight = (ImageView) findViewById(R.id.mandatory_weight);
		imgMandatoryWaist = (ImageView) findViewById(R.id.mandatory_waist);
		imgMandatoryWrist = (ImageView) findViewById(R.id.mandatory_wrist);
		imgMandatoryHips = (ImageView) findViewById(R.id.mandatory_hips);
		imgMandatoryForearms = (ImageView) findViewById(R.id.mandatory_forarms);
		btnClear = (ImageView) findViewById(R.id.bnt_clear);
		Typeface tf = Typeface.createFromAsset(getAssets(), "DS-DIGIB.TTF");
		etAgeScreen.setTypeface(tf);
		etForeArmsScreen.setTypeface(tf);
		etHeightScreen.setTypeface(tf);
		etHeightScreen2.setTypeface(tf);
		etHipsScreen.setTypeface(tf);
		etWaistScreen.setTypeface(tf);
		etWeightScreen.setTypeface(tf);
		etWristScreen.setTypeface(tf);

		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
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
		Switch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etAgeScreen.setText("");
				etHeightScreen.setText("");
				etHeightScreen2.setText("");
				etWeightScreen.setText("");
				etWaistScreen.setText("");
				etWristScreen.setText("");
				etHipsScreen.setText("");
				etForeArmsScreen.setText("");

				imgMandatoryAge.setVisibility(View.GONE);
				imgMandatoryHeight.setVisibility(View.GONE);
				imgMandatoryWeight.setVisibility(View.GONE);
				imgMandatoryWaist.setVisibility(View.GONE);
				imgMandatoryWrist.setVisibility(View.GONE);
				imgMandatoryHips.setVisibility(View.GONE);
				imgMandatoryForearms.setVisibility(View.GONE);
				if (isMetric) {
					Switch.setImageResource(R.drawable.standard_switch);
					isMetric = false;
					heightScreen.setImageResource(R.drawable.height_bg_2);
					heightScreen2.setVisibility(View.VISIBLE);
					etHeightScreen2.setVisibility(View.VISIBLE);
					weightScreen.setImageResource(R.drawable.weight_bg);
					waistScreen.setImageResource(R.drawable.height_bg_standard);
					wristScreen.setImageResource(R.drawable.height_bg_standard);
					hipsScreen.setImageResource(R.drawable.height_bg_standard);
					forearmsScreen
							.setImageResource(R.drawable.height_bg_standard);
				} else {
					Switch.setImageResource(R.drawable.metric_switch);
					isMetric = true;
					heightScreen.setImageResource(R.drawable.height_bg_metric);
					heightScreen2.setVisibility(View.GONE);
					etHeightScreen2.setVisibility(View.GONE);
					weightScreen.setImageResource(R.drawable.weight_bg_metric);
					waistScreen.setImageResource(R.drawable.height_bg_metric);
					wristScreen.setImageResource(R.drawable.height_bg_metric);
					hipsScreen.setImageResource(R.drawable.height_bg_metric);
					forearmsScreen
							.setImageResource(R.drawable.height_bg_metric);
				}
			}
		});

		femaleUnselected.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isFemaleSelected) {
					femaleUnselected
							.setImageResource(R.drawable.female_selected);
					MaleUnselected.setImageResource(R.drawable.male_unselected);
					isMaleSelected = false;
					isFemaleSelected = true;
					female_layout.setVisibility(View.VISIBLE);

				}
			}
		});

		MaleUnselected.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isMaleSelected) {
					femaleUnselected
							.setImageResource(R.drawable.female_unselected);
					MaleUnselected.setImageResource(R.drawable.male_selected);
					isMaleSelected = true;
					isFemaleSelected = false;
					female_layout.setVisibility(View.GONE);
				}
			}
		});
		btnClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etAgeScreen.setText("");
				etHeightScreen.setText("");
				etHeightScreen2.setText("");
				etWeightScreen.setText("");
				etWaistScreen.setText("");
				etWristScreen.setText("");
				etHipsScreen.setText("");
				etForeArmsScreen.setText("");

				imgMandatoryAge.setVisibility(View.GONE);
				imgMandatoryHeight.setVisibility(View.GONE);
				imgMandatoryWeight.setVisibility(View.GONE);
				imgMandatoryWaist.setVisibility(View.GONE);
				imgMandatoryWrist.setVisibility(View.GONE);
				imgMandatoryHips.setVisibility(View.GONE);
				imgMandatoryForearms.setVisibility(View.GONE);

			}
		});

		btnCalculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgMandatoryAge.setVisibility(View.GONE);
				imgMandatoryHeight.setVisibility(View.GONE);
				imgMandatoryWeight.setVisibility(View.GONE);
				imgMandatoryWaist.setVisibility(View.GONE);
				imgMandatoryWrist.setVisibility(View.GONE);
				imgMandatoryHips.setVisibility(View.GONE);
				imgMandatoryForearms.setVisibility(View.GONE);
				if (isFemaleSelected) {
					if (validate()) {
						double ageInYears = Double.parseDouble(etAgeScreen
								.getText().toString());
						if (isMetric) {

							double CmHeight = Double.parseDouble(etHeightScreen
									.getText().toString());
							double heightInMeters = (CmHeight / 100);
							double weightInKGS = Double
									.parseDouble(etWeightScreen.getText()
											.toString());
							double BMI = (weightInKGS / (heightInMeters * heightInMeters));
							double BMR = 655 + (9.6 * weightInKGS)
									+ (1.8 * CmHeight) - (4.7 * ageInYears);
							double wristInCm = Double.parseDouble(etWristScreen
									.getText().toString());
							double waistInCm = Double.parseDouble(etWaistScreen
									.getText().toString());
							double hipsInCm = Double.parseDouble(etHipsScreen
									.getText().toString());
							double forearmsInCm = Double
									.parseDouble(etForeArmsScreen.getText()
											.toString());
							double weightInLBS = weightInKGS * 2.20462;
							double wristInInches = wristInCm * 0.393701;
							double waistInInches = waistInCm * 0.393701;
							double hipsInInches = hipsInCm * 0.393701;
							double forearmsInInches = forearmsInCm * 0.393701;
							double LeanBodyWeight = ((((((weightInLBS * 0.732) + 8.987) + (wristInInches / 3.14)) - (waistInInches * 0.157)) - (hipsInInches * 0.249)) + (forearmsInInches * 0.434));
							double BodyFat = ((weightInLBS - LeanBodyWeight) * 100)
									/ weightInLBS;
							Intent i = new Intent(getApplicationContext(),
									HealthResults.class);
							DecimalFormat f = new DecimalFormat("##.00");
							i.putExtra("BMI", f.format(BMI) + "");
							i.putExtra("BMR", f.format(BMR) + "");
							i.putExtra("BodyFat", f.format(BodyFat) + "");
							i.putExtra("Gender", "Female");
							startActivity(i);
							finish();
							toast("BMI-> " + BMI + "--BMR->" + BMR);

						} else {

							double FeetHeight = Double
									.parseDouble(etHeightScreen.getText()
											.toString());
							double InchHeight = Double
									.parseDouble(etHeightScreen2.getText()
											.toString());
							double heightInInches = (FeetHeight * 12)
									+ InchHeight;
							double weightInLBS = Double
									.parseDouble(etWeightScreen.getText()
											.toString());
							double BMI = (weightInLBS / (heightInInches * heightInInches)) * 703;
							double BMR = 655 + (4.35 * weightInLBS)
									+ (4.7 * heightInInches)
									- (4.7 * ageInYears);
							double waistInInches = Double
									.parseDouble(etWaistScreen.getText()
											.toString());
							double wristInInches = Double
									.parseDouble(etWristScreen.getText()
											.toString());
							double hipsInInches = Double
									.parseDouble(etHipsScreen.getText()
											.toString());
							double forearmsInInches = Double
									.parseDouble(etForeArmsScreen.getText()
											.toString());
							double LeanBodyWeight = ((((((weightInLBS * 0.732) + 8.987) + (wristInInches / 3.14)) - (waistInInches * 0.157)) - (hipsInInches * 0.249)) + (forearmsInInches * 0.434));
							double BodyFat = ((weightInLBS - LeanBodyWeight) * 100)
									/ weightInLBS;
							Log.d("Results are: ", "BMI-> " + BMI + "--BMR->"
									+ BMR);
							Intent i = new Intent(getApplicationContext(),
									HealthResults.class);
							DecimalFormat f = new DecimalFormat("##.00");
							i.putExtra("BMI", f.format(BMI) + "");
							i.putExtra("BMR", f.format(BMR) + "");
							i.putExtra("BodyFat", f.format(BodyFat) + "");
							i.putExtra("Gender", "Female");
							startActivity(i);
							finish();

						}
					} else {
						// toast(errorMessage);
					}
				}

				if (isMaleSelected) {

					if (validate()) {
						double ageInYears = Double.parseDouble(etAgeScreen
								.getText().toString());
						if (isMetric) {

							double CmHeight = Double.parseDouble(etHeightScreen
									.getText().toString());
							double waistInCm = Double.parseDouble(etWaistScreen
									.getText().toString());
							double heightInMeters = (CmHeight / 100);
							double weightInKGS = Double
									.parseDouble(etWeightScreen.getText()
											.toString());
							double BMI = (weightInKGS / (heightInMeters * heightInMeters));
							double BMR = 66 + (13.7 * weightInKGS)
									+ (5 * CmHeight) - (6.8 * ageInYears);

							double weightInLBS = weightInKGS * 2.20462;
							double waistInInches = waistInCm * 0.393701;
							double LeanBodyWeight = ((weightInLBS * 1.082) + 94.42)
									- (waistInInches * 4.15);
							double BodyFat = ((weightInLBS - LeanBodyWeight) * 100)
									/ weightInLBS;
							Intent i = new Intent(getApplicationContext(),
									HealthResults.class);
							DecimalFormat f = new DecimalFormat("##.00");
							i.putExtra("BMI", f.format(BMI) + "");
							i.putExtra("BMR", f.format(BMR) + "");
							i.putExtra("BodyFat", f.format(BodyFat) + "");
							i.putExtra("Gender", "Male");
							startActivity(i);
							finish();

						} else {
							double FeetHeight = Double
									.parseDouble(etHeightScreen.getText()
											.toString());
							double InchHeight = Double
									.parseDouble(etHeightScreen2.getText()
											.toString());
							double heightInInches = (FeetHeight * 12)
									+ InchHeight;
							double weightInLBS = Double
									.parseDouble(etWeightScreen.getText()
											.toString());
							double waistInInches = Double
									.parseDouble(etWaistScreen.getText()
											.toString());
							double BMI = (weightInLBS / (heightInInches * heightInInches)) * 703;
							double BMR = 66 + (6.23 * weightInLBS)
									+ (12.7 * heightInInches)
									- (6.8 * ageInYears);
							Log.d("wieghtinlbs", weightInLBS + "");
							Log.d("waistInInches", waistInInches + "");
							double LeanBodyWeight = ((weightInLBS * 1.082) + 94.42)
									- (waistInInches * 4.15);
							double BodyFat = ((weightInLBS - LeanBodyWeight) * 100)
									/ weightInLBS;
							Log.d("results are: ", "BMI: " + BMI
									+ "  --  BMR: " + BMR + " -- BodyFat: "
									+ BodyFat);
							Intent i = new Intent(getApplicationContext(),
									HealthResults.class);
							DecimalFormat f = new DecimalFormat("##.00");
							i.putExtra("BMI", f.format(BMI) + "");
							i.putExtra("BMR", f.format(BMR) + "");
							i.putExtra("BodyFat", f.format(BodyFat) + "");
							i.putExtra("Gender", "Male");
							startActivity(i);
							finish();
						}
					} else {
						toast(errorMessage);
					}
				}
			}
		});
	}

	@Override
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
		if(upgradePopUp==1)
		{
			rl_upgrade_parent.startAnimation(anim_back);
			upgradePopUp=0;
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
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					sideNavigationView.toggleMenu();
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
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return true;
	}

	public Boolean validate() {
		if (isMaleSelected) {

			if (!isMetric) {
				if (etAgeScreen.getText().toString().length() == 0
						|| etHeightScreen.getText().toString().length() == 0
						|| etWeightScreen.getText().toString().length() == 0
						|| etWaistScreen.getText().toString().length() == 0
						|| etHeightScreen2.getText().toString().length() == 0) {
					if (etAgeScreen.getText().toString().length() == 0) {
						imgMandatoryAge.setVisibility(View.VISIBLE);
					}

					if (etHeightScreen.getText().toString().length() == 0) {
						imgMandatoryHeight.setVisibility(View.VISIBLE);
					}
					if (etHeightScreen2.getText().toString().length() == 0) {
						imgMandatoryHeight.setVisibility(View.VISIBLE);
					}

					if (etWeightScreen.getText().toString().length() == 0) {
						imgMandatoryWeight.setVisibility(View.VISIBLE);
					}

					if (etWaistScreen.getText().toString().length() == 0) {
						imgMandatoryWaist.setVisibility(View.VISIBLE);
					}
					return false;
				}
			} else {
				if (etAgeScreen.getText().toString().length() == 0
						|| etHeightScreen.getText().toString().length() == 0
						|| etWeightScreen.getText().toString().length() == 0
						|| etWaistScreen.getText().toString().length() == 0) {
					if (etAgeScreen.getText().toString().length() == 0) {
						imgMandatoryAge.setVisibility(View.VISIBLE);
					}

					if (etHeightScreen.getText().toString().length() == 0) {
						imgMandatoryHeight.setVisibility(View.VISIBLE);
					}

					if (etWeightScreen.getText().toString().length() == 0) {
						imgMandatoryWeight.setVisibility(View.VISIBLE);
					}

					if (etWaistScreen.getText().toString().length() == 0) {
						imgMandatoryWaist.setVisibility(View.VISIBLE);
					}
					return false;
				}

			}
		}

		if (isFemaleSelected) {
			if (!isMetric) {
				if (etAgeScreen.getText().toString().length() == 0
						|| etHeightScreen.getText().toString().length() == 0
						|| etWeightScreen.getText().toString().length() == 0
						|| etWaistScreen.getText().toString().length() == 0
						|| etWristScreen.getText().toString().length() == 0
						|| etHipsScreen.getText().toString().length() == 0
						|| etForeArmsScreen.getText().toString().length() == 0
						|| etHeightScreen2.getText().toString().length() == 0) {
					if (etAgeScreen.getText().toString().length() == 0) {
						imgMandatoryAge.setVisibility(View.VISIBLE);
					}

					if (etHeightScreen.getText().toString().length() == 0) {
						imgMandatoryHeight.setVisibility(View.VISIBLE);
					}

					if (etWeightScreen.getText().toString().length() == 0) {
						imgMandatoryWeight.setVisibility(View.VISIBLE);
					}

					if (etWaistScreen.getText().toString().length() == 0) {
						imgMandatoryWaist.setVisibility(View.VISIBLE);
					}

					if (etWristScreen.getText().toString().length() == 0) {
						imgMandatoryWrist.setVisibility(View.VISIBLE);
					}

					if (etHipsScreen.getText().toString().length() == 0) {
						imgMandatoryHips.setVisibility(View.VISIBLE);
					}

					if (etForeArmsScreen.getText().toString().length() == 0) {
						imgMandatoryForearms.setVisibility(View.VISIBLE);
					}
					if (etHeightScreen2.getText().toString().length() == 0) {
						imgMandatoryHeight.setVisibility(View.VISIBLE);
					}
					return false;
				}

			}

			else {
				if (etAgeScreen.getText().toString().length() == 0
						|| etHeightScreen.getText().toString().length() == 0
						|| etWeightScreen.getText().toString().length() == 0
						|| etWaistScreen.getText().toString().length() == 0
						|| etWristScreen.getText().toString().length() == 0
						|| etHipsScreen.getText().toString().length() == 0
						|| etForeArmsScreen.getText().toString().length() == 0) {
					if (etAgeScreen.getText().toString().length() == 0) {
						imgMandatoryAge.setVisibility(View.VISIBLE);
					}

					if (etHeightScreen.getText().toString().length() == 0) {
						imgMandatoryHeight.setVisibility(View.VISIBLE);
					}

					if (etWeightScreen.getText().toString().length() == 0) {
						imgMandatoryWeight.setVisibility(View.VISIBLE);
					}

					if (etWaistScreen.getText().toString().length() == 0) {
						imgMandatoryWaist.setVisibility(View.VISIBLE);
					}

					if (etWristScreen.getText().toString().length() == 0) {
						imgMandatoryWrist.setVisibility(View.VISIBLE);
					}

					if (etHipsScreen.getText().toString().length() == 0) {
						imgMandatoryHips.setVisibility(View.VISIBLE);
					}

					if (etForeArmsScreen.getText().toString().length() == 0) {
						imgMandatoryForearms.setVisibility(View.VISIBLE);
					}
					return false;
				}
			}

		}
		return true;
	}

	public void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
				.show();
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


}
