package com.ioptime.calculatorapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore.Images;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.smartcalculator.R;

public class HealthResults extends SherlockActivity implements OnClickListener,
		ISideNavigationCallback {
	Bundle b;
	TextView tvBMR;
	TextView tvBMI;
	TextView tvBodyFat;
	LinearLayout funtionPad;
	ImageView imageBMI;
	ImageView imageBodyFat;
	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private SideNavigationView sideNavigationView;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	ImageView menuIcon;
	Vibrator vibe;

	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp = 0;
	Animation anim;
	Animation anim_back;
	ImageView facebookshare;
	ImageView poweredby;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_results);
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
		funtionPad = (LinearLayout) findViewById(R.id.functionPad);
		facebookshare = (ImageView) findViewById(R.id.result_FacebookShare);
		poweredby = (ImageView) findViewById(R.id.result_powered_by);
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
		facebookshare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				createImage(funtionPad);
			}
		});

		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;

		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		menuIcon = (ImageView) findViewById(R.id.menuicon);
		imageBMI = (ImageView) findViewById(R.id.result_BMI);
		imageBodyFat = (ImageView) findViewById(R.id.result_BODYFAT);
		b = getIntent().getExtras();
		tvBMI = (TextView) findViewById(R.id.tv_BMI);
		tvBMR = (TextView) findViewById(R.id.tv_BMR);
		tvBodyFat = (TextView) findViewById(R.id.tv_BodyFat);
		Typeface tf = Typeface.createFromAsset(getAssets(), "DS-DIGIB.TTF");
		tvBMI.setTypeface(tf);
		tvBMR.setTypeface(tf);
		tvBodyFat.setTypeface(tf);
		tvBMI.setText(b.getString("BMI"));
		tvBMR.setText(b.getString("BMR"));
		tvBodyFat.setText(b.getString("BodyFat"));
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		funtionPad.setOnTouchListener(gestureListener);
		sideNavigationView.setMenuClickCallback(this);
		String gender = b.getString("Gender");

		double BMI = Double.parseDouble(b.getString("BMI"));
		if (BMI < 18.5) {
			imageBMI.setImageResource(R.drawable.result_bmi_underweight);
		} else if (BMI >= 18.5 && BMI < 24.9) {
			imageBMI.setImageResource(R.drawable.result_bmi_normal);
		} else if (BMI >= 25 && BMI < 29.9) {
			imageBMI.setImageResource(R.drawable.result_bmi_overweight);
		} else if (BMI >= 30) {
			imageBMI.setImageResource(R.drawable.result_bmi_obesity);
		}

		double BodyFat = Double.parseDouble(b.getString("BodyFat"));
		if (gender.equals("Male")) {
			if (BodyFat < 5) {
				imageBodyFat.setImageResource(R.drawable.essential_fat_men);
			} else if (BodyFat >= 5 && BMI < 14) {
				imageBodyFat.setImageResource(R.drawable.athletes_men);
			} else if (BodyFat >= 14 && BodyFat < 18) {
				imageBodyFat.setImageResource(R.drawable.fitness_men);
			} else if (BodyFat >= 18 && BodyFat < 25) {
				imageBodyFat.setImageResource(R.drawable.acceptable_men);
			} else if (BodyFat >= 25) {
				imageBodyFat.setImageResource(R.drawable.obese_men);
			}
		}

		else if (gender.equals("Female")) {
			if (BodyFat < 13) {
				imageBodyFat.setImageResource(R.drawable.essential_fat_women);
			} else if (BodyFat >= 13 && BodyFat < 21) {
				imageBodyFat.setImageResource(R.drawable.athletes_women);
			} else if (BodyFat >= 21 && BodyFat < 25) {
				imageBodyFat.setImageResource(R.drawable.fitness_women);
			} else if (BodyFat >= 25 && BodyFat < 32) {
				imageBodyFat.setImageResource(R.drawable.acceptable_women);
			} else if (BodyFat >= 32) {
				imageBodyFat.setImageResource(R.drawable.obese_women);
			}
		}

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
	}

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
			// finish();
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
		if (upgradePopUp == 1) {
			rl_upgrade_parent.startAnimation(anim_back);
			upgradePopUp = 0;
		} else {
			startActivity(new Intent(getApplicationContext(),
					HealthCalculator.class));
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
			upgradePopUp = 1;
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
		// InputMethodManager imm = (InputMethodManager)
		// getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return true;
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

	public void createImage(View view) {
		View viewChild = view;
		Canvas canvas = null;
		facebookshare.setVisibility(View.INVISIBLE);
		poweredby.setVisibility(View.VISIBLE);
		menuIcon.setVisibility(View.INVISIBLE);
		Bitmap data = Bitmap.createBitmap(viewChild.getWidth(),
				viewChild.getHeight(), Bitmap.Config.ARGB_8888);
		Bitmap returnedBitmap = Bitmap.createBitmap(viewChild.getWidth(),
				viewChild.getHeight(), Bitmap.Config.ARGB_8888);
		canvas = new Canvas(data);
		Paint paint = new Paint();
		paint.setColor(Color.rgb(256, 256, 256));
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(returnedBitmap, 0, 0, paint);
		viewChild.draw(canvas);
		Canvas c1 = new Canvas(returnedBitmap);
		c1.drawColor(Color.rgb(256, 256, 256));
		int width = returnedBitmap.getWidth();
		int left = (width);
		c1.drawBitmap(returnedBitmap, left, returnedBitmap.getHeight(), null);
		c1.drawBitmap(data, 0, 0, null);
		facebookshare.setVisibility(View.VISIBLE);
		poweredby.setVisibility(View.INVISIBLE);
		menuIcon.setVisibility(View.VISIBLE);
		shareOnFB(returnedBitmap);
	}

	public void shareOnFB(Bitmap bitmap) {
		String pathofBmp = Images.Media.insertImage(getContentResolver(),
				bitmap, "title", null);
		Uri bmpUri = Uri.parse(pathofBmp);
		final Intent emailIntent1 = new Intent(
				android.content.Intent.ACTION_SEND);
		emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
		emailIntent1.setType("image/png");
		startActivity(Intent.createChooser(emailIntent1,
				"Share your fitness results!"));
	}

}
