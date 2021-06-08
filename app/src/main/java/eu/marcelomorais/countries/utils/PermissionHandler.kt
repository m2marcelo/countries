package eu.marcelomorais.countries.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionHandler(private var handler: PermissionListener?) {

    companion object {
        private const val PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
    }

    interface PermissionListener {
        fun onPermissionGranted()
        fun onPermissionDenied()
    }

    fun checkPermissions(fragment: Fragment) {
        if (hasGrantedPermission(fragment.requireContext())) {
            handler?.onPermissionGranted()
        } else {
            askPermission(fragment)
        }
    }

    private fun hasGrantedPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
                context,
                PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission(fragment: Fragment) {
        val permissions = ActivityResultContracts.RequestPermission()
        fragment.registerForActivityResult(permissions) {
            if (it) {
                handler?.onPermissionGranted()
            } else {
                handler?.onPermissionDenied()
            }
        }.launch(PERMISSION, ActivityOptionsCompat.makeBasic())
    }

    fun unregister() {
        handler = null
    }
}