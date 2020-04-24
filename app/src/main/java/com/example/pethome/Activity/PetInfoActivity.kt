package com.example.pethome.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pethome.R
import com.example.pethome.ui.profile.user
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_petinfo.*
import android.graphics.Paint

class PetInfoActivity : AppCompatActivity() {
    private lateinit var userDB: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_petinfo)

        userDB = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        userDB.firestoreSettings = settings


    }

    override fun onStart() {
        super.onStart()

        val intent = intent
        val bundle = intent.extras


        if (bundle != null) {
            petName.text = bundle.getString("name")
        }

        if (bundle?.getString("pic")!="null"){
            Picasso.get().load(bundle?.getString("pic")).into(petPic)
        }
        petAge.text = "Age: " + bundle?.getString("age")
        petBreed.text = "Breed: " + bundle?.getString("breed")
        petWeight.text = "Size: " + bundle?.getString("weight")
        petGender.text = "Gender: " + bundle?.getString("gender")
        if (bundle?.getString("address1") == null || bundle?.getString("address1") == "null"){
            petShelter.text = "Address: " + bundle?.getString("city")
        } else {
            petShelter.text = "Address: " + bundle?.getString("address1") + " " + bundle?.getString("city")
            petShelter.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }

        favoriteBtn.setOnClickListener {
            if (user==null){
                Toast.makeText(this, "Please Login/Signup", Toast.LENGTH_SHORT).show()
            } else {
                val favoriteMap: MutableMap<String, Any?> = HashMap()

                favoriteMap["id"] = bundle?.getLong("id")
                favoriteMap["name"] = bundle?.getString("name")
                favoriteMap["pic"] = bundle?.getString("pic")
                favoriteMap["age"] = bundle?.getString("age")
                favoriteMap["breed"] = bundle?.getString("breed")
                favoriteMap["weight"] = bundle?.getString("weight")
                favoriteMap["gender"] = bundle?.getString("gender")
                favoriteMap["address1"] = bundle?.getString("address1")
                favoriteMap["email"] = bundle?.getString("email")
                favoriteMap["city"] = bundle?.getString("city")
                favoriteMap["postcode"] = bundle?.getString("postcode")
                favoriteMap["phone"] = bundle?.getString("phone")
                favoriteMap["tags"] = bundle?.getString("tags")
                favoriteMap["uid"] = user!!.uid

                userDB.collection("favorites").document(bundle?.getString("name").toString())
                    .set(favoriteMap as Map<String, Any>)
                    .addOnCompleteListener { document ->
                        Toast.makeText(this, "Add to Favorite", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e->
                        Toast.makeText(this,"Fail to Add Favorite", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        petShelter.setOnClickListener{
            val intent = Intent(this, ShelterInfoActivity::class.java)
            intent.putExtras(bundle!!)
            startActivity(intent)
        }


        petBackBtn.setOnClickListener {
            finish()
        }
    }
}