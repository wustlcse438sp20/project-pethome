package com.example.pethome.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pethome.Activity.DogBreedInfoActivity
import com.example.pethome.Data.DogBreedData
import com.example.pethome.Data.DogBreedPic
import com.example.pethome.R
import com.squareup.picasso.Picasso

class DogBreedViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_breed, parent, false)) {
    private val dogBreedName: TextView
    public val dogBreedPicSrc: ImageView

    init {
        dogBreedName = itemView.findViewById(R.id.breed_name)
        dogBreedPicSrc = itemView.findViewById(R.id.breed_pic)
    }

    fun bind(dogBreedPic: DogBreedPic) {
        dogBreedName.text = dogBreedPic.breeds[0].name
        Picasso.get().load(dogBreedPic.url).into(dogBreedPicSrc)
    }
}

class DogBreedAdapter(private val list: ArrayList<DogBreedPic>, context: Context):
        RecyclerView.Adapter<DogBreedViewHolder>() {

    val myContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DogBreedViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        val dogBreedPic = list[position]

        holder.bind(dogBreedPic)
        holder.dogBreedPicSrc.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("breadName", dogBreedPic.breeds[0].name)
            bundle.putString("breadPic", dogBreedPic.url)
            bundle.putString("avg_life", dogBreedPic.breeds[0].life_span)
            bundle.putString("origin", dogBreedPic.breeds[0].origin)
            bundle.putString("avg_weight", dogBreedPic.breeds[0].weight.imperial)
            bundle.putString("avg_height", dogBreedPic.breeds[0].height.imperial)
            bundle.putString("temperament",dogBreedPic.breeds[0].temperament)

            val intent = Intent(myContext,DogBreedInfoActivity::class.java)
            intent.putExtras(bundle)

            myContext.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}