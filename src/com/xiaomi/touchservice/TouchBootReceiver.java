package com.xiaomi.touchservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class TouchBootReceiver extends BroadcastReceiver {
    private Context mContext;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        Utils.logI("TouchService-TouchBootReceiver", "Received Intent " + intent.toString());
        ComponentName componentName = new ComponentName(context.getPackageName(), TouchService.class.getName());
        Intent intent2 = new Intent();
        intent2.setComponent(componentName);
        intent2.setAction("touchservice.intent.start");
        if (context.startService(intent2) == null) {
            Utils.logE("TouchService-TouchBootReceiver", "Start Service  " + componentName.toString() + "  Failed");
            return;
        }
        Utils.logI("TouchService-TouchBootReceiver", "Start Service  " + componentName.toString() + "  Successfully");
    }
}
