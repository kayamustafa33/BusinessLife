package com.mobile.businesslife.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.businesslife.adapter.newsAdapter.TopHeadlinesAdapter
import com.mobile.businesslife.databinding.FragmentTopHeadlinesBinding
import com.mobile.businesslife.model.news.Article
import com.mobile.businesslife.service.newsApi.TopHeadlinesAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopHeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentTopHeadlinesBinding
    private val BASE_URL = "https://newsapi.org"
    private var job : Job? = null
    private var newsTopHeadLines : ArrayList<Article>? = null
    private var topHeadlinesAdapter : TopHeadlinesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentTopHeadlinesBinding.inflate(inflater,container,false)
        //NewsAPI (Top Headlines) -> https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=4dddd76a8883407eabfa37a58c38465d

        newsTopHeadLines = ArrayList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topHeadLinesRV.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL, false)
        loadTopHeadlinesData()
    }

    private fun loadTopHeadlinesData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TopHeadlinesAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getHeadlines()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    for(i in 0 until response.body()!!.articles.size){
                        newsTopHeadLines?.add(response.body()!!.articles[i])
                    }

                    topHeadlinesAdapter = TopHeadlinesAdapter(binding.root.context,newsTopHeadLines!!)
                    binding.topHeadLinesRV.adapter = topHeadlinesAdapter
                }
            }
        }
    }

}