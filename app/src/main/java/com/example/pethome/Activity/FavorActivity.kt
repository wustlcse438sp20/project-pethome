package com.example.pethome.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pethome.Adapter.FavorAdapter
import com.example.pethome.Data.Animals
import com.example.pethome.Data.Favorite
import com.example.pethome.R
import com.example.pethome.ui.profile.user
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_favor.*

class FavorActivity : AppCompatActivity() {
    private lateinit var userDB: FirebaseFirestore
    private var favorlist: ArrayList<Favorite> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favor)

        userDB = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        userDB.firestoreSettings = settings
    }

    override fun onStart() {
        super.onStart()

        val recyclerView = favorite_recycler_view
        val adapter = FavorAdapter(favorlist, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        userDB.collection("favorites")
            .whereEqualTo("uid", user!!.uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    favorlist.clear()
                    for (document in task.result!!) {
                        favorlist.add(
                            Favorite(
                                document.get("id") as Long,
                                document.get("name").toString(),
                                document.get("pic").toString(),
                                document.get("age").toString(),
                                document.get("breed").toString(),
                                document.get("weight").toString(),
                                document.get("gender").toString(),
                                document.get("address1").toString(),
                                document.get("email").toString(),
                                document.get("city").toString(),
                                document.get("postcode").toString(),
                                document.get("phone").toString(),
                                document.get("tags").toString(),
                                document.get("uid").toString()
                            )
                        )

                    }
                    adapter.notifyDataSetChanged()
                } else{
                    Log.e("Error", "fail to get data")
                }
            }


        favorBackBtn.setOnClickListener {
            finish()
        }
    }
}


