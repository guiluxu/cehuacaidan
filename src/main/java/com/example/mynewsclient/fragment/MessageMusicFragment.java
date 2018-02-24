package com.example.mynewsclient.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mynewsclient.R;
import com.example.mynewsclient.common.MyBroadCastReceiver;
import com.example.mynewsclient.common.PlayingMusicServices;
import com.shizhefei.fragment.LazyFragment;

public class MessageMusicFragment extends LazyFragment implements View.OnClickListener {

    /**
     * 规定开始音乐、暂停音乐、结束音乐的标志
     */
    public  static final int PLAT_MUSIC=1;
    public  static final int PAUSE_MUSIC=2;
    public  static final int STOP_MUSIC=3;

    private MyBroadCastReceiver receiver;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain_message_music);
        receiver=new MyBroadCastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.complete");
        getActivity().registerReceiver(receiver,filter);
        Button button = (Button) findViewById(R.id.btn_startmusic);
        button.setOnClickListener(this);
        Button button1 = (Button) findViewById(R.id.btn_pausemusic);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.btn_stopmusic);
        button2.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            //开始音乐
            case R.id.btn_startmusic:
                playingmusic(PLAT_MUSIC);
                break;
            //暂停
            case R.id.btn_pausemusic:
                playingmusic(PAUSE_MUSIC);
                break;
            //停止
            case R.id.btn_stopmusic:
                playingmusic(STOP_MUSIC);
                break;
        }
    }

    private void playingmusic(int type) {
        //启动服务，播放音乐
        Intent intent=new Intent(getContext(),PlayingMusicServices.class);
        intent.putExtra("type",type);
        getActivity().startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

}
