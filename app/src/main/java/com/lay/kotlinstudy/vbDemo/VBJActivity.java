package com.lay.kotlinstudy.vbDemo;

import android.util.Log;

import com.lay.kotlinstudy.base.BaseVBJActivity;
import com.lay.kotlinstudy.databinding.Vb2ActivityBinding;

public class VBJActivity extends BaseVBJActivity<Vb2ActivityBinding> {

    @Override
    protected void initUI() {
        bind.btnVb.setOnClickListener(view -> {
            Log.e(TAG, "initUI: VBJActivity" );
        });
    }
}
