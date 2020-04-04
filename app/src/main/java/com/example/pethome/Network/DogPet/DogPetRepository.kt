package com.example.pethome.Network.DogPet

import androidx.lifecycle.MutableLiveData
import com.example.pethome.Data.Animal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DogPetRepository {
    val service = DogPetApiClient.makeRetrofitService()

    fun getAnimalsByType(resBody: MutableLiveData<Animal>, type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAnimalsByType(type)

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