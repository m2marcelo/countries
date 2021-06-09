package eu.marcelomorais.countries.restApi.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentCountry(
    val countryName: String
)