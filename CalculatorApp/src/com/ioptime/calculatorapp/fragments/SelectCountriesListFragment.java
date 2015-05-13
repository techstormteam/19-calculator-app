package com.ioptime.calculatorapp.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.ioptime.calculatorapp.ConstantAds;
import com.ioptime.calculatorapp.CurrencyFlags;
import com.ioptime.calculatorapp.CurrencyListArray;
import com.ioptime.calculatorapp.Purchases;
import com.smartcalculator.MainActivityA;
import com.smartcalculator.R;

public class SelectCountriesListFragment extends SherlockFragment implements Upgradeable {

	Context ctx;
	LinearLayout functionPad;

	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	ImageView menuIcon;
	Vibrator vibe;
	RelativeLayout mainRelativeLayout;
	RelativeLayout rl_upgrade;
	RelativeLayout rl_upgrade_parent;
	ImageView upgrade_close;
	ImageView upgrade_bg;
	ImageView upgrade_text;
	int upgradePopUp = 0;
	Animation anim;
	Animation anim_back;
	CurrencyListArray currencyListArray;
	int[] CurrencyArray;
	List<String> currency;
	int[] flag;
	EditText etSearch;
	SearchableAdapter adapter;
	ListView lv;
	ArrayList<CurrencyFlags> arraylist = new ArrayList<CurrencyFlags>();
	ListView list;
	SharedPreferences.Editor editor;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	boolean checkvar = false;
	SharedPreferences prefs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.select_countries, container, false);
		
		super.onCreate(savedInstanceState);
		ctx = container.getContext();
		Purchases.initiatePurchase(MainActivityA.getInstance());
		mainRelativeLayout = (RelativeLayout) view.findViewById(R.id.main_relative_layout);
		prefs = ctx.getSharedPreferences(MY_PREFS_NAME, ctx.MODE_PRIVATE);
		if (!prefs.getString("isPaymentMade", "").equals("true")) {
			ConstantAds.loadInterstitialAd(ctx,
					"top");
		}
		currencyListArray = new CurrencyListArray();
		CurrencyArray = currencyListArray.drawables();
		currency = currencyListArray.currencyList();
		flag = currencyListArray.drawables();
		etSearch = (EditText) view.findViewById(R.id.etSearch);
		ImageView addCurrency = (ImageView) view.findViewById(R.id.addCurrency);
		SharedPreferences prefs = ctx.getSharedPreferences(MY_PREFS_NAME,
				ctx.MODE_PRIVATE);
		String currencyNames = prefs.getString("currencyNames", "");
		Log.d("test", currencyNames);
		for (int i = 0; i < currencyNames.length(); i++) {
			if (currencyNames.startsWith(",")) {
				currencyNames = currencyNames.substring(1);
			}
		}
		String currencyNamesArray[] = currencyNames.split(",");
		if (!currencyNames.equals("") && currencyNamesArray.length == 1) {
			addCurrency.setImageResource(R.drawable.slot_4_5);
		}
		if (currencyNamesArray.length == 2) {
			addCurrency.setImageResource(R.drawable.slot_3_5);
		}
		if (currencyNamesArray.length == 3) {
			addCurrency.setImageResource(R.drawable.slot_2_5);
		}
		if (currencyNamesArray.length == 4) {
			addCurrency.setImageResource(R.drawable.slot_1_5);
		}
		if (currencyNamesArray.length == 5) {
			addCurrency.setImageResource(R.drawable.slot_0_5);
		}

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
//				if (checkvar == true) {
//					editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//							.edit();
//					editor.putString("isPaymentMade", "true");
//					editor.commit();
//				} else {
//					editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
//							.edit();
//					editor.putString("isPaymentMade", "false");
//					editor.commit();
//				}
				rl_upgrade_parent.startAnimation(anim_back);
				upgradePopUp = 0;
			}
		});
		rl_upgrade.setVisibility(View.GONE);
		upgradePopUp = 0;
		functionPad = (LinearLayout) view.findViewById(R.id.functionPad);
		menuIcon = (ImageView) view.findViewById(R.id.menuicon);
		functionPad.setOnTouchListener(gestureListener);
		gestureDetector = new GestureDetector(ctx, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vibe = (Vibrator) ctx.getSystemService(
				Context.VIBRATOR_SERVICE);

		list = (ListView) view.findViewById(R.id.lv);
		for (int i = 0; i < currency.size(); i++) {
			CurrencyFlags wp = new CurrencyFlags(currency.get(i), flag[i],
					false);
			arraylist.add(wp);
		}
		adapter = new SearchableAdapter(ctx, arraylist);
		list.setAdapter(adapter);
		etSearch = (EditText) view.findViewById(R.id.etSearch);

		etSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				String text = etSearch.getText().toString().trim()
						.toLowerCase(Locale.getDefault());
				adapter.filter(text);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

		etSearch.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
				}
				return false;
			}
		});
		
		return view;
	}

	public class SearchableAdapter extends BaseAdapter {

		Context mContext;
		LayoutInflater inflater;
		private List<CurrencyFlags> worldpopulationlist = null;
		private ArrayList<CurrencyFlags> arraylist;
		SharedPreferences prefs = ctx.getSharedPreferences(MY_PREFS_NAME,
				ctx.MODE_PRIVATE);
		String currencyFullNames = prefs.getString("currencyFullNames", "");

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

		public SearchableAdapter(Context context,
				List<CurrencyFlags> worldpopulationlist) {
			mContext = context;
			this.worldpopulationlist = worldpopulationlist;
			inflater = LayoutInflater.from(mContext);
			this.arraylist = new ArrayList<CurrencyFlags>();
			this.arraylist.addAll(worldpopulationlist);
			for (int i = 0; i < currencyFullNames.length(); i++) {
				if (currencyFullNames.startsWith(",")) {
					currencyFullNames = currencyFullNames.substring(1);
				}
			}

		}

		public class ViewHolder {
			TextView currency;
			ImageView flag;
			ImageView tickmark;
		}

		public int getCount() {
			return worldpopulationlist.size();
		}

		public Object getItem(int position) {
			return worldpopulationlist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getViewTypeCount() {
			// Count=Size of ArrayList.
			return worldpopulationlist.size();
		}

		// added functions
		@Override
		public int getItemViewType(int position) {
			return position;
		}

		public View getView(final int position, View view, ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				holder = new ViewHolder();
				view = inflater.inflate(R.layout.list_item, null);
				// Locate the TextViews in listview_item.xml
				holder.currency = (TextView) view.findViewById(R.id.tvItem);
				// Locate the ImageView in listview_item.xml
				holder.flag = (ImageView) view.findViewById(R.id.imgView);
				holder.tickmark = (ImageView) view
						.findViewById(R.id.ivTickMark);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			String spc[] = currencyFullNames.split(",");

			// Set the results into TextViews

			String getCurrency = worldpopulationlist.get(position)
					.getCurrency();
			String stringItem = getCurrency;
			for (int i = 0; i < spc.length; i++) {
				if (stringItem.equals(spc[i])) {
					holder.tickmark
							.setImageResource(R.drawable.add_currency_tick);
					worldpopulationlist.get(position).setTick(true);
					break;
				} else if (!stringItem.equals(spc[i])) {
					worldpopulationlist.get(position).setTick(false);

				}
			}
			String split[] = stringItem.split("-");
			String CurrencyAbbreviation = split[0];
			CurrencyAbbreviation = CurrencyAbbreviation.toUpperCase();
			holder.currency.setText(getCurrency);
			Typeface tf = Typeface.createFromAsset(ctx.getAssets(),
					"HelveticaNeue-Medium.otf");
			holder.currency.setTypeface(tf);
			// Set the results into ImageView
			if (worldpopulationlist.get(position).getTick() == true) {
				holder.tickmark.setImageResource(R.drawable.add_currency_tick);
			} else {
				holder.tickmark.setImageResource(0);
			}
			holder.flag.setImageResource(worldpopulationlist.get(position)
					.getFlag());

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Send single item click data to SingleItemView Class
					String currencyNameToPass;
					int currencyFlagToPass;
					SharedPreferences prefs = ctx.getSharedPreferences(
							MY_PREFS_NAME, ctx.MODE_PRIVATE);
					String currencyNames = prefs.getString("currencyNames", "");
					for (int i = 0; i < currencyNames.length(); i++) {
						if (currencyNames.startsWith(",")) {
							currencyNames = currencyNames.substring(1);
						}
					}
					String currencyFullNames = prefs.getString(
							"currencyFullNames", "");
					String currencyFlags = prefs.getString("flags", "");
					String fromCurrencyNamesFull = prefs.getString(
							"fromCurrencyNamesFull", "USD - USA");

					String currencyNamesArray[] = currencyNames.split(",");
					if (currencyNamesArray.length < 5) {

						String getCurrency = worldpopulationlist.get(position)
								.getCurrency();
						if (!fromCurrencyNamesFull.equals(getCurrency)) {
							Log.d("getCurrency", getCurrency);
							Log.d("fromCurrencyNamesFull",
									fromCurrencyNamesFull);
							String stringItem = getCurrency;
							String split[] = stringItem.split("-");
							String CurrencyAbbreviation = split[0];
							CurrencyAbbreviation = CurrencyAbbreviation.trim();
							currencyNameToPass = CurrencyAbbreviation;
							currencyFlagToPass = worldpopulationlist.get(
									position).getFlag();

							if (!currencyFlags
									.contains(currencyFlagToPass + "")) {
								if (currencyNames.equals("")) {
									currencyNames = currencyNameToPass;
									currencyFullNames = getCurrency;
									currencyFlags = currencyFlagToPass + "";
								} else {
									currencyNames = currencyNames + ","
											+ currencyNameToPass;
									currencyFullNames = currencyFullNames + ","
											+ getCurrency;
									currencyFlags = currencyFlags + ","
											+ currencyFlagToPass;
								}
								Log.d("passingcurrencyandflags", currencyNames
										+ "------" + currencyFlags);
								editor = ctx.getSharedPreferences(MY_PREFS_NAME,
										ctx.MODE_PRIVATE).edit();
								editor.putString("currencyNames", currencyNames);
								editor.putString("flags", currencyFlags);
								editor.putString("currencyFullNames",
										currencyFullNames);
								editor.commit();

								MainActivityA.getInstance().showCurrencyConverterFragment("refresh");
							}

							else {
								Toast.makeText(ctx,
										"Currency already added",
										Toast.LENGTH_LONG).show();
							}
						} else {
							Toast.makeText(ctx,
									"Currency already set as main currency",
									Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(ctx,
								"Currency slot full", Toast.LENGTH_LONG).show();
					}
				}
			});

			return view;
		}

		// Filter Class
		public void filter(String charText) {
			charText = charText.toLowerCase(Locale.getDefault());
			worldpopulationlist.clear();
			if (charText.length() == 0) {
				worldpopulationlist.addAll(arraylist);
			} else {
				for (CurrencyFlags wp : arraylist) {
					if (wp.getCurrency().toLowerCase(Locale.getDefault())
							.contains(charText)) {
						worldpopulationlist.add(wp);
					}
				}
			}
			notifyDataSetChanged();
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
		InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(MainActivityA.getInstance().getCurrentFocus().getWindowToken(), 0);
		return true;
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
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Purchases.destroy();
		ConstantAds.displayInterstitial();
	}
	
}
