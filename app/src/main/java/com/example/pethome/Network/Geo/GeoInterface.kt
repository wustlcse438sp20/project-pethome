package com.example.pethome.Network.Geo

import com.example.pethome.Data.GeoLatLng
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoInterface {
    @GET("json")
    suspend fun getLatlng(@Query("address") address: String, @Query("key") key: String): Response<GeoLatLng>
}