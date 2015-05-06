package com.ioptime.calculatorapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smartcalculator.R;

public class UnitConverterListBackup {
	List<String> unitList = new ArrayList<String>();
//	unitList.add("Meters");
//	unitList.add("Feet");
//	unitList.add("Yards");
//	unitList.add("Inches");
//	unitList.add("Kilometers");
//	unitList.add("Miles");
//	unitList.add("Centimeters");
//	unitList.add("Milimiters");
//	unitList.add("Microns");
//	unitList.add("Nanometers");
//	unitList.add("Light Years");
//	List<String> symbolList = new ArrayList<String>();
//	symbolList.add("m");
//	symbolList.add("ft");
//	symbolList.add("yd");
//	symbolList.add("in");
//	symbolList.add("km");
//	symbolList.add("mi");
//	symbolList.add("cm");
//	symbolList.add("mm");
//	symbolList.add("um");
//	symbolList.add("nm");
//	symbolList.add("ly");
//	Boolean boolCheck[] = new Boolean[unitList.size()];
//	for (int i = 0; i < unitList.size(); i++) {
//		boolCheck[i] = false;
//	}

	// lv = (ListView) findViewById(R.id.uc_lv);
	// lv.setDivider(null);
	// lv.setDividerHeight(0);
	// lv.setAdapter(new SearchableAdapter(UnitConverterLength.this,
	// unitList,
	// symbolList, boolCheck));
	
	public class SearchableAdapter extends BaseAdapter {

		Context mContext;
		LayoutInflater inflater;
		private List<String> unitsList = null;
		private List<String> symbolList = null;
		Boolean boolCheck[];
		Boolean oneChecked = false;
		private ArrayList<String> arraylist;

		public boolean checkPositionArr(long pos, int array[]) {
			boolean toReturn = false;
			if (array != null) {
				for (int i = 0; i < array.length; i++) {
					if (array[i] == pos) {
						toReturn = true;
					}
				}
			}
			return toReturn;
		}

		public SearchableAdapter(Context context, List<String> unitsList,
				List<String> symbolList, Boolean[] _boolCheck) {
			mContext = context;
			this.unitsList = unitsList;
			this.symbolList = symbolList;
			inflater = LayoutInflater.from(mContext);
			this.arraylist = new ArrayList<String>();
			this.arraylist.addAll(unitsList);
			boolCheck = _boolCheck;
		}

		public class ViewHolder {
			TextView units;
			ImageView conversionArrow;
			TextView conversion;
			TextView conversions_symbols;
			LinearLayout uc_hl;
			ImageView separator;
			int check;
		}

		public int getCount() {
			return unitsList.size();
		}

		public Object getItem(int position) {
			return unitsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getViewTypeCount() {
			// Count=Size of ArrayList.
			return unitsList.size();
		}

		// added functions
		@Override
		public int getItemViewType(int position) {
			return position;
		}

		//
		public boolean isChecked(int position) {
			return boolCheck[position];
		}

		public void setChecked(int position, boolean isChecked) {

			if (!boolCheck[position]) {

				if (oneChecked) {

					for (int i = 0; i < boolCheck.length; i++) {
						boolCheck[i] = false;
					}

				}
				oneChecked = true;
				boolCheck[position] = isChecked;
				notifyDataSetChanged();
			}
		}

		public void toggle(int position) {
			setChecked(position, !isChecked(position));
		}

		public View getView(final int position, View view, ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				holder = new ViewHolder();
				view = inflater.inflate(R.layout.uc_list_item, null);
				// Locate the TextViews in listview_item.xml
				holder.units = (TextView) view.findViewById(R.id.tv_unit);
				holder.conversionArrow = (ImageView) view
						.findViewById(R.id.conversion_arrow);
				holder.conversion = (TextView) view
						.findViewById(R.id.tv_conversions);
				holder.conversions_symbols = (TextView) view
						.findViewById(R.id.tv_symbols);
				holder.uc_hl = (LinearLayout) view
						.findViewById(R.id.uc_list_hl);
				holder.separator = (ImageView) view
						.findViewById(R.id.separator);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			String units = unitsList.get(position);
			holder.units.setText(units);
			String symbols = symbolList.get(position);
			holder.conversions_symbols.setText(symbols);
			holder.uc_hl.setBackgroundResource(0);

			if (boolCheck[position]) {
				holder.uc_hl
						.setBackgroundColor(Color.parseColor("#30000000"));
				holder.separator.setImageResource(0);
			} else if (!boolCheck[position]) {
				holder.uc_hl.setBackgroundResource(0);
				holder.separator
						.setImageResource(R.drawable.uc_list_item_separator);

			}
			holder.uc_hl.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Send single item click data to SingleItemView Class
					toggle(position);

				}
			});

			return view;

		}

	}

}
