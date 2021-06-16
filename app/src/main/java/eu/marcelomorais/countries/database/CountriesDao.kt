package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountriesDao {
    /**
     * @return all countries in a live data.
     */
    @Query("SELECT * FROM countries_database_table")
    fun getAllCountriesFromDB(): LiveData<List<CountriesDBModel>>

    /**
     * @return all countries.
     */
    @Query("SELECT * FROM countries_database_table")
    fun getAllCountriesFromDBSync(): List<CountriesDBModel>

    /**
     * @param countryId the id of the country
     * @return the list of CountriesDBModel objects with the countryId
     */
    @Query("SELECT * FROM countries_database_table WHERE id = :countryId")
    suspend fun getCountriesById(countryId: Int): List<CountriesDBModel?>

    /**
     * Delete database content
     */
    @Query("DELETE FROM countries_database_table")
    suspend fun deleteCountries()

}