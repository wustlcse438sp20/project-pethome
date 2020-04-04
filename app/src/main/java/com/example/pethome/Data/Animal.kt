package com.example.pethome.Data

data class Animal (
    val animals: List<Animals>
)

data class Animals (
    val id: Int,
    val organization_id: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: Breed,
    val age: String,
    val gender: String,
    val size: String,
    val tags: List<String>,
    val name: String,
    val description: String,
    val photos: List<Photos>,
    val status: String,
    val published_at: String,
    val contact: Contact
)

data class Breed (
    val primary: String,
    val secondary: String? = null,
    val mixed: Boolean,
    val unknown: Boolean
)

data class Photos(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)

data class Contact (
    val email: String,
    val phone: String,
    val address: Address
)

data class Address(
    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
)