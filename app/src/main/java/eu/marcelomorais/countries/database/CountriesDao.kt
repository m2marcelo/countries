package eu.marcelomorais.countries.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountriesDao {
    /**
     * @return all countries.
     */
    @Query("SELECT * FROM countries_database")
    suspend fun getAllCountriesFromDB(): List<CountriesDBModel>

    /**
     * @param countryId the id of the country
     * @return the list of CountriesDBModel objects with the countryId
     */
    @Query("SELECT * FROM countries_database WHERE id = :countryId")
    suspend fun getCountriesById(countryId: Int): List<CountriesDBModel?>

    /**
     * Delete database content
     */
    @Query("DELETE FROM countries_database")
    suspend fun deleteCountries()

}