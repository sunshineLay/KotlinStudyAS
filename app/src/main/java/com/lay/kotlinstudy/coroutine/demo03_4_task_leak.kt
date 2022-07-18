package com.lay.kotlinstudy.coroutine

fun main() {

}
//任务泄漏
//定义：进行了一次无效的任务执行。例如：进行网络请求，Activity被用户点击了返回按钮。
// 此时必须要进行处理，否则就会出现内存泄漏，CPU资源浪费的问题。

//Scope 作用域
//范围/规模

//结构化并发
//使用结构化并发的作用：
//1、取消任务
//2、追踪任务
//3、发出错误信号

//CoroutineScope
//定义：协程必须指定其CoroutineScope,它会跟踪所有协程，同样还能取消由它启动的所有协程。

//常用相关API：
//GlobalScope，生命周期是process级别，即使A和F销毁，仍然在执行
//MainScope，在A中使用，可以在onDestroy()中取消协程
//ViewModelScope，只能在ViewModel中使用，绑定ViewModel的生命周期
//LifecycleScope，只能在A、F中使用，会绑定A和F的生命周期