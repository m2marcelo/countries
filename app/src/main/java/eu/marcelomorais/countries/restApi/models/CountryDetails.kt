package eu.marcelomorais.countries.restApi.models

import com.squareup.moshi.JsonClass

data class Currency(
    val code: String,
    val name: String
)

data class Language(
    val iso639_1: String,
    val name: String,
    val nativeName: String
)

@JsonClass(generateAdapter = true)
data class CountryDetails(
    val name: String,
    val capital: String,
    val callingCode: String,
    val region: String,
    val subRegion: String,
    val population: String,
    val flag: String,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val borders: List<String>? = null,
    val timezone: List<String>
)