package com.example.pethome.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pethome.Activity.CatBreedInfoActivity
import com.example.pethome.Data.CatBreedData
import com.example.pethome.Data.CatBreedPic
import com.example.pethome.R
import com.squareup.picasso.Picasso

class CatBreedViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_breed, parent, false)) {
    private val catBreedName: TextView
    public val catBreedPicSrc: ImageView

    init {
        catBreedName = itemView.findViewById(R.id.breed_name)
        catBreedPicSrc = itemView.findViewById(R.id.breed_pic)
    }

    fun bind(catBreedPic: CatBreedPic) {
        catBreedName.text = catBreedPic.breeds[0].name
        Picasso.get().load(catBreedPic.url).into(catBreedPicSrc)
    }
}

class CatBreedAdapter(private val list: ArrayList<CatBreedPic>, context: Context):
    RecyclerView.Adapter<CatBreedViewHolder>() {

    val myContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CatBreedViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        val catBreedPic = list[position]

        holder.bind(catBreedPic)
        holder.catBreedPicSrc.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("breadName", catBreedPic.breeds[0].name)
            bundle.putString("breadPic", catBreedPic.url)
            bundle.putString("avg_life", catBreedPic.breeds[0].life_span)
            bundle.putString("origin", catBreedPic.breeds[0].origin)
            bundle.putString("avg_weight", catBreedPic.breeds[0].weight.imperial)
            bundle.putInt("cat_dog_friendly", catBreedPic.breeds[0].dog_friendly)
            bundle.putString("temperament",catBreedPic.breeds[0].temperament)

            val intent = Intent(myContext, CatBreedInfoActivity::class.java)
            intent.putExtras(bundle)

            myContext.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}