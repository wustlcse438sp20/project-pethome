package com.example.pethome.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pethome.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_catbreedinfo.*

class CatBreedInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catbreedinfo)

        val intent = intent
        val bundle = intent.extras

        catBreedName.text = bundle!!.getString("breadName")
        Picasso.get().load(bundle.getString("breadPic")).into(catBreedPic)
        cat_avg_life.text = "Average Life Span: "+bundle.getString("avg_life")
        cat_origin.text = "Origin: "+bundle.getString("origin")
        cat_avg_weight.text = "Average Weight: "+ bundle.getString("avg_weight")
        cat_dog_friendly.text = "Dog friendly: "+ bundle.getInt("cat_dog_friendly")
        cat_temperament.text = "Temperament: "+bundle.getString("temperament")

        catBreedBackBtn.setOnClickListener {
            finish()
        }
    }
}

