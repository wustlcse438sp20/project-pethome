package com.example.pethome.Network.Cat

import com.example.pethome.Data.CatBreedData
import com.example.pethome.Data.CatBreedPic
import com.example.pethome.Data.DogBreedData
import com.example.pethome.Data.DogBreedPic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatDataInterface {
    @Headers("x-api-key: 670b29ca-27df-467f-b407-fe4c74400700")
    @GET("v1/breeds")
    suspend fun getBreeds() : Response<List<CatBreedData>>

    @Headers("x-api-key: 670b29ca-27df-467f-b407-fe4c74400700")
    @GET("v1/images/search")
    suspend fun getBreedPic(@Query("breed_id") breed_id: String): Response<List<CatBreedPic>>
}