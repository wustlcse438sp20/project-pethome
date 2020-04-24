package com.example.pethome.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pethome.Data.DogBreedData
import com.example.pethome.Data.DogBreedPic
import com.example.pethome.Network.Dog.DogDataRepository

class HomeViewModel (application: Application): AndroidViewModel(application) {
    public var dogBreedDataList: MutableLiveData<List<DogBreedData>> = MutableLiveData()
    public var dogBreedPicList: MutableLiveData<List<DogBreedPic>> = MutableLiveData()
    public var dogDataRepository: DogDataRepository = DogDataRepository()

    fun getBreeds() {
        dogDataRepository.getBreeds(dogBreedDataList)
    }

    fun getBreedPic(breed_id: Int) {
        dogDataRepository.getBreedPic(dogBreedPicList, breed_id)
    }

    fun getBreedsBySearch(breed: String) {
        dogDataRepository.getBreedsBySearch(dogBreedDataList, breed)
    }
}