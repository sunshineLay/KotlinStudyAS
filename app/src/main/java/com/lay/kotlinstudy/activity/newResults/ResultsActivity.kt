package com.lay.kotlinstudy.activity.newResults

import com.lay.kotlinstudy.base.BaseActivity
import com.lay.kotlinstudy.databinding.ResultAActivityBinding

//通过新的 Activity Result API，我们可以单独的类中处理结果回调，真正做到单一职责；

//随着应用的扩展，onActivityResult回调方法各种嵌套、耦合严重、难以维护；
//在google最新的activity-ktx beta版本中已经废弃了原onActivityResult方法，推荐使用Activity Results API来处理页面数据的处理；

//Activity Results API 是 Google官方推荐的Activity、Fragment获取返回结果的方式

//相当于原startActivityForResult 和onActivityResult方法；

//参考：https://juejin.cn/post/6888960189911793678 - 使用Activity Results API来替代onActivityResult
// 这个API还有很多其他的功能，都是和Intent有关的功能，跳槽之后可以做成一篇博客。

class ResultsActivity: BaseActivity<ResultAActivityBinding>(ResultAActivityBinding::inflate){
    private val activityLauncher = registerForActivityResult(ResultContract()){
        //第二个页面关闭后回到第一个页面的回调方法,即parseResult的结果
        binding.tvA?.apply {
            text = it
        }
    }
    override fun initUI() {
        binding.btA?.setOnClickListener {
            //点击跳转到ReceiveActivity
            activityLauncher.launch("A给B的信息-API")
        }
    }

}