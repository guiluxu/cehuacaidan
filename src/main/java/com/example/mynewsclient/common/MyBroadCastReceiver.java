package com.example.mynewsclient.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by HGTXxgl on 2018/2/23.
 */

public class MyBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"音乐播放结束",Toast.LENGTH_SHORT).show();
    }
}
