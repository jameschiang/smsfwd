package com.jameschiang.smsfwd;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by JamesChiang on 2015/10/29.
 */
public class SmsFwdDeliveredReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getCanonicalName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive");
        if (getResultCode() == Activity.RESULT_OK){
            Log.e(TAG, "Delivered success");
            Toast.makeText(context.getApplicationContext(), "已送达", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Delivered failed");
            Toast.makeText(context.getApplicationContext(), "未送达", Toast.LENGTH_LONG).show();
        }
    }
}
