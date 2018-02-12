package com.example.mynewsclient.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynewsclient.R;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

public class TabMessageFragment extends LazyFragment {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private String tabName;
	private int index;
	private List<LazyFragment> fragments;
	private String[] tabNames;
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain_message);
		Resources res = getResources();
		fragments = new ArrayList<>();
		fragments.add(new MessageMusicFragment());
		fragments.add(new MessageVideoFragment());
		fragments.add(new MessageNewsFragment());
		tabNames = new String[]{"MUSIC", "VIDEO", "NEWS"};
		Bundle bundle = getArguments();
		tabName = bundle.getString(INTENT_STRING_TABNAME);
		index = bundle.getInt(INTENT_INT_INDEX);

		ViewPager viewPager = (ViewPager) findViewById(R.id.fragment_tabmain_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.fragment_tabmain_indicator);

		indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));

		float unSelectSize = 16;
		float selectSize = unSelectSize * 1.2f;

		int selectColor = res.getColor(R.color.tab_top_text_2);
		int unSelectColor = res.getColor(R.color.tab_top_text_1);
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

		viewPager.setOffscreenPageLimit(4);

		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());

		// 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
		// 而在activity里面用FragmentManager 是 getSupportFragmentManager()
		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			/*if (index == 0){
				return 0;
			}else if(index == 1){
				return 2;
			}else{*/
				return 3;
//			}
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(tabNames[position]);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			/*MessageMusicFragment mainFragment = new MessageMusicFragment();
			Bundle bundle = new Bundle();
			bundle.putString(MessageMusicFragment.INTENT_STRING_TABNAME, tabName);
			bundle.putInt(MessageMusicFragment.INTENT_INT_POSITION, position);
			mainFragment.setArguments(bundle);*/
			LazyFragment mainFragment = fragments.get(position);
			/*Bundle bundle = new Bundle();
			if (tabNames[position].equals("MUSIC")){
				bundle.putString(TabHomeFragment.INTENT_STRING_TABNAME, tabNames[position]);
				bundle.putInt(TabHomeFragment.INTENT_INT_INDEX, position);
			}else if (tabNames[position].equals("VIDEO")){
				bundle.putString(TabMessageFragment.INTENT_STRING_TABNAME, tabNames[position]);
				bundle.putInt(TabMessageFragment.INTENT_INT_INDEX, position);
			}else if (tabNames[position].equals("NEWS")){
				bundle.putString(TabMessageFragment.INTENT_STRING_TABNAME, tabNames[position]);
				bundle.putInt(TabMessageFragment.INTENT_INT_INDEX, position);
			}
			mainFragment.setArguments(bundle);*/
			return mainFragment;
		}
	}

}
