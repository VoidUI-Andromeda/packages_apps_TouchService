package com.xiaomi.touchservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.xiaomi.touchservice.ITouchService;

public class TouchService extends Service {
    private static Handler handler;
    private static Context mContext;
    private static DebugReceiver mDebugReceiver;
    private static ITouchFeature mTouchFeature;
    private static TouchTrack mTrack;
    private static Runnable run = new Runnable() { // from class: com.xiaomi.touchservice.TouchService.1
        @Override // java.lang.Runnable
        public void run() {
            String touchEvent = ITouchFeature.getInstance().getTouchEvent();
            Utils.logD("TouchService-TouchService", "touch event string: " + touchEvent);
            if (touchEvent != null && touchEvent != "") {
                TouchService.mTrack.reportTouchEvent(touchEvent);
            }
            TouchService.handler.postDelayed(this, 1800000L);
        }
    };
    private ITouchService.Stub mBinder;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Utils.logI("TouchService-TouchService", "onBind");
        String action = intent.getAction();
        if (!action.equals("touchservice.intent.bind")) {
            Utils.logE("TouchService-TouchService", "onBind invalid action " + action);
            return null;
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.i("TouchService-TouchService", "onCreate");
        mContext = this;
        mTrack = TouchTrack.getInstance(this);
        mTouchFeature = ITouchFeature.getInstance();
        this.mBinder = new ITouchServiceStubImpl();
        handler = new Handler();
        mDebugReceiver = new DebugReceiver();
        registerDebugReceiver();
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Utils.logI("TouchService-TouchService", "onStartCommand");
        handler.removeCallbacks(run);
        handler.post(run);
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i("TouchService-TouchService", "onDestroy");
        super.onDestroy();
    }

    public static void testReport() {
        handler.removeCallbacks(run);
        handler.post(run);
    }

    private void registerDebugReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("touchservice.intent.debugall");
        intentFilter.addAction("touchservice.intent.testonetrack");
        mContext.registerReceiver(mDebugReceiver, intentFilter);
    }
}
