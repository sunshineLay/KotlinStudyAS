package com.lay.kotlinstudy.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lay.kotlinstudy.R
import com.lay.kotlinstudy.base.BaseActivity
import com.lay.kotlinstudy.databinding.ListChildActivityBinding

class ListChildFragActivity:BaseActivity<ListChildActivityBinding>(ListChildActivityBinding::inflate) {
    private val exampleList = mutableListOf<Fragment>()
    private val colorArray = arrayOf(
        R.color.md_red_500, R.color.md_pink_500, R.color.md_amber_500, R.color.md_blue_500,
        R.color.md_blue_grey_500, R.color.md_brown_500, R.color.md_cyan_500, R.color.md_deep_orange_500,
        R.color.md_deeppurple_500, R.color.md_green_500)
    private val dataList = mutableListOf<String>()
    private var currentPosition = 0;
    override fun initUI() {
        initListData()
        setFragment()
        binding.rvList?.apply {
             layoutManager = LinearLayoutManager(this@ListChildFragActivity)
             val listAdapter = ListAdapter(dataList,this@ListChildFragActivity)
             listAdapter.setOnItemClickListener(object :ListAdapter.OnItemClickListener{
                 override fun onItemClick(position: Int) {
                    supportFragmentManager.beginTransaction()?.apply {
                        //隐藏现在展示的Fragment - 没有这个API，自己记录一下当前Fragment的position
                        //下面这个逻辑就能实现hide()和show()搭配的replace效果，比add()和remove()搭配的原版效果内存消耗要小。
                        hide(exampleList[currentPosition])
                        if(exampleList[position].isAdded){
                            show(exampleList[position])
                            commit()
                        }else{
                            add(R.id.frag_child,exampleList[position])
                            commit()
                        }
                        currentPosition = position
                    }
                 }
             })
             adapter = listAdapter
             addItemDecoration(DividerItemDecoration(this@ListChildFragActivity,DividerItemDecoration.VERTICAL))
        }
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()?.apply {
            add(R.id.frag_child,exampleList[0])
            commit()
        }
    }

    private fun initListData() {
        for (i in 0..9){
            dataList.add("fragment-${i}")
            //Fragment
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