package com.lay.kotlinstudy.activity.result

import android.content.Intent
import com.lay.kotlinstudy.base.BaseActivity
import com.lay.kotlinstudy.databinding.ResultAActivityBinding

//启动带返回值【第一种方法】 - onActivityResult & startActivityForResult
const val RESULT_KEY = "RESULT_KEY"
const val RESULT_CODE = 101
class ResultActivity:BaseActivity<ResultAActivityBinding>(ResultAActivityBinding::inflate) {
    override fun initUI() {
        binding.btA?.setOnClickListener {
            val intent = Intent(this,ReceiveBActivity::class.java)
            intent.putExtra(RESULT_KEY,"A给B的信息")
            startActivityForResult(intent, RESULT_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode.equals(RESULT_CODE)&&resultCode.equals(RECEIVE_CODE)){
            binding.tvA?.apply {
                text = "B返回结果值：${data?.getStringExtra(RECEIVE_KEY)}"
            }
        }

    }
}