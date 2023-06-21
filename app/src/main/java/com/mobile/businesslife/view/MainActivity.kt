package com.mobile.businesslife.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.ActivityMainBinding
import com.mobile.businesslife.fragments.CryptoFragment
import com.mobile.businesslife.fragments.EverythingFragment
import com.mobile.businesslife.fragments.TopHeadlinesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        replaceFragment(TopHeadlinesFragment())

        //API Key -> 4dddd76a8883407eabfa37a58c38465d

        //NewsAPI (Everything) -> https://newsapi.org/v2/everything?domains=wsj.com&apiKey=4dddd76a8883407eabfa37a58c38465d
        //NewsAPI (Top Headlines) -> https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=4dddd76a8883407eabfa37a58c38465d

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

    private fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
    }
}