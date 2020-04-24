package com.example.pethome.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pethome.Data.CatBreedData
import com.example.pethome.Data.CatBreedPic
import com.example.pethome.Network.Cat.CatDataRepository

class CatHomeViewModel (application: Application): AndroidViewModel(application) {
    public var catBreedDataList: MutableLiveData<List<CatBreedData>> = MutableLiveData()
    public var catBreedPicList: MutableLiveData<List<CatBreedPic>> = MutableLiveData()
    public var catDataRepository: CatDataRepository = CatDataRepository()

    fun getBreeds() {
        catDataRepository.getBreeds(catBreedDataList)
    }

    fun getBreedPic(breed_id: String) {
        catDataRepository.getBreedPic(catBreedPicList, breed_id)
    }
}