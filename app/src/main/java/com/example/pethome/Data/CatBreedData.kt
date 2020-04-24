package com.example.pethome.Data

data class CatBreedData(
    val country_code: String,
    val id: String,
    val life_span: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val weight: CatWeight,
    val dog_friendly: Int
)

data class CatWeight (
    val imperial: String,
    val metric: String
)

