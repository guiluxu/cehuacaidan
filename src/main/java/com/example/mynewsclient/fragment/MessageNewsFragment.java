package com.example.mynewsclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.example.mynewsclient.R;
import com.example.mynewsclient.common.MyAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;

public class MessageNewsFragment extends LazyFragment {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    private int[] icons;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_message_news);
        icons = new int[]{
                R.mipmap.a0, R.mipmap.a1,
                R.mipmap.a2,R.mipmap.a3,
                R.mipmap.a4,R.mipmap.a5,
                R.mipmap.a6,R.mipmap.a7,
                R.mipmap.a8,R.mipmap.a9,
                R.mipmap.b0,R.mipmap.b1,
                R.mipmap.b2,R.mipmap.b3,
                R.mipmap.b4,R.mipmap.b5,
                R.mipmap.b6,R.mipmap.b7,
                R.mipmap.b8, R.mipmap.b9,
                R.mipmap.c0,R.mipmap.c1,
                R.mipmap.c2,R.mipmap.c3,
                R.mipmap.c4,R.mipmap.c5,
                R.mipmap.c6,R.mipmap.c7,
                R.mipmap.a,R.mipmap.b,
                R.mipmap.c,R.mipmap.d,
                R.mipmap.e,R.mipmap.f,
                R.mipmap.g,R.mipmap.h,
                R.mipmap.i,R.mipmap.j,
                R.mipmap.k,R.mipmap.l,
                R.mipmap.m,R.mipmap.n,
                R.mipmap.o,R.mipmap.p,
                R.mipmap.k,R.mipmap.r};

        mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        listData.clear();
                        for(int i = 0; i < 15 ;i++){
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {

                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            mRecyclerView.loadMoreComplete();
                            for(int i = 0; i < 15 ;i++){
                                listData.add("item" + (i + listData.size()) );
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < 9 ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;

            }
        });

        listData = new ArrayList<String>();
        for(int i = 0; i < 15 ;i++){
            listData.add("item" + i);
        }

        mAdapter = new MyAdapter(listData,icons);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
