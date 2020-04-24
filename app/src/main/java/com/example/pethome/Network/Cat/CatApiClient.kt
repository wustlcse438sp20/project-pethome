package com.example.pethome.Network.Cat

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CatApiClient {
    const val BASE_URL = "https://api.thecatapi.com/"

    fun makeRetrofitService() : CatDataInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(CatDataInterface::class.java)
    }
}