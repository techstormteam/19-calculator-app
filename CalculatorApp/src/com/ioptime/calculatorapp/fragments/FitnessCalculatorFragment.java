package com.ioptime.calculatorapp.fragments;

import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class FitnessCalculatorFragment extends SherlockFragment implements
	OnClickListener, Upgradeable {

	Context ctx;
	
	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
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

	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	LinearLayout funtionPad;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.health_calculator, container, false);
		
		super.onCreate(savedInstanceState);
		ctx = container.getContext();

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
		Switch = (ImageView) view.findViewById(R.id.metric_switch);
		female_layout = (RelativeLayout) view.findViewById(R.id.female_layout);
		MaleUnselected = (ImageView) view.findViewById(R.id.male_unselected);
		femaleUnselected = (ImageView) view.findViewById(R.id.female_selected);
		heightScreen = (ImageView) view.findViewById(R.id.height_screen);
		heightScreen2 = (ImageView) view.findViewById(R.id.height_screen_2);
		weightScreen = (ImageView) view.findViewById(R.id.weight_screen);
		waistScreen = (ImageView) view.findViewById(R.id.waist_screen);
		wristScreen = (ImageView) view.findViewById(R.id.wrist_screen);
		hipsScreen = (ImageView) view.findViewById(R.id.hips_screen);
		forearmsScreen = (ImageView) view.findViewById(R.id.forearms_screen);
		btnCalculate = (ImageView) view.findViewById(R.id.bnt_calculate);
		etAgeScreen = (EditText) view.findViewById(R.id.et_age_screen);
		etForeArmsScreen = (EditText) view.findViewById(R.id.et_forearms_screen);
		etHeightScreen = (EditText) view.findViewById(R.id.et_height_screen);
		etHeightScreen2 = (EditText) view.findViewById(R.id.et_height_screen_2);
		etHipsScreen = (EditText) view.findViewById(R.id.et_hips_screen);
		etWaistScreen = (EditText) view.findViewById(R.id.et_waist_screen);
		etWeightScreen = (EditText) view.findViewById(R.id.et_weight_screen);
		etWristScreen = (EditText) view.findViewById(R.id.et_wrist_screen);
		imgMandatoryAge = (ImageView) view.findViewById(R.id.mandatory_age);
		imgMandatoryHeight = (ImageView) view.findViewById(R.id.mandatory_height);
		imgMandatoryWeight = (ImageView) view.findViewById(R.id.mandatory_weight);
		imgMandatoryWaist = (ImageView) view.findViewById(R.id.mandatory_waist);
		imgMandatoryWrist = (ImageView) view.findViewById(R.id.mandatory_wrist);
		imgMandatoryHips = (ImageView) view.findViewById(R.id.mandatory_hips);
		imgMandatoryForearms = (ImageView) view.findViewById(R.id.mandatory_forarms);
		btnClear = (ImageView) view.findViewById(R.id.bnt_clear);
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(), "DS-DIGIB.TTF");
		etAgeScreen.setTypeface(tf);
		etForeArmsScreen.setTypeface(tf);
		etHeightScreen.setTypeface(tf);
		etHeightScreen2.setTypeface(tf);
		etHipsScreen.setTypeface(tf);
		etWaistScreen.setTypeface(tf);
		etWeightScreen.setTypeface(tf);
		etWristScreen.setTypeface(tf);

		funtionPad.setOnTouchListener(gestureListener);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getApplicationContext().getSystemService(
				Context.VIBRATOR_SERVICE);
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
							DecimalFormat f = new DecimalFormat("##.00");
							MainActivityA.getInstance().showHealthResultsFragment(
									f.format(BMI), f.format(BMR), f.format(BodyFat), "Female");
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
							DecimalFormat f = new DecimalFormat("##.00");
							MainActivityA.getInstance().showHealthResultsFragment(
									f.format(BMI), f.format(BMR), f.format(BodyFat), "Female");
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
							DecimalFormat f = new DecimalFormat("##.00");
							MainActivityA.getInstance().showHealthResultsFragment(
									f.format(BMI), f.format(BMR), f.format(BodyFat), "Male");

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
							DecimalFormat f = new DecimalFormat("##.00");
							MainActivityA.getInstance().showHealthResultsFragment(
									f.format(BMI), f.format(BMR), f.format(BodyFat), "Male");
						}
					} else {
						toast(errorMessage);
					}
				}
			}
		});
		
		return view;
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public boolean onTouchEvent(MotionEvent event) {
		InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(MainActivityA.getInstance().getCurrentFocus().getWindowToken(), 0);
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

	@Override
	public void showUpgrade() {
		rl_upgrade_parent.startAnimation(anim);
		upgradePopUp=1;
	}

	@Override
	public int getUpgradePopUp() {
		return upgradePopUp;
	}
	
	public void toast(String message) {
		Toast.makeText(ctx, message, Toast.LENGTH_LONG)
				.show();
	}
	
	
}
