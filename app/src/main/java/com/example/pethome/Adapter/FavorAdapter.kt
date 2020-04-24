package com.example.pethome.Adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pethome.Activity.PetInfoActivity
import com.example.pethome.Data.Animals
import com.example.pethome.Data.Favorite
import com.example.pethome.R
import com.example.pethome.ui.profile.user
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.squareup.picasso.Picasso

class FavorViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_favor, parent, false)) {

    public var petName: TextView
    public var petPic: ImageView
    public var deleteBtn: Button

    init {
        petName = itemView.findViewById(R.id.favor_name)
        petPic = itemView.findViewById(R.id.favor_pic)
        deleteBtn = itemView.findViewById(R.id.delete_favor_btn)
    }

    fun bind(favorite: Favorite) {
        petName.text = favorite.name
        if (favorite.pic != "null"){
            Picasso.get().load(favorite.pic).into(petPic)
        }
    }
}

class FavorAdapter (private val list: ArrayList<Favorite>, context: Context)
    :RecyclerView.Adapter<FavorViewHolder>() {
    val myContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavorViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FavorViewHolder, position: Int) {
        val favor: Favorite = list[position]

        var userDB = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        userDB.firestoreSettings = settings

        holder.bind(favor)
        holder.petPic.setOnClickListener{

            val bundle = Bundle()
            bundle.putLong("id", favor.id)
            bundle.putString("name", favor.name)
            bundle.putString("pic", favor.pic)
            bundle.putString("age", favor.age)
            bundle.putString("breed", favor.breed)
            bundle.putString("weight", favor.weight)
            bundle.putString("gender", favor.gender)
            bundle.putString("address1", favor.address1)
            bundle.putString("email", favor.email)
            bundle.putString("city", favor.city)
            bundle.putString("postcode", favor.postcode)
            bundle.putString("phone", favor.phone)
            bundle.putString("tags", favor.tags)
            bundle.putString("uid", favor.uid)


            val intent = Intent(myContext, PetInfoActivity::class.java)
            intent.putExtras(bundle)

            myContext.startActivity(intent)

        }
        holder.deleteBtn.setOnClickListener {
            userDB.collection("favorites").document(favor.name.toString())
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

            list.remove(favor)
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}
