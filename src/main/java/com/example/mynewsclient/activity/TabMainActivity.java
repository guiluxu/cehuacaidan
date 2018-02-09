package com.example.mynewsclient.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynewsclient.R;
import com.example.mynewsclient.fragment.FirstLayerFragment;
import com.example.mynewsclient.fragment.MeFragment;
import com.example.mynewsclient.fragment.MoreTabFragment;
import com.example.mynewsclient.fragment.NewsFragment;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import java.util.ArrayList;
import java.util.List;

public class TabMainActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;
    private FixedIndicatorView indicator;

    private String[] tabNames;
    private List<LazyFragment> fragments;
    private LazyFragment fr1, fr2, fr3, fr4;
    private FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_tabmain);
        initView();
    }

    private void initView() {
        tabNames = new String[]{"主页", "消息", "发现", "我"};
        fragments = new ArrayList<>();
        fragments.add(fr1 = new MoreTabFragment());
        fragments.add(fr2 = new FirstLayerFragment());
        fragments.add(fr3 = new NewsFragment());
        fragments.add(fr4 = new MeFragment());
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
        indicator = (FixedIndicatorView) findViewById(R.id.tabmain_indicator);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.GRAY));

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);
    }

    private class MyAdapter extends IndicatorFragmentPagerAdapter {
        private int[] tabIcons = {R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
                R.drawable.maintab_4_selector};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            LazyFragment mainFragment = fragments.get(position);
            Bundle bundle = new Bundle();
            if (tabNames[position].equals("主页")){
                bundle.putString(MoreTabFragment.INTENT_STRING_TABNAME, tabNames[position]);
                bundle.putInt(MoreTabFragment.INTENT_INT_INDEX, position);
            }else if (tabNames[position].equals("消息")){
                bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME, tabNames[position]);
                bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
            }else if (tabNames[position].equals("发现")){
                bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME, tabNames[position]);
                bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
            }else{
                bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME, tabNames[position]);
                bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
            }
            mainFragment.setArguments(bundle);
            return mainFragment;
        }
    }
}
