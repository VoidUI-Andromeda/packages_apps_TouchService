package com.xiaomi.touchservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class DebugReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (action.equals("touchservice.intent.debugall")) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                boolean z = extras.getBoolean("debug_all");
                Utils.setDebugAll(z);
                Utils.logI("TouchService-DebugReceiver", "setDebugAll: " + z);
            }
        } else if (action.equals("touchservice.intent.testonetrack")) {
            TouchService.testReport();
        }
    }
}
