package com.ym.flutter.flutterhost.plugin;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

public class PluginProvider {

    private static final String HttpChannelName = "com.ym.framework.plugins/http";
    private static final String ToastChannelName = "com.mrper.framework.plugins/toast";
    private static final String CHANNEL = "samples.flutter.io/battery";
    private static final String NavigateCHannel = "com.ym.framework.plugins/navigate";

    public static void registerPlugin(final Context context, BinaryMessenger messenger, final PluginDelegate iDelegate) {
        new MethodChannel(messenger, ToastChannelName).setMethodCallHandler((methodCall, result) -> {
            try {
                JSONObject jsonObject = new JSONObject(methodCall.arguments.toString());
                jsonObject.put("action", "toast");
                iDelegate.call(context, methodCall, jsonObject, result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        new MethodChannel(messenger, HttpChannelName).setMethodCallHandler((methodCall, result) -> {
            try {
                JSONObject jsonObject = new JSONObject(methodCall.arguments.toString());
                jsonObject.put("action", "http");
                iDelegate.call(context, methodCall, jsonObject, result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        new MethodChannel(messenger, CHANNEL).setMethodCallHandler((methodCall, result) -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action", "battery");
                iDelegate.call(context, methodCall, jsonObject, result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        new MethodChannel(messenger, CHANNEL).setMethodCallHandler((methodCall, result) -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action", "navigate");
                iDelegate.call(context, methodCall, jsonObject, result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        new MethodChannel(messenger, NavigateCHannel).setMethodCallHandler((methodCall, result) -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action", "navigate");
                iDelegate.call(context, methodCall, jsonObject, result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


    }
}
