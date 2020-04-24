package com.example.pethome.Data

data class CatBreedPic(
    val breeds: List<CatBreedData>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)