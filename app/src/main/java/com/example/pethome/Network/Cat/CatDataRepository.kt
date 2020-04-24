package com.example.pethome.Network.Cat

import androidx.lifecycle.MutableLiveData
import com.example.pethome.Data.CatBreedData
import com.example.pethome.Data.CatBreedPic
import com.example.pethome.Network.Cat.CatApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CatDataRepository {
    val service = CatApiClient.makeRetrofitService()

    fun getBreeds(resBody: MutableLiveData<List<CatBreedData>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getBreeds()

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful){
                        resBody.value = response.body()
                    } else {
                        println("HTTP error")
                    }
                } catch (e: HttpException) {
                    //http exception
                    println("HTTP Exception")
                } catch (e: Throwable) {
                    //error
                    println("Error")
                }
            }
        }
    }

    fun getBreedPic(resBody: MutableLiveData<List<CatBreedPic>>, breed_id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getBreedPic(breed_id)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful){
                        resBody.value = response.body()
                    } else {
                        println("HTTP error")
                    }
                } catch (e: HttpException) {
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