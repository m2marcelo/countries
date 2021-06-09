package eu.marcelomorais.countries.restApi.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    val capital: String,
    val region: String,
    val flag: String
)
