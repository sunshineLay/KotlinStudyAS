package com.lay.kotlinstudy.vbDemo

import android.util.Log
import com.lay.kotlinstudy.base.BaseActivity
import com.lay.kotlinstudy.databinding.Vb2ActivityBinding

class VB2Activity:BaseActivity<Vb2ActivityBinding>(Vb2ActivityBinding::inflate) {

    //执行UI操作
    override fun initUI() {
        var count = 0
        binding.btnVb.apply {
            setOnClickListener {
                ++count
                Log.e(TAG, "点击次数：$count")
            }
        }
    }

}
//在这个Demo中遇到了问题：
// Android - Theme : 主题通常不会深用
// 什么是Style，什么是Theme？
//1.1 联系
//Style 和 theme：是一个包含一种 或者 多种格式化 属性 的集合 ，并且 style和theme都是资源，存放在res/values/themes 文件夹下
//
//1.2 区别：
//style：View级别的，只能在某个Activity的布局文件中使用
//Theme：应用级别的，你必须在AndroidManifest.xml中 的<application>或者<activity>中使用
// [Theme] android:theme="@style/Theme.KotlinStudy"

//Android 具体主题的使用场景：一、夜间模式切换；二、换肤场景的Theme切换；
//通常情况下，使用主题：Theme.Design.Light 或者 Theme.Design.Light.NoActionBar
