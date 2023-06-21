package com.mobile.businesslife.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import com.bumptech.glide.Glide
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.ActivityShowNewsBinding

class ShowNewsActivity : AppCompatActivity() {

    private var imageUrl : String? = null
    private var title : String? = null
    private var description : String? = null
    private var content : String? = null
    private var authorName : String? = null
    private var date : String? = null

    private lateinit var binding : ActivityShowNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        getData()
        goBack()
    }

    private fun getData(){
        val data = intent
        if (data != null) {
            imageUrl = data.getStringExtra("image")
            title = data.getStringExtra("title")
            description = data.getStringExtra("description")
            content = data.getStringExtra("content")
            authorName = data.getStringExtra("authorName")
            date = data.getStringExtra("date")
        }

        if(imageUrl!!.isNotEmpty() || title!!.isNotEmpty() || description!!.isNotEmpty()
            || content!!.isNotEmpty()
            || authorName!!.isNotEmpty()
            || date!!.isNotEmpty()){

            Glide.with(binding.root.context)
                .load(imageUrl)
                .error(R.drawable.empty_symbol)
                .into(binding.showNewsImage)

            binding.showTitleText.text = title
            binding.showDescriptionText.text = description
            binding.showContentText.text = content
            binding.showAuthorNameText.text = authorName
            binding.showDateText.text = date
        }

    }

    private fun goBack(){
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}