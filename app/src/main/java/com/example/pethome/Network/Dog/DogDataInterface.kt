package com.example.pethome.Network.Dog

import com.example.pethome.Data.DogBreedData
import com.example.pethome.Data.DogBreedPic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface DogDataInterface {
    @Headers("x-api-key: 8ee7386b-e7db-4105-8996-ced23906c011")
    @GET("v1/breeds")
    suspend fun getBreeds() : Response<List<DogBreedData>>

    @Headers("x-api-key: 8ee7386b-e7db-4105-8996-ced23906c011")
    @GET("v1/images/search")
    suspend fun getBreedPic(@Query("breed_id") breed_id: Int): Response<List<DogBreedPic>>
}