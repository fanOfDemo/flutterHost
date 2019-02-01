package com.ym.flutter.flutterhost.plugin;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.widget.Toast;

import org.json.JSONObject;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class CustomDelegate implements ICustomDelegate {

    @Override
    public void call(Context context, MethodCall methodCall, JSONObject jsonObject, MethodChannel.Result result) {
        String action = jsonObject.optString("action");
        switch (action) {
            case "toast":
                switch (methodCall.method) {
                    case "showShortToast":
                        Toast.makeText(context, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                        result.success(null);
                        break;
                    case "showLongToast":
                        Toast.makeText(context, jsonObject.optString("message"), Toast.LENGTH_LONG).show();
                        result.success(null);
                        break;

                    case "showToast":
                        Toast.makeText(context, jsonObject.optString("message"), jsonObject.optInt("duration")).show();
                        result.success(null);
                        break;
                    default:
                        result.notImplemented();
                        break;
                }
                break;
            case "http":
                switch (methodCall.method) {
                    case "get":
                        String param = jsonObject.optString("json");
                        result.success(param+"get 成功 success");
                        break;
                    case "post":
                        result.success("post成功success");
                        break;
                    default:
                        result.notImplemented();
                        break;
                }

                break;
            case "battery":
                if (methodCall.method.equals("getBatteryLevel")) {
                    int batteryLevel = getBatteryLevel(context);
                    if (batteryLevel != -1) {
                        result.success(batteryLevel);
                    } else {
                        result.error("UNAVAILABLE", "Battery level not available.", null);
                    }
                } else {
                    result.notImplemented();
                }
                break;

        }
    }

    private int getBatteryLevel(Context context) {
        int batteryLevel = -1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
            if (batteryManager != null) {
                batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            }
        } else {
            Intent intent = new ContextWrapper(context.getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            if (intent != null) {
                batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                        intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            }
        }
        return batteryLevel;
    }
}
