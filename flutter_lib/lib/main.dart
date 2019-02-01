import 'package:flutter/material.dart';
import 'dart:ui';
import './bridge.dart';

void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
  switch (route) {
    case 'route1':
      return MyApp();
    case 'route2':
      return MyApp();
    default:
      return Center(
        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
      );
  }
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  String _batteryLevel = 'Unknown battery level.';

//
//  Future<Null> _getBatteryLevel() async {
//    String batteryLevel;
//    batteryLevel = await bridge.getBatteryLevel();
//    setState(() {
//      _batteryLevel = batteryLevel;
//    });
//  }
  @override
  void initState() {
    super.initState();
    Future<String> future = bridge.getBatteryLevel();
    future.then((value) {
      setState(() {
        _batteryLevel = value;
      });
    });
  }

  void _incrementCounter() {
    setState(() {
      _counter++;
      post();
      if (_counter > 5) {
        print("TTT: +$_counter");
        bridge.gotoVideoPage();
      }
    });
  }

  Future post() async {
    String result = await bridge.httpRequest(1, "发送一个post请求");
    setState(() {
      print("TTT " + result);
      bridge.showShortToast(result);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "剩余电量" + _batteryLevel + "%",
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.display1,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
