package com.example.pethome.Network.Dog

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DogApiClient {
    const val BASE_URL = "https://api.thedogapi.com/"

    fun makeRetrofitService() : DogDataInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(DogDataInterface::class.java)
    }
}