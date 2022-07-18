package com.lay.kotlinstudy.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lay.kotlinstudy.R
import kotlinx.android.synthetic.main.example_fragment.*

const val DATA = "DATA"
const val COLOR = "COLOR"
class ExampleFragment: Fragment() {
    //注意：在 BaseFragment 的 onAttach() 方法中获取 Activity 并赋值给成员变量，然后在需要使用 getActivity() 的地方使用该成员变量就可以了。
    //这里是一个写在基类的关键点
    lateinit var nowContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        nowContext = context
    }
    //唯一必须实现的方法
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.example_fragment, container, false)
        val tvExample = view.findViewById<TextView>(R.id.tv_example)
        val background = view.findViewById<ConstraintLayout>(R.id.background)
        arguments?.getString(DATA)?.apply {
            tvExample?.text = this
        }
        arguments?.getInt(COLOR)?.apply {
            background.setBackgroundColor(ContextCompat.getColor(nowContext,this))
        }
        return view
    }
}

