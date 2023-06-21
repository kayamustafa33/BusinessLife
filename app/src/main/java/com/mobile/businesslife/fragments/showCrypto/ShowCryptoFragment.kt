package com.mobile.businesslife.fragments.showCrypto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.FragmentShowCryptoBinding

class ShowCryptoFragment : Fragment() {

    private lateinit var binding: FragmentShowCryptoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShowCryptoBinding.inflate(inflater,container,false)

        return binding.root
    }

}