package com.mobile.businesslife.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.ActivityMainBinding
import com.mobile.businesslife.fragments.main.CryptoFragment
import com.mobile.businesslife.fragments.main.EverythingFragment
import com.mobile.businesslife.fragments.main.TopHeadlinesFragment
import com.mobile.businesslife.service.checkNetwork.CheckNetwork

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var networkState : CheckNetwork? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        networkState = CheckNetwork()

        if(networkState!!.checkForInternet(this)){
            replaceFragment(TopHeadlinesFragment())
            changeBottomItem()
        }else{
            Toast.makeText(this,"There is no connection!",Toast.LENGTH_SHORT).show()
        }

        //API Key -> 4dddd76a8883407eabfa37a58c38465d

        //NewsAPI (Everything) -> https://newsapi.org/v2/everything?domains=wsj.com&apiKey=4dddd76a8883407eabfa37a58c38465d
        //NewsAPI (Top Headlines) -> https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=4dddd76a8883407eabfa37a58c38465d


    }

    private fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
    }

    private fun changeBottomItem(){
        binding.mainBottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.topHeadLines -> {
                    replaceFragment(TopHeadlinesFragment())
                    true
                }

                R.id.everything -> {
                    replaceFragment(EverythingFragment())
                    true
                }

                R.id.crypto_menu -> {
                    replaceFragment(CryptoFragment())
                    true
                }

                else -> {
                    true
                }

            }
        }
    }
}