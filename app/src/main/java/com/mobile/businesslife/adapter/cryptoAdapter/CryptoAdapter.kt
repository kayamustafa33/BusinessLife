package com.mobile.businesslife.adapter.cryptoAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.businesslife.R
import com.mobile.businesslife.databinding.RecyclerCryptoBinding
import com.mobile.businesslife.model.crypto.CryptoModelItem
import java.util.Locale


class CryptoAdapter(private val context : Context, private var dataList: List<CryptoModelItem>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    class ViewHolder(val binding : RecyclerCryptoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerCryptoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.coinNameText.text = dataList[position].symbol
        holder.binding.coinPriceText.text = "$${dataList[position].lastPrice}"

        if(dataList[position].priceChange.toDouble() < 0){
            holder.binding.coinPriceChangeImage.setImageResource(R.drawable.down)
        }else{
            holder.binding.coinPriceChangeImage.setImageResource(R.drawable.up)
        }

        holder.binding.coinPriceChangeText.text = dataList[position].priceChange

        val symbol = dataList[position].symbol

        var symbolName = ""
        if (symbol.endsWith("usdt", ignoreCase = true)) {
            symbolName = symbol.substring(0, symbol.length - 4).lowercase(Locale.getDefault())
        }

        val imageUrl = "https://cryptoicons.org/api/white/$symbolName/200"

        Glide.with(context)
            .load(imageUrl)
            .error(R.drawable.empty_symbol)
            .into(holder.binding.coinSymbolImage)
    }

    fun filteredList(filteredList: ArrayList<CryptoModelItem>) {
        dataList = filteredList
        notifyDataSetChanged()
    }
}