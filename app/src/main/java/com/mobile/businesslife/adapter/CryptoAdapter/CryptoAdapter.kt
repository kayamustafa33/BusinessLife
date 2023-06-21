package com.mobile.businesslife.adapter.CryptoAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.businesslife.databinding.RecyclerCryptoBinding
import com.mobile.businesslife.model.Crypto.CryptoModelItem

class CryptoAdapter(private val context : Context, private val dataList: List<CryptoModelItem>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    class ViewHolder(val binding : RecyclerCryptoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerCryptoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}