package com.example.pethome.Network.Dog

import androidx.lifecycle.MutableLiveData
import com.example.pethome.Data.DogBreedData
import com.example.pethome.Data.DogBreedPic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DogDataRepository {
    val service = DogApiClient.makeRetrofitService()

    fun getBreeds(resBody: MutableLiveData<List<DogBreedData>>) {
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

    fun getBreedPic(resBody: MutableLiveData<List<DogBreedPic>>, breed_id: Int) {
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