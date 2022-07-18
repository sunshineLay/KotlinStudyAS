package com.lay.kotlinstudy.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseVBActivity<VB:ViewBinding> : AppCompatActivity() {
    protected val TAG = this::class.simpleName
    //反射可以做到在父类中拿到运行时子类实例，进行使用
    protected val binding by lazy {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<*>
        val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null,layoutInflater) as VB
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }
    protected abstract fun initUI()
}
//问题一：①by lazy{} 和 ②lateinit的对比
// 相同点：延迟初始化
// 不同点：
//① 只能是val,②只能是var
//① 默认是线程安全的。
//① 被首次调用时，才会进行赋值操作。
//① 本身是一种属性委托
//②不能用于基本数据类型，如Int，Long，需要用包装类包装
//②只能用来修饰类属性，不能用来修饰局部变量 ①两者都能使用
//②作用也比较简单，就是让编译期在检查时不要因为属性变量未被初始化而报错。

//①lazy是初始化线程安全的
//LazyThreadSafetyMode.SYNCHRONIZED: 添加同步锁，使lazy延迟初始化线程安全
//LazyThreadSafetyMode. PUBLICATION： 初始化的lambda表达式可以在同一时间被多次调用，但是只有第一个返回的值作为初始化的值。
//LazyThreadSafetyMode. NONE：没有同步锁，多线程访问时候，初始化的值是未知的，非线程安全，一般情况下，不推荐使用这种方式，除非你能保证初始化和属性始终在同一个线程

//本质上讲，lateinit 就是告诉编译器不要管变量的初始化问题，我自己会处理。by lazy{} 就是在首次调用的时候进行，并且已经明确了具体的方法。

//问题二：lateinit和 ?= null的使用时机
//lateinit：非常确定自己会实例化对象，用于自己使用的变量
//?=null：依赖于他人提供的对象，不确定他人是否会进行实例化返回真实对象

//问题三：什么是Kotlin中的委托？
//关键字是by

//一、类委托。
// 例子：// 委托对象
//class DelegateGamePlayer(private val player: IGamePlayer): IGamePlayer by player
//(val player: IGamePlayer)
//: IGamePlayer
// by player

//二、属性委托
//by lazy (延迟属性)
//by Delegates.observable (可观察属性)
//var observableProp: String by Delegates.observable("默认值：xxx"){
//    property, oldValue, newValue ->
//    println("property: $property: $oldValue -> $newValue ")
//}

//by Delegates.vetoable(0)//类似observable，但是观察逻辑是lambda定义的
//var vetoableProp: Int by Delegates.vetoable(0){
//    _, oldValue, newValue ->
//    // 如果新的值大于旧值，则生效
//    newValue > oldValue
//}

//by map 属性存在Map里面
//class User(val map: Map<String, Any?>) {
//    val name: String by map
//    val age: Int     by map
//}

