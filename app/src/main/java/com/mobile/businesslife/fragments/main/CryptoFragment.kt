package com.mobile.businesslife.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
import java.util.Locale

class CryptoFragment : Fragment() {

    private lateinit var binding: FragmentCryptoBinding
    private val BASE_URL = "https://data.binance.com"
    private var cryptoModelItem : ArrayList<CryptoModelItem>? = null
    private var cryptoAdapter : CryptoAdapter? = null
    private var job : Job? = null
    private var filteredList : ArrayList<CryptoModelItem>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoBinding.inflate(inflater,container,false)

        //Binance API -> https://data.binance.com/api/v3/ticker/24hr
        //Crypto Symbols API -> https://cryptoicons.org/api/white/btc/200

        textListener()

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

                         filteredList = ArrayList(it.filter { model ->
                            model.symbol.endsWith("usdt", ignoreCase = true)
                        })

                        filteredList.let {
                            cryptoAdapter = CryptoAdapter(binding.root.context,filteredList!!)
                            binding.cryptoRV.adapter = cryptoAdapter
                        }
                    }
                }
            }
        }
    }

    private fun textListener(){
        binding.searchCryptoText.addTextChangedListener {
            filter(binding.searchCryptoText.text.toString())
        }
    }

    private fun filter(newText: String) {
        val newList: ArrayList<CryptoModelItem> = ArrayList()
        for (item in filteredList!!) {
            if (item.symbol.lowercase(Locale.getDefault()).contains(newText)) {
                newList.add(item)
            }
        }
        cryptoAdapter?.filteredList(newList)
    }
}