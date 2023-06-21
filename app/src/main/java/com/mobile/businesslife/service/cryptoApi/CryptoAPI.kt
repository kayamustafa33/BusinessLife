package com.mobile.businesslife.service.cryptoApi

import com.mobile.businesslife.model.crypto.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("/api/v3/ticker/24hr")
    suspend fun getCryptoData() : Response<CryptoModel>
}