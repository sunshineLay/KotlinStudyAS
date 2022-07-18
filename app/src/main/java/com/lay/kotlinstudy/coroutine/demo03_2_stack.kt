package com.lay.kotlinstudy.coroutine

import kotlin.coroutines.*

fun main() {
    //基础设施层
    val continuation = suspend {
        //异步执行部分
        5
    }.createCoroutine(object :Continuation<Int>{
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: kotlin.Result<Int>) {
            //得到执行结果部分
            println("result:${result.getOrNull()}")
        }
    })
    continuation.resume(Unit)
}
//堆栈帧中的函数调用流程：P9
//Main-Thread Stack：getUser()
//挂起：getUser()
//Main-Thread Stack：get()
//挂起：get()
//恢复：get()
//Main-Thread Stack：get()
//Main-Thread Stack：get()移除Stack，执行完了
//恢复：getUser()
//Main-Thread Stack：show()
//Main-Thread Stack：show()移除Stack，执行完了
//Main-Thread Stack：getUser()移除Stack，执行完了

//挂起和阻塞的区别
//回答：阻塞就是干等。挂起就是单线程异步编程。

//Kotlin 的协程的基础部分和业务框架部分


//补充
//1、suspend函数，只能在协程体内或其他挂起函数内调用