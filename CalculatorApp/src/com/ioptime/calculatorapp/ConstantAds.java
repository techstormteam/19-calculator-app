package com.ioptime.calculatorapp;


import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smartcalculator.R;





public class ConstantAds {
	private static InterstitialAd interstitial;
	
	public static void loadAd(RelativeLayout layout, Context ctx, String place) {
		AdView mAdView = new AdView(ctx);
		mAdView.setAdUnitId(ctx.getResources().getString(R.string.ADS_BANNER));
		
		if (isTablet(ctx.getApplicationContext())) {
			mAdView.setAdSize(AdSize.FULL_BANNER);
		} else {
			Log.d("device is", "smart phone");
			mAdView.setAdSize(AdSize.BANNER);
		}

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		if (place.contains("top")) {

			params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

			params.addRule(RelativeLayout.CENTER_HORIZONTAL,
					RelativeLayout.TRUE);

		} else if (place.contains("bottom")) {
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
					RelativeLayout.TRUE);
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
					RelativeLayout.TRUE);
		}
		layout.addView(mAdView, params);
		// mAdView.loadAd(new AdRequest.Builder().addTestDevice(
		// "17FF6034AB18013A3B49560F83D3E3D4").build());
		mAdView.loadAd(new AdRequest.Builder().build());

	}
	
	public static void loadInterstitialAd(Context ctx, String place) {
		// Prepare the Interstitial Ad
		interstitial = new InterstitialAd(ctx);
		// Insert the Ad Unit ID
		interstitial.setAdUnitId(ctx.getResources().getString(R.string.ADS_INTERSTITIAL));
		
		// Load ads into Interstitial Ads
		interstitial.loadAd(new AdRequest.Builder()
		.build());
		
		// Prepare an Interstitial Ad Listener
		interstitial.setAdListener(new AdListener() {
			public void onAdLoaded() {
//				displayInterstitial();
			}
		});
	}
	
	public static void displayInterstitial() {
		// If Ads are loaded, show Interstitial else show nothing.
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}
	
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

}
