package com.ioptime.calculatorapp;

import android.content.Context;
import android.content.Intent;

public class Constants {
	
	public static void startActivity(Context ctx, Intent intent) {
		ctx.startActivity(intent);
		//((Activity) ctx).overridePendingTransition(R.drawable.right_slide_in,
				//R.drawable.right_slide_out);
	}

	
	public static void startActivityPrev(Context ctx, Intent intent) {
		ctx.startActivity(intent);
		//((Activity) ctx).overridePendingTransition(drawable.left_slide_in,
			//	drawable.left_slide_out);
	}

}
