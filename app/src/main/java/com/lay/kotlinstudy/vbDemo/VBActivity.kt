package com.lay.kotlinstudy.vbDemo

import android.util.Log
import com.lay.kotlinstudy.base.BaseVBActivity
import com.lay.kotlinstudy.databinding.Vb2ActivityBinding

class VBActivity : BaseVBActivity<Vb2ActivityBinding>() {
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