package com.example.pethome.ui.pet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pethome.Data.Animal
import com.example.pethome.Data.Token
import com.example.pethome.Network.DogPet.DogPetRepository
import com.example.pethome.Network.CatPet.CatPetRepository

class PetViewModel : ViewModel() {
    public var token: MutableLiveData<Token> = MutableLiveData()
    public var dogPetRepository: DogPetRepository = DogPetRepository()
    public var catPetRepository: CatPetRepository = CatPetRepository()
    public var animal: MutableLiveData<Animal> = MutableLiveData()

    fun getToken() {
        dogPetRepository.getToken(token)
        catPetRepository.getToken(token)
    }

    fun getTypeDog(param: String) {
        dogPetRepository.getTypeDog(animal, param)
    }

    fun getTypeCat(param: String) {
        catPetRepository.getTypeCat(animal, param)
    }

}