package com.xiaomi.touchservice;

import android.content.Context;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TouchTrack {
    private static String APP_ID = "31000000625";
    private static String ONETRACK_ACTION = "onetrack.action.TRACK_EVENT";
    private static String ONETRACK_PACKAGE_NAME = "com.miui.analytics";
    private static TouchTrack mTrack;
    private Context mContext;
    private String[] TouchUseDataParams = {"click_num", "slibe_num", "operation_time", "doubletap_wakeup", "fod", "aod", "fod_shortcut", "adjust", "water_mode_num", "glove_mode_num", "film_num", "edge_filter", "pri_or_sec", "navigation", "hand"};
    private String[] TouchExceptionParams = {"long_press_num", "fod_exception", "fw_exception", "spi_exception", "irq_timeout", "probe_fail", "frozen", "frame_drop_num", "noise_num", "alg_exception", "esd_num", "scan_exception", "ic_buffer_overflow", "touch_break", "ghost", "no_response", "palm_error"};
    private String[] GameModeParams = {"tolerence", "up_threshold", "aim_sensitivity", "tap_stability", "edge_filter"};
    private String[] PenParams = {"active_num", "click_num", "pair_time", "charger_time", "use_time", "hover_time", "write_time", "attach_time"};
    private String[] KeyBoardParams = {"connect_time", "connection_num"};

    public static TouchTrack getInstance(Context context) {
        if (mTrack == null) {
            mTrack = new TouchTrack(context);
        }
        return mTrack;
    }

    private TouchTrack(Context context) {
        this.mContext = context;
    }

    public void reportTouchEvent(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("event");
                char c = 65535;
                switch (string.hashCode()) {
                    case -1704717099:
                        if (string.equals("GameMode")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1621867086:
                        if (string.equals("TouchUseData")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1378905104:
                        if (string.equals("TouchException")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 80121:
                        if (string.equals("Pen")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 538830823:
                        if (string.equals("KeyBoard")) {
                            c = 4;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    reportTouchUseData(jSONObject);
                } else if (c == 1) {
                    reportTouchException(jSONObject);
                } else if (c == 2) {
                    reportGameMode(jSONObject);
                } else if (c == 3) {
                    reportPen(jSONObject);
                } else if (c == 4) {
                    reportKeyboard(jSONObject);
                } else {
                    Utils.logE("TouchService-TouchTrack", "Invalid event name " + string);
                }
            }
        } catch (JSONException e) {
            Utils.logE("TouchService-TouchTrack", "parse json failed: " + e.toString());
        }
    }

    private void reportTouchUseData(JSONObject jSONObject) {
        try {
            Intent intent = new Intent(ONETRACK_ACTION);
            intent.setPackage(ONETRACK_PACKAGE_NAME);
            intent.putExtra("APP_ID", APP_ID);
            intent.putExtra("EVENT_NAME", "TouchUseData");
            intent.putExtra("PACKAGE", this.mContext.getPackageName());
            int i = 0;
            while (true) {
                String[] strArr = this.TouchUseDataParams;
                if (i < strArr.length) {
                    if (jSONObject.has(strArr[i])) {
                        String str = this.TouchUseDataParams[i];
                        if (str == "no_response") {
                            intent.putExtra(str, jSONObject.getString(str));
                        } else {
                            intent.putExtra(str, jSONObject.getInt(str));
                        }
                    }
                    i++;
                } else {
                    this.mContext.startService(intent);
                    return;
                }
            }
        } catch (Exception e) {
            Utils.logE("TouchService-TouchTrack", "reportTouchUseData failed: " + e.toString());
        }
    }

    private void reportTouchException(JSONObject jSONObject) {
        try {
            Intent intent = new Intent(ONETRACK_ACTION);
            intent.setPackage(ONETRACK_PACKAGE_NAME);
            intent.putExtra("APP_ID", APP_ID);
            intent.putExtra("EVENT_NAME", "TouchException");
            intent.putExtra("PACKAGE", this.mContext.getPackageName());
            int i = 0;
            while (true) {
                String[] strArr = this.TouchExceptionParams;
                if (i < strArr.length) {
                    if (jSONObject.has(strArr[i])) {
                        String str = this.TouchExceptionParams[i];
                        intent.putExtra(str, jSONObject.getInt(str));
                    }
                    i++;
                } else {
                    this.mContext.startService(intent);
                    return;
                }
            }
        } catch (Exception e) {
            Utils.logE("TouchService-TouchTrack", "reportTouchException failed: " + e.toString());
        }
    }

    private void reportGameMode(JSONObject jSONObject) {
        try {
            Intent intent = new Intent(ONETRACK_ACTION);
            intent.setPackage(ONETRACK_PACKAGE_NAME);
            intent.putExtra("APP_ID", APP_ID);
            intent.putExtra("EVENT_NAME", "GameMode");
            intent.putExtra("PACKAGE", this.mContext.getPackageName());
            int i = 0;
            while (true) {
                String[] strArr = this.GameModeParams;
                if (i < strArr.length) {
                    if (jSONObject.has(strArr[i])) {
                        String str = this.GameModeParams[i];
                        intent.putExtra(str, jSONObject.getInt(str));
                    }
                    i++;
                } else {
                    this.mContext.startService(intent);
                    return;
                }
            }
        } catch (Exception e) {
            Utils.logE("TouchService-TouchTrack", "reportGameMode failed: " + e.toString());
        }
    }

    private void reportPen(JSONObject jSONObject) {
        try {
            Intent intent = new Intent(ONETRACK_ACTION);
            intent.setPackage(ONETRACK_PACKAGE_NAME);
            intent.putExtra("APP_ID", APP_ID);
            intent.putExtra("EVENT_NAME", "Pen");
            intent.putExtra("PACKAGE", this.mContext.getPackageName());
            int i = 0;
            while (true) {
                String[] strArr = this.PenParams;
                if (i < strArr.length) {
                    if (jSONObject.has(strArr[i])) {
                        String str = this.PenParams[i];
                        intent.putExtra(str, jSONObject.getInt(str));
                    }
                    i++;
                } else {
                    this.mContext.startService(intent);
                    return;
                }
            }
        } catch (Exception e) {
            Utils.logE("TouchService-TouchTrack", "reportPen failed: " + e.toString());
        }
    }

    private void reportKeyboard(JSONObject jSONObject) {
        try {
            Intent intent = new Intent(ONETRACK_ACTION);
            intent.setPackage(ONETRACK_PACKAGE_NAME);
            intent.putExtra("APP_ID", APP_ID);
            intent.putExtra("EVENT_NAME", "KeyBoard");
            intent.putExtra("PACKAGE", this.mContext.getPackageName());
            int i = 0;
            while (true) {
                String[] strArr = this.KeyBoardParams;
                if (i < strArr.length) {
                    if (jSONObject.has(strArr[i])) {
                        String str = this.KeyBoardParams[i];
                        intent.putExtra(str, jSONObject.getInt(str));
                    }
                    i++;
                } else {
                    this.mContext.startService(intent);
                    return;
                }
            }
        } catch (Exception e) {
            Utils.logE("TouchService-TouchTrack", "reportKeyBoard failed: " + e.toString());
        }
    }
}
