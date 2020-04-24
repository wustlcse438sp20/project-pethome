package com.example.pethome.Data

data class GeoLatLng(
    val results: List<Result>,
    val status: String
)

data class Result (
    val address_components: List<Addrcomp>,
    val formatted_address: String,
    val geometry: Geometry,
    val place_id: String,
    val types: List<String>
)

data class Addrcomp(
    val long_name: String,
    val short_name: String,
    val types: List<String>
)

data class Geometry(
    val bounds: Bounds,
    val location: Location,
    val location_type: String,
    val viewport: Bounds
)

data class Bounds(
    val northeast: Location,
    val southwest: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)