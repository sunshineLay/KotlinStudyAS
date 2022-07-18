package com.lay.kotlinstudy.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun main() {

}
//MainScope 通常函数首字母大写，工厂模式
//Retrofit 针对协程进行了大量优化，只要发现挂起函数（请求接口中的函数被suspend修饰），就会自动在IO线程中执行。

//launch 发动，运行

//MainScope，在A中使用，可以在onDestroy()中取消协程
class MainActivity:AppCompatActivity(){
    private val mainScope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScope.launch {
            //IO异步：Retrofit网 络请求
            //UI操作
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
//第二种写法
class Main2Activity:AppCompatActivity(),CoroutineScope by MainScope(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            //IO异步：Retrofit网 络请求
            //UI操作
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
