import 'package:flutter/services.dart';

class bridge {
  static const _bridgePlatform =
      const MethodChannel("com.ym.framework.plugins/bridge");

  static Future<String> dispenser(var dispenser) async {
    try {
      String method = dispenser['method'];
      var params = dispenser['params'];
      return await _bridgePlatform.invokeMethod(method, params);
    } on PlatformException catch (e) {
      return e.message;
    }
  }

  static void gotoVideoPage() {
    dispenser({
      "method": "video",
      "params": {"action": "navigate"}
    });
  }

  static void showShortToast(String message) {
    message = message.replaceAll(" ", "");
    dispenser({
      "method": "showShortToast",
      "params": {"action": "toast", "message": message}
    });
  }

  static void showLongToast(String message) {
    message = message.replaceAll(" ", "");
    dispenser({
      "method": "showLongToast",
      "params": {"action": "toast", "message": message}
    });
  }

  static void showToast(String message, int duration) {
    message = message.replaceAll(" ", "");
    dispenser({
      "method": "showToast",
      "params": {"action": "toast", "message": message, "duration": duration}
    });
  }

  static Future<String> httpRequest(var requestType, String url) async {
    return dispenser({
      "method": "get",
      "params": {"action": "http", "message": url}
    });
  }

  static Future<String> getBatteryLevel() async {
    return dispenser({
      "method": "getBatteryLevel",
      "params": {"action": "battery", "message": "msg"}
    });
  }
}
