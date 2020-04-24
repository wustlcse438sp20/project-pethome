package com.example.pethome.Network.Geo

import androidx.lifecycle.MutableLiveData
import com.example.pethome.Data.GeoLatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class GeoRepository {
    val service = GeoApiClient.makeRetrofitService()

    fun getLatlng(resBody: MutableLiveData<GeoLatLng>, address: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val key = "AIzaSyCMWZTT_gROaxfnll1jR0x9Z7viAVYzDJI"

            val response = service.getLatlng(address,key)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        resBody.value = response.body()
                    } else{
                        //response error
                        println("HTTP error")
                    }
                }catch (e: HttpException) {
                    //http exception
                    println("HTTP Exception")
                } catch (e: Throwable) {
                    //error
                    println("Error")
                }
            }
        }
    }
}