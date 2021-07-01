package eu.marcelomorais.countries.restApi.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentCountry(
    val countryName: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable