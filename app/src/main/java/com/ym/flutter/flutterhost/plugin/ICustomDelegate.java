package com.ym.flutter.flutterhost.plugin;

import android.content.Context;

import org.json.JSONObject;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public interface ICustomDelegate  {
    void call(Context context, MethodCall methodCall, JSONObject json, MethodChannel.Result result);
}
