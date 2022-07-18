package com.lay.kotlinstudy.coroutine

//Kotlin - 协程课程大纲
//1、协程基础
//2、协程Android
//3、协程取消相关
//3、协程异常处理
//4、协程热数据通道
//5、协程冷数据流Flow
//6、协程项目实战
fun main() {

}
//一、
//基础普及：
//设计模式 - 针对代码
//框架 - 针对功能 - 框架通常不涉及编译器层面的操作。
//架构 - 针对项目工程

//二、
//补充：Android 的ANR
//默认情况下，Android主线程(UI线程)不让进行网络请求，否则会抛出NetworkOnMainThreadException。
//但是主线程还可以让程序员进行其它类型的耗时操作，比如读写磁盘数据、遍历操作一个大数组，但是如果超过5秒就会提示ANR错误。

//默认情况下，在android中Activity的最长执行时间是5秒，BroadcastReceiver的最长执行时间则是10秒。

//(不常用)
//那么如何让主线程可以访问网络，而不抛出异常呢？在我们的Activity类的onCreate方法中，设置如下规则：
//StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//StrictMode.setThreadPolicy(policy);

//二 - 1、如何避免ANR？
//1、运行在Activity主线程里的任何方法都尽可能少做事情。（计算 or IO）
//通过Handler、异步任务、Kotlin 协程、RxJava来处理

//2、应用程序应该避免在BroadcastReceiver里做耗时的操作或计算。
// 但不再是在子线程里做这些任务（因为 BroadcastReceiver的生命周期短），替代的是，
// 如果响应Intent广播需要执行一个耗时的动作的话，应用程序应该启动一个 Service。
// （此处需要注意的是可以在广播接受者中启动Service，但是却不可以在Service中启动broadcasereciver,关于原因后续会有介绍，此处不是本文重点）

//补充：BroadcastReceiver的生命周期
//BroadcastReceiver的生命周期很简单，发送事件后，收广播方调用onReceive方法，执行完毕后，对象被销毁，这就是一整个流程。
// 要注意的是 onReceive() 方法在10 秒内没有执行完毕， Android 会认为该程序无响应，所以 onReceive() 方法不能执行耗时操作。

//3、避免在Intent Receiver里启动一个Activity，因为它会创建一个新的画面，并从当前用户正在运行的程序上抢夺焦点。
//如果你的应用程序在响应Intent广 播时需要向用户展示什么，你应该使用Notification Manager来实现。
//由此引出我们为什么需要【通知功能 - Notification】

//三、Android 中子线程真的不能更新 UI 吗？【经典面试题】
//回答：Android 中子线程在满足一定的条件下可以更新 UI。

// 条件是：在ViewRootImpl 被创建完成，View Tree 初始化完成之前，子线程可以更新UI。
// ViewRootImpl 作为 DecorView 与 WindowManager 之间的「桥梁」

//第一种子线程更新UI的方法。
// 在onCreate、onResume 这两个方法中创建子线程修改UI。大多数时候可以修改成功。
//ViewRootImpl 是在 WindowManagerGlobal#addView() 方法中被创建出来的。并且是在 Activity#onResume 方法调用之后才被创建。

//第二种子线程更新UI的方法。
// 【基本逻辑就是WMG的checkThread()方法是判断，当前线程是不是创建UI的线程。所以，只要在子线程创建的View，都能在这个子线程更新UI、】
//例子：SubThread.txt

//三 - 1、使用子线程更新 UI 有实际应用场景吗？
//回答：Android 中的 SurfaceView 通常会通过一个子线程来进行页面的刷新。
// 如果我们的自定义 View 需要频繁刷新，或者刷新时数据处理量比较大，那么可以考虑使用 SurfaceView 来取代 View。

// 参考：https://juejin.cn/post/6844904131136618510 - 答案博客

//补充：Android 权限体系，运行时权限适配

