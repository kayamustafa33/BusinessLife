package com.mobile.businesslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mobile.businesslife.R
import com.mobile.businesslife.adapter.cryptoAdapter.CryptoAdapter
import com.mobile.businesslife.databinding.FragmentCryptoBinding
import com.mobile.businesslife.model.crypto.CryptoModelItem
import com.mobile.businesslife.service.cryptoApi.CryptoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoFragment : Fragment() {

    private lateinit var binding: FragmentCryptoBinding
    private val BASE_URL = "https://data.binance.com"
    private var cryptoModelItem : ArrayList<CryptoModelItem>? = null
    private var cryptoAdapter : CryptoAdapter? = null
    private var job : Job? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoBinding.inflate(inflater,container,false)

        //Binance API -> https://data.binance.com/api/v3/ticker/24hr
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cryptoRV.layoutManager = GridLayoutManager(binding.root.context,2)
        loadCryptoData()
    }

    private fun loadCryptoData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getCryptoData()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModelItem = ArrayList(it)

                        val filteredList = ArrayList(it.filter { model ->
                            model.symbol.endsWith("usdt", ignoreCase = true)
                        })

                        filteredList.let {
                            cryptoAdapter = CryptoAdapter(binding.root.context,filteredList)
                            binding.cryptoRV.adapter = cryptoAdapter
                        }
                    }
                }
            }
        }
    }


}