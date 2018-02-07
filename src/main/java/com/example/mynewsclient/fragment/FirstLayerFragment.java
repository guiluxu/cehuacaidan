package com.example.mynewsclient.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynewsclient.R;
import com.example.mynewsclient.utils.DpUtil;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

public class FirstLayerFragment extends LazyFragment {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private String tabName;
	private int index;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain);
		Resources res = getResources();

		Bundle bundle = getArguments();
		tabName = bundle.getString(INTENT_STRING_TABNAME);
		index = bundle.getInt(INTENT_INT_INDEX);

		ViewPager viewPager = (ViewPager) findViewById(R.id.fragment_tabmain_viewPager);
//		Indicator indicator = (Indicator) findViewById(R.id.fragment_tabmain_indicator);
		ScrollIndicatorView scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);

//		indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));


//		float unSelectSize = 16;
//		float selectSize = unSelectSize * 1.2f;
		float unSelectSize = 12;
		float selectSize = unSelectSize * 1.3f;
		scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF2196F3, Color.GRAY).setSize(selectSize, unSelectSize));
		scrollIndicatorView.setScrollBar(new ColorBar(getContext(), 0xFF2196F3, 4));

//		int selectColor = res.getColor(R.color.tab_top_text_2);
//		int unSelectColor = res.getColor(R.color.tab_top_text_1);
//		indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

//		viewPager.setOffscreenPageLimit(4);
//
//		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		viewPager.setOffscreenPageLimit(2);
		indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
		indicatorViewPager.setAdapter(new MyAdapter());

		inflate = LayoutInflater.from(getApplicationContext());

		// 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
		// 而在activity里面用FragmentManager 是 getSupportFragmentManager()
//		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

		Log.d("cccc", "Fragment 将要创建View " + this);
		
	}
	/*private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(tabName + " " + position);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			SecondLayerFragment mainFragment = new SecondLayerFragment();
			Bundle bundle = new Bundle();
			bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME, tabName);
			bundle.putInt(SecondLayerFragment.INTENT_INT_POSITION, position);
			mainFragment.setArguments(bundle);
			return mainFragment;
		}
	}*/
	private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
		private String[] versions = {"Cupcake", "Donut", "Éclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lolipop", "Marshmallow"};
		private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶", "姜饼", "蜂巢", "冰激凌三明治", "果冻豆", "奇巧巧克力棒", "棒棒糖", "棉花糖"};

		@Override
		public int getCount() {
			return versions.length;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(versions[position]);

			int witdh = getTextWidth(textView);
			int padding = DpUtil.dipToPix(getApplicationContext(), 8);
			//因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
			//1.3f是根据上面字体大小变化的倍数1.3f设置
			textView.setWidth((int) (witdh * 1.3f) + padding);

			return convertView;
		}

		@Override
		public View getViewForPage(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = new TextView(container.getContext());
			}
			TextView textView = (TextView) convertView;
			textView.setText(names[position]);
			textView.setGravity(Gravity.CENTER);
			textView.setTextColor(Color.GRAY);
			return convertView;
		}

		@Override
		public int getItemPosition(Object object) {
			//这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
			// 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
			return PagerAdapter.POSITION_UNCHANGED;
		}

		private int getTextWidth(TextView textView) {
			if (textView == null) {
				return 0;
			}
			Rect bounds = new Rect();
			String text = textView.getText().toString();
			Paint paint = textView.getPaint();
			paint.getTextBounds(text, 0, text.length(), bounds);
			int width = bounds.left + bounds.width();
			return width;
		}

	}

}
