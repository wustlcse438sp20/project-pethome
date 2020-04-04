package com.example.pethome.Data

data class DogBreedData(
    val bred_for: String,
    val breed_group: String,
    val country_code: String,
    val height: Height,
    val id: Int,
    val life_span: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val weight: Weight
)

data class Height (
    val imperial: String,
    val metric: String
)

data class Weight (
    val imperial: String,
    val metric: String
)