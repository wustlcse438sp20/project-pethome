package com.example.pethome.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pethome.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_petinfo.*
import kotlinx.android.synthetic.main.activity_shelterinfo.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pethome.Data.GeoLatLng
import com.example.pethome.Data.Result
import java.net.URLEncoder


class ShelterInfoActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var homeLat : Double? = null
    private var homeLong : Double? = null
    private lateinit var viewModel: GeoViewModel
    private var geoList: ArrayList<Result> = ArrayList()
    private lateinit var city: String
    private  var street: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelterinfo)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    override fun onStart() {
        super.onStart()

        val intent = intent
        val bundle = intent.extras

        city = bundle?.getString("city").toString()
        street = bundle?.getString("address1")

        if (bundle?.getString("address1") == null){
            shelterAddr.text = "Address: " + bundle?.getString("city")
        } else {
            shelterAddr.text = "Address: " + bundle?.getString("address1") + " " + bundle?.getString("city")
        }

        shelterPhone.text = "Phone: " + bundle?.getString("phone")
        shelterPhone.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        shelterEmail.text = "Email: " + bundle?.getString("email")

        shelterPhone.setOnClickListener{
            val phone = bundle?.getString("phone")
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        shelterBackBtn.setOnClickListener {
            finish()
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
//        enableMyLocation()

        viewModel = ViewModelProvider(this).get(GeoViewModel::class.java)
        viewModel.geoList.observe(this, Observer { latlng ->
            geoList.clear()
            geoList.addAll(latlng.results)
            if (!geoList.isEmpty()){

                val pos = LatLng(geoList[0].geometry.location.lat, geoList[0].geometry.location.lng)
                Log.e("loc", pos.toString())
                map.addMarker(MarkerOptions().position(pos).title(city))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15f))
            }
        })

        if (street==null){
            viewModel.getLatlng(Uri.encode(city))
        } else {
            Log.e("street", street)
            val address = street + " " + city
            viewModel.getLatlng(Uri.encode(address))
        }


//        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    homeLat =  location?.latitude as Double?
                    homeLong = location?.longitude as Double?

                    //set the location and zoom variables
                    val homeLatLng = LatLng(homeLat as Double, homeLong as Double)
                    val zoomLevel = 15f

                    //move the camera and set a marker
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                    map.addMarker(MarkerOptions().position(homeLatLng))
                }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    //check the permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }


    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }
}