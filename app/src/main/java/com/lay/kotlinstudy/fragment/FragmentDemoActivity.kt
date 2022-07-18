package com.lay.kotlinstudy.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hjq.toast.ToastUtils
import com.lay.kotlinstudy.R
import com.lay.kotlinstudy.base.BaseActivity
import com.lay.kotlinstudy.databinding.FragActivityBinding
import com.lay.kotlinstudy.tools.IntentUtil

class FragmentDemoActivity:BaseActivity<FragActivityBinding>(FragActivityBinding::inflate) {
    val exampleList = mutableListOf<Fragment>()
    val colorArray = arrayOf(R.color.md_red_500,R.color.md_pink_500,R.color.md_amber_500,R.color.md_blue_500,
    R.color.md_blue_grey_500,R.color.md_brown_500,R.color.md_cyan_500,R.color.md_deep_orange_500,
        R.color.md_deeppurple_500,R.color.md_green_500)
    var currrentFragNum = 0//当前添加到Activity的Fragment数量

    override fun initUI() {
        initFragment()
        binding.btAdd?.setOnClickListener {
            //动态添加Fragment
            if(currrentFragNum < 10){
                //此处注意：supportFragmentManager.beginTransaction()一个事务只能提交一次，重复提交会报错。
                    // 不能使用一个事务变量执行一个事务对象反复使用。会报错：安卓fragment 的commit already called错误
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fl_con,exampleList[currrentFragNum])
                    commit()
                }
                currrentFragNum++
            }else{
                //ToastUtils 加入
                ToastUtils.show("当前已将10个Fragment全部添加完毕")
            }
        }
        binding.btRemove?.setOnClickListener {
            //动态移除
            if(currrentFragNum > 0){
                supportFragmentManager.beginTransaction().apply {
                    remove(exampleList[--currrentFragNum])
                    commit()
                }
            }else{
                //ToastUtils 加入
                ToastUtils.show("当前已将10个Fragment全部删除")
            }
        }
        //替换方法有三对
        //第一对：add()和remove(),replace()等于remove() + add()
        //往Activity中添加和移除一个Fragment,如果移除的时候没有添加到回退栈，这个实例会被销毁。

        //第二对：attach()和detach()
        //前者重建View视图，附加到UI并显示。后者会将View从UI中移除。

        //第三对：hide()和show()
        //前者隐藏当前的Fragment，仅仅是不可见不可交互，但是不会销毁。
        //后者显示了之前隐藏的Fragment。

        //总结：
        //1-按照这个定义，第一次添加的时候肯定要通过add()来向A添加F实例；
        //2-之后每一次都应该通过第三对进行显示隐藏。（通常情况下）
        //3-如果我们不希望销毁Fragment实例，我们又希望不要保留Fragment的状态，那么我们可以通过第二对来实现。
        binding.btReplace?.setOnClickListener {
            //动态替换Fragment - hide()和show() - 结合Fragment嵌套写一个Demo - 学习通过AS自带的内存分析工具分析两种情况下的内存消耗区别
            // 通过 Android Studio 的 Memory Profiler
            //结合RecyclerView来实现一个简单的左边列表切换
            IntentUtil.doAction(this,ListChildFragActivity::class.java)

        }

        binding.btNews?.setOnClickListener {
            IntentUtil.doAction(this,NewsActivity::class.java)
        }


    }

    private fun initFragment() {
        //添加Fragment到List里面去
        var n = 9
        for (i in 0..n){
            val exampleFragment = if(i%2==0){
                ExampleFragment()
            }else{
                Example2Fragment()
            }
            val bundle = Bundle()
            bundle.putString(DATA,"动态添加的第${i}个Fragment")
            bundle.putInt(COLOR,colorArray[i])
            exampleFragment.arguments = bundle
            exampleList.add(exampleFragment)
        }
    }

}