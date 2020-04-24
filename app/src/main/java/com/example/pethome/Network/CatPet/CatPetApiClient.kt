package com.example.pethome.Network.CatPet

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CatPetApiClient {

    const val BASE_URL=" https://api.petfinder.com/"

    fun makeRetrofitService(): CatPetInterface {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(CatPetInterface::class.java)
    }

}