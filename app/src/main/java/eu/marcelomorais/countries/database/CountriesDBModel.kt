package eu.marcelomorais.countries.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.marcelomorais.countries.restApi.models.Country

@Entity(tableName = "countries_database")
data class CountriesDBModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "countryName") val countryName: String,
    @ColumnInfo(name = "countryCapital") val countryCapital: String,
    @ColumnInfo(name = "countryRegion") val countryRegion: String,
    @ColumnInfo(name = "countryFlag") val countryFlag: String,
)

fun List<CountriesDBModel>.convertToCountryModel() : List<Country> {
    return map{
        Country(
            it.id,
            it.countryName,
            it.countryCapital,
            it.countryRegion,
            it.countryFlag
        )
    }
}
