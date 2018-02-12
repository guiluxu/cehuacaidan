package com.example.mynewsclient.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.mynewsclient.R;
import com.example.mynewsclient.common.Beauty;
import com.example.mynewsclient.common.BeautyAdapter;
import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HGTXxgl on 2018/2/9.
 */

public class TabDiscoverFragment extends LazyFragment {
    private RecyclerView recyclerView;
    private List<Beauty> data = new ArrayList<>();
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_discover);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //使用瀑布流布局,第一个参数 spanCount 列数,第二个参数 orentation 排列方向
        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //给recyclerView设置LayoutManager
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        initData();
        BeautyAdapter adapter = new BeautyAdapter(data, getContext());
        //设置adapter
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        Beauty beauty;
        for (int i = 0; i < 10; i++) {
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.a);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.b);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.c);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.d);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.e);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.f);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.g);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.h);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.i);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.j);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.k);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.l);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.m);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.n);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.o);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.p);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.q);
            data.add(beauty);
            beauty = new Beauty("阳阳燕儿头" + i, R.mipmap.r);
            data.add(beauty);
        }
    }
}