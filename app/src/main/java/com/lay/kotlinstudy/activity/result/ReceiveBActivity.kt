package com.lay.kotlinstudy.activity.result

import android.content.Intent
import com.lay.kotlinstudy.base.BaseActivity
import com.lay.kotlinstudy.databinding.ReceiveBActivityBinding

const val RECEIVE_KEY = "RESULT_KEY"
const val RECEIVE_CODE = 201
class ReceiveBActivity:BaseActivity<ReceiveBActivityBinding>(ReceiveBActivityBinding::inflate) {
    override fun initUI() {
        val receiveCon = intent.getStringExtra(RESULT_KEY)
        binding.tvB?.apply {
            text = "A传递过来的数据：${receiveCon}"
        }
        binding.btB?.setOnClickListener {
            val intent = Intent()
            intent.putExtra(RECEIVE_KEY,"B返回的信息^_^")
            setResult(RECEIVE_CODE,intent)
            finish()
        }
    }

}