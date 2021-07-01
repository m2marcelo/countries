package eu.marcelomorais.countries.countryview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.MyCountryFragmentBinding
import eu.marcelomorais.countries.restApi.models.CurrentCountry
import eu.marcelomorais.countries.utils.LocationUtils
import eu.marcelomorais.countries.utils.PermissionHandler

class MyCountryFragment : Fragment(), PermissionHandler.PermissionListener, LocationUtils.LocationListener {

    private val viewModel by viewModels<MyCountryViewModel> {
        MyCountryViewModelFactory(
            (requireContext().applicationContext as CountriesApp).countriesRepository
        )
    }

    private val permissionUtil = PermissionHandler(this)
    private val locationUtil = LocationUtils(this)
    private lateinit var viewDataBinding: MyCountryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = MyCountryFragmentBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.myCountryInfo.observe(viewLifecycleOwner){
        }

        viewModel.myCountryDestinations.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.clearMyCountryNavigationLiveData()
                findNavController().navigate(it)
            }
        }

        Log.d("MyCountryFragment", "onCreateView")

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionUtil.checkPermissions(this)
    }

    override fun onDestroyView() {
        permissionUtil.unregister()
        super.onDestroyView()
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionGranted() {
        locationUtil.getLocation(requireActivity(), requireContext())
    }

    override fun onPermissionDenied() {
        Toast.makeText(
            requireContext(),
            getString(R.string.error_permission_denied),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCurrentLocationReady(location: CurrentCountry) {
        Log.d("onCurrentLocationReady in MyCountryFragment", "location = $location")
        viewModel.getCurrentCountry(location)
    }


}