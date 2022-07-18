package com.lay.kotlinstudy.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lay.kotlinstudy.R
import com.lay.kotlinstudy.databinding.DemoActivityBinding

//协程 + Retrofit + ViewModel + LiveData + DataBinding
//1572e8b33ff76dccff6799c8d901d02e
class DemoActivity : AppCompatActivity(){
    private lateinit var binding: DemoActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }
    private fun initUI() {
        binding = DataBindingUtil.setContentView<DemoActivityBinding>(this, R.layout.demo_activity)
    }
}
//Retrofit 部分 - Json的data class - 使用JsonToKotlinClass 插件
data class News(
    val charge: Boolean,
    val code: String,
    val msg: String,
    val requestId: String,
    val result: Result<Any?>
)
data class Result<T>(
    val msg: String,
    val result: ResultX,
    val status: Int
)
data class ResultX(
    val channel: String,
    val list: List<Content>,
    val num: Int
)
data class Content(
    val category: String,
    val content: String,
    val pic: String,
    val src: String,
    val time: String,
    val title: String,
    val url: String,
    val weburl: String
)