package com.ioptime.calculatorapp.fragments;

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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class HealthResultsFragment extends SherlockFragment implements Upgradeable {

	Context ctx;
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
	ImageView facebookshare;
	ImageView poweredby;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.health_results, container, false);
		
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
		funtionPad = (LinearLayout) view.findViewById(R.id.functionPad);
		facebookshare = (ImageView) view.findViewById(R.id.result_FacebookShare);
		poweredby = (ImageView) view.findViewById(R.id.result_powered_by);
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
		facebookshare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				createImage(funtionPad);
			}
		});

		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;

		imageBMI = (ImageView) view.findViewById(R.id.result_BMI);
		imageBodyFat = (ImageView) view.findViewById(R.id.result_BODYFAT);
		b = MainActivityA.getInstance().getIntent().getExtras();
		tvBMI = (TextView) view.findViewById(R.id.tv_BMI);
		tvBMR = (TextView) view.findViewById(R.id.tv_BMR);
		tvBodyFat = (TextView) view.findViewById(R.id.tv_BodyFat);
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(), "DS-DIGIB.TTF");
		tvBMI.setTypeface(tf);
		tvBMR.setTypeface(tf);
		tvBodyFat.setTypeface(tf);
		tvBMI.setText(b.getString("BMI"));
		tvBMR.setText(b.getString("BMR"));
		tvBodyFat.setText(b.getString("BodyFat"));
		funtionPad.setOnTouchListener(gestureListener);
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

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);
		return view;
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
//					sideNavigationView.toggleMenu();
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


	public void createImage(View view) {
		View viewChild = view;
		Canvas canvas = null;
		facebookshare.setVisibility(View.INVISIBLE);
		poweredby.setVisibility(View.VISIBLE);
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
		shareOnFB(returnedBitmap);
	}

	public void shareOnFB(Bitmap bitmap) {
		String pathofBmp = Images.Media.insertImage(ctx.getContentResolver(),
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
