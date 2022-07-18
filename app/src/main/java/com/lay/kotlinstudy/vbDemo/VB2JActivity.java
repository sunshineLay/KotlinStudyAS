package com.lay.kotlinstudy.vbDemo;

import android.util.Log;
import android.view.View;

import com.lay.kotlinstudy.base.BaseVB2JActivity;
import com.lay.kotlinstudy.databinding.Vb2ActivityBinding;

public class VB2JActivity extends BaseVB2JActivity<Vb2ActivityBinding> {
    int count = 0;//对于运行时数据区的常见问题理解
    @Override
    protected void setBinding() {
        binding = Vb2ActivityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initUI() {
        //在这个方法里面就可以进行
//        int count = 0;//这个地方体现了闭包的问题，Java不是真的闭包，无法使用外部环境的局部变量
        binding.btnVb.setOnClickListener(view -> {
            ++count;
            Log.e(TAG, "点击次数："+count);
        });
    }
}
