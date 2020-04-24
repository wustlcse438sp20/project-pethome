package com.example.pethome.Network.DogPet

import androidx.lifecycle.MutableLiveData
import com.example.pethome.Data.Animal
import com.example.pethome.Data.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DogPetRepository {
    val service = DogPetApiClient.makeRetrofitService()

    public val params: HashMap<String, String> = HashMap<String, String>()



    fun getToken(resBody: MutableLiveData<Token>) {
        params.put("grant_type", "client_credentials")
        params.put("client_id", "TGR1wJzIyLrFIsI16rIiNLWVc4c3nZ5VOIGlEiyRSZf4r8a7B8")
        params.put("client_secret", "49c8eQZkKDaYr2ZW5BSjrS4ffHFM8CtubnuM172o")
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getToken(params)

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

    fun getTypeDog(resBody: MutableLiveData<Animal>, param: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTypeDog(param)

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