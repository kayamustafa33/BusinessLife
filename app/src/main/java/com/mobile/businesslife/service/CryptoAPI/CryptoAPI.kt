package com.mobile.businesslife.service.CryptoAPI

import com.mobile.businesslife.model.Crypto.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("/api/v3/ticker/24hr")
    suspend fun getCryptoData() : Response<CryptoModel>
}