package com.ioptime.calculatorapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
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
import com.devspark.sidenavigation.SideNavigationView;
import com.ioptime.calculatorapp.ConstantAds;
import com.ioptime.calculatorapp.Purchases;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class AboutFragment extends SherlockFragment {

	Context ctx;
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
	TextView tvVersion;
	LinearLayout functionPad;
	ImageView plugin_fb;
	ImageView plugin_tw;
	ImageView plugin_in;
	RelativeLayout mainRelativeLayout;
	boolean checkvar = false;
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about, container, false);

		super.onCreate(savedInstanceState);
		ctx = container.getContext();
		float matrics = getResources().getDisplayMetrics().density;
		Purchases.initiatePurchase(MainActivityA.getInstance());
		mainRelativeLayout = (RelativeLayout) view.findViewById(R.id.main_relative_layout);
		prefs = ctx.getSharedPreferences(MY_PREFS_NAME, ctx.MODE_PRIVATE);
		if (!prefs.getString("isPaymentMade", "").equals("true")) {
			ConstantAds.loadInterstitialAd(ctx,
					"top");
		}
		plugin_fb = (ImageView) view.findViewById(R.id.fb_plugin);
		plugin_tw = (ImageView) view.findViewById(R.id.tw_plugin);
		plugin_in = (ImageView) view.findViewById(R.id.in_plugin);

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
			Purchases.makePurchase(MainActivityA.getInstance());

//			if (checkvar == true) {
//				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//						.edit();
//				editor.putString("isPaymentMade", "true");
//				editor.commit();
//			} else {
//				editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//						.edit();
//				editor.putString("isPaymentMade", "false");
//				editor.commit();
//			}
				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp = 0;
			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;
		functionPad = (LinearLayout) view.findViewById(R.id.functionPad);
		functionPad.setOnTouchListener(gestureListener);

		tvVersion = (TextView) view.findViewById(R.id.tv_version);
		PackageInfo pInfo = null;
		try {
			pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
			tvVersion.setText("Version " + pInfo.versionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			tvVersion.setText("Version not available");
			e.printStackTrace();
		}

		gestureDetector = new GestureDetector(ctx, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};
		sideNavigationView = (SideNavigationView) view.findViewById(R.id.side_navigation_view);
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(), "DS-DIGIB.TTF");


		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getApplicationContext().getSystemService(
				Context.VIBRATOR_SERVICE);

		plugin_fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openLink("http://www.facebook.com/smartcalculator");

			}
		});
		plugin_tw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openLink("http://www.twitter.com/smartcalculator");

			}
		});
		plugin_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openLink("http://www.instagram.com/smartcalculator");

			}
		});
		
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
	
	public void openLink(String url) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Purchases.destroy();
		ConstantAds.displayInterstitial();
	}

}
