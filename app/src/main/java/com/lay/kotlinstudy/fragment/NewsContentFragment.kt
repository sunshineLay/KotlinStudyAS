package com.lay.kotlinstudy.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lay.kotlinstudy.R

class NewsContentFragment: Fragment() {
    private lateinit var tvTitle:TextView
    private lateinit var tvCon:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_content_frag, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById<TextView>(R.id.tv_title)
        tvCon = view.findViewById<TextView>(R.id.tv_con)
    }

    fun refresh(title:String,content:String){
        tvTitle.text = title
        tvCon.text = content
    }
}