package eu.marcelomorais.countries.utils

import android.app.Activity
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import eu.marcelomorais.countries.restApi.models.CurrentCountry
import java.util.*

class LocationUtils(private var handler: LocationUtils.LocationListener?) {

    lateinit var locationProvider: FusedLocationProviderClient

    interface LocationListener {
        fun onCurrentLocationReady(location: CurrentCountry)
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun getLocation(activity: Activity, context: Context) {
        locationProvider = LocationServices.getFusedLocationProviderClient(activity)

        locationProvider.getCurrentLocation(100, object : CancellationToken() {
            override fun isCancellationRequested(): Boolean {
                return false
            }

            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                return this
            }

        }).addOnSuccessListener {
            val currentLocation = geoCodeLocation(it, context)
            handler?.onCurrentLocationReady(currentLocation)
            Log.d("LocationUtils", "getLocation addOnSuccessListener-> currentLocation = $currentLocation")

        }.addOnCompleteListener {
            Log.d("LocationUtils", "getLocation addOnCompleteListener")

        }.addOnFailureListener {
            Log.d("LocationUtils", "getLocation addOnFailureListener")

        }
    }

    private fun geoCodeLocation(location: Location, context: Context): CurrentCountry {
        val geocoder = Geocoder(context, Locale.getDefault())
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
            .map { address ->
                CurrentCountry(address.countryName)
            }
            .first()
    }
}