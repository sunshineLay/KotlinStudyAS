package com.lay.kotlinstudy.coroutine

import android.os.AsyncTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//1、协程基础

//①定义
//定义：协程基于线程，是轻量级线程。
//定义扩展：协程是语言层面的，基于Kotlin编译器做了很多工作的轻量级线程。而不是框架层面仅仅针对异步需求进行的封装的异步框架。
// RxJava是这样的框架。Kotlin协程不是。

//②作用
//Kotlin 协程在Android 中解决的任务：一切异步任务需求（IO密集型耗时操作 或者 CPU密集型计算操作）。
//由于Android 主线程（UI线程）在Activity不能进行超过5秒钟的耗时操作。所以，通过子线程执行异步任务，就是在保证主线程安全。

//③取代：Android11 官方已经宣布异步任务过时，推荐Kotlin协程取代AsyncTake
//例子：获取网络数据，对比两者。
//异步任务的问题：
// 第一，具体执行的方法是接口回调的函数。可能会出现【回调地狱问题】
// 第二，异步任务的执行是异步化的，没有同步化的写法那么一目了然。【方便阅读理解】

//Kotlin 协程的优点：
// 第一，异步任务同步化。【方便人的思维惯性，方便阅读理解】
// 第二，方便的指定当前协程所在的线程是IO线程还是UI线程。
// 第三，协程写法不存在接口回调问题。

// 存在协程的作用域和父子协程关系。

//补充：什么是回调地狱？
//简单来说就是回调函数或者回调接口套娃。参考：https://juejin.cn/post/6844903949258981390 - Java如何实现一个回调地狱（Callback Hell）？
//Kotlin 的coroutine 和RxJava 可以解决这个问题

//协程的优点：
// 1、协程让异步逻辑同步化，杜绝回调地狱。
// 2、最核心的点就是，函数或者一段程序能够被挂起suspend，然后再在挂起的位置恢复。

//④协程的常规重要操作：挂起和恢复
//常规的函数基础操作包括：invoke(call)调用，和return 返回
//协程新增suspend挂起和resume恢复
//挂起（suspend）：暂停当前协程，保存当前所有局部变量。
//恢复（resume）：用于让已经暂停的协程从暂停的地方恢复。
fun main() {
    //例子：相同的其他技术点代码不写，只写异步任务和协程的对比部分
    //异步任务写法
//    object : AsyncTask<Void, Void, User>(){
//        override fun doInBackground(vararg p0: Void?): User {
//            //return 执行网络请求，返回User对象
//        }
//        override fun onPostExecute(result: User?) {
//            super.onPostExecute(result)
//            //设置网络请求数据
//        }
//    }.execute()

    //协程写法
    //1、同步化写法 - 解决了回调地狱的可读性问题
    //2、任务调度器 - Dispatchers.Main
    //父协程、指定主线程
    GlobalScope.launch(Dispatchers.Main) {
        //子协程
        withContext(Dispatchers.IO){
            //进行网络请求得到结果
            //Retrofit 的请求接口用suspend来修饰
        }
        //ui 显示
    }

}


data class User(val name:String)
//data class 会自动重写equals()/hashCode()