package com.lay.kotlinstudy.activity.newResults

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.lay.kotlinstudy.activity.result.RECEIVE_CODE
import com.lay.kotlinstudy.activity.result.RECEIVE_KEY
import com.lay.kotlinstudy.activity.result.RESULT_KEY
import com.lay.kotlinstudy.activity.result.ReceiveBActivity

//自定义ActivityResultContract，用来处理两个activity互传数据的处理，替代onActivityResult方法
//其中ActivityResultContract<String, String>第一个泛型参数表示输入的参数类型，即要带到下一个Activity的数据类型，
//第二个泛型参数表示输出参数类型，即第二个Activity回传第一个Activity的数据类型。

class ResultContract:ActivityResultContract<String,String?>() {
    override fun createIntent(context: Context, input: String?): Intent {
        //要传到下一个activity中的数据，从A activity中带到B activity中的数据。
        return Intent(context, ReceiveBActivity::class.java).putExtra(RESULT_KEY,input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        //从ReceiveActivity回传的数据
        val receive = intent?.getStringExtra(RECEIVE_KEY)
        if(resultCode == RECEIVE_CODE && receive!= null){
            return receive
        }
        return ""
    }
}