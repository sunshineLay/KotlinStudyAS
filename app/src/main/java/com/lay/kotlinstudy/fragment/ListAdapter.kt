package com.lay.kotlinstudy.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lay.kotlinstudy.R
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val dataList:MutableList<String>,private val context: Context):
    RecyclerView.Adapter<ListAdapter.RecyclerViewHolder>() {

    lateinit var listenser:OnItemClickListener
    //Kt里面的静态内部类不需要任何修饰符
   class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView
        init {
            textView = itemView.findViewById(R.id.tv_tab)
        }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.textView.text = dataList[position]
        holder.itemView.setOnClickListener {
            listenser.onItemClick(position)
        }
    }

    override fun getItemCount()= dataList.size

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listenser = listener
    }

}