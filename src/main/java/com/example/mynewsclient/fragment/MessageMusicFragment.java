package com.example.mynewsclient.fragment;

import android.os.Bundle;
import com.example.mynewsclient.R;
import com.shizhefei.fragment.LazyFragment;

public class MessageMusicFragment extends LazyFragment {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_message_music);
    }

}
