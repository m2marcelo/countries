package eu.marcelomorais.countries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountriesDBModel::class], version = 1, exportSchema = false)
abstract class CountriesDatabase : RoomDatabase() {
    abstract val electionDao: CountriesDao

    companion object {

        @Volatile
        private var INSTANCE: CountriesDatabase? = null

        fun getInstance(context: Context): CountriesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountriesDatabase::class.java,
                        "countries_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}