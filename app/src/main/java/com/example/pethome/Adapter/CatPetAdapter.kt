package com.example.pethome.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pethome.Activity.PetInfoActivity
import com.example.pethome.Data.Animals
import com.example.pethome.R
import com.squareup.picasso.Picasso

class CatPetViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pet, parent, false)) {
    public val petPic: ImageView
    private val petName: TextView
    private val petBreed: TextView

    init {
        petPic = itemView.findViewById(R.id.pet_pic)
        petName = itemView.findViewById(R.id.pet_name)
        petBreed = itemView.findViewById(R.id.petbreed_name)
    }

    fun bind(animals: Animals) {
        petName.text = animals.name
        petBreed.text = animals.breeds.primary
        if (animals.photos.isNotEmpty()){
            Picasso.get().load(animals.photos[0].full).into(petPic)
        }
    }
}

class CatPetAdapter (private val list: ArrayList<Animals>, context: Context)
    :RecyclerView.Adapter<CatPetViewHolder>() {

    val myContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatPetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CatPetViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CatPetViewHolder, position: Int) {
        val animals: Animals = list[position]
        holder.bind(animals)
        holder.petPic.setOnClickListener{

            val bundle = Bundle()
            bundle.putInt("id", animals.id)
            bundle.putString("name", animals.name)
            if (animals.photos.isNotEmpty()){
                bundle.putString("pic", animals.photos[0].full)
            } else{
                bundle.putString("pic", "null")
            }
            bundle.putString("age", animals.age)
            bundle.putString("breed", animals.breeds.primary)
            bundle.putString("weight", animals.size)
            bundle.putString("gender", animals.gender)
            bundle.putString("address1", animals.contact.address.address1)
            bundle.putString("city", animals.contact.address.city)
            bundle.putString("postcode", animals.contact.address.postcode)
            bundle.putString("email", animals.contact.email)
            bundle.putString("phone", animals.contact.phone)
            bundle.putString("tags", animals.tags.toString())

            val intent = Intent(myContext, PetInfoActivity::class.java)
            intent.putExtras(bundle)

            myContext.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}