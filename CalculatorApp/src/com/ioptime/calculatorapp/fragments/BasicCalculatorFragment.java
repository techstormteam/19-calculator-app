package com.ioptime.calculatorapp.fragments;

import java.text.DecimalFormat;
import java.util.Stack;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import bsh.Interpreter;

import com.actionbarsherlock.app.SherlockFragment;
import com.android.trivialdrivesample.util.Purchase;
import com.ioptime.calculatorapp.CalculatorBrain;
import com.ioptime.calculatorapp.ConstantAds;
import com.ioptime.calculatorapp.MainActivity;
import com.ioptime.calculatorapp.Purchases;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class BasicCalculatorFragment extends SherlockFragment implements OnClickListener {
	
	private Context ctx;
	
	public static TextView mCalculatorDisplay;
	public static TextView mTextViewSmall;
	String sFirstOperand = "";
	String sSecondOperand = "";
	private Boolean userIsInTheMiddleOfTypingANumber = false;
	Boolean isAfterOperation = false;
	private CalculatorBrain mCalculatorBrain;
	private static final String DIGITS = "0123456789.";
	private static final String OPERATIONS = "+-*/";
	public static Boolean isFirstCalc = true;
	public static DecimalFormat df = new DecimalFormat("@####################");
	public Stack<String> s;
	public Boolean isBracketsOpened = false;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	LinearLayout functionPad;
	TextView secondaryTextView;
	Boolean isGreaterThan10Digit = false;
	Vibrator vibe;
	String payload;

	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp = 0;
	Animation anim;
	Animation anim_back;
	Boolean isBracketOpened = false;
	Boolean isBracketClosed = true;
	String smallTextviewText;
	ImageView imgScreen;
	RelativeLayout row1;

	boolean checkvar = false;
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.backup_mainactivity, container, false);
		
		ctx = container.getContext();
		
		// hide the window title.
//		MainActivityA.getInstance().requestWindowFeature(Window.FEATURE_NO_TITLE);
		// hide the status bar and other OS-level chrome
//		MainActivityA.getInstance().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		Purchases.initiatePurchase(MainActivityA.getInstance());
		prefs = container.getContext().getSharedPreferences(MY_PREFS_NAME, container.getContext().MODE_PRIVATE);
		if (!prefs.getString("isPaymentMade", "").equals("true")) {
			ConstantAds.loadInterstitialAd(container.getContext(),
					"top");
		}
		int sw = getResources().getConfiguration().screenWidthDp;
		Log.d("pixel density", sw + "  " + android.os.Build.MANUFACTURER
				+ "    " + android.os.Build.MODEL);
//		gestureDetector = new GestureDetector(container.getContext(), new MyGestureDetector());
//		gestureListener = new View.OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				return gestureDetector.onTouchEvent(event);
//			}
//		};
		imgScreen = (ImageView) rootView.findViewById(R.id.screen);
		row1 = (RelativeLayout) rootView.findViewById(R.id.row1);
		if (isTablet(container.getContext())) {
			Log.d("device is", "tablet");
			imgScreen.setVisibility(View.INVISIBLE);
			row1.setBackground(getResources().getDrawable(R.drawable.screen));
		} else {
			Log.d("device is", "smart phone");
		}

		// icon = (ImageView) rootView.findViewById(android.R.id.icon);
		vibe = (Vibrator) container.getContext().getSystemService(
				Context.VIBRATOR_SERVICE);
//		if (getIntent().hasExtra(EXTRA_TITLE)) {
//			String title = getIntent().getStringExtra(EXTRA_TITLE);
//			// int resId = getIntent().getIntExtra(EXTRA_RESOURCE_ID, 0);
//			setTitle(title);
//			// icon.setImageResource(resId);
//		}
		mCalculatorBrain = new CalculatorBrain();
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		anim = AnimationUtils.loadAnimation(container.getContext(), R.drawable.scale);
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

		anim_back = AnimationUtils.loadAnimation(container.getContext(), R.drawable.scale_back);
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

		rl_upgrade = (RelativeLayout) rootView.findViewById(R.id.rl_upgrade);
		rl_upgrade_parent = (RelativeLayout) rootView.findViewById(R.id.rl_upgrade_parent);
		upgrade_close = (ImageView) rootView.findViewById(R.id.upgrade_close);
		upgrade_bg = (ImageView) rootView.findViewById(R.id.upgrade_bg);
		upgrade_text = (ImageView) rootView.findViewById(R.id.upgrade_text);
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
			 Purchases.makePurchase(MainActivityA.getInstance());

//						if (checkvar == true) {
//							editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//									.edit();
//							editor.putString("isPaymentMade", "true");
//							editor.commit();
//						} else {
//							editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//									.edit();
//							editor.putString("isPaymentMade", "false");
//							editor.commit();
//						}
//				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp = 0;

			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;
		functionPad = (LinearLayout) rootView.findViewById(R.id.functionPad);
		mCalculatorDisplay = (TextView) rootView.findViewById(R.id.textView1);
		mTextViewSmall = (TextView) rootView.findViewById(R.id.textViewSmall);
		secondaryTextView = (TextView) rootView.findViewById(R.id.secondrayTextView1);

		//
		// Typeface tf = Typeface.createFromAsset(getAssets(), "DS-DIGIB.TTF");
		Typeface tf2 = Typeface.createFromAsset(MainActivityA.getInstance().getAssets(), "DS-DIGI.TTF");
		mCalculatorDisplay.setTypeface(tf2);
		mTextViewSmall.setTypeface(tf2);
		secondaryTextView.setTypeface(tf2);

		df.setMinimumFractionDigits(0);
		df.setMinimumIntegerDigits(1);
		df.setMaximumIntegerDigits(100);

		rootView.findViewById(R.id.button0).setOnClickListener(this);
		rootView.findViewById(R.id.button1).setOnClickListener(this);
		rootView.findViewById(R.id.button2).setOnClickListener(this);
		rootView.findViewById(R.id.button3).setOnClickListener(this);
		rootView.findViewById(R.id.button4).setOnClickListener(this);
		rootView.findViewById(R.id.button5).setOnClickListener(this);
		rootView.findViewById(R.id.button6).setOnClickListener(this);
		rootView.findViewById(R.id.button7).setOnClickListener(this);
		rootView.findViewById(R.id.button8).setOnClickListener(this);
		rootView.findViewById(R.id.button9).setOnClickListener(this);

		rootView.findViewById(R.id.buttonAdd).setOnClickListener(this);
		rootView.findViewById(R.id.buttonSubtract).setOnClickListener(this);
		rootView.findViewById(R.id.buttonMultiply).setOnClickListener(this);
		rootView.findViewById(R.id.buttonDivide).setOnClickListener(this);
		rootView.findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
		rootView.findViewById(R.id.buttonEquals).setOnClickListener(this);
		rootView.findViewById(R.id.buttonClear).setOnClickListener(this);
		rootView.findViewById(R.id.buttonClearMemory).setOnClickListener(this);
		rootView.findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
		rootView.findViewById(R.id.buttonSubtractFromMemory).setOnClickListener(this);
		rootView.findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
		rootView.findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
		rootView.findViewById(R.id.buttonPercentage).setOnClickListener(this);
		rootView.findViewById(R.id.buttonSquared).setOnClickListener(this);
		rootView.findViewById(R.id.buttonToggleSign).setOnClickListener(this);
		functionPad.setOnTouchListener(gestureListener);
		rootView.findViewById(R.id.buttonBackSpace).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						vibe.vibrate(50);
						if (mCalculatorDisplay.getText().length() > 0) {
							// if last character to be removed is an operation
							// do set the waiting operation as ""
							if (OPERATIONS.contains(mCalculatorDisplay
									.getText()
									.toString()
									.substring(
											mCalculatorDisplay.getText()
													.toString().length() - 1))
									|| mCalculatorDisplay
											.getText()
											.toString()
											.substring(
													mCalculatorDisplay
															.getText()
															.toString()
															.length() - 1)
											.equals("%")

									|| mCalculatorDisplay
											.getText()
											.toString()
											.substring(
													mCalculatorDisplay
															.getText()
															.toString()
															.length() - 1)
											.equals("^")) {
								CalculatorBrain.mWaitingOperator = "";

							}

							if (mCalculatorDisplay
									.getText()
									.toString()
									.substring(
											mCalculatorDisplay.getText()
													.toString().length() - 1)
									.equals(")")) {
								isBracketClosed = false;
								isBracketOpened = true;
							}

							if (mCalculatorDisplay
									.getText()
									.toString()
									.substring(
											mCalculatorDisplay.getText()
													.toString().length() - 1)
									.equals("(")) {
								isBracketOpened = false;
								isBracketClosed = true;
							}
							mCalculatorDisplay.setText(mCalculatorDisplay
									.getText()
									.toString()
									.substring(
											0,
											mCalculatorDisplay.getText()
													.length() - 1));
							int size = 10;
							if (android.os.Build.MODEL.contains("N7100")) {
								size = 12;
							} else {
								size = 10;
							}
							if (mCalculatorDisplay.getText().toString()
									.length() > size) {
								int displayLength = mCalculatorDisplay
										.getText().toString().length()
										- size;
								String SecondaryText = "";
								SecondaryText = mCalculatorDisplay.getText()
										.toString();
								SecondaryText = SecondaryText.substring(0,
										size - 4);
								mCalculatorDisplay.setVisibility(View.GONE);
								secondaryTextView.setVisibility(View.VISIBLE);
								secondaryTextView.setText(SecondaryText
										+ "x10^" + displayLength);

							} else {
								secondaryTextView.setVisibility(View.GONE);
								mCalculatorDisplay.setVisibility(View.VISIBLE);
							}

						}

						if (mCalculatorDisplay.getText().length() == 0
								|| mCalculatorDisplay.getText().equals("0.0")) {
							mCalculatorDisplay.setText("0");
							userIsInTheMiddleOfTypingANumber = false;

						}
					}
				});

		if (rootView.findViewById(R.id.buttonInvert) != null) {
			rootView.findViewById(R.id.buttonInvert).setOnClickListener(this);
		}
		if (rootView.findViewById(R.id.buttonSine) != null) {
			rootView.findViewById(R.id.buttonSine).setOnClickListener(this);
		}
		if (rootView.findViewById(R.id.buttonCosine) != null) {
			rootView.findViewById(R.id.buttonCosine).setOnClickListener(this);
		}
		if (rootView.findViewById(R.id.buttonTangent) != null) {
			rootView.findViewById(R.id.buttonTangent).setOnClickListener(this);
		}
		
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		vibe.vibrate(50);
		String buttonPressed = "";
		switch (v.getId()) {
		case R.id.button0:
			buttonPressed = "0";
			break;
		case R.id.button1:
			buttonPressed = "1";
			break;
		case R.id.button2:
			buttonPressed = "2";
			break;
		case R.id.button3:
			buttonPressed = "3";
			break;
		case R.id.button4:
			buttonPressed = "4";
			break;
		case R.id.button5:
			buttonPressed = "5";
			break;
		case R.id.button6:
			buttonPressed = "6";
			break;
		case R.id.button7:
			buttonPressed = "7";
			break;
		case R.id.button8:
			buttonPressed = "8";
			break;
		case R.id.button9:
			buttonPressed = "9";
			break;
		case R.id.buttonSubtract:
			buttonPressed = "-";
			break;
		case R.id.buttonAdd:
			buttonPressed = "+";
			break;
		case R.id.buttonMultiply:
			buttonPressed = "*";
			break;
		case R.id.buttonDivide:
			buttonPressed = "/";
			break;
		case R.id.buttonDecimalPoint:
			buttonPressed = ".";
			break;
		case R.id.buttonEquals:
			buttonPressed = "=";
			break;
		case R.id.buttonClear:
			buttonPressed = "C";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonClearMemory:
			buttonPressed = "MC";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonAddToMemory:
			buttonPressed = "M+";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonSubtractFromMemory:
			buttonPressed = "M-";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonRecallMemory:
			buttonPressed = "MR";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonSquareRoot:
			buttonPressed = "√";
			break;
		case R.id.buttonToggleSign:
			buttonPressed = "+/-";
			break;
		case R.id.buttonSquared:
			buttonPressed = "^";
			break;
		case R.id.buttonInvert:
			buttonPressed = "1/x";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonSine:
			buttonPressed = "sin";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonCosine:
			buttonPressed = "cos";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonTangent:
			buttonPressed = "tan";
			mTextViewSmall.setText("");
			break;
		case R.id.buttonPercentage:
			buttonPressed = "%";
			break;
		}

		if (DIGITS.contains(buttonPressed)) {
			if (userIsInTheMiddleOfTypingANumber) {

				if (buttonPressed.equals(".")
						&& mCalculatorDisplay.getText().toString()
								.contains(".")) {

					// if second operand doesn't contain dot so append dot.
					if (!sFirstOperand.equals("")) {
						sSecondOperand = mCalculatorDisplay.getText()
								.toString().replace(sFirstOperand, "");
						if (!sSecondOperand.contains(".")) {
							if (sSecondOperand.equals("")) {
								mCalculatorDisplay.append(0 + buttonPressed);
							} else {
								mCalculatorDisplay.append(buttonPressed);
							}
						}
					}
				} else {

					mCalculatorDisplay.append(buttonPressed);
				}
				userIsInTheMiddleOfTypingANumber = true;

			} else {
				if (buttonPressed.equals(".")) {
					mCalculatorDisplay.setText(0 + buttonPressed);
				} else {
					mCalculatorDisplay.setText(buttonPressed);

				}
				isAfterOperation = false;
				isFirstCalc = true;
				userIsInTheMiddleOfTypingANumber = true;
			}
		} else {
			if (!isBracketsOpened) {
				sSecondOperand = mCalculatorDisplay.getText().toString()
						.replace(sFirstOperand, "");
			}

			if (buttonPressed.equals("+/-")) {
				String currentDisplay = mCalculatorDisplay.getText().toString();
				if (currentDisplay.equals("0")) {
					mCalculatorDisplay.setText("(");
					isBracketsOpened = true;
					isBracketOpened = true;
					userIsInTheMiddleOfTypingANumber = true;
				} else {

					if (isBracketOpened) {
						isBracketOpened = false;
						isBracketClosed = true;
						mCalculatorDisplay.append(")");
					}

					else if (isBracketClosed) {
						isBracketOpened = true;
						isBracketsOpened = true;
						isBracketClosed = false;
						String x = mCalculatorDisplay.getText().toString();
						String y = x.substring(x.length() - 1);
						if (DIGITS.contains(y)) {
							mCalculatorDisplay.append("*(");
						} else {
							mCalculatorDisplay.append("(");
						}

					}
				}
			}
			if (userIsInTheMiddleOfTypingANumber || isAfterOperation) {
				if (buttonPressed.equals("MC") || buttonPressed.equals("M+")
						|| buttonPressed.equals("M-")
						|| buttonPressed.equals("MR")
						|| buttonPressed.equals("√")) {
					if (!sSecondOperand.equals("")) {
						if (isBracketsOpened && buttonPressed.equals("√")) {

						} else {
							mCalculatorBrain.setOperand(Double
									.parseDouble(sSecondOperand));
							mCalculatorBrain.performOperation(buttonPressed);
							if (df.format(mCalculatorBrain.getResult()).equals(
									"0.0")) {
								mCalculatorDisplay.setText("0");

							} else {
								mCalculatorDisplay.setText(df
										.format(mCalculatorBrain.getResult()));
							}
							userIsInTheMiddleOfTypingANumber = true;
						}
					}
				}

				else if (OPERATIONS.contains(buttonPressed)
						|| buttonPressed.equals("^")) {
					if (isBracketsOpened) {

						mCalculatorDisplay.append(buttonPressed);

					}

					else if (!sSecondOperand.equals("")
							|| OPERATIONS.contains(mCalculatorDisplay
									.getText()
									.toString()
									.substring(
											mCalculatorDisplay.getText()
													.toString().length() - 1))) {
						if (isBracketsOpened && buttonPressed.equals("^")) {
						} else {
							if (OPERATIONS.contains(mCalculatorDisplay
									.getText()
									.toString()
									.substring(
											mCalculatorDisplay.getText()
													.toString().length() - 1))) {
								CalculatorBrain.mWaitingOperator = "";
								mCalculatorDisplay.setText(mCalculatorDisplay
										.getText()
										.toString()
										.substring(
												0,
												mCalculatorDisplay.getText()
														.length() - 1));
								sFirstOperand = mCalculatorDisplay.getText()
										.toString();
								mCalculatorBrain
										.performOperation(buttonPressed);
								mCalculatorDisplay.append(buttonPressed);
								sFirstOperand = mCalculatorDisplay.getText()
										.toString();
								userIsInTheMiddleOfTypingANumber = true;

							} else {
								mCalculatorBrain.setOperand(Double
										.parseDouble(sSecondOperand));
								mCalculatorBrain
										.performOperation(buttonPressed);
								mCalculatorDisplay.append(buttonPressed);
								sFirstOperand = mCalculatorDisplay.getText()
										.toString();
								userIsInTheMiddleOfTypingANumber = true;
							}
						}

					}
				}

				else if (buttonPressed.equals("%")) {
					if (!sSecondOperand.equals("")) {
						if (isBracketsOpened) {

						} else {
							if (sFirstOperand.contains("*")) {
								String firstOperand = sFirstOperand.replace(
										"*", "");
								double f = Double.parseDouble(firstOperand);
								double s = Double.parseDouble(sSecondOperand);
								mCalculatorBrain.setOperand(f * s);
								mCalculatorBrain
										.performOperation(buttonPressed);
								if (df.format(mCalculatorBrain.getResult())
										.equals("0.0")) {
									mCalculatorDisplay.setText("0");

								} else {
									mCalculatorDisplay.setText(df
											.format(mCalculatorBrain
													.getResult()));
								}
								CalculatorBrain.mWaitingOperator = "";
								CalculatorBrain.mWaitingOperand = 0;
								userIsInTheMiddleOfTypingANumber = false;
								isAfterOperation = true;
								mTextViewSmall.setText("");
							}

						}
					}
				}

				else if (buttonPressed.equals("=")) {

					if (isBracketsOpened) {

						// PostfixEval p = new PostfixEval();
						// PerformOperation po = new PerformOperation();
						try {

							String test = mCalculatorDisplay.getText()
									.toString();
							Interpreter i = new Interpreter();
							smallTextviewText = mCalculatorDisplay.getText()
									.toString();
							if (smallTextviewText.length() < 27) {
								MainActivity.mTextViewSmall
										.setText(smallTextviewText);
							} else {
								MainActivity.mTextViewSmall
										.setText(smallTextviewText
												.substring(smallTextviewText
														.length() - 27));
							}
							// mTextViewSmall.setText(mCalculatorDisplay.getText()
							// .toString());
							mCalculatorDisplay.setText(i.eval(test) + "");
							isBracketsOpened = false;
						} catch (Exception e) {
							Log.e("Exception is", e.toString());
							mTextViewSmall.setText("INVALID INPUT");

						}
						userIsInTheMiddleOfTypingANumber = false;
						isAfterOperation = true;

					} else if (!sSecondOperand.equals("")) {

						mCalculatorBrain.setOperand(Double
								.parseDouble(sSecondOperand));
						mCalculatorBrain.performOperation(buttonPressed);
						if (df.format(mCalculatorBrain.getResult()).equals(
								"0.0")) {
							mCalculatorDisplay.setText("0");

						} else {
							mCalculatorDisplay.setText(df
									.format(mCalculatorBrain.getResult()));
						}
						userIsInTheMiddleOfTypingANumber = false;
						isAfterOperation = true;
					}

				}

				else if (buttonPressed.equals("C")) {
					mCalculatorBrain.performOperation(buttonPressed);
					if (df.format(mCalculatorBrain.getResult()).equals("0.0")) {
						mCalculatorDisplay.setText("0");

					} else {
						mCalculatorDisplay.setText(df.format(mCalculatorBrain
								.getResult()));
					}
					userIsInTheMiddleOfTypingANumber = false;
					isAfterOperation = true;
					isFirstCalc = true;
					isBracketsOpened = false;
					isBracketOpened = false;
					isBracketClosed = true;

				}

			}
		}
		// decreasing display size if it is greater than 12
		int size = 10;
		if (android.os.Build.MODEL.contains("N7100")) {
			size = 12;
		} else {
			size = 10;
		}
		if (mCalculatorDisplay.getText().toString().length() > size) {
			int displayLength = mCalculatorDisplay.getText().toString()
					.length()
					- size;
			String SecondaryText = "";
			SecondaryText = mCalculatorDisplay.getText().toString();
			SecondaryText = SecondaryText.substring(0, size - 4);
			mCalculatorDisplay.setVisibility(View.GONE);
			secondaryTextView.setVisibility(View.VISIBLE);
			secondaryTextView.setText(SecondaryText + "x10^" + displayLength);

		} else {
			secondaryTextView.setVisibility(View.GONE);
			mCalculatorDisplay.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Purchases.destroy();
		ConstantAds.displayInterstitial();
	}

	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getSupportMenuInflater().inflate(R.menu.main_menu, menu);
//		if (sideNavigationView.getMode() == Mode.RIGHT) {
//			menu.findItem(R.id.mode_right).setChecked(true);
//		} else {
//			menu.findItem(R.id.mode_left).setChecked(true);
//		}
//		return super.onCreateOptionsMenu(menu);
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			sideNavigationView.toggleMenu();
//			break;
//		case R.id.mode_left:
//			item.setChecked(true);
//			sideNavigationView.setMode(Mode.LEFT);
//			break;
//		case R.id.mode_right:
//			item.setChecked(true);
//			sideNavigationView.setMode(Mode.RIGHT);
//			break;
//
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//		return true;
//	}


//	@Override
//	public void onBackPressed() {
//		// hide menu if it shown
//		if (sideNavigationView.isShown()) {
//			sideNavigationView.hideMenu();
//			finish();
//		}
//		if (upgradePopUp == 1) {
//			rl_upgrade_parent.startAnimation(anim_back);
//			upgradePopUp = 0;
//		} else {
//			super.onBackPressed();
//			finish();
//		}
//	}

	private void invokeActivity(String title) {
//		Intent intent = new Intent();
//		if (title.equals("SIMPLE CALCULATOR")) {
//			intent = new Intent(ctx, MainActivity.class);
//			intent.putExtra(EXTRA_TITLE, title);
//			intent.putExtra(EXTRA_MODE,
//					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			overridePendingTransition(0, 0);
//		} else if (title.equals("BASIC CALCULATOR")) {
//			intent = new Intent(ctx, MainActivity.class);
//			intent.putExtra(EXTRA_TITLE, title);
//			intent.putExtra(EXTRA_MODE,
//					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			overridePendingTransition(0, 0);
//		} else if (title.equals("UNIT CONVERTER")) {
//			if (prefs.getString("isPaymentMade", "").equals("true")) {
//				intent = new Intent(this, UnitConverterLength.class);
//				intent.putExtra(EXTRA_TITLE, title);
//				intent.putExtra(EXTRA_MODE,
//						sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//				overridePendingTransition(0, 0);
//			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
//				sideNavigationView.hideMenu();
//				rl_upgrade_parent.startAnimation(anim);
//				upgradePopUp = 1;
//				overridePendingTransition(0, 0);
//			}
//		} else if (title.equals("CURRENCY CONVERTERS")) {
//			intent = new Intent(this, CurrencyConverter.class);
//			intent.putExtra(EXTRA_TITLE, title);
//			intent.putExtra(EXTRA_MODE,
//					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			overridePendingTransition(0, 0);
//		} else if (title.equals("FITNESS CALCULATOR")) {
//			if (prefs.getString("isPaymentMade", "").equals("true")) {
//				intent = new Intent(ctx, HealthCalculator.class);
//				intent.putExtra(EXTRA_TITLE, title);
//				intent.putExtra(EXTRA_MODE,
//						sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//				overridePendingTransition(0, 0);
//			} else if (!prefs.getString("isPaymentMade", "").equals("true")) {
//				sideNavigationView.hideMenu();
//				rl_upgrade_parent.startAnimation(anim);
//				upgradePopUp = 1;
//				overridePendingTransition(0, 0);
//			}
//		} else if (title.equals("SETTINGS")) {
//			intent = new Intent(ctx, MainActivity.class);
//			Toast.makeText(getApplicationContext(), "Coming soon",
//					Toast.LENGTH_LONG).show();
//			intent.putExtra(EXTRA_TITLE, title);
//			intent.putExtra(EXTRA_MODE,
//					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			overridePendingTransition(0, 0);
//		} else if (title.equals("ABOUT")) {
//			intent = new Intent(ctx, About.class);
//			intent.putExtra(EXTRA_TITLE, title);
//			intent.putExtra(EXTRA_MODE,
//					sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			overridePendingTransition(0, 0);
//		} else if (title.equals("UPGRADE")) {
//
//			if (!prefs.getString("isPaymentMade", "").equals("true")) {
//				sideNavigationView.hideMenu();
//				rl_upgrade_parent.startAnimation(anim);
//				upgradePopUp = 1;
//				overridePendingTransition(0, 0);
//			}
//
//		}
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_MENU) {
//
//			sideNavigationView.toggleMenu();
//			return true;
//		}
//
//		// let the system handle all other key events
//		return super.onKeyDown(keyCode, event);
//	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		outtoLeft.setAnimationListener(new AnimationListener() {

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

		return outtoLeft;
	}

	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		inFromLeft.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				rl_upgrade.setVisibility(View.VISIBLE);
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
		return inFromLeft;
	}

	boolean verifyDeveloperPayload(Purchase p) {
		payload = p.getDeveloperPayload();

		/*
		 * TODO: verify that the developer payload of the purchase is correct.
		 * It will be the same one that you sent when initiating the purchase.
		 * 
		 * WARNING: Locally generating a random string when starting a purchase
		 * and verifying it here might seem like a good approach, but this will
		 * fail in the case where the user purchases an item on one device and
		 * then uses your app on a different device, because on the other device
		 * you will not have access to the random string you originally
		 * generated.
		 * 
		 * So a good developer payload has these characteristics:
		 * 
		 * 1. If two different users purchase an item, the payload is different
		 * between them, so that one user's purchase can't be replayed to
		 * another user.
		 * 
		 * 2. The payload must be such that you can verify it even when the app
		 * wasn't the one who initiated the purchase flow (so that items
		 * purchased by the user on one device work on other devices owned by
		 * the user).
		 * 
		 * Using your own server to store and verify developer payloads across
		 * app installations is recommended.
		 */

		return true;
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// if (mHelper != null
	// && !mHelper.handleActivityResult(requestCode, resultCode, data)) {
	// // not handled, so handle it ourselves (here's where you'd
	// // perform any handling of activity results not related to in-app
	// // billing...
	// super.onActivityResult(requestCode, resultCode, data);
	// }
	// }

	public void alert(String toast) {
		Toast.makeText(ctx, toast, Toast.LENGTH_LONG)
				.show();
	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	
}
