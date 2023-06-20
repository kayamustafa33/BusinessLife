package com.mobile.businesslife.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.businesslife.databinding.RecyclerTopheadlinesBinding
import com.mobile.businesslife.model.Article
import com.mobile.businesslife.model.NewsTopHeadlines

class TopHeadlinesAdapter(private val dataList: List<Article>) : RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {

    class ViewHolder(binding: RecyclerTopheadlinesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerTopheadlinesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


}