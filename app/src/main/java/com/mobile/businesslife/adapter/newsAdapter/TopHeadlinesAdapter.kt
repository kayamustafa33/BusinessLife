package com.mobile.businesslife.adapter.newsAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.businesslife.databinding.RecyclerTopheadlinesBinding
import com.mobile.businesslife.model.news.Article
import com.mobile.businesslife.view.ShowNewsActivity

class TopHeadlinesAdapter(private val context : Context, private val dataList: List<Article>) : RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerTopheadlinesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerTopheadlinesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textOfTitle.text = dataList[position].title

        Glide.with(context)
            .load(dataList[position].urlToImage)
            .into(holder.binding.imageOfTopHeadlines)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ShowNewsActivity::class.java)
            intent.putExtra("image",dataList[position].urlToImage)
            intent.putExtra("title",dataList[position].title)
            intent.putExtra("description",dataList[position].description)
            intent.putExtra("content",dataList[position].content)
            intent.putExtra("authorName",dataList[position].author)
            intent.putExtra("date",dataList[position].publishedAt)
            context.startActivity(intent)
        }
    }


}