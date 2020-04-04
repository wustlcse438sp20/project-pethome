package com.example.pethome.Data

data class DogBreedPic(
    val breeds: List<DogBreedData>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)