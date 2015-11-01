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
public class SmsFwdSentReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getCanonicalName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive");
        if (getResultCode() == Activity.RESULT_OK){
            Log.e(TAG, "sent success");
            Toast.makeText(context.getApplicationContext(), "短信转发成功", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "sent failed");
            Toast.makeText(context.getApplicationContext(), "短信转发失败", Toast.LENGTH_LONG).show();
        }
    }
}
