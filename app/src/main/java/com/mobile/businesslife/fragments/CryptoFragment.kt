package com.mobile.businesslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.FragmentCryptoBinding

class CryptoFragment : Fragment() {

    private lateinit var binding: FragmentCryptoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoBinding.inflate(inflater,container,false)

        //Binance API -> https://data.binance.com/api/v3/ticker/24hr
        return binding.root
    }

    private fun loadCryptoData(){

    }


}