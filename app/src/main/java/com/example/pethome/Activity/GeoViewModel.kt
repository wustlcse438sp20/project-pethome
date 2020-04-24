package com.example.pethome.Activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pethome.Data.GeoLatLng
import com.example.pethome.Network.Geo.GeoRepository

class GeoViewModel (application: Application): AndroidViewModel(application) {
    public var geoList: MutableLiveData<GeoLatLng> = MutableLiveData()
    public var geoRepository: GeoRepository = GeoRepository()

    fun getLatlng(address: String) {
        geoRepository.getLatlng(geoList, address)
    }
}