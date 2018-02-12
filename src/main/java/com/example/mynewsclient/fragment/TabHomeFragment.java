package com.example.mynewsclient.fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynewsclient.R;
import com.example.mynewsclient.utils.DisplaysUtil;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

/**
 * Created by LuckyJayce on 2016/6/25.
 */
public class TabHomeFragment extends LazyFragment {
    private IndicatorViewPager indicatorViewPager;
    public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
    public static final String INTENT_INT_INDEX = "intent_int_index";
    private String tabName;
    private int index;
    private LayoutInflater inflate;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_home);
        inflate = LayoutInflater.from(getApplicationContext());
        Bundle bundle = getArguments();
        tabName = bundle.getString(INTENT_STRING_TABNAME);
        index = bundle.getInt(INTENT_INT_INDEX);

        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        ScrollIndicatorView scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);

        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF2196F3, Color.GRAY).setSize(selectSize, unSelectSize));

        scrollIndicatorView.setScrollBar(new ColorBar(getContext(), 0xFF2196F3, 4));

        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter());
    }

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
            int padding = DisplaysUtil.dipToPix(getApplicationContext(), 8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.3f) + padding);

            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            /*if (convertView == null) {
                convertView = new TextView(container.getContext());
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.GRAY);
            return convertView;*/



            if (convertView == null)
            {
                convertView = inflate.inflate(R.layout.tab_moretab_item, container, false);
            }
            ImageView centerPic = (ImageView) convertView.findViewById(R.id.ivFeedCenter);
            TextView centerBottom = (TextView) convertView.findViewById(R.id.ivFeedBottom);
            if (position == 0){
                centerPic.setImageResource(R.mipmap.a);
                centerBottom.setText("Yang Yang and his mother looked at the phone camera together. They had a funny look.");
            }else if (position == 1){
                centerPic.setImageResource(R.mipmap.b);
                centerBottom.setText("I was most satisfied with the picture of yan and I kissing each other in front of the old windowsill.");
            }else if (position == 2){
                centerPic.setImageResource(R.mipmap.c);
                centerBottom.setText("Yan's first person is standing quietly on the lake of charm staring into the distance, this picture is very beautiful.");
            }else if (position == 3){
                centerPic.setImageResource(R.mipmap.d);
                centerBottom.setText("Yan and I stood together on the beautiful lake, and we looked at each other.");
            }else if (position == 4){
                centerPic.setImageResource(R.mipmap.e);
                centerBottom.setText("Yang Yang was born on this day. He was still in his grandmother's arms.");
            }else if (position == 5){
                centerPic.setImageResource(R.mipmap.f);
                centerBottom.setText("When we were bathing in yangyang, he peed and was very happy.");
            }else if (position == 6){
                centerPic.setImageResource(R.mipmap.g);
                centerBottom.setText("Yan just finished his hair, his hair is very beautiful, especially his beauty.");
            }else if (position == 7){
                centerPic.setImageResource(R.mipmap.h);
                centerBottom.setText("The wedding picture taken by yan and I in a european-style room is very retro and beautiful.");
            }else if (position == 8){
                centerPic.setImageResource(R.mipmap.i);
                centerBottom.setText("This picture was taken by yan before I knew her. She was eating a snack.");
            }else if (position == 9){
                centerPic.setImageResource(R.mipmap.j);
                centerBottom.setText("Yangyang and yan at the city god temple, they look very warm.");
            }else if (position == 10){
                centerPic.setImageResource(R.mipmap.k);
                centerBottom.setText("Yangyang is very cute. Yan's head is holding him. I like this picture very much.");
            }
            return convertView;
/*

            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabName + " " + position);
            return convertView;*/
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
