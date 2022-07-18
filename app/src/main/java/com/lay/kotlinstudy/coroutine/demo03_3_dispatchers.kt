package com.lay.kotlinstudy.coroutine

fun main() {

}
//协程调度器
//Dispatchers.Main
    //Android 主线程。UI交互、调用suspend函数、更新LiveData
//Dispatchers.IO(非主线程)
    //IO 密集型任务：网络、数据库、文件
//Dispatchers.Default（默认情况）(非主线程)
    //CPU密集型任务：JSON数据解析，数组排序，处理判断，计算类相关任务
    //取消的方式和前两者不同