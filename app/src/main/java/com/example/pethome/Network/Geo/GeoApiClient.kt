package com.example.pethome.Network.Geo

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GeoApiClient {
    const val BASE_URL = "https://maps.googleapis.com/maps/api/geocode/"

    fun makeRetrofitService() : GeoInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(GeoInterface::class.java)
    }
}