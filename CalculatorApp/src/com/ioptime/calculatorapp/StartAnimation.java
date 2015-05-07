package com.ioptime.calculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.smartcalculator.R;

public class StartAnimation extends Activity{

	ImageView main_icon;
	RelativeLayout img_parent;
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_animation);
		main_icon=(ImageView) findViewById(R.id.main_icon);
		img_parent=(RelativeLayout) findViewById(R.id.img_parent);
		
		
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
		fadeIn.setDuration(2000);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
		fadeOut.setStartOffset(1000);
		fadeOut.setDuration(500);
		fadeOut.setAnimationListener(new AnimationListener() {

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
				img_parent.setVisibility(View.GONE);
				startActivity(new Intent(getApplicationContext(),MainActivity.class));
				finish();
				
			}
		});
		
		
		AnimationSet animation = new AnimationSet(false); //change to false
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		img_parent.setAnimation(animation);
		
	
	}
}
