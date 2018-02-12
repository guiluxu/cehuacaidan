package com.example.mynewsclient.fragment;

import android.os.Bundle;

import com.example.mynewsclient.R;
import com.shizhefei.fragment.LazyFragment;

/**
 * Created by HGTXxgl on 2018/2/12.
 */

public class MessageVideoFragment extends LazyFragment {
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_message_video);
    }
}
