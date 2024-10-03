package com.zahitbogus.cryptocrazyapp.service

import com.zahitbogus.cryptocrazyapp.model.Crypto
import com.zahitbogus.cryptocrazyapp.model.CryptoList
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {
    @GET("zbogus/CryptoMarket/blob/main/cryptolist.json")
    suspend fun getCryptoList(
        @Query("key") key: String
    ):CryptoList

    @GET("zbogus/CryptoMarket/blob/main/crypto.json")
    suspend fun getCrypto(
        @Query("key") key: String,
        @Query("ids") id: String,
        @Query("attributes") attributes: String
    ): Crypto

}