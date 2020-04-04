package com.example.pethome.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pethome.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_breedinfo.*

class DogBreedInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breedinfo)

        val intent = intent
        val bundle = intent.extras

        dogBreedName.text = bundle!!.getString("breadName")
        Picasso.get().load(bundle.getString("breadPic")).into(dogBreedPic)
        dog_avg_life.text = "Average Life Span: "+bundle.getString("avg_life")
        dog_origin.text = "Origin: "+bundle.getString("origin")
        dog_avg_weight.text = "Average Weight: "+ bundle.getString("avg_weight")
        dog_avg_height.text = "Average Height: "+ bundle.getString("avg_height")
        dog_temperament.text = "Temperament: "+bundle.getString("temperament")

        dogBreedBackBtn.setOnClickListener {
            finish()
        }
    }
}