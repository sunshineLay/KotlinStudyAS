package com.lay.kolinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.lay.kotlinstudy.R
import com.lay.kotlinstudy.databinding.ActivityMainBinding

class EasyVBActivity : AppCompatActivity() {
    private val baseContext = this
    private val TAG = EasyVBActivity::class.simpleName
    //1、
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //2、
        binding = ActivityMainBinding.inflate(layoutInflater)
        //3、
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.tvCon.apply {
            setText(resources.getString(R.string.author_name))
            setTextSize(resources.getDimension(R.dimen.tvCon_size))
            //设置颜色1 多种状态的颜色设置 - 过时
//            setTextColor(resources.getColorStateList(R.color.colorstate_list))
            //设置颜色1 多种状态的颜色设置 - 最新方案
            setTextColor(ContextCompat.getColorStateList(baseContext,R.color.colorstate_list))
            //设置颜色2 系统资源颜色设置 - 过时
//            setTextColor(resources.getColor(Color.BLUE))
//            //设置颜色3 自定义颜色设置 - 数值
//            setTextColor(0xF44336)
//            //设置颜色4 自定义颜色设置 - 资源文件 - 过时
//            setTextColor(resources.getColor(R.color.md_red_500))
//            //设置颜色5 自定义颜色设置 - 资源文件 - 最新方案
//            setTextColor(ContextCompat.getColor(context,R.color.md_red_500))

            //getColor方法在Android 6.0即API 23中 已经过时
            //替代方案：ContextCompat.getColor(context, R.color.black)；

            //Color和Drawable这两种过时方案的都由ContextCompat替代
            var onclickCount = 0
            setOnClickListener {
                onclickCount++
                Log.e(TAG, "initUI: "+ onclickCount)
            }
        }
    }

}

//Kotlin在Android中的使用
//1、fby的解决方法：viewBinding的基本使用

//问题一：为什么要替代fby?
//答：
// 1、过于冗余
// findViewById 对应所有的View 都需要书写以下 findViewById(R.id.xxx) 的方法
// 2、不安全
// 2-1、空类型的不安全，findViewById  又可能返回为null,导致程序异常
// 2-2、强转的不安全，findViewById。 将 对应的id 需要强转成对应的View
// TextView tv = findViewById(R.id.textview);
// 一旦我的类型给错了，就会出现异常，比如将textview 错强转成 ImageView

//谷歌已经把kotlin-android-extensions插件废弃，目前推荐使用ViewBinding来进行替代。

//正文：
// ViewBinding的目的：避免编写findViewById
// 使用ViewBinding前的注意事项：
// 1、确保你的Android Studio是3.6或更高的版本
// 2、在build.gradle中加入以下配置：buildFeatures { viewBinding true }

//使用ViewBinding的步骤：
//1、首先要调用activity_main.xml布局文件对应的Binding类，也就是ActivityMainBinding的inflate()函数去加载该布局，inflate()函数接收一个LayoutInflater参数，在Activity中是可以直接获取到的
//2、接下来调用Binding类的getRoot()函数可以得到activity_main.xml中根元素的实例
//3、把根元素的实例传入到setContentView()函数当中，这样Activity就可以成功显示activity_main.xml这个布局的内容了

