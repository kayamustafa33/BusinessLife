package com.mobile.businesslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.businesslife.adapter.newsAdapter.EverythingAdapter
import com.mobile.businesslife.databinding.FragmentEverythingBinding
import com.mobile.businesslife.model.news.Article
import com.mobile.businesslife.service.newsApi.EverythingAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EverythingFragment : Fragment() {

    private lateinit var binding: FragmentEverythingBinding
    private val BASE_URL = "https://newsapi.org"
    private var job : Job? = null
    private var newsEverything : ArrayList<Article>? = null
    private var everythingAdapter : EverythingAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentEverythingBinding.inflate(inflater,container,false)
        newsEverything = ArrayList()
        //NewsAPI (Everything) -> https://newsapi.org/v2/everything?domains=wsj.com&apiKey=4dddd76a8883407eabfa37a58c38465d

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.everythingRV.layoutManager = LinearLayoutManager(binding.root.context)
        loadEverythingData()
    }


    private fun loadEverythingData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EverythingAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getEverything()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    for (i in 0 until response.body()!!.articles.size) {
                        newsEverything?.add(response.body()!!.articles[i])
                    }
                    everythingAdapter = EverythingAdapter(binding.root.context,newsEverything!!)
                    binding.everythingRV.adapter = everythingAdapter

                }
            }
        }
    }
}