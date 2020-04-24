package com.example.pethome.Network.CatPet

import com.example.pethome.Data.Animal
import com.example.pethome.Data.Token
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface CatPetInterface {
    @FormUrlEncoded
    @POST("v2/oauth2/token")
    suspend fun getToken(@FieldMap params: HashMap<String, String>): Response<Token>

    @GET("v2/animals?type=cat")
    suspend fun getTypeCat(@Header("Authorization") param: String): Response<Animal>
}