import 'package:flutter/services.dart';

class bridge {
  static const _httpPlatform =
      const MethodChannel("com.ym.framework.plugins/http");
  static const _toastPlatform =
      const MethodChannel("com.mrper.framework.plugins/toast");
  static const platform = const MethodChannel('samples.flutter.io/battery');
  static const _navigatPlatform =
      const MethodChannel("com.ym.framework.plugins/navigate");

  static void gotoVideoPage() {
    _navigatPlatform.invokeMethod("video");
  }

  static void showShortToast(String message) {
    message = message.replaceAll(" ", "");
    _toastPlatform.invokeMethod("showShortToast", {"message": message});
  }

  static void showLongToast(String message) =>
      _toastPlatform.invokeMethod("showLongToast", {"message": message});

  static void showToast(String message, int duration) => _toastPlatform
      .invokeMethod("showToast", {"message": message, "duration": duration});

  static Future<String> httpRequest(var requestType, String message) async {
    String result;
    try {
      if (requestType == 0) {
        result = await _httpPlatform.invokeMethod("get", {"json": message});
      } else if (requestType == 1) {
        result = await _httpPlatform.invokeMethod("post", {"json": message});
      }
    } on PlatformException catch (e) {
      result = "Failed to get result: '${e.message}'.";
    }
    return result;
  }

  static Future<String> getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await platform.invokeMethod('getBatteryLevel');
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }
    return batteryLevel;
  }
}
