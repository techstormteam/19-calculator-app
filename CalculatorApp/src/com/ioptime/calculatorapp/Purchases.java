package com.ioptime.calculatorapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.android.trivialdrivesample.util.IabHelper;
import com.android.trivialdrivesample.util.IabResult;
import com.android.trivialdrivesample.util.Inventory;
import com.android.trivialdrivesample.util.Purchase;

public class Purchases {
	static boolean isPaymentMade = false;
	static String payload;
	static final String TAG = "TrivialDrive";
	static boolean mIsPremium = false;
	static final String SKU_PREMIUM = "com.smartcalculator.inapp";
	static final int RC_REQUEST = 10001;
	// The helper object
	static IabHelper mHelper;
	static IabHelper.QueryInventoryFinishedListener mQueryFinishedListener;
	RelativeLayout mainRelativeLayout;
	static IabHelper.OnConsumeFinishedListener mConsumeFinishedListener;
	static IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
	static String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnGaklz1OAuNcxcLWhsAiaoQm80TvA3mzzWNAOmak1/XfYaUwIOIwEvyXDzjkSffkRxIBrfupUSZXAZZcJH8qDXsqUoQG6eNFMCf87lrJwVqA6BpTyNGibDttV7cytlrVhy3eQ0tFKcataCOMxG9fh8F4DcLaLM/OJS7FJHTiCzzxOIA9H6dWKlQDSvz2LZkZ4dsXDBLOuYGNObTGwcmYE4cQ62ebXlgIpG+9oU3G1LET8FXIK2AF/Lx0miCRPDWBKJ6p25IvqDDONuksyu1tXmjYNB+g6x8iBH3Z5rWYEa256Yg8UDHGWYjUnTKUdOmZmBUkgUkS+856weXOXDC6qQIDAQAB";
	SharedPreferences prefs;
	static SharedPreferences.Editor editor;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	static Context context;

	public static void initiatePurchase(final Context ctx) {
		context=ctx;
		mHelper = new IabHelper(ctx, base64EncodedPublicKey);
		// mHelper.enableDebugLogging(true);
		// /////////////////////////////////////////////////////////////////////////////////////////

		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				if (!result.isSuccess()) {
					Log.d(TAG, "Setup not successful. ");

					Toast.makeText(ctx,
							"Problem setting up in-app billing: " + result,
							Toast.LENGTH_LONG).show();
					return;
				}
				if (mHelper == null)
					Log.d(TAG, "Setup successful. Querying inventory.");
				mHelper.queryInventoryAsync(mQueryFinishedListener);
			}
		});

		// /////////////////////////////////////////////////////////////////////////////////////////

		mQueryFinishedListener = new IabHelper.QueryInventoryFinishedListener() {
			@Override
			public void onQueryInventoryFinished(final IabResult result,
					final Inventory inventory) {
				Log.d(TAG, "Query inventory finished.");
				if (mHelper == null) {
					return;
				}

				if (result.isFailure()) {
					Toast.makeText(ctx, "Failed to query inventory: " + result,
							Toast.LENGTH_LONG).show();
					return;
				}

				Log.d(TAG, "Query inventory was successful.");
				// String featurePrice = inventory.getSkuDetails(SKU_PREMIUM)
				// .getPrice();
				mIsPremium = inventory.hasPurchase(SKU_PREMIUM);
				isPaymentMade = inventory.hasPurchase(SKU_PREMIUM);
				if(isPaymentMade)
				{
					editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
							.edit();
					editor.putString("isPaymentMade", "true");
					editor.commit();
				}
				Log.d(TAG,
						"Initial inventory query finished; enabling main UI.");
			}
		};

		mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
			public void onIabPurchaseFinished(IabResult result,
					Purchase purchase) {
				Log.d(TAG, "Purchase finished: " + result + ", purchase: "
						+ purchase);

				// if we were disposed of in the meantime, quit.
				if (mHelper == null)
					return;

				if (result.isFailure()) {
					Toast.makeText(ctx, "Error purchasing: " + result,
							Toast.LENGTH_LONG).show();

					if (result.toString().contains("Already Owned")) {
						editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
								.edit();
						editor.putString("isPaymentMade", "true");
						editor.commit();
					} else {
						editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
								.edit();
						editor.putString("isPaymentMade", "false");
						editor.commit();
					}
					// setWaitScreen(false);
					return;
				}
				if (!verifyDeveloperPayload(purchase)) {
					Toast.makeText(
							ctx,
							"Error purchasing. Authenticity verification failed.",
							Toast.LENGTH_LONG).show();
					// setWaitScreen(false);
					return;
				}

				if (purchase.getSku().equals(SKU_PREMIUM)) {
					// bought the premium upgrade!
					Log.d(TAG,
							"Purchase is premium upgrade. Congratulating user.");
					Toast.makeText(ctx, "Thank you for upgrading to premium!",
							Toast.LENGTH_LONG).show();
					mIsPremium = true;
					isPaymentMade = true;
					editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
							.edit();
					editor.putString("isPaymentMade", "true");
					editor.commit();
					// updateUi();
					// setWaitScreen(false);
				} else {
					mIsPremium = false;
					isPaymentMade = false;
					editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
							.edit();
					editor.putString("isPaymentMade", "false");
					editor.commit();
				}
			}
		};

		mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
			public void onConsumeFinished(Purchase purchase, IabResult result) {
				Log.d(TAG, "Consumption finished. Purchase: " + purchase
						+ ", result: " + result);
				if (mHelper == null)
					return;
				if (result.isSuccess()) {
					Log.d(TAG, "Consumption successful. Provisioning.");
					editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
							.edit();
					editor.putString("isPaymentMade", "true");
					editor.commit();
				} else {
					Toast.makeText(ctx, "Error while consuming: " + result,
							Toast.LENGTH_LONG).show();
					mIsPremium = true;
					isPaymentMade = true;
					editor = context.getSharedPreferences(MY_PREFS_NAME, 0)
							.edit();
					editor.putString("isPaymentMade", "false");
					editor.commit();
				}
				// updateUi();
				// setWaitScreen(false);
				Log.d(TAG, "End consumption flow.");
			}
		};
	}

	public static boolean verifyDeveloperPayload(Purchase p) {
		payload = p.getDeveloperPayload();
		return true;
	}

	public static void makePurchase(final Activity ctx) {

		if (mHelper != null) {

			mHelper.launchPurchaseFlow(ctx, SKU_PREMIUM, RC_REQUEST,
					mPurchaseFinishedListener, payload);

		} else
			Log.d("null", "MHELPER");

		// return isPaymentMade;

	}

	public static void destroy() {
		// if (mHelper != null)
		// mHelper.dispose();
		// mHelper = null;
	}

}
