package com.ioptime.calculatorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.smartcalculator.R;

public class MenuListAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	String[] mTitleStr;
	int[] mTitle;
	LayoutInflater inflater;

	public MenuListAdapter(Context context, String[] titleStr, int[] title) {
		this.context = context;
		this.mTitleStr = titleStr;
		this.mTitle = title;
	}

	@Override
	public int getCount() {
		return mTitleStr.length;
	}

	@Override
	public Object getItem(int position) {
		return mTitleStr[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		final ImageView imgTitle;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.drawer_list_item, parent,
				false);

		// Locate the ImageViews in drawer_list_item.xml
		imgTitle = (ImageView) itemView.findViewById(R.id.side_navigation_item_text);
		imgTitle.setImageResource(mTitle[position]);
		imgTitle.setContentDescription(mTitleStr[position]);
		
//		imgTitle.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				imgTitle.setImageResource(mTitleClicked[position]);
//			}
//		});
		
		return itemView;
	}

}
