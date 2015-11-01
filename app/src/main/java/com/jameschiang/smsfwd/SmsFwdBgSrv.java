package com.jameschiang.smsfwd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by JamesChiang on 2015/10/29.
 */
public class SmsFwdBgSrv extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("bgsrv", "on start***");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("bgsrv", "onBind***");
        return null;
    }
}
