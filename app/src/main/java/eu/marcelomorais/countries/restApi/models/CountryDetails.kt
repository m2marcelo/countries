package eu.marcelomorais.countries.restApi.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val code: String,
    val name: String
)

@JsonClass(generateAdapter = true)
data class Language(
    val iso639_1: String,
    val name: String,
    val nativeName: String
)

@JsonClass(generateAdapter = true)
data class CountryDetails(
    val id: Int?,
    val name: String?,
    val capital: String?,
    val callingCodes: List<String>,
    val region: String,
    val subregion: String,
    val population: String,
    val flag: String,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val borders: List<String>? = null,
    val timezones: List<String>
)