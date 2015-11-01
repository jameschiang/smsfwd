package com.jameschiang.smsfwd;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;

/**
 * Created by JamesChiang on 2015/10/29.
 */
public class SmsReceiver extends BroadcastReceiver {
    public static final String SENT_SMS_ACTION = "com.jameschiang.smsfwd.SENT_SMS_ACTION";
    public static final String DELIVERED_SMS_ACTION = "com.jameschiang.smsfwd.DELIVERED_SMS_ACTION";

    SmsManager smsMgr;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("SmsReceiver", "onReceive***");
        Intent intentStartBgSrv = new Intent(context,SmsFwdBgSrv.class);
//        Toast.makeText(context,"gotit", Toast.LENGTH_LONG).show();
        smsMgr = SmsManager.getDefault();
        Log.e("SmsReceiver","intent.getAction()"+intent.getAction().toString());

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.e("SmsReceiver", "SMS_RECEIVED***");
            StringBuilder sb = new StringBuilder();
            Bundle bd = intent.getExtras();
            if (bd != null){
                Object[] pdus = (Object[])bd.get("pdus");
                SmsMessage[] msgs = new SmsMessage[pdus.length];

//                final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
                TelephonyManager phoneMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                  String format = intent.getStringExtra("format");
                Log.e("SmsReceiver", "format***" + format);
                if (Build.VERSION.SDK_INT >= 19/*Boolean.FALSE*/) {// 4.4 kitkat or later
                    msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                    Log.e("SmsReceiver", "Build.VERSION.SDK_INT >= 19***");
                } else {
                    Log.e("SmsReceiver", "Build.VERSION.SDK_INT < 19***");
                    for (int i = 0; i < pdus.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    }
                }
                for (SmsMessage msg : msgs) {
                    CharSequence cs = msg.getDisplayOriginatingAddress();
                    Log.e("SmsReceiver", "msg addr : "+ cs);
                    if (cs.toString().startsWith("100") || cs.toString().startsWith("106") || cs.toString().startsWith("9")/* || cs.toString().startsWith("13693020368")*/) {

                        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT_SMS_ACTION), PendingIntent.FLAG_ONE_SHOT);
                        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0, new Intent(DELIVERED_SMS_ACTION), PendingIntent.FLAG_ONE_SHOT);

                        smsMgr.sendTextMessage("13693020368", null, msg.getDisplayMessageBody(), sentPI, deliveredPI);
                        Log.e("SmsReceiver", "sendTextMessage : " + msg.getDisplayMessageBody());
                    }
                }
            }


        }

//        context.startService(intentStartBgSrv);
    }
}
