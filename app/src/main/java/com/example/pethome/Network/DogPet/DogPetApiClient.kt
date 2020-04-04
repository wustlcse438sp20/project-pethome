package com.example.pethome.Network.DogPet

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DogPetApiClient {
    const val BASE_URL=" https://api.petfinder.com/"

    fun makeRetrofitService(): DogPetInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(DogPetInterface::class.java)
    }
}