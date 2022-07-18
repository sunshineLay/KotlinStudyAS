package com.lay.kotlinstudy.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseVB2JActivity<VB extends ViewBinding> extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();
    protected VB binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());
        initUI();
    }

    protected abstract void setBinding();
    protected abstract void initUI();

}
