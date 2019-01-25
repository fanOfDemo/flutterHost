package com.ym.flutter.flutterhost;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ToastProviderPlugin {
    public static final String ChannelName = "com.mrper.framework.plugins/toast";

    public static void register(final Context context, BinaryMessenger messenger) {
        new MethodChannel(messenger, ChannelName).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                try {
                    JSONObject jsonObject = new JSONObject(methodCall.arguments.toString());

                    switch (methodCall.method) {
                        case "showShortToast":
                            Toast.makeText(context, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                            break;
                        case "showLongToast":
                            Toast.makeText(context, jsonObject.optString("message"), Toast.LENGTH_LONG).show();
                            break;

                        case "showToast":
                            Toast.makeText(context, jsonObject.optString("message"), jsonObject.optInt("duration")).show();
                            break;
                    }
                    result.success(null); //没有返回值，所以直接返回为null
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
