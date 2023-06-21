package com.mobile.businesslife.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.ActivityShowCryptoBinding

class ShowCryptoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShowCryptoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}